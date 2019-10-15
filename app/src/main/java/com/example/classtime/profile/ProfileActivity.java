package com.example.classtime.profile;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.example.classtime.R;
import com.example.classtime.addcourse.AddCourseActivity;
import com.example.classtime.dashboard.DashboardActivity;

public class ProfileActivity extends AppCompatActivity implements ProfileContract.View {
    private ProfilePresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        BottomNavigationView navView = findViewById(R.id.nav_view);

        presenter = new ProfilePresenter(this);

        navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        navView.getMenu().getItem(2).setChecked(true);
    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_dashboard:
                    presenter.onBottomNavDashboardClicked();
                    break;
                case R.id.navigation_add_course:
                    presenter.onBottomNavAddCourseClicked();
                    break;
                case R.id.navigation_profile:
                    break;
            }
            return false;
        }
    };

    @Override
    public void navigateToDashboard() {
        Intent intent = new Intent(this, DashboardActivity.class);
        startActivity(intent);
        overridePendingTransition(0, 0);
        finish();
    }

    @Override
    public void navigateToAddCourse() {
        Intent intent = new Intent(this, AddCourseActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.anim_slide_up, R.anim.anim_no_change);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(0, 0);
    }
}
