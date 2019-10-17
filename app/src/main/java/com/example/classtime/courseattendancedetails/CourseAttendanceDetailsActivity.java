package com.example.classtime.courseattendancedetails;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
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
    private CourseAttendanceDetailsPresenter presenter;

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
        presenter = new CourseAttendanceDetailsPresenter(this, courseAttendance);

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
    public void closeCourse() {
        finish();
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.course_attendance_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.deleteCourse) {
            new AlertDialog.Builder(this)
                .setTitle(R.string.delete_course)
                .setMessage(R.string.delete_course_message)
                .setPositiveButton(R.string.delete_course, new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        presenter.onDeleteCourseClicked();
                    }

                })
                .setNegativeButton(android.R.string.no, null)
                .show();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
