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
import android.widget.TextView;

import com.tranphunguyen.mymall.Utils.Contanst;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link OnFragmentSignInInteractionListener} interface
 * to handle interaction events.
 * Use the {@link SignInFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SignInFragment extends Fragment {

    private TextView dontHaveAccount;

    private EditText edtEmail, edtPassword;

    private Button btnSignIn;

    private Drawable iconError;

    private OnFragmentSignInInteractionListener mListener;

    public SignInFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SignInFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SignInFragment newInstance(String param1, String param2) {
        return new SignInFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_sign_in, container, false);

        dontHaveAccount = view.findViewById(R.id.tv_dont_have_account);

        edtEmail = view.findViewById(R.id.edt_email);
        edtPassword = view.findViewById(R.id.edt_password);
        btnSignIn = view.findViewById(R.id.btn_sign_in);

        iconError = getResources().getDrawable(R.drawable.ic_round_error_16dp);
        iconError.setBounds(new Rect(0, 0, iconError.getIntrinsicWidth(), iconError.getIntrinsicHeight()));

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        dontHaveAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mListener.onClickDontHaveAccount();

            }
        });

        onTextChangeEdtEmail();
        onTextChangeEdtPassword();

    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
//        if (mListener != null) {
//            mListener.onClickDontHaveAccount(uri);
//        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentSignInInteractionListener) {
            mListener = (OnFragmentSignInInteractionListener) context;
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
    public interface OnFragmentSignInInteractionListener {

        void onClickDontHaveAccount();
    }


    private boolean isValidInput() {

        Log.d("ErrorTest", "mail" + edtEmail.getError());
        Log.d("ErrorTest", "edtPassword" + edtPassword.getError());

        return edtEmail.getError() == null &&
                edtPassword.getError() == null;
    }

    private void disableButtonSignIn() {

        btnSignIn.setEnabled(false);
        btnSignIn.setTextColor(getResources().getColor(R.color.btnTextColorDisable));

    }

    private void enableButtonSignIn() {

        btnSignIn.setEnabled(true);
        btnSignIn.setTextColor(getResources().getColor(R.color.colorAccent));

    }

    private void onTextChangeEdtEmail() {

        edtEmail.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (TextUtils.isEmpty(s) || s.toString().length() < 6 || !s.toString().matches(Contanst.REGEX_EMAIL)) {

                    edtEmail.setError("Invalid Email", iconError);

                    disableButtonSignIn();

                } else {
                    edtEmail.setError(null);
                    if (isValidInput()) {

                        enableButtonSignIn();

                    }

                }
            }
        });

    }

    private void onTextChangeEdtPassword() {

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

                } else {
                    edtPassword.setError(null);

                    if (isValidInput()) {

                        enableButtonSignIn();

                    }

                }
            }
        });

    }

}
