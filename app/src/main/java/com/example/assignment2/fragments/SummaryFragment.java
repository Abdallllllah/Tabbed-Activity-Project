package com.example.assignment2.fragments;

import android.database.Cursor;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.assignment2.MySQliteHelper;
import com.example.assignment2.R;


public class SummaryFragment extends Fragment {
    MySQliteHelper db;
    Button btn1;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        db = new MySQliteHelper(getContext());
        View view = inflater.inflate(R.layout.fragment_summary, container, false);
        btn1 = (Button) view.findViewById(R.id.btn1);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


        Cursor res = db.getdata();
        if(res.getCount()==0){
            Toast.makeText(getContext(), "No Entry Exists", Toast.LENGTH_SHORT).show();
            return;
        }
        StringBuffer buffer = new StringBuffer();
        while(res.moveToNext()){
            buffer.append("First Name :"+res.getString(0)+"\n");
            buffer.append("ID Number :"+res.getString(1)+"\n");
            buffer.append("Major :"+res.getString(2)+"\n");
            buffer.append("Total Fees :"+Double.parseDouble(res.getString(3))+"\n");
            buffer.append("Fees Balance :"+Double.parseDouble(res.getString(4))+"\n");
            buffer.append("Clearance Date :"+res.getString(5)+"\n\n");
        }

        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setCancelable(true);
        builder.setTitle("Entries");
        builder.setMessage(buffer.toString());
        builder.show();
            }
        });

        return view;
    }


}


