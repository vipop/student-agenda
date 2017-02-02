package com.vladpop.studentagenda;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;

import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnTabSelectListener;

import java.util.ArrayList;

public class Activity extends AppCompatActivity implements AddCourseInterface, SubjectInterface {

    private ArrayList<Subject> subjects = new ArrayList<Subject>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity);

        BottomBar bottomBar = (BottomBar) findViewById(R.id.bottomBar);
        bottomBar.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelected(@IdRes int tabId) {
                swapPage(tabId);
            }
        });

    }

    public void swapPage(@IdRes int tabId)
    {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();

        if (tabId == R.id.tab_courses) {
            if (fm.findFragmentByTag("courses") == null)
            {
                System.out.println("CREATE NEW INSTANCE WITH NO SUBJECTS < ------------------------");
                ft.replace(R.id.content, Courses.newInstance(), "courses");
                ft.addToBackStack(null);
                ft.commit();
            }
            else
            {
                System.out.println("USE SAME OLD INSTANCE < ------------------------");
                ft.replace(R.id.content, fm.findFragmentByTag("courses"));
                ft.addToBackStack(null);
                ft.commit();
            }
        }
        else if (tabId == R.id.tab_assignments) {
            ft.replace(R.id.content, Assignments.newInstance());
            ft.addToBackStack(null);
            ft.commit();
        }
        else if (tabId == R.id.tab_agenda) {
            ft.replace(R.id.content, Agenda.newInstance());
            ft.addToBackStack(null);
            ft.commit();
        }
        else if (tabId == R.id.tab_exams) {
            ft.replace(R.id.content, Exams.newInstance());
            ft.addToBackStack(null);
            ft.commit();
        }
        else if (tabId == R.id.tab_grades) {
            ft.replace(R.id.content, Grades.newInstance());
            ft.addToBackStack(null);
            ft.commit();
        }

    }

    @Override
    public void sendCourse(Subject subject) {
        subjects.add(subject);

        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        System.out.println("CREATE NEW INSTANCE WITH ADDED SUBJECT < ------------------------");
        ft.replace(R.id.content, Courses.newInstance(subjects), "courses");
        ft.commit();
    }

    @Override
    public int getNextSubId() {
        return subjects.size();
    }

    @Override
    public void deleteSubject(int id) {
        subjects.remove(id);

        FragmentManager fm = getSupportFragmentManager();
        System.out.println("REMOVE SUBJECT < ------------------------");
        Courses courses = (Courses) fm.findFragmentByTag("courses");
        CoursesAdapter cAdapter = courses.getCAdapter();
        cAdapter.notifyDataSetChanged();
    }
}
