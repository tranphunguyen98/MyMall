package com.tranphunguyen.mymall;

import android.content.Intent;
import android.os.SystemClock;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;

import com.google.firebase.auth.FirebaseAuth;

public class SplashScreen extends AppCompatActivity {

    FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

    }

    @Override
    protected void onStart() {
        super.onStart();


    }

    @Override
    protected void onResume() {
        super.onResume();

        checkCurrentUser();
    }

    private void jumpToRegisterActivity() {

        Intent registerIntent = new Intent(this, RegisterActivity.class);

        startActivity(registerIntent);

        finish();
    }

    private void jumpToMainActivity() {

        Intent mainIntent = new Intent(this, HomeActivity.class);

        startActivity(mainIntent);

        finish();
    }

    private void checkCurrentUser() {

        jumpToMainActivity();
//        if(firebaseAuth.getCurrentUser() == null) {
//
//            jumpToRegisterActivity();
//
//        } else {
//
//            jumpToMainActivity();
//
//        }

    }

}
