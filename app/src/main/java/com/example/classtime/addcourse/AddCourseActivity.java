package com.example.classtime.addcourse;

import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.classtime.R;

public class AddCourseActivity extends AppCompatActivity implements AddCourseContract.View {
    private AddCoursePresenter presenter;
    private EditText courseName;
    private EditText courseCode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_course);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_close);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle(R.string.add_course);

        final String phoneNumber = PreferenceManager.getDefaultSharedPreferences(this).getString("PHONE_NUMBER", "");

        presenter = new AddCoursePresenter(this);

        Button addCourseBtn = findViewById(R.id.addCourseBtn);
        courseName = findViewById(R.id.courseName);
        courseCode = findViewById(R.id.courseCode);

        addCourseBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.onAddCourseClicked(phoneNumber, courseName.getText().toString().trim(), courseCode.getText().toString().trim());
            }
        });
    }

    @Override
    public void showCourseNameError() {
        courseName.setError(getString(R.string.course_name_required));
    }

    @Override
    public void showCourseCodeError() {
        courseCode.setError(getString(R.string.course_code_required));
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.anim_no_change, R.anim.anim_slide_down);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    public void closeAddCourse() {
        onBackPressed();
    }
}
