package com.example.classtime.ui.login;

public class LoginPresenter implements LoginContract.Presenter {
    private LoginContract.View view;

    LoginPresenter(LoginContract.View view) {
        this.view = view;
    }

    @Override
    public void onSendCodeClicked(String phoneNumber) {
        if (phoneNumber.isEmpty()) {
            view.showPhoneNumberRequiredError();
            return;
        }

        view.navigateToPhoneVerification(phoneNumber);
    }
}
