package com.example.test_Cognito.Controller;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.amplifyframework.core.Amplify;
import com.example.test_Cognito.Activities.CompletaRecuperoPasswordActivity;
import com.example.test_Cognito.Activities.IniziaRecuperoPasswordActivity;
import com.example.test_Cognito.Dialogs.MessageDialog;

public class RecuperoPasswordController {

    MessageDialog messageDialog;

    public RecuperoPasswordController() {
    }

    public void openStartPasswordRecoveryScreen(Context context){
        Intent intent = new Intent(context, IniziaRecuperoPasswordActivity.class);
        context.startActivity(intent);
    }

    public void startPasswordRecovery(Context context, String usernameEmail){

        messageDialog = new MessageDialog(context);

        if(!ErrorHandlerUtils.isThereAnEmptyField(messageDialog,usernameEmail)) return;

        Amplify.Auth.resetPassword(
                usernameEmail,
                result -> {
                    Log.i("TEST", result.toString());
                    Intent intent = new Intent(context, CompletaRecuperoPasswordActivity.class);
                    context.startActivity(intent);
                    ((Activity)context).finish();

                },
                error -> {
                    Log.e("TEST", error.toString());
                    ErrorHandlerUtils.handleMessageError(messageDialog, error);
                }
        );
    }

    public void completePasswordRecovery(Context context, String code, String password1, String password2){

        messageDialog = new MessageDialog(context);

        if(!ErrorHandlerUtils.isThereAnEmptyField(messageDialog,code,password1,password2)) return;

        if(!ErrorHandlerUtils.doPasswordMatch(messageDialog,password1,password2)) return;

        Amplify.Auth.confirmResetPassword(
                password1,
                code,
                () -> {
                    Log.i("TEST", "New password confirmed");
                },
                error ->{
                    Log.e("TEST", error.toString());
                    ErrorHandlerUtils.handleMessageError(messageDialog, error);
                }
        );




    }
}
