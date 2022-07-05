package com.example.test_Cognito.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.test_Cognito.Controller.AutenticazioneController;
import com.example.test_Cognito.Controller.RecuperoPasswordController;
import com.example.test_Cognito.Controller.RegistrazioneController;
import com.example.test_Cognito.R;

public class AutenticazioneActivity extends AppCompatActivity {

    AutenticazioneController autenticazioneController;
    RegistrazioneController registrazioneController;
    RecuperoPasswordController recuperoPasswordController;

    EditText textField_UsernameEmail;
    EditText textField_Password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_autenticazione);

        autenticazioneController = new AutenticazioneController();
        registrazioneController = new RegistrazioneController();
        recuperoPasswordController = new RecuperoPasswordController();

        textField_UsernameEmail = findViewById(R.id.SignIn_textField_usernameEmail);
        textField_Password = findViewById(R.id.SignIn_passwordField_password);
    }


    public void pressButtonSignIn(View view) {

        String usernameEmail = String.valueOf(textField_UsernameEmail.getText());
        String password = String.valueOf(textField_Password.getText());

        autenticazioneController.signIn(this,usernameEmail,password);

    }

    public void pressButtonSignInGuest(View view) {
        autenticazioneController.signInGuest(this);
    }

    public void pressTextSignUp(View view) {
        registrazioneController.openSignUpScreen(this);
    }

    public void pressTextPasswordRecovery(View view) {
        recuperoPasswordController.openStartPasswordRecoveryScreen(this);
    }
}