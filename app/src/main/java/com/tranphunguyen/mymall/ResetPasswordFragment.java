package com.tranphunguyen.mymall;

import android.content.Context;
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
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.android.material.button.MaterialButton;
import com.tranphunguyen.mymall.Utils.Constant;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ResetPasswordFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ResetPasswordFragment extends Fragment {

    private OnFragmentInteractionListener mListener;

    private MaterialButton btnGoBack, btnResetPassword;
    private ProgressBar prgLinearSendEmail, prgSendEmail;
    private TextView tvSentEmailSuccessful;
    private EditText edtEmail;

    public ResetPasswordFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment ResetPasswordFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ResetPasswordFragment newInstance() {
        return new ResetPasswordFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_reset_password, container, false);

        btnGoBack = view.findViewById(R.id.btn_go_back);
        btnResetPassword = view.findViewById(R.id.btn_reset_password);
        prgLinearSendEmail = view.findViewById(R.id.prg_linear_reset_password);
        prgSendEmail = view.findViewById(R.id.prg_reset_password);
        tvSentEmailSuccessful = view.findViewById(R.id.tv_sent_email_successful);
        edtEmail = view.findViewById(R.id.edt_email_forgot_password);

        return view;

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        onTextChangeEdtEmail();

        btnGoBack.setOnClickListener(v -> mListener.onClickGoBack());
        btnResetPassword.setOnClickListener(v -> mListener.resetPassword(edtEmail.getText().toString()));

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
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
    public interface OnFragmentInteractionListener {
        void onClickGoBack();
        void resetPassword(String email);
    }

    void onLoading() {

        btnResetPassword.setText("");
        prgLinearSendEmail.setProgress(50);
        prgSendEmail.setVisibility(View.VISIBLE);
        tvSentEmailSuccessful.setVisibility(View.INVISIBLE);

    }

    void onSentEmailSuccessful() {

        btnResetPassword.setText(getResources().getText(R.string.reset_password));
        prgLinearSendEmail.setProgress(100);
        prgSendEmail.setVisibility(View.GONE);
        tvSentEmailSuccessful.setVisibility(View.VISIBLE);

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
                if (TextUtils.isEmpty(s) || s.toString().length() < 6 || !s.toString().matches(Constant.REGEX_EMAIL)) {

                    edtEmail.setError("Invalid Email");

                    disableButtonResetPassword();

                } else {
                    edtEmail.setError(null);
                    if (isValidInput()) {

                        enableButtonResetPassword();

                    }

                }
            }
        });


    }

    private boolean isValidInput() {

        Log.d("ErrorTest", "mail" + edtEmail.getError());

        return edtEmail.getError() == null;
    }

    private void disableButtonResetPassword() {

        btnResetPassword.setEnabled(false);
        btnResetPassword.setTextColor(getResources().getColor(R.color.btnTextColorDisable));

    }

    private void enableButtonResetPassword() {

        btnResetPassword.setEnabled(true);
        btnResetPassword.setTextColor(getResources().getColor(R.color.colorAccent));

    }


}
