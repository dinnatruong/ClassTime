package com.example.classtime.ui.dashboard;

import android.content.Intent;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.support.annotation.NonNull;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;

import com.example.classtime.R;
import com.example.classtime.ui.addcourse.AddCourseActivity;
import com.example.classtime.ui.courseattendancedetails.CourseAttendanceDetailsActivity;
import com.example.classtime.data.model.CourseAttendance;
import com.example.classtime.ui.profile.ProfileActivity;

import java.util.ArrayList;

public class DashboardActivity extends AppCompatActivity implements DashboardContract.View {
    private DashboardPresenter presenter;
    private RecyclerView courseAttendances;
    private String phoneNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.drawable.logo);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        phoneNumber = PreferenceManager.getDefaultSharedPreferences(this).getString("PHONE_NUMBER", "");

        presenter = new DashboardPresenter(this);
        presenter.getStudent(phoneNumber);

        // Initialize RecyclerView for course attendances list
        courseAttendances = findViewById(R.id.courseAttendancesList);
        courseAttendances.setLayoutManager(new LinearLayoutManager(this));
        courseAttendances.addItemDecoration(new DividerItemDecoration(courseAttendances.getContext(), DividerItemDecoration.VERTICAL));

        // Set up bottom nav bar
        BottomNavigationView navView = findViewById(R.id.nav_view);
        navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        navView.getMenu().getItem(0).setChecked(true);
    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_dashboard:
                    break;
                case R.id.navigation_add_course:
                    presenter.onBottomNavAddCourseClicked();
                    break;
                case R.id.navigation_profile:
                    presenter.onBottomNavProfileClicked();
                    break;
            }
            return false;
        }
    };


    private View.OnClickListener onCourseClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            RecyclerView.ViewHolder viewHolder = (RecyclerView.ViewHolder) view.getTag();
            int position = viewHolder.getAdapterPosition();
            presenter.onCourseAttendanceClicked(position);
        }
    };

    @Override
    protected void onResume() {
        super.onResume();
        presenter.getClassAttendances(phoneNumber);
    }

    @Override
    public void navigateToAddCourse() {
        Intent intent = new Intent(this, AddCourseActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.anim_slide_up, R.anim.anim_no_change);
    }

    @Override
    public void navigateToProfile() {
        Intent intent = new Intent(this, ProfileActivity.class);
        startActivity(intent);
        overridePendingTransition(0, 0);
    }

    @Override
    public void navigateToCourseAttendanceDetails(CourseAttendance courseAttendance) {
        Intent intent = new Intent(DashboardActivity.this, CourseAttendanceDetailsActivity.class);
        intent.putExtra("COURSE_ATTENDANCE", courseAttendance);
        startActivity(intent);
    }

    @Override
    public void updateCourseAttendances(ArrayList<CourseAttendance> courseAttendances) {
        CourseAttendanceAdapter courseAttendanceAdapter = new CourseAttendanceAdapter(this, courseAttendances);
        courseAttendanceAdapter.setOnItemClickListener(onCourseClickListener);
        this.courseAttendances.setAdapter(courseAttendanceAdapter);
    }

    @Override
    public void onBackPressed() {
        moveTaskToBack(true);
    }
}
