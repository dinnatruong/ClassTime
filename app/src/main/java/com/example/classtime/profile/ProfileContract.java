package com.example.classtime.profile;

public interface ProfileContract {

    interface View {
        void navigateToDashboard();
        void navigateToAddCourse();
    }

    interface Presenter {
        void onBottomNavDashboardClicked();
        void onBottomNavAddCourseClicked();
    }
}
