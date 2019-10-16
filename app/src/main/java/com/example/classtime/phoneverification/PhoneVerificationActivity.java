package com.example.classtime.phoneverification;

import android.content.Intent;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.classtime.R;
import com.example.classtime.dashboard.DashboardActivity;

public class PhoneVerificationActivity extends AppCompatActivity implements PhoneVerificationContract.View {
    private EditText codeEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phone_verification);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle(R.string.phone_verification);

        final String phoneNumber = getIntent().getStringExtra("PHONE_NUMBER");

        final PhoneVerificationPresenter presenter = new PhoneVerificationPresenter(this);
        presenter.sendVerificationCode(phoneNumber);

        // Set up click listeners
        Button signInBtn = findViewById(R.id.signInBtn);
        codeEditText = findViewById(R.id.code);

        signInBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.onSignInClicked(codeEditText.getText().toString().trim(), phoneNumber);
            }
        });

        codeEditText.setOnEditorActionListener(new EditText.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    presenter.onSignInClicked(codeEditText.getText().toString().trim(), phoneNumber);
                    return true;
                }
                return false;
            }
        });
    }

    @Override
    public void showCodeRequiredError() {
        codeEditText.setError(getString(R.string.code_required));
        codeEditText.requestFocus();
    }

    @Override
    public void showFirebaseError(String message) {
        codeEditText.setError(message);
        codeEditText.requestFocus();
    }

    @Override
    public void navigateToDashboard(String phoneNumber) {
        Intent intent = new Intent(this, DashboardActivity.class);
        PreferenceManager.getDefaultSharedPreferences(this).edit().putString("PHONE_NUMBER", phoneNumber).apply();
        startActivity(intent);
        finish();
    }

    @Override
    public void toggleProgressBar(boolean isVisible) {
        ProgressBar progressBar = findViewById(R.id.progressBar);
        progressBar.setVisibility(isVisible ? View.VISIBLE : View.GONE);
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}
