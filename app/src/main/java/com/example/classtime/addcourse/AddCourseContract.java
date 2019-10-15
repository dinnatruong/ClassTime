package com.example.classtime.addcourse;

public interface AddCourseContract {

    interface View {
        void navigateToDashboard();
        void navigateToProfile();
    }

    interface Presenter {
        void onBottomNavDashboardClicked();
        void onBottomNavProfileClicked();
    }
}
