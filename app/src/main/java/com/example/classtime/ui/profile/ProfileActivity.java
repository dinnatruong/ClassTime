package com.example.classtime.ui.profile;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.classtime.R;
import com.example.classtime.ui.addcourse.AddCourseActivity;
import com.example.classtime.ui.dashboard.DashboardActivity;
import com.example.classtime.ui.login.LoginActivity;
import com.google.firebase.auth.FirebaseAuth;

public class ProfileActivity extends AppCompatActivity implements ProfileContract.View {
    private ProfilePresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        getSupportActionBar().setTitle(R.string.profile);
        BottomNavigationView navView = findViewById(R.id.nav_view);

        // Set up click listeners
        String phoneNumber = PreferenceManager.getDefaultSharedPreferences(this).getString("PHONE_NUMBER", "");
        presenter = new ProfilePresenter(this);
        presenter.getProfile(phoneNumber);

        Button signOut = findViewById(R.id.signOutBtn);
        signOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.onSignOutClicked();
            }
        });

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
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
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
    public void signOut() {
        new AlertDialog.Builder(this)
            .setIcon(R.drawable.ic_sign_out)
            .setTitle(R.string.sign_out)
            .setMessage(R.string.sign_out_message)
            .setPositiveButton(R.string.sign_out, new DialogInterface.OnClickListener()
            {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Intent intent = new Intent(ProfileActivity.this, LoginActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                    startActivity(intent);
                    FirebaseAuth.getInstance().signOut();
                    finish();
                }

            })
            .setNegativeButton(android.R.string.no, null)
            .show();
    }

    @Override
    public void setPhoneNumber(String phoneNumber) {
        TextView phoneNumberTextView = findViewById(R.id.phoneNumber);
        phoneNumberTextView.setText(phoneNumber);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(0, 0);
        finish();
    }
}
