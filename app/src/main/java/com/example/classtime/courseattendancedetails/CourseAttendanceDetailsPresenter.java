package com.example.classtime.courseattendancedetails;

import com.example.classtime.data.model.CourseAttendance;
import com.example.classtime.data.model.Result;
import com.example.classtime.data.service.ClassTimeService;
import com.example.classtime.data.service.RetrofitClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CourseAttendanceDetailsPresenter implements CourseAttendanceDetailsContract.Presenter {
    private CourseAttendanceDetailsContract.View view;
    private ClassTimeService classTimeService;
    private CourseAttendance courseAttendance;

    CourseAttendanceDetailsPresenter(CourseAttendanceDetailsContract.View view, CourseAttendance courseAttendance) {
        this.view = view;
        classTimeService = new RetrofitClient().getClassTimeService();
        this.courseAttendance = courseAttendance;
        view.displayCourseDetails(courseAttendance.getCourseTitle(), courseAttendance.getCourseCode(),
                String.valueOf(courseAttendance.getAttended()), String.valueOf(courseAttendance.getMissed()));
    }

    @Override
    public void updateAttended(boolean isAddition) {
        int attended = courseAttendance.getAttended();

        if (isAddition) {
            courseAttendance.setAttended(attended+1);
        } else {
            courseAttendance.setAttended(attended > 0 ? attended-1 : attended);
        }

        updateCourseAttendance();
    }

    @Override
    public void updateMissed(boolean isAddition) {
        int missed = courseAttendance.getMissed();

        if (isAddition) {
            courseAttendance.setMissed(missed+1);
        } else {
            courseAttendance.setMissed(missed > 0 ? missed-1 : missed);
        }

        updateCourseAttendance();
    }

    @Override
    public void onDeleteCourseClicked() {
        classTimeService.deleteCourseAttendance(courseAttendance.getAttendanceId()).enqueue(new Callback<Result>() {
            @Override
            public void onResponse(Call<Result> call, Response<Result> response) {
                if (response.body() != null && response.body().getResult().contains("Success")) {
                    view.closeCourse();
                }
            }

            @Override
            public void onFailure(Call<Result> call, Throwable t) {

            }
        });
    }

    private void updateCourseAttendance() {
        classTimeService.updateCourseAttendance(courseAttendance, courseAttendance.getAttendanceId()).enqueue(new Callback<Result>() {
            @Override
            public void onResponse(Call<Result> call, Response<Result> response) {
                if (response.body() != null && response.body().getResult().contains("Success")) {
                    view.updateCourseAttendanceDetails(String.valueOf(courseAttendance.getAttended()), String.valueOf(courseAttendance.getMissed()));
                }
            }

            @Override
            public void onFailure(Call<Result> call, Throwable t) {

            }
        });
    }
}
