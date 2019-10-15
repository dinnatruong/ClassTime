package com.example.classtime.dashboard;

public class DashboardPresenter implements DashboardContract.Presenter {
    DashboardContract.View view;

    DashboardPresenter(DashboardContract.View view) {
        this.view = view;
    }

    @Override
    public void onBottomNavAddCourseClicked() {
        view.navigateToAddCourse();
    }

    @Override
    public void onBottomNavProfileClicked() {
        view.navigateToProfile();
    }
}
