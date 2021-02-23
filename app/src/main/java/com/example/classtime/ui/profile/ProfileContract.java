package com.example.classtime.ui.profile;

public interface ProfileContract {

    interface View {
        void navigateToDashboard();
        void navigateToAddCourse();
        void signOut();
        void setPhoneNumber(String phoneNumber);
    }

    interface Presenter {
        void onBottomNavDashboardClicked();
        void onBottomNavAddCourseClicked();
        void getProfile(String phoneNumber);
        void onSignOutClicked();
    }
}
