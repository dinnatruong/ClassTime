package com.example.classtime.dashboard;

public interface DashboardContract {

    interface View {
        void navigateToAddCourse();
        void navigateToProfile();
    }

    interface Presenter {
        void onBottomNavAddCourseClicked();
        void onBottomNavProfileClicked();
    }
}
