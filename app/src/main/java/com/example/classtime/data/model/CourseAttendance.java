package com.example.classtime.data.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class CourseAttendance implements Serializable {

    @SerializedName("id_attendance")
    private int attendanceId;

    @SerializedName("id_student")
    private int studentId;

    @SerializedName("attended")
    private int attended;

    @SerializedName("missed")
    private int missed;

    @SerializedName("id_course")
    private int courseId;

    @SerializedName("title")
    private String courseTitle;

    @SerializedName("code")
    private String courseCode;

    public CourseAttendance(int studentId, int attended, int missed, String courseTitle, String courseCode) {
        this.studentId = studentId;
        this.attended = attended;
        this.missed = missed;
        this.courseTitle = courseTitle;
        this.courseCode = courseCode;
    }

    public int getAttendanceId() {
        return attendanceId;
    }

    public int getStudentId() {
        return studentId;
    }

    public int getAttended() {
        return attended;
    }

    public int getMissed() {
        return missed;
    }

    public int getCourseId() {
        return courseId;
    }

    public String getCourseTitle() {
        return courseTitle;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public void setAttended(int attended) {
        this.attended = attended;
    }

    public void setMissed(int missed) {
        this.missed = missed;
    }
}
