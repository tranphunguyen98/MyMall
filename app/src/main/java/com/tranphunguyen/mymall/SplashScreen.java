package com.tranphunguyen.mymall;

import android.content.Intent;
import android.os.SystemClock;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

//        SystemClock.sleep(2000);

        jumpToRegisterActivity();

    }

    private void jumpToRegisterActivity() {

        Intent registerIntent = new Intent(this, RegisterActivity.class);

        startActivity(registerIntent);

        finish();
    }

}
