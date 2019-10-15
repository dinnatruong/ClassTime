package com.example.classtime.dashboard;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.support.annotation.NonNull;
import android.view.MenuItem;

import com.example.classtime.R;
import com.example.classtime.addcourse.AddCourseActivity;
import com.example.classtime.profile.ProfileActivity;

public class DashboardActivity extends AppCompatActivity implements DashboardContract.View {
    private DashboardPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        BottomNavigationView navView = findViewById(R.id.nav_view);

        presenter = new DashboardPresenter(this);

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

    @Override
    public void navigateToAddCourse() {
        Intent intent = new Intent(this, AddCourseActivity.class);
        startActivity(intent);
        overridePendingTransition(0, 0);
    }

    @Override
    public void navigateToProfile() {
        Intent intent = new Intent(this, ProfileActivity.class);
        startActivity(intent);
        overridePendingTransition(0, 0);
    }

    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(this)
            .setIcon(R.drawable.ic_sign_out)
            .setTitle(R.string.sign_out)
            .setMessage(R.string.sign_out_message)
            .setPositiveButton(R.string.sign_out, new DialogInterface.OnClickListener()
            {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    finish();
                }

            })
            .setNegativeButton(android.R.string.no, null)
            .show();
    }
}
