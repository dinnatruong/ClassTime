package com.example.classtime.dashboard;

import com.example.classtime.data.model.CourseAttendance;

import java.util.ArrayList;

public interface DashboardContract {

    interface View {
        void navigateToAddCourse();
        void navigateToProfile();
        void updateCourseAttendances(ArrayList<CourseAttendance> courseAttendances);
    }

    interface Presenter {
        void onBottomNavAddCourseClicked();
        void onBottomNavProfileClicked();
        void getStudent(String phoneNumber);
        void getClassAttendances(String phoneNumber);
    }
}
