package com.example.classtime.addcourse;

public class AddCoursePresenter implements AddCourseContract.Presenter {
    AddCourseContract.View view;

    AddCoursePresenter(AddCourseContract.View view) {
        this.view = view;
    }
}
