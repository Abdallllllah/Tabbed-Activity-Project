package com.example.assignment2.fragments;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.assignment2.MainActivity;
import com.example.assignment2.MySQliteHelper;
import com.example.assignment2.R;



public class RegisterFragment extends Fragment {
    EditText edt_userfname,edt_userlname, edt_userid, edt_major;
    // Context context=this;
    MySQliteHelper mysqlDbHelper;
    SQLiteDatabase sqLiteDatabase;
    Button btn_add;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_register,container,false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        edt_userfname = (EditText)view.findViewById(R.id.edt_userfname);
        edt_userlname = (EditText)view.findViewById(R.id.edt_userlname);
        edt_userid  = (EditText)view.findViewById(R.id.edt_userid);
        edt_major  = (EditText)view.findViewById(R.id.edt_major);
        btn_add = (Button)view.findViewById(R.id.btn_add);
        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                register();
            }
        });
    }
    public void register()
    {
        String fname=edt_userfname.getText().toString();
        String lname=edt_userlname.getText().toString();
        String userid=edt_userid.getText().toString();
        String major=edt_major.getText().toString();

        mysqlDbHelper = new MySQliteHelper(getContext());

        Boolean checkinsertdata = mysqlDbHelper.insertuserdata(fname, lname, userid, major);
        if(checkinsertdata==true)
            Toast.makeText(getContext(), "New Entry Inserted", Toast.LENGTH_SHORT).show();
        else
            Toast.makeText(getContext(), "New Entry Not Inserted", Toast.LENGTH_SHORT).show();

        mysqlDbHelper.close();
    }
}