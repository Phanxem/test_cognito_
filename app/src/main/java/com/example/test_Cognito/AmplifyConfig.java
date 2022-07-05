package com.example.test_Cognito;

import android.app.Application;
import android.util.Log;

import com.amplifyframework.AmplifyException;
import com.amplifyframework.auth.cognito.AWSCognitoAuthPlugin;
import com.amplifyframework.core.Amplify;

public class AmplifyConfig extends Application {
    public void onCreate() {
        super.onCreate();

        try {
            Amplify.addPlugin(new AWSCognitoAuthPlugin());
            Amplify.configure(getApplicationContext());
            Log.i("AmplifyConfig", "Initialized Amplify");
        } catch (AmplifyException error) {
            Log.e("AmplifyConfig", "Could not initialize Amplify", error);
        }
    }
}