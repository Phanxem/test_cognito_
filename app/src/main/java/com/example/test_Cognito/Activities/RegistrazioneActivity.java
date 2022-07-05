package com.example.test_Cognito.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.test_Cognito.Controller.RegistrazioneController;
import com.example.test_Cognito.R;

public class RegistrazioneActivity extends AppCompatActivity {

    RegistrazioneController registrazioneController;

    EditText textField_Username;
    EditText textField_Email;
    EditText textField_Password;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrazione);

        registrazioneController = new RegistrazioneController();

        textField_Username = findViewById(R.id.SignUp_textField_username);
        textField_Email = findViewById(R.id.SignUp_emailField_email);
        textField_Password = findViewById(R.id.SignUp_passwordField_password);
    }



    public void pressButtonSignUp(View view) {
        String username = String.valueOf(textField_Username.getText());
        String email = String.valueOf(textField_Email.getText());
        String password = String.valueOf(textField_Password.getText());

        registrazioneController.signUp(this, username, email, password);
    }

    public void pressIconBack(View view) {
        onBackPressed();
    }

/*
    public void showPasswordPolicy(){

        final String PASSWORD_POLICY = "\nUna password valida deve:\n - Essere lunga almeno 8 caratteri\n - Deve contentere almeno un numero\n - Deve contenere almeno un carattere speciale\n - Deve contenere almeno una lettera maiuscola\n - Deve contentere almeno una lettera minuscola";

        //textView_passwordPolicy.setLayoutParams(new ViewGroup.LayoutParams(0, ViewGroup.LayoutParams.WRAP_CONTENT));
        textView_passwordPolicy.setVisibility(View.VISIBLE);
        textView_passwordPolicy.setText(PASSWORD_POLICY);
    }

 */
}