package com.example.classtime.phoneverification;

public interface PhoneVerificationContract {

    interface View {
        void showCodeRequiredError();
        void showFirebaseError(String message);
        void navigateToDashboard(String phoneNumber);
        void toggleProgressBar(boolean isVisible);
    }

    interface Presenter {
        void sendVerificationCode(String phoneNumber);
        void onSignInClicked(String code, String phoneNumber);
    }
}
