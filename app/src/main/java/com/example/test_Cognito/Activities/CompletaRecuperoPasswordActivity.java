package com.example.test_Cognito.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.test_Cognito.Controller.RecuperoPasswordController;
import com.example.test_Cognito.R;

public class CompletaRecuperoPasswordActivity extends AppCompatActivity {

    RecuperoPasswordController recuperoPasswordController;

    EditText textField_Code;
    EditText textField_Password1;
    EditText textField_Password2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_completa_recupero_password);

        recuperoPasswordController = new RecuperoPasswordController();

        textField_Code = findViewById(R.id.CompletaRecuperoPassword_editNumber_codice);
        textField_Password1 = findViewById(R.id.CompletaRecuperoPassword_editPassword_password1);
        textField_Password2 = findViewById(R.id.CompletaRecuperoPassword_editPassword_password2);
    }

    public void pressButtonConfirm(View view) {
        String code = String.valueOf(textField_Code.getText());
        String password1 = String.valueOf(textField_Password1.getText());
        String password2 = String.valueOf(textField_Password2.getText());

        recuperoPasswordController.completePasswordRecovery(this,code,password1,password2);


    }

    public void pressIconBack(View view) {
        onBackPressed();
    }
}