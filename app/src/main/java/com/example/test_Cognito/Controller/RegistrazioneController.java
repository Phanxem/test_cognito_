package com.example.test_Cognito.Controller;

import static android.content.ContentValues.TAG;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.Toast;

import com.amplifyframework.auth.AuthUserAttributeKey;
import com.amplifyframework.auth.options.AuthSignUpOptions;
import com.amplifyframework.core.Amplify;
import com.example.test_Cognito.Activities.AttivaAccountActivity;
import com.example.test_Cognito.Activities.RegistrazioneActivity;

import com.example.test_Cognito.Dialogs.MessageDialog;


public class RegistrazioneController {
    public static final String SHARED_PREFERENCES_IS_IN_ACCOUNT_ACTIVATION_SCREEN = "isInAccountActivation";
    public static final String SHARED_PREFERENCES_USERNAME = "USERNAME";
    public static final String SHARED_PREFERENCES_PASSWORD = "PASSWORD";

    public static final String EXTRA_USERNAME = "USERNAME";
    public static final String EXTRA_PASSWORD = "PASSWORD";

    MessageDialog messageDialog;
    AutenticazioneController autenticazioneController = new AutenticazioneController();

    public RegistrazioneController(){

    }

    public void openSignUpScreen(Context context){
        Intent intent = new Intent(context, RegistrazioneActivity.class);
        context.startActivity(intent);
    }

    public void signUp(Context context, String username, String email, String password){

        messageDialog = new MessageDialog(context);

        if(!ErrorHandlerUtils.isThereAnEmptyField(messageDialog,username,email,password)) return;


        AuthSignUpOptions options = AuthSignUpOptions
                .builder()
                .userAttribute(AuthUserAttributeKey.email(), email)
                .build();

        Amplify.Auth.signUp(
                username,
                password,
                options,
                result -> {
                    Log.i("TEST", "Result: " + result.toString());
                    Intent intent = new Intent(context, AttivaAccountActivity.class);
                    intent.putExtra(EXTRA_USERNAME,username);
                    intent.putExtra(EXTRA_PASSWORD,password);
                    context.startActivity(intent);
                },
                error -> {
                    ErrorHandlerUtils.handleMessageError(messageDialog, error);
                }
        );
    }

    public void initAccountActivation(Context context, String username, String password){
        String packageName = context.getApplicationContext().getPackageName();
        SharedPreferences sharedPreferences = context.getSharedPreferences(packageName,Context.MODE_PRIVATE);
        SharedPreferences.Editor shPrEdit = sharedPreferences.edit();

        shPrEdit.putBoolean(SHARED_PREFERENCES_IS_IN_ACCOUNT_ACTIVATION_SCREEN,true);
        shPrEdit.putString(SHARED_PREFERENCES_USERNAME,username);
        shPrEdit.putString(SHARED_PREFERENCES_PASSWORD,password);
        shPrEdit.commit();
    }

    public void accountActivation(Context context, String username, String password, String code){

        messageDialog = new MessageDialog(context);

        ErrorHandlerUtils.isThereAnEmptyField(messageDialog,code);

        Amplify.Auth.confirmSignUp(
                username,
                code,
                confirmSignUpResult -> {
                    Log.i("TEST", "Confirm signUp succeeded");

                    String packageName = context.getApplicationContext().getPackageName();
                    SharedPreferences sharedPreferences = context.getSharedPreferences(packageName,Context.MODE_PRIVATE);
                    SharedPreferences.Editor shPrEdit = sharedPreferences.edit();

                    shPrEdit.remove(SHARED_PREFERENCES_IS_IN_ACCOUNT_ACTIVATION_SCREEN);
                    shPrEdit.remove(SHARED_PREFERENCES_USERNAME);
                    shPrEdit.remove(SHARED_PREFERENCES_PASSWORD);
                    shPrEdit.commit();

                    autenticazioneController.effectiveSignIn(context,messageDialog,username,password);
                },
                error -> {
                    ErrorHandlerUtils.handleMessageError(messageDialog, error);
                }
        );
    }

    public void resendCode(Context context, String username){
            Amplify.Auth.resendSignUpCode(
                    username,
                    result -> {
                        Log.i(TAG, "resend Code Success");
                        Toast.makeText(context, "Codice Inviato", Toast.LENGTH_SHORT).show();
                    },
                    error -> {
                        Log.e(TAG, "resend Code Failure");
                        Toast.makeText(context, "Impossibile inviare il codice", Toast.LENGTH_SHORT).show();
                    }
            );
    }

}
