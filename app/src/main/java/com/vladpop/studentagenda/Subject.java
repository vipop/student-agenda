package com.vladpop.studentagenda;

import android.media.Image;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.provider.ContactsContract;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class Subject extends Fragment implements Parcelable, View.OnClickListener {

    private int id;
    private String name;
    private String code;
    private String professor;

    private ArrayList<String> days = new ArrayList<String>();
    private ArrayList<String> locations = new ArrayList<String>();
    private ArrayList<String> stimes = new ArrayList<String>();
    private ArrayList<String> etimes = new ArrayList<String>();
    private ArrayList<String> types = new ArrayList<String>();

    ImageButton button;


    public Subject() {
        // Required empty public constructor
    }

    public static Subject newInstance(int id, String name, String code, String professor,
                                      ArrayList<String> days, ArrayList<String> locations,
                                      ArrayList<String> stimes, ArrayList<String> etimes,
                                      ArrayList<String> types) {

        Subject subject = new Subject();

        subject.setSubId(id);
        subject.setName(name);
        subject.setCode(code);
        subject.setProfessor(professor);

        subject.setDays(days);
        subject.setLocations(locations);
        subject.setSTimes(stimes);
        subject.setETimes(etimes);
        subject.setTypes(types);

        return subject;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View inflatedView = inflater.inflate(R.layout.fragment_subject, container, false);

        button = (ImageButton) inflatedView.findViewById(R.id.btn_delete);
        button.setOnClickListener(this);

        return inflatedView;
    }

    public int getSubId()
    {
        return id;
    }

    public String getName()
    {
        return name;
    }

    public String getCode()
    {
        return code;
    }

    public String getProfessor()
    {
        return professor;
    }

    public ArrayList<String> getDays()
    {
        return days;
    }

    public ArrayList<String> getSTimes()
    {
        return stimes;
    }

    public ArrayList<String> getETimes()
    {
        return etimes;
    }

    public ArrayList<String> getTypes()
    {
        return types;
    }

    public ArrayList<String> getLocations()
    {
        return locations;
    }

    public void setSubId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setProfessor(String professor) {
        this.professor = professor;
    }

    public void setDays(ArrayList<String> days) {
        this.days = days;
    }

    public void setLocations(ArrayList<String> locations) {
        this.locations = locations;
    }

    public void setSTimes(ArrayList<String> stimes) {
        this.stimes = stimes;
    }

    public void setETimes(ArrayList<String> etimes) {
        this.etimes = etimes;
    }

    public void setTypes(ArrayList<String> types) {
        this.types = types;
    }

    public void print()
    {
        System.out.println("/// /// SUBJECT INFO /// ///");

        System.out.println(name);
        System.out.println(code);
        System.out.println(professor);

        System.out.println(days);
        System.out.println(locations);
        System.out.println(stimes);
        System.out.println(etimes);
        System.out.println(types);

        System.out.println("/// /// SUBJECT INFO /// ///");
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(code);
        dest.writeString(professor);
        dest.writeStringList(days);
        dest.writeStringList(locations);
        dest.writeStringList(stimes);
        dest.writeStringList(etimes);
        dest.writeStringList(types);
    }

    @Override
    public void onClick(View v) {
        if (v == button)
        {
            SubjectInterface comm;
            comm = (SubjectInterface) getActivity();
            comm.deleteSubject(id);
        }
    }
}
