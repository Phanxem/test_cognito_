package com.example.test_Cognito.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.test_Cognito.Controller.SplashPageController;
import com.example.test_Cognito.R;

public class SplashPageActivity extends AppCompatActivity {

    SplashPageController splashPageController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading);

        splashPageController = new SplashPageController();
/*
        String packageName = this.getApplicationContext().getPackageName();
        SharedPreferences sharedPreferences = this.getSharedPreferences(packageName, Context.MODE_PRIVATE);
        SharedPreferences.Editor shPrEdit = sharedPreferences.edit();

        shPrEdit.remove(RegistrazioneController.SHARED_PREFERENCES_IS_IN_ACCOUNT_ACTIVATION_SCREEN);
        shPrEdit.remove(RegistrazioneController.SHARED_PREFERENCES_USERNAME);
        shPrEdit.remove(RegistrazioneController.SHARED_PREFERENCES_PASSWORD);
        shPrEdit.commit();
*/
        splashPageController.checkSignedIn(this);





        //Verifica gli shared preference, e vedi se si deve andare alla pagina di verifica codice registrazione
        //se si apri in successione gli activity di autenticazione, registrazione e attivazione account, come di seguito:
        /*
        Intent i = new Intent(this, A.class);
        i.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        startActivity(i);

        Intent j = new Intent(this, B.class);
        j.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        startActivity(j);

        Intent k = new Intent(this, C.class);
        startActivity(k);
         */

    }
}