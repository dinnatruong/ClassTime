package com.example.classtime.courseattendancedetails;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.classtime.R;
import com.example.classtime.data.model.CourseAttendance;

public class CourseAttendanceDetailsActivity extends AppCompatActivity implements CourseAttendanceDetailsContract.View {
    private TextView courseName;
    private TextView courseCode;
    private TextView classesAttended;
    private TextView classesMissed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_attendance_details);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle(R.string.attendance_details);

        courseName = findViewById(R.id.courseName);
        courseCode = findViewById(R.id.courseCode);
        classesAttended = findViewById(R.id.classesAttended);
        classesMissed = findViewById(R.id.classesMissed);

        CourseAttendance courseAttendance = (CourseAttendance) getIntent().getSerializableExtra("COURSE_ATTENDANCE");
        final CourseAttendanceDetailsPresenter presenter = new CourseAttendanceDetailsPresenter(this, courseAttendance);

        // Set up click listeners
        Button subtractAttended = findViewById(R.id.subtractAttended);
        subtractAttended.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.updateAttended(false);
            }
        });

        Button addAttended = findViewById(R.id.addAttended);
        addAttended.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.updateAttended(true);
            }
        });

        Button subtractMissed = findViewById(R.id.subtractMissed);
        subtractMissed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.updateMissed(false);
            }
        });

        Button addMissed = findViewById(R.id.addMissed);
        addMissed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.updateMissed(true);
            }
        });
    }

    @Override
    public void displayCourseDetails(String courseName, String courseCode, String attended, String missed) {
        this.courseName.setText(courseName);
        this.courseCode.setText(courseCode);
        classesAttended.setText(attended);
        classesMissed.setText(missed);
    }

    @Override
    public void updateCourseAttendanceDetails(String attended, String missed) {
        classesAttended.setText(attended);
        classesMissed.setText(missed);
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}
