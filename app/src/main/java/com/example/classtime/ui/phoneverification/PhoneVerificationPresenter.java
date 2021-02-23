package com.example.classtime.ui.phoneverification;

import android.support.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskExecutors;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class PhoneVerificationPresenter implements PhoneVerificationContract.Presenter {
    private PhoneVerificationContract.View view;
    private String verificationCode;
    private FirebaseAuth firebaseAuth;

    PhoneVerificationPresenter(PhoneVerificationContract.View view) {
        this.view = view;
        firebaseAuth = FirebaseAuth.getInstance();
    }

    @Override
    public void sendVerificationCode(final String phoneNumber) {

        PhoneAuthProvider.getInstance().verifyPhoneNumber(phoneNumber, 60, TimeUnit.SECONDS, TaskExecutors.MAIN_THREAD, new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
            @Override
            public void onVerificationCompleted(PhoneAuthCredential phoneAuthCredential) {
                String code = phoneAuthCredential.getSmsCode();

                if (code != null) {
                    view.toggleProgressBar(true);
                    verifyCode(code, phoneNumber);
                }
            }

            @Override
            public void onVerificationFailed(FirebaseException e) {
                view.toggleProgressBar(false);
                view.showFirebaseError(e.getMessage());
            }

            @Override
            public void onCodeSent(String s, PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                super.onCodeSent(s, forceResendingToken);
                verificationCode = s;
            }
        });
    }

    @Override
    public void onSignInClicked(String code, String phoneNumber) {
        if (code.isEmpty()) {
            view.toggleProgressBar(false);
            view.showCodeRequiredError();
            return;
        }

        view.toggleProgressBar(true);
        verifyCode(code, phoneNumber);

    }

    private void verifyCode(String code, final String phoneNumber) {
        try {
            PhoneAuthCredential credential = PhoneAuthProvider.getCredential(verificationCode, code);

            firebaseAuth.signInWithCredential(credential)
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                view.navigateToDashboard(phoneNumber);
                            } else {
                                view.toggleProgressBar(false);
                                view.showFirebaseError(task.getException().getMessage());
                            }
                        }
                    });
        } catch (Exception e) {
            view.toggleProgressBar(false);
            view.showFirebaseError("Incorrect verification code");
        }
    }
}
