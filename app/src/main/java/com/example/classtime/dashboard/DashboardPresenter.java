package com.example.classtime.dashboard;

import com.example.classtime.data.model.CourseAttendance;
import com.example.classtime.data.model.Student;
import com.example.classtime.data.service.ClassTimeService;
import com.example.classtime.data.service.RetrofitClient;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DashboardPresenter implements DashboardContract.Presenter {
    private DashboardContract.View view;
    private ClassTimeService classTimeService;
    private Student student;

    DashboardPresenter(DashboardContract.View view) {
        this.view = view;
        classTimeService = new RetrofitClient().getClassTimeService();
    }

    @Override
    public void onBottomNavAddCourseClicked() {
        view.navigateToAddCourse();
    }

    @Override
    public void onBottomNavProfileClicked() {
        view.navigateToProfile();
    }

    @Override
    public void getStudent(final String phoneNumber) {
        if (student == null) {
            classTimeService.getStudent(phoneNumber).enqueue(new Callback<ArrayList<Student>>() {
                @Override
                public void onResponse(Call<ArrayList<Student>> call, Response<ArrayList<Student>> response) {

                    if (response.body() != null && response.body().size() > 0) {
                        student = response.body().get(0);
                    } else {
                        createStudent(phoneNumber);
                    }
                }

                @Override
                public void onFailure(Call<ArrayList<Student>> call, Throwable t) {

                }
            });
        }
    }

    private void createStudent(String phoneNumber) {
        if (student == null) {
            Student newStudent = new Student("", "", phoneNumber);

            classTimeService.createStudent(newStudent).enqueue(new Callback<ArrayList<Student>>() {
                @Override
                public void onResponse(Call<ArrayList<Student>> call, Response<ArrayList<Student>> response) {
                    if (response.body() != null && response.body().size() > 0) {
                        student = response.body().get(0);
                    }
                }

                @Override
                public void onFailure(Call<ArrayList<Student>> call, Throwable t) {

                }
            });
        }
    }

    @Override
    public void getClassAttendances(String phoneNumber) {
        classTimeService.getCourseAttendances(phoneNumber).enqueue(new Callback<ArrayList<CourseAttendance>>() {
            @Override
            public void onResponse(Call<ArrayList<CourseAttendance>> call, Response<ArrayList<CourseAttendance>> response) {
                if (response.body() != null) {
                    view.updateCourseAttendances(response.body());
                }
            }

            @Override
            public void onFailure(Call<ArrayList<CourseAttendance>> call, Throwable t) {

            }
        });
    }
}
