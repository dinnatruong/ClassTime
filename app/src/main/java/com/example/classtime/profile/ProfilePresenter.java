package com.example.classtime.profile;

public class ProfilePresenter implements ProfileContract.Presenter {
    ProfileContract.View view;

    ProfilePresenter(ProfileContract.View view) {
        this.view = view;
    }

    @Override
    public void onBottomNavDashboardClicked() {
        view.navigateToDashboard();
    }

    @Override
    public void onBottomNavAddCourseClicked() {
        view.navigateToAddCourse();
    }
}
