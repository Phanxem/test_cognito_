package com.example.test_Cognito.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.test_Cognito.Controller.RegistrazioneController;
import com.example.test_Cognito.R;

public class AttivaAccountActivity extends AppCompatActivity {

    RegistrazioneController registrazioneController;

    Intent intent;
    String username;
    String password;

    EditText textField_Code;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attiva_account);

        intent = getIntent();
        username = intent.getStringExtra(RegistrazioneController.EXTRA_USERNAME);
        password = intent.getStringExtra(RegistrazioneController.EXTRA_PASSWORD);


        registrazioneController = new RegistrazioneController();

        textField_Code = findViewById(R.id.AccountActivation_numberField_codice);

        //MEMORIZZA IN SHADER PREFERENCE CHE SIAMO IN QUESTA PAGINA e l'username dell'utente
        registrazioneController.initAccountActivation(this, username, password);


    }

    public void pressTextResendCode(View view) {
        registrazioneController.resendCode(this ,username);
    }

    public void pressButtonConfirm(View view) {
        String code = String.valueOf(textField_Code.getText());

        registrazioneController.accountActivation(this, username, password, code);
    }

    @Override
    public void onBackPressed() {
        Intent a = new Intent(Intent.ACTION_MAIN);
        a.addCategory(Intent.CATEGORY_HOME);
        a.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(a);
    }


    public void pressButtonCancel(View view) {

        //elimina l'utente dall'userpool e torna alla pagina di registrazione (con la pagina di accesso in coda)
    }
}