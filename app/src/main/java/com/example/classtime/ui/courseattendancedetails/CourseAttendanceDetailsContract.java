package com.example.classtime.ui.courseattendancedetails;

public interface CourseAttendanceDetailsContract {

    interface View {
        void displayCourseDetails(String courseName, String courseCode, String attended, String missed);
        void updateCourseAttendanceDetails(String attended, String missed);
        void closeCourse();
    }

    interface Presenter {
        void updateAttended(boolean isAddition);
        void updateMissed(boolean isAddition);
        void onDeleteCourseClicked();
    }

}
