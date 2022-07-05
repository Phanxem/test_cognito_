package com.example.test_Cognito.Controller;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;

import com.amplifyframework.core.Amplify;
import com.example.test_Cognito.Activities.AttivaAccountActivity;
import com.example.test_Cognito.Activities.AutenticazioneActivity;
import com.example.test_Cognito.Activities.HomeActivity;
import com.example.test_Cognito.Dialogs.MessageDialog;

public class SplashPageController {

    MessageDialog messageDialog;
    RegistrazioneController registrazioneController;

    public void SplashPageController() {
        registrazioneController = new RegistrazioneController();
    }

    public void checkSignedIn(Context context){
        Amplify.Auth.fetchAuthSession(
                result -> {
                    Log.i("TEST", result.toString());
                    //AUTENTICATO
                    if(result.isSignedIn()){
                        Intent intent = new Intent(context, HomeActivity.class);
                        context.startActivity(intent);
                    }
                    else{
                        String packageName = context.getApplicationContext().getPackageName();
                        SharedPreferences sharedPreferences = context.getSharedPreferences(packageName,Context.MODE_PRIVATE);

                        Boolean isInAccountActivationScreen = sharedPreferences.getBoolean(registrazioneController.SHARED_PREFERENCES_IS_IN_ACCOUNT_ACTIVATION_SCREEN, false);

                        //Fase di Attivazione account
                        if(isInAccountActivationScreen){
                            String username = sharedPreferences.getString(registrazioneController.SHARED_PREFERENCES_USERNAME, null);
                            String password = sharedPreferences.getString(registrazioneController.SHARED_PREFERENCES_PASSWORD, null);
                            Intent intent = new Intent(context, AttivaAccountActivity.class);
                            intent.putExtra(registrazioneController.EXTRA_USERNAME,username);
                            intent.putExtra(registrazioneController.EXTRA_PASSWORD,password);
                            context.startActivity(intent);
                        }
                        //NON AUTENTICATO
                        else{
                            Intent intent = new Intent(context, AutenticazioneActivity.class);
                            context.startActivity(intent);
                        }


                    }


                },
                error -> {
                    Log.e("TEST", error.toString());
                }
        );
    }

}
