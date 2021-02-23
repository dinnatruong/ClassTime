package com.example.classtime.ui.addcourse;

public interface AddCourseContract {

    interface View {
        void showCourseNameError();
        void showCourseCodeError();
        void closeAddCourse();
    }

    interface Presenter {
        void onAddCourseClicked(String phoneNumber, String courseName, String courseCode);
    }
}
