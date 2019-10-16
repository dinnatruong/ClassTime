package com.example.classtime.courseattendancedetails;

public interface CourseAttendanceDetailsContract {

    interface View {
        void displayCourseDetails(String courseName, String courseCode, String attended, String missed);
        void updateCourseAttendanceDetails(String attended, String missed);
    }

    interface Presenter {
        void updateAttended(boolean isAddition);
        void updateMissed(boolean isAddition);
    }

}
