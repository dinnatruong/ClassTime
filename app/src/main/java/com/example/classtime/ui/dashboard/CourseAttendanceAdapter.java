package com.example.classtime.ui.dashboard;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.classtime.R;
import com.example.classtime.data.model.CourseAttendance;

import java.util.List;

public class CourseAttendanceAdapter extends RecyclerView.Adapter<CourseAttendanceAdapter.ViewHolder> {
    private List<CourseAttendance> mData;
    private LayoutInflater mInflater;
    private View.OnClickListener mOnItemClickListener;

    public CourseAttendanceAdapter(Context context, List<CourseAttendance> data) {
        this.mInflater = LayoutInflater.from(context);
        this.mData = data;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.course_attendance_listitem, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        CourseAttendance courseAttendance = mData.get(position);
        holder.courseNameTextView.setText(courseAttendance.getCourseTitle());
        holder.courseCodeTextView.setText(courseAttendance.getCourseCode());

        int totalClasses = courseAttendance.getAttended() + courseAttendance.getMissed();
        float averageAttended = totalClasses > 0 ? ((float) courseAttendance.getAttended() / (float) totalClasses) * 100 : 0;
        holder.percentageTextView.setText(String.format("%d%%", (int) averageAttended));
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView courseNameTextView;
        TextView courseCodeTextView;
        TextView percentageTextView;

        ViewHolder(View itemView) {
            super(itemView);
            itemView.setTag(this);
            courseNameTextView = itemView.findViewById(R.id.courseName);
            courseCodeTextView = itemView.findViewById(R.id.courseCode);
            percentageTextView = itemView.findViewById(R.id.percentage);
            itemView.setOnClickListener(mOnItemClickListener);
        }
    }

    public void setOnItemClickListener(View.OnClickListener itemClickListener) {
        mOnItemClickListener = itemClickListener;
    }
}
