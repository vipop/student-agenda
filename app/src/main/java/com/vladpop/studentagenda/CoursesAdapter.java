package com.vladpop.studentagenda;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Vlad Pop on 1/12/2017.
 */

public class CoursesAdapter extends RecyclerView.Adapter<CoursesAdapter.MyViewHolder> implements View.OnClickListener {

    ArrayList<Subject> subjects;

    public CoursesAdapter(ArrayList<Subject> subjects)
    {
        this.subjects = subjects;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView nameText;
        TextView codeText;
        TextView professorText;

        RelativeLayout monday;
        TextView monTypeText;
        TextView monSTimeText;
        TextView monETimeText;
        TextView monLocationText;

        RelativeLayout tuesday;
        TextView tueTypeText;
        TextView tueSTimeText;
        TextView tueETimeText;
        TextView tueLocationText;

        RelativeLayout wednesday;
        TextView wedTypeText;
        TextView wedSTimeText;
        TextView wedETimeText;
        TextView wedLocationText;

        RelativeLayout thursday;
        TextView thuTypeText;
        TextView thuSTimeText;
        TextView thuETimeText;
        TextView thuLocationText;

        RelativeLayout friday;
        TextView friTypeText;
        TextView friSTimeText;
        TextView friETimeText;
        TextView friLocationText;

        ImageButton button;


        public MyViewHolder(View view) {
            super(view);
            nameText = (TextView) view.findViewById(R.id.sub_name);
            codeText = (TextView) view.findViewById(R.id.sub_code);
            professorText = (TextView) view.findViewById(R.id.sub_prof);

            monday = (RelativeLayout) view.findViewById(R.id.monday);
            monTypeText = (TextView) view.findViewById(R.id.sub_mon_type);
            monSTimeText = (TextView) view.findViewById(R.id.sub_mon_stime);
            monETimeText = (TextView) view.findViewById(R.id.sub_mon_etime);
            monLocationText = (TextView) view.findViewById(R.id.sub_mon_loc);

            tuesday = (RelativeLayout) view.findViewById(R.id.tuesday);
            tueTypeText = (TextView) view.findViewById(R.id.sub_tue_type);
            tueSTimeText = (TextView) view.findViewById(R.id.sub_tue_stime);
            tueETimeText = (TextView) view.findViewById(R.id.sub_tue_etime);
            tueLocationText = (TextView) view.findViewById(R.id.sub_tue_loc);

            wednesday = (RelativeLayout) view.findViewById(R.id.wednesday);
            wedTypeText = (TextView) view.findViewById(R.id.sub_wed_type);
            wedSTimeText = (TextView) view.findViewById(R.id.sub_wed_stime);
            wedETimeText = (TextView) view.findViewById(R.id.sub_wed_etime);
            wedLocationText = (TextView) view.findViewById(R.id.sub_wed_loc);

            thursday = (RelativeLayout) view.findViewById(R.id.thursday);
            thuTypeText = (TextView) view.findViewById(R.id.sub_thu_type);
            thuSTimeText = (TextView) view.findViewById(R.id.sub_thu_stime);
            thuETimeText = (TextView) view.findViewById(R.id.sub_thu_etime);
            thuLocationText = (TextView) view.findViewById(R.id.sub_thu_loc);

            friday = (RelativeLayout) view.findViewById(R.id.friday);
            friTypeText = (TextView) view.findViewById(R.id.sub_fri_type);
            friSTimeText = (TextView) view.findViewById(R.id.sub_fri_stime);
            friETimeText = (TextView) view.findViewById(R.id.sub_fri_etime);
            friLocationText = (TextView) view.findViewById(R.id.sub_fri_loc);

            button = (ImageButton) view.findViewById(R.id.btn_delete);

        }
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_subject, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Subject sub = subjects.get(position);

        String name = sub.getName();
        String code = sub.getCode();
        String professor = sub.getProfessor();

        ArrayList<String> days = sub.getDays();
        ArrayList<String> locations = sub.getLocations();
        ArrayList<String> stimes = sub.getSTimes();;
        ArrayList<String> etimes = sub.getETimes();;
        ArrayList<String> types = sub.getTypes();;

        if (name.equals(""))
        {
            holder.nameText.setVisibility(View.GONE);
        }
        else
        {
            holder.nameText.setText(name);
            holder.nameText.setVisibility(View.VISIBLE);
        }

        if (code.equals(""))
        {
            holder.codeText.setVisibility(View.GONE);
        }
        else
        {
            holder.codeText.setText("(" + code.toUpperCase() + ")");
            holder.codeText.setVisibility(View.VISIBLE);
        }

        if (professor.equals(""))
        {
            holder.professorText.setVisibility(View.GONE);
        }
        else
        {
            holder.professorText.setText(capitalize(professor));
            holder.professorText.setVisibility(View.VISIBLE);
        }

        for (int i = 0; i < days.size(); i++)
        {
            if (days.get(i).equals("monday"))
            {
                holder.monTypeText.setText(types.get(i));
                holder.monSTimeText.setText(stimes.get(i));
                holder.monETimeText.setText(etimes.get(i));
                holder.monLocationText.setText(locations.get(i));

                holder.monday.setVisibility(View.VISIBLE);

            }
            else if (days.get(i).equals("tuesday"))
            {
                holder.tueTypeText.setText(types.get(i));
                holder.tueSTimeText.setText(stimes.get(i));
                holder.tueETimeText.setText(etimes.get(i));
                holder.tueLocationText.setText(locations.get(i));

                holder.tuesday.setVisibility(View.VISIBLE);

            }
            else if (days.get(i).equals("wednesday"))
            {
                holder.wedTypeText.setText(types.get(i));
                holder.wedSTimeText.setText(stimes.get(i));
                holder.wedETimeText.setText(etimes.get(i));
                holder.wedLocationText.setText(locations.get(i));

                holder.wednesday.setVisibility(View.VISIBLE);

            }
            else if (days.get(i).equals("thursday"))
            {
                holder.thuTypeText.setText(types.get(i));
                holder.thuSTimeText.setText(stimes.get(i));
                holder.thuETimeText.setText(etimes.get(i));
                holder.thuLocationText.setText(locations.get(i));

                holder.thursday.setVisibility(View.VISIBLE);

            }
            else if (days.get(i).equals("friday"))
            {
                holder.friTypeText.setText(types.get(i));
                holder.friSTimeText.setText(stimes.get(i));
                holder.friETimeText.setText(etimes.get(i));
                holder.friLocationText.setText(locations.get(i));

                holder.friday.setVisibility(View.VISIBLE);

            }
        }

        holder.button.setTag(position);
        holder.button.setOnClickListener(this);

    }

    public String capitalize(String s)
    {
        StringBuilder sb = new StringBuilder(s);
        sb.setCharAt(0, Character.toUpperCase(sb.charAt(0)));

        return sb.toString();
    }

    @Override
    public int getItemCount() {
        return subjects.size();
    }

    @Override
    public void onClick(View v) {
        SubjectInterface comm;
        comm = (SubjectInterface) v.getContext();
        comm.deleteSubject((int) v.getTag());

    }
}
