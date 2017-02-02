package com.vladpop.studentagenda;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;

public class AddCourseDayInfo extends Fragment implements View.OnClickListener{

    private String day;
    private String location;
    private String stime;
    private String etime;
    private String type;

    private TextInputLayout dayLocLayout;
    private TextView dayLoc;
    private TextInputLayout daySTimeLayout;
    private TextView daySTime;
    private TextInputLayout dayETimeLayout;
    private TextView dayETime;

    private RadioButton day_lec;
    private RadioButton day_sem;
    private RadioButton day_lab;
    private TextView day_error;

    public static AddCourseDayInfo newInstance(String day) {
        AddCourseDayInfo addCourseDayInfo = new AddCourseDayInfo();

        Bundle bundle = new Bundle();
        bundle.putSerializable("day", day);
        addCourseDayInfo.setArguments(bundle);

        return addCourseDayInfo;
    }

    public AddCourseDayInfo() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View inflatedView = inflater.inflate(R.layout.fragment_add_course_day_info, container, false);

        TextView dayTitle = (TextView) inflatedView.findViewById(R.id.day_info_title);
        String title = getArguments().getString("day");
        dayTitle.setText(title + " Information");

        dayLocLayout = (TextInputLayout) inflatedView.findViewById(R.id.input_layout_day_location);
        daySTimeLayout = (TextInputLayout) inflatedView.findViewById(R.id.input_layout_day_stime);
        dayETimeLayout = (TextInputLayout) inflatedView.findViewById(R.id.input_layout_day_etime);
        dayLoc = (TextView) inflatedView.findViewById(R.id.day_location);
        daySTime = (TextView) inflatedView.findViewById(R.id.day_stime);
        dayETime = (TextView) inflatedView.findViewById(R.id.day_etime);

        day_lec = (RadioButton) inflatedView.findViewById(R.id.day_lec);
        day_sem = (RadioButton) inflatedView.findViewById(R.id.day_sem);
        day_lab = (RadioButton) inflatedView.findViewById(R.id.day_lab);
        day_error = (TextView) inflatedView.findViewById(R.id.day_rad_error);

        day_lec.setOnClickListener(this);
        day_sem.setOnClickListener(this);
        day_lab.setOnClickListener(this);
        
        return inflatedView;
    }

    @Override
    public void onClick(View v) {
        
        if (v == day_lec) {
            day_lec.setChecked(true);
            day_sem.setChecked(false);
            day_lab.setChecked(false);
            day_error.setVisibility(View.GONE);
        }

        else if (v == day_sem) {
            day_lec.setChecked(false);
            day_sem.setChecked(true);
            day_lab.setChecked(false);
            day_error.setVisibility(View.GONE);
        }

        else if (v == day_lab) {
            day_lec.setChecked(false);
            day_sem.setChecked(false);
            day_lab.setChecked(true);
            day_error.setVisibility(View.GONE);
        }
        
    }

    public String getLocation()
    {
        location = dayLoc.getText().toString();
        if (location.equals(""))
        {
            dayLocLayout.setErrorEnabled(true);
            dayLocLayout.setError("Invalid Input");
            dayLoc.setError("Location cannot be blank");
        }
        else
        {
            dayLocLayout.setErrorEnabled(false);
        }
        return location;
    }

    public String getStime()
    {
        stime = daySTime.getText().toString();
        if (stime.equals(""))
        {
            daySTimeLayout.setErrorEnabled(true);
            daySTimeLayout.setError("Invalid Input");
            daySTime.setError("Start Time cannot be blank");
        }
        else
        {
            daySTimeLayout.setErrorEnabled(false);
        }
        return stime;
    }

    public String getEtime()
    {
        etime = dayETime.getText().toString();
        if (etime.equals(""))
        {
            dayETimeLayout.setErrorEnabled(true);
            dayETimeLayout.setError("Invalid Input");
            dayETime.setError("End Time cannot be blank");
        }
        else
        {
            dayETimeLayout.setErrorEnabled(false);
        }
        return etime;
    }

    public String getType()
    {
        if (day_lec.isChecked()) {
            type = "(LECTURE)";
        }
        else if (day_sem.isChecked()) {
            type = "(SEMINAR)";
        }
        else if (day_lab.isChecked()) {
            type = "(LABORATORY)";
        }
        else
        {
            day_error.setVisibility(View.VISIBLE);
        }
        return type;
    }

}
