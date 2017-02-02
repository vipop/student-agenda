package com.vladpop.studentagenda;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class Courses extends Fragment implements View.OnClickListener {

    //private static final String ARG_PARAM1 = "arg1";
    private RecyclerView recyclerView;
    private CoursesAdapter cAdapter;
    private ArrayList<Subject> subjects;

    private LinearLayout sub1;
    private LinearLayout sub2;
    private LinearLayout sub3;
    private LinearLayout sub4;
    private LinearLayout sub5;
    private LinearLayout sub6;
    private LinearLayout sub7;
    private LinearLayout sub8;
    private LinearLayout sub9;
    private LinearLayout sub10;

    public Courses() {
        // Required empty public constructor
    }

    public static Courses newInstance() {
        Courses courses = new Courses();
        Bundle bundle = new Bundle();
        bundle.putInt("flag", 0);
        courses.setArguments(bundle);
        return courses;
    }

    public static Courses newInstance(ArrayList<Subject> subs) {
        Courses courses = new Courses();

        Bundle bundle = new Bundle();
        bundle.putInt("flag", 1);
        bundle.putSerializable("subjects", subs);
        courses.setArguments(bundle);

        return courses;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        subjects = new ArrayList<Subject>();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View inflatedView = inflater.inflate(R.layout.fragment_courses, container, false);

        FloatingActionButton addSubject = (FloatingActionButton) inflatedView.findViewById(R.id.add_subject);
        addSubject.setOnClickListener(this);

        int flag = getArguments().getInt("flag");

        if (flag == 1)
        {
            subjects = getArguments().getParcelableArrayList("subjects");
            if (subjects != null) {
                recyclerView = (RecyclerView) inflatedView.findViewById(R.id.recycler_view);

                cAdapter = new CoursesAdapter(subjects);
                RecyclerView.LayoutManager cLayoutManager = new LinearLayoutManager(getActivity());
                recyclerView.setLayoutManager(cLayoutManager);
                recyclerView.setItemAnimator(new DefaultItemAnimator());
                recyclerView.setAdapter(cAdapter);
            }
        }

        return inflatedView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onClick(View v) {
        System.out.println("BUTTON CLICKED < -----------------------------------");
        FragmentManager fm = getActivity().getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.content, AddCourse.newInstance());
        ft.addToBackStack(null);
        ft.commit();
    }

    public ArrayList<Subject> getSubjects()
    {
        return subjects;
    }

    public CoursesAdapter getCAdapter()
    {
        return cAdapter;
    }
}
