package com.tranphunguyen.mymall;

import android.content.Context;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;

import java.util.Objects;

import io.opencensus.internal.StringUtils;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link OnFragmentSignUpInteractionListener} interface
 * to handle interaction events.
 * Use the {@link SignUpFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SignUpFragment extends Fragment {

    private TextView alreadyHaveAnAccount;
    private EditText edtEmail, edtFullName, edtPassword, edtComfirmPassword;
    private Button btnSignUp;
    private ProgressBar prgSignUp;
    private FirebaseAuth firebaseAuth;

    private Drawable iconError;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentSignUpInteractionListener mListener;

    public SignUpFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SignUpFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SignUpFragment newInstance(String param1, String param2) {
        SignUpFragment fragment = new SignUpFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

        firebaseAuth = FirebaseAuth.getInstance();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_sign_up, container, false);

        alreadyHaveAnAccount = view.findViewById(R.id.tv_already_have_an_account);
        edtEmail = view.findViewById(R.id.edt_email);
        edtFullName = view.findViewById(R.id.edt_full_name);
        edtPassword = view.findViewById(R.id.edt_password);
        edtComfirmPassword = view.findViewById(R.id.edt_confirm_password);
        btnSignUp = view.findViewById(R.id.btn_sign_up);

        iconError = getResources().getDrawable(R.drawable.ic_round_error_16dp);
        iconError.setBounds(new Rect(0, 0, iconError.getIntrinsicWidth(), iconError.getIntrinsicHeight()));

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        alreadyHaveAnAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mListener.onClickAlreadyHaveAccount();

            }
        });

        setErrorIcon();

        edtEmail.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (TextUtils.isEmpty(s) || s.toString().length() < 6) {

                    edtEmail.setError("Invalid Email", iconError);

                    disableButtonSignIn();

                } else {

                    if (isValidInput()) {

                        enableButtonSignIn();

                    }

                }
            }
        });

        edtFullName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (TextUtils.isEmpty(s) || s.toString().length() < 6) {

                    edtFullName.setError("Invalid Name", iconError);

                    disableButtonSignIn();

                } else {

                    if (isValidInput()) {

                        enableButtonSignIn();

                    }

                }
            }
        });

        edtPassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (TextUtils.isEmpty(s) || s.toString().length() < 6) {

                    edtPassword.setError("Invalid password (< 6 character)", iconError);

                    disableButtonSignIn();

                } else if (!TextUtils.isEmpty(edtComfirmPassword.getText()) && !s.toString().equals(edtComfirmPassword.getText().toString())) {

                    edtPassword.setError("Please enter same password", iconError);

                    disableButtonSignIn();

                } else {
                    edtPassword.setError(null);
                    if (!TextUtils.isEmpty(edtComfirmPassword.getText())) {

                        edtComfirmPassword.setError(null);

                    }
                    if (isValidInput()) {

                        enableButtonSignIn();

                    }

                }
            }
        });

        edtComfirmPassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (TextUtils.isEmpty(s) || s.toString().length() < 6) {

                    edtComfirmPassword.setError("Invalid password (< 6 character)", iconError);

                    disableButtonSignIn();

                } else if (!s.toString().equals(edtPassword.getText().toString())) {

                    edtComfirmPassword.setError("Please enter same password", iconError);

                    disableButtonSignIn();

                } else {
                    edtPassword.setError(null);
                    edtComfirmPassword.setError(null);
                    if (isValidInput()) {

                        enableButtonSignIn();

                    }

                }
            }
        });

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


//                firebaseAuth.createUserWithEmailAndPassword()

//                firebaseAuth.addAuthStateListener();

            }
        });

    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
//        if (mListener != null) {
//            mListener.onClickAlreadyHaveAccount();
//        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentSignUpInteractionListener) {
            mListener = (OnFragmentSignUpInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentSignInInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentSignUpInteractionListener {
        // TODO: Update argument type and name
        void onClickAlreadyHaveAccount();
    }

    private void setErrorIcon() {
        edtEmail.setError("Required", iconError);
        edtFullName.setError("Required", iconError);
        edtPassword.setError("Required", iconError);
        edtComfirmPassword.setError("Required", iconError);
    }

    private boolean isValidInput() {

        Log.d("ErrorTest", "mail" + edtEmail.getError());
        Log.d("ErrorTest", "mail1" + edtFullName.getError());
        Log.d("ErrorTest", "mail2" + edtPassword.getError());
        Log.d("ErrorTest", "mail3" + edtComfirmPassword.getError());

        return edtEmail.getError() == null &&
                edtFullName.getError() == null &&
                edtPassword.getError() == null &&
                edtComfirmPassword.getError() == null;
    }

    private void disableButtonSignIn() {

        btnSignUp.setEnabled(false);
        btnSignUp.setTextColor(getResources().getColor(R.color.btnTextColorDisable));

    }

    private void enableButtonSignIn() {

        btnSignUp.setEnabled(true);
        btnSignUp.setTextColor(getResources().getColor(R.color.colorAccent));

    }
}
