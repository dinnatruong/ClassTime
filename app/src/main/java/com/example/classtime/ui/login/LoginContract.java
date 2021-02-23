package com.example.classtime.ui.login;

public interface LoginContract {

    interface View {
        void showPhoneNumberRequiredError();
        void navigateToPhoneVerification(String phoneNumber);
    }

    interface Presenter {
        void onSendCodeClicked(String phoneNumber);
    }
}
