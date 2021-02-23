package com.example.classtime.profile;

import android.util.Log;

import com.example.classtime.data.model.Student;
import com.example.classtime.data.remote.ClassTimeService;
import com.example.classtime.data.remote.RetrofitClient;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfilePresenter implements ProfileContract.Presenter {
    private ProfileContract.View view;
    private ClassTimeService classTimeService;
    private Student student;

    ProfilePresenter(ProfileContract.View view) {
        this.view = view;
        classTimeService = new RetrofitClient().getClassTimeService();
    }

    @Override
    public void onBottomNavDashboardClicked() {
        view.navigateToDashboard();
    }

    @Override
    public void onBottomNavAddCourseClicked() {
        view.navigateToAddCourse();
    }

    @Override
    public void getProfile(String phoneNumber) {
        if (student == null) {
            classTimeService.getStudent(phoneNumber).enqueue(new Callback<ArrayList<Student>>() {
                @Override
                public void onResponse(Call<ArrayList<Student>> call, Response<ArrayList<Student>> response) {

                    if (response.body() != null && response.body().size() > 0) {
                        student = response.body().get(0);
                        view.setPhoneNumber(student.getPhoneNumber());
                    }
                }

                @Override
                public void onFailure(Call<ArrayList<Student>> call, Throwable t) {
                    Log.d("ERROR", t.getLocalizedMessage());
                }
            });
        }
    }

    @Override
    public void onSignOutClicked() {
        view.signOut();
    }
}
