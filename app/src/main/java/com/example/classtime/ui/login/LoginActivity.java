package com.example.classtime.ui.login;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.classtime.R;
import com.example.classtime.ui.phoneverification.PhoneVerificationActivity;

public class LoginActivity extends AppCompatActivity implements LoginContract.View {
    private EditText phoneNumberEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        final LoginPresenter presenter = new LoginPresenter(this);

        Button sendCodeBtn = findViewById(R.id.sendCodeBtn);
        phoneNumberEditText = findViewById(R.id.phoneNumber);

        // Set listeners to send the verification code
        sendCodeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.onSendCodeClicked(phoneNumberEditText.getText().toString().trim());
            }
        });

        phoneNumberEditText.setOnEditorActionListener(new EditText.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    presenter.onSendCodeClicked(phoneNumberEditText.getText().toString().trim());
                    return true;
                }
                return false;
            }
        });
    }

    @Override
    public void showPhoneNumberRequiredError() {
        phoneNumberEditText.setError(getString(R.string.phone_number_required));
        phoneNumberEditText.requestFocus();
    }

    @Override
    public void navigateToPhoneVerification(String phoneNumber) {
        Intent intent = new Intent(this, PhoneVerificationActivity.class);
        intent.putExtra("PHONE_NUMBER", phoneNumber);
        startActivity(intent);
    }
}
