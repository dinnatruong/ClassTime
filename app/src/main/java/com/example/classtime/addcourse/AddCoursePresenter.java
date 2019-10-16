package com.example.classtime.addcourse;

import com.example.classtime.data.model.CourseAttendance;
import com.example.classtime.data.model.Result;
import com.example.classtime.data.model.Student;
import com.example.classtime.data.service.ClassTimeService;
import com.example.classtime.data.service.RetrofitClient;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddCoursePresenter implements AddCourseContract.Presenter {
    private AddCourseContract.View view;
    private Student student;
    private ClassTimeService classTimeService;

    AddCoursePresenter(AddCourseContract.View view) {
        this.view = view;
        classTimeService = new RetrofitClient().getClassTimeService();
    }

    @Override
    public void onAddCourseClicked(String phoneNumber, String courseName, String courseCode) {
        if (courseName.isEmpty()) {
            view.showCourseNameError();
            return;
        }

        if (courseCode.isEmpty()) {
            view.showCourseCodeError();
            return;
        }

        getStudent(phoneNumber, courseName, courseCode);

    }

    private void getStudent(final String phoneNumber, final String courseName, final String courseCode) {
        if (student == null) {
            classTimeService.getStudent(phoneNumber).enqueue(new Callback<ArrayList<Student>>() {
                @Override
                public void onResponse(Call<ArrayList<Student>> call, Response<ArrayList<Student>> response) {

                    if (response.body() != null && response.body().size() > 0) {
                        student = response.body().get(0);
                        addCourse(student.getStudentId(), courseName, courseCode);
                    }
                }

                @Override
                public void onFailure(Call<ArrayList<Student>> call, Throwable t) {

                }
            });
        }
    }

    private void addCourse(int studentId, String courseName, String courseCode) {
        CourseAttendance newCourseAttendance = new CourseAttendance(studentId, 0, 0, courseName, courseCode);

        classTimeService.createCourseAttendance(newCourseAttendance).enqueue(new Callback<Result>() {
            @Override
            public void onResponse(Call<Result> call, Response<Result> response) {
                if (response.body().getResult() != null && response.body().getResult().contains("Success")) {
                    view.closeAddCourse();
                }
            }

            @Override
            public void onFailure(Call<Result> call, Throwable t) {

            }
        });

    }
}
