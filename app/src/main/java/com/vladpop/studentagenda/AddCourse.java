package com.vladpop.studentagenda;

import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class AddCourse extends Fragment implements View.OnClickListener {

    FragmentManager fm;

    private TextInputLayout nameLayout;
    private TextView nameText;
    private TextInputLayout codeLayout;
    private TextView codeText;
    private TextInputLayout professorLayout;
    private TextView professorText;

    private TextView checkError;

    private CheckBox additionalCheck;
    private LinearLayout additionalInfo;
    private CheckBox monCheck;
    private CheckBox tueCheck;
    private CheckBox wedCheck;
    private CheckBox thuCheck;
    private CheckBox friCheck;

    private LinearLayout monInfo;
    private LinearLayout tueInfo;
    private LinearLayout wedInfo;
    private LinearLayout thuInfo;
    private LinearLayout friInfo;

    AddCourseDayInfo mon;
    AddCourseDayInfo tue;
    AddCourseDayInfo wed;
    AddCourseDayInfo thu;
    AddCourseDayInfo fri;

    private Button confirm;

    private String name = "";
    private String code = "";
    private String professor = "";
    private ArrayList<String> days = new ArrayList<String>();
    private ArrayList<String> locations = new ArrayList<String>();
    private ArrayList<String> stimes = new ArrayList<String>();
    private ArrayList<String> etimes = new ArrayList<String>();
    private ArrayList<String> types = new ArrayList<String>();

    public AddCourse() {
        // Required empty public constructor
    }

    public static AddCourse newInstance() {

        return new AddCourse();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View inflatedView = inflater.inflate(R.layout.fragment_add_course, container, false);

        nameLayout = (TextInputLayout) inflatedView.findViewById(R.id.input_layout_subject_name);
        nameText = (TextView) inflatedView.findViewById(R.id.subject_name);
        codeLayout = (TextInputLayout) inflatedView.findViewById(R.id.input_layout_course_code);
        codeText = (TextView) inflatedView.findViewById(R.id.course_code);
        professorLayout = (TextInputLayout) inflatedView.findViewById(R.id.input_layout_professor);
        professorText = (TextView) inflatedView.findViewById(R.id.professor);

        additionalCheck = (CheckBox) inflatedView.findViewById(R.id.check_additional);
        additionalCheck.setOnClickListener(this);

        additionalInfo = (LinearLayout) inflatedView.findViewById(R.id.additional_info);

        monCheck = (CheckBox) inflatedView.findViewById(R.id.check_mon);
        monCheck.setOnClickListener(this);

        tueCheck = (CheckBox) inflatedView.findViewById(R.id.check_tue);
        tueCheck.setOnClickListener(this);

        wedCheck = (CheckBox) inflatedView.findViewById(R.id.check_wed);
        wedCheck.setOnClickListener(this);

        thuCheck = (CheckBox) inflatedView.findViewById(R.id.check_thu);
        thuCheck.setOnClickListener(this);

        friCheck = (CheckBox) inflatedView.findViewById(R.id.check_fri);
        friCheck.setOnClickListener(this);

        checkError = (TextView) inflatedView.findViewById(R.id.check_error);

        monInfo = (LinearLayout) inflatedView.findViewById(R.id.monInfo);
        tueInfo = (LinearLayout) inflatedView.findViewById(R.id.tueInfo);
        wedInfo = (LinearLayout) inflatedView.findViewById(R.id.wedInfo);
        thuInfo = (LinearLayout) inflatedView.findViewById(R.id.thuInfo);
        friInfo = (LinearLayout) inflatedView.findViewById(R.id.friInfo);

        confirm = (Button) inflatedView.findViewById(R.id.button_confirm_subject);
        confirm.setOnClickListener(this);

        fm = getActivity().getSupportFragmentManager();

        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.monInfo, AddCourseDayInfo.newInstance("Monday"), "monInfo");
        ft.replace(R.id.tueInfo, AddCourseDayInfo.newInstance("Tuesday"), "tueInfo");
        ft.replace(R.id.wedInfo, AddCourseDayInfo.newInstance("Wednesday"), "wedInfo");
        ft.replace(R.id.thuInfo, AddCourseDayInfo.newInstance("Thursday"), "thuInfo");
        ft.replace(R.id.friInfo, AddCourseDayInfo.newInstance("Friday"), "friInfo");
        ft.addToBackStack(null);
        ft.commit();

        monInfo.setVisibility(View.GONE);
        tueInfo.setVisibility(View.GONE);
        wedInfo.setVisibility(View.GONE);
        thuInfo.setVisibility(View.GONE);
        friInfo.setVisibility(View.GONE);

        return inflatedView;
    }

    @Override
    public void onClick(View v) {

        if (v == additionalCheck) toggleAdditional();
        else if (v == monCheck) toggleMonday();
        else if (v == tueCheck) toggleTuesday();
        else if (v == wedCheck) toggleWednesday();
        else if (v == thuCheck) toggleThursday();
        else if (v == friCheck) toggleFriday();
        else if (v == confirm) {

            mon = (AddCourseDayInfo) fm.findFragmentByTag("monInfo");
            tue = (AddCourseDayInfo) fm.findFragmentByTag("tueInfo");
            wed = (AddCourseDayInfo) fm.findFragmentByTag("wedInfo");
            thu = (AddCourseDayInfo) fm.findFragmentByTag("thuInfo");
            fri = (AddCourseDayInfo) fm.findFragmentByTag("friInfo");

            Boolean error = false;

            error = validateName();

            if (!monCheck.isChecked() && !tueCheck.isChecked() && !wedCheck.isChecked() && !thuCheck.isChecked() && !friCheck.isChecked())
            {
                checkError.setVisibility(View.VISIBLE);
                return;
            }

            if (monCheck.isChecked()) error = validateMonday(mon);
            if (tueCheck.isChecked()) error = validateTuesday(tue);
            if (wedCheck.isChecked()) error = validateWednesday(wed);
            if (thuCheck.isChecked()) error = validateThursday(thu);
            if (friCheck.isChecked()) error = validateFriday(fri);

            if (!error) {
                addSubject();
            }

        }

    }

    public void toggleAdditional() {
        if (additionalCheck.isChecked())
        {
            System.out.println("ADDITIONAL INFO ENABLED <---------------------");
            additionalInfo.setVisibility(View.VISIBLE);
        }
        else
        {
            System.out.println("ADDITIONAL INFO DISABLED <---------------------");
            additionalInfo.setVisibility(View.GONE);
        }
    }

    public void toggleMonday() {
        if (monCheck.isChecked())
        {
            System.out.println("MONDAY WAS CHECKED <---------------------");
            monInfo.setVisibility(View.VISIBLE);
            checkError.setVisibility(View.GONE);
        }
        else
        {
            System.out.println("MONDAY WAS UNCHECKED <---------------------");
            monInfo.setVisibility(View.GONE);
        }
    }

    public void toggleTuesday() {
        if (tueCheck.isChecked())
        {
            System.out.println("TUESDAY WAS CHECKED <---------------------");
            tueInfo.setVisibility(View.VISIBLE);
            checkError.setVisibility(View.GONE);
        }
        else
        {
            System.out.println("TUESDAY WAS UNCHECKED <---------------------");
            tueInfo.setVisibility(View.GONE);
        }
    }

    public void toggleWednesday() {
        if (wedCheck.isChecked())
        {
            System.out.println("WEDNESDAY WAS CHECKED <---------------------");
            wedInfo.setVisibility(View.VISIBLE);
            checkError.setVisibility(View.GONE);
        }
        else
        {
            System.out.println("WEDNESDAY WAS UNCHECKED <---------------------");
            wedInfo.setVisibility(View.GONE);
        }
    }

    public void toggleThursday() {
        if (thuCheck.isChecked())
        {
            System.out.println("THURSDAY WAS CHECKED <---------------------");
            thuInfo.setVisibility(View.VISIBLE);
            checkError.setVisibility(View.GONE);
        }
        else
        {
            System.out.println("THURSDAY WAS UNCHECKED <---------------------");
            thuInfo.setVisibility(View.GONE);
        }
    }

    public void toggleFriday() {
        if (friCheck.isChecked())
        {
            System.out.println("FRIDAY WAS CHECKED <---------------------");
            friInfo.setVisibility(View.VISIBLE);
            checkError.setVisibility(View.GONE);
        }
        else
        {
            System.out.println("FRIDAY WAS UNCHECKED <---------------------");
            friInfo.setVisibility(View.GONE);
        }
    }

    public void addSubject() {
        name = nameText.getText().toString();
        code = codeText.getText().toString();
        professor = professorText.getText().toString();

        if (monCheck.isChecked()) {
            days.add("monday");
            locations.add(mon.getLocation());
            stimes.add(mon.getStime());
            etimes.add(mon.getEtime());
            types.add(mon.getType());
        }
        if (tueCheck.isChecked()) {
            days.add("tuesday");
            locations.add(tue.getLocation());
            stimes.add(tue.getStime());
            etimes.add(tue.getEtime());
            types.add(tue.getType());
        }
        if (wedCheck.isChecked()) {
            days.add("wednesday");
            locations.add(wed.getLocation());
            stimes.add(wed.getStime());
            etimes.add(wed.getEtime());
            types.add(wed.getType());
        }
        if (thuCheck.isChecked()) {
            days.add("thursday");
            locations.add(thu.getLocation());
            stimes.add(thu.getStime());
            etimes.add(thu.getEtime());
            types.add(thu.getType());
        }
        if (friCheck.isChecked()) {
            days.add("friday");
            locations.add(fri.getLocation());
            stimes.add(fri.getStime());
            etimes.add(fri.getEtime());
            types.add(fri.getType());
        }

        AddCourseInterface comm;
        comm = (AddCourseInterface) getActivity();
        int id = comm.getNextSubId();
        Subject subject = Subject.newInstance(id, name, code, professor, days, locations, stimes, etimes, types);
        comm.sendCourse(subject);
    }

    public Boolean validateName() {
        Boolean error = false;

        if (nameText.getText().toString().equals(""))
        {
            nameLayout.setErrorEnabled(true);
            nameLayout.setError("Invalid Input");
            nameText.setError("Subject Name cannot be blank");
            error = true;
        }
        else nameLayout.setErrorEnabled(false);

        return error;
    }

    public Boolean validateMonday(AddCourseDayInfo mon){
        Boolean error = false;

        if (mon.getLocation() == null || mon.getLocation().equals("")) {
            resetSub();
            error = true;
        }
        if (mon.getStime() == null || mon.getStime().equals("")) {
            resetSub();
            error = true;
        }
        if (mon.getEtime() == null || mon.getEtime().equals("")) {
            resetSub();
            error = true;
        }
        if (mon.getType() == null || mon.getType().equals("")) {
            resetSub();
            error = true;
        }

        return error;
    }

    public Boolean validateTuesday(AddCourseDayInfo tue) {
        Boolean error = false;

        if (tue.getLocation() == null || tue.getLocation().equals("")) {
            resetSub();
            error = true;
        }
        if (tue.getStime() == null || tue.getStime().equals("")) {
            resetSub();
            error = true;
        }
        if (tue.getEtime() == null || tue.getEtime().equals("")) {
            resetSub();
            error = true;
        }
        if (tue.getType() == null || tue.getType().equals("")) {
            resetSub();
            error = true;
        }

        return error;
    }

    public Boolean validateWednesday(AddCourseDayInfo wed) {
        Boolean error = false;

        if (wed.getLocation() == null || wed.getLocation().equals("")) {
            resetSub();
            error = true;
        }

        if (wed.getStime() == null || wed.getStime().equals("")) {
            resetSub();
            error = true;
        }
        if (wed.getEtime() == null || wed.getEtime().equals("")) {
            resetSub();
            error = true;
        }
        if (wed.getType() == null || wed.getType().equals("")) {
            resetSub();
            error = true;
        }

        return error;
    }

    public Boolean validateThursday(AddCourseDayInfo thu) {
        Boolean error = false;

        if (thu.getLocation() == null || thu.getLocation().equals("")) {
            resetSub();
            error = true;
        }
        if (thu.getStime() == null || thu.getStime().equals("")) {
            resetSub();
            error = true;
        }
        if (thu.getEtime() == null || thu.getEtime().equals("")) {
            resetSub();
            error = true;
        }
        if (thu.getType() == null || thu.getType().equals("")) {
            resetSub();
            error = true;
        }

        return error;
    }

    public Boolean validateFriday(AddCourseDayInfo fri) {
        Boolean error = false;

        if (fri.getLocation() == null || fri.getLocation().equals("")) {
            resetSub();
            error = true;
        }
        if (fri.getStime() == null || fri.getStime().equals("")) {
            resetSub();
            error = true;
        }
        if (fri.getEtime() == null || fri.getEtime().equals("")) {
            resetSub();
            error = true;
        }
        if (fri.getType() == null || fri.getType().equals("")) {
            resetSub();
            error = true;
        }

        return error;
    }

    public void resetSub() {
        name = "";
        code = "";
        professor = "";
        days = new ArrayList<String>();
        locations = new ArrayList<String>();
        stimes = new ArrayList<String>();
        etimes = new ArrayList<String>();
        types = new ArrayList<String>();
    }
}
