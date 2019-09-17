package com.tranphunguyen.mymall;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.FrameLayout;

public class RegisterActivity extends AppCompatActivity implements
        SignInFragment.OnFragmentSignInInteractionListener,
        SignUpFragment.OnFragmentSignUpInteractionListener
{

    FrameLayout frameLayout;

    FragmentManager fragmentManager = getSupportFragmentManager();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        frameLayout = findViewById(R.id.frame_layout);

        addFragment(new SignInFragment());
    }

    private void addFragment(Fragment fragment) {

        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();

        fragmentTransaction.add(frameLayout.getId(),fragment);

        fragmentTransaction.commit();

    }

    @Override
    public void onClickDontHaveAccount() {

        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        fragmentTransaction.setCustomAnimations(R.anim.slide_from_right,R.anim.slide_out_from_left);

        fragmentTransaction.replace(frameLayout.getId(),new SignUpFragment());

        fragmentTransaction.commit();

    }

    @Override
    public void onClickAlreadyHaveAccount() {

        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        fragmentTransaction.setCustomAnimations(R.anim.slide_from_left,R.anim.slide_out_from_right);

        fragmentTransaction.replace(frameLayout.getId(),new SignInFragment());

        fragmentTransaction.commit();

    }
}
