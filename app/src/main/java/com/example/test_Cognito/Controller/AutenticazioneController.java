package com.example.test_Cognito.Controller;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.amplifyframework.auth.cognito.AWSCognitoAuthSession;
import com.amplifyframework.core.Amplify;
import com.example.test_Cognito.Activities.HomeActivity;
import com.example.test_Cognito.Activities.HomeGuestActivity;
import com.example.test_Cognito.Dialogs.MessageDialog;

public class AutenticazioneController {


    MessageDialog messageDialog;

    public AutenticazioneController(){
    }

    public void signIn(Context context, String usernameEmail, String password) {
        messageDialog = new MessageDialog(context);

        if(!ErrorHandlerUtils.isThereAnEmptyField(messageDialog,usernameEmail,password)) return;

        effectiveSignIn(context,messageDialog,usernameEmail,password);

    }

    public void effectiveSignIn(Context context, MessageDialog messageDialog, String usernameEmail, String password){
        Amplify.Auth.signIn(
                usernameEmail,
                password,
                result -> {
                    Log.i("TEST", "Confirm signIn succeeded");
                    Intent intent = new Intent(context, HomeActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    context.startActivity(intent);
                },
                error -> {
                    ErrorHandlerUtils.handleMessageError(messageDialog, error);
                }
        );
    }

    public void signInGuest(Context context){
        MessageDialog messageDialog = new MessageDialog(context);

        Amplify.Auth.fetchAuthSession(
                result -> {
                    AWSCognitoAuthSession cognitoAuthSession = (AWSCognitoAuthSession) result;
                    switch(cognitoAuthSession.getIdentityId().getType()) {
                        case SUCCESS:
                            Log.i("TEST", "IdentityId: " + cognitoAuthSession.getIdentityId().getValue());
                            Intent intent = new Intent(context, HomeGuestActivity.class);
                            context.startActivity(intent);

                            break;
                        case FAILURE:
                            Log.i("TEST", "IdentityId not present because: " + cognitoAuthSession.getIdentityId().getError().toString());
                            ErrorHandlerUtils.handleMessageError(messageDialog, cognitoAuthSession.getIdentityId().getError());
                    }
                },
                error -> {
                    ErrorHandlerUtils.handleMessageError(messageDialog, error);
                    Log.e("TEST", error.toString());
                }
        );
    }


}
