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



public class FeesFragment extends Fragment {
    EditText edt_tfees,edt_pfees, edt_userid, edt_bfees, edt_cdate;
    // Context context=this;
    MySQliteHelper mysqlDbHelper;
    SQLiteDatabase sqLiteDatabase;
    Button btn_add;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_fees,container,false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        edt_bfees = (EditText)view.findViewById(R.id.edt_bfees);
        edt_pfees = (EditText)view.findViewById(R.id.edt_pfees);
        edt_userid  = (EditText)view.findViewById(R.id.edt_userid);
        edt_cdate  = (EditText)view.findViewById(R.id.edt_cdate);
        edt_tfees  = (EditText)view.findViewById(R.id.edt_tfees);
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
        String idnum=edt_userid.getText().toString();
        String tfees=edt_tfees.getText().toString();
        String pfees=edt_pfees.getText().toString();
        String bfees=edt_bfees.getText().toString();
        String c_date=edt_cdate.getText().toString();

        mysqlDbHelper = new MySQliteHelper(getContext());

        Boolean checkinsertdata = mysqlDbHelper.insertfees(idnum, tfees, pfees, bfees,c_date);
        if(checkinsertdata==true)
            Toast.makeText(getContext(), "New Entry Inserted", Toast.LENGTH_SHORT).show();
        else
            Toast.makeText(getContext(), "New Entry Not Inserted", Toast.LENGTH_SHORT).show();

        mysqlDbHelper.close();
    }
}