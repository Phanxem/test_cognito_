package com.example.test_Cognito.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.test_Cognito.Controller.RecuperoPasswordController;
import com.example.test_Cognito.R;

public class IniziaRecuperoPasswordActivity extends AppCompatActivity {

    RecuperoPasswordController recuperoPasswordController;

    EditText textField_Username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inizia_recupero_password);

        recuperoPasswordController = new RecuperoPasswordController();

        textField_Username = findViewById(R.id.StartPasswordRecovery_editText_username);
    }


    public void pressButtonNext(View view) {
        String username = String.valueOf(textField_Username.getText());

        recuperoPasswordController.startPasswordRecovery(this,username);
    }

    public void pressIconBack(View view) {
        onBackPressed();
    }
}