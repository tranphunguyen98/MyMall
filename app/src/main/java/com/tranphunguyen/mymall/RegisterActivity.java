package com.tranphunguyen.mymall;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.tranphunguyen.mymall.Utils.Contanst;

import java.util.Objects;

public class RegisterActivity extends AppCompatActivity implements
        SignInFragment.OnFragmentSignInInteractionListener,
        SignUpFragment.OnFragmentSignUpInteractionListener
{

    FrameLayout frameLayout;

    FragmentManager fragmentManager = getSupportFragmentManager();

    FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        frameLayout = findViewById(R.id.frame_layout);

        addFragment(SignInFragment.newInstance());
    }

    private void addFragment(Fragment fragment) {

        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();

        fragmentTransaction.add(frameLayout.getId(),fragment,Contanst.TAG_SIGN_IN_FRAG);

        fragmentTransaction.commit();

    }

    @Override
    public void onClickDontHaveAccount() {

        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        fragmentTransaction.setCustomAnimations(R.anim.slide_from_right,R.anim.slide_out_from_left);

        fragmentTransaction.replace(frameLayout.getId(),SignUpFragment.newInstance(),Contanst.TAG_SIGN_UP_FRAG);

        fragmentTransaction.commit();

    }

    @Override
    public void onSignIn(String email, String password) {

        final SignInFragment signInFragment = (SignInFragment) fragmentManager.findFragmentByTag(Contanst.TAG_SIGN_IN_FRAG);

        assert signInFragment != null;
        signInFragment.onLoadingStart();

        firebaseAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if(task.isSuccessful()) {

                    SystemClock.sleep(1000);

                    jumpToMainActivitiy();

                } else {

                    Toast.makeText(RegisterActivity.this, Objects.requireNonNull(task.getException()).getMessage(),Toast.LENGTH_LONG).show();

                }

                signInFragment.onLoadingStop();

            }
        });

    }

    @Override
    public void onClickAlreadyHaveAccount() {

        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        fragmentTransaction.setCustomAnimations(R.anim.slide_from_left,R.anim.slide_out_from_right);

        fragmentTransaction.replace(frameLayout.getId(),SignInFragment.newInstance(),Contanst.TAG_SIGN_IN_FRAG);

        fragmentTransaction.commit();

    }

    @Override
    public void onSignUp(String email, String password) {

        final SignUpFragment signInFragment = (SignUpFragment) fragmentManager.findFragmentByTag(Contanst.TAG_SIGN_UP_FRAG);

        assert signInFragment != null;
        signInFragment.onLoadingStart();

        firebaseAuth.createUserWithEmailAndPassword(email,password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if(task.isSuccessful()) {

                           jumpToMainActivitiy();

                        } else {

                            Toast.makeText(RegisterActivity.this, Objects.requireNonNull(task.getException()).getMessage(),Toast.LENGTH_LONG).show();

                        }

                        signInFragment.onLoadingStop();

                    }
                });

    }

    private void jumpToMainActivitiy() {

        Intent mainIntent = new Intent(RegisterActivity.this,MainActivity.class);

        startActivity(mainIntent);

        RegisterActivity.this.finish();

    }
}
