package com.example.classtime.data.service;

import com.example.classtime.data.model.CourseAttendance;
import com.example.classtime.data.model.Result;
import com.example.classtime.data.model.Student;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ClassTimeService {

    @POST("/students")
    Call<ArrayList<Student>> createStudent(@Body Student body);

    @GET("/students")
    Call<ArrayList<Student>> getStudent(@Query("phone_number") String phoneNumber);

    @GET("/attendance")
    Call<ArrayList<CourseAttendance>> getCourseAttendances(@Query("phone_number") String phoneNumber);

    @POST("/attendance")
    Call<Result> createCourseAttendance(@Body CourseAttendance body);
}
