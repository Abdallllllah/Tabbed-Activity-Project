package com.example.assignment2.fragments;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import com.example.assignment2.MainActivity;
import com.example.assignment2.MySQliteHelper;
import com.example.assignment2.R;



public class RegisterFragment extends Fragment {
    EditText edt_userfname,edt_userlname, edt_userid, edt_major;
    // Context context=this;
    MySQliteHelper mysqlDbHelper;
    SQLiteDatabase sqLiteDatabase;
    Button btn_add,btn_delete,btn_update, btn_view;

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
        btn_delete = (Button)view.findViewById(R.id.btn_delete);
        btn_update = (Button)view.findViewById(R.id.btn_update);
        btn_view = (Button)view.findViewById(R.id.btn_view);
        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                register();
            }
        });
        btn_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                update();

            }
        });
        btn_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mysqlDbHelper = new MySQliteHelper(getContext());
                update();
                Cursor res = mysqlDbHelper.getdatar();
                if(res.getCount()==0){
                    Toast.makeText(getContext(), "No Entry Exists", Toast.LENGTH_SHORT).show();
                    return;
                }
                StringBuffer buffer = new StringBuffer();
                while(res.moveToNext()){
                    buffer.append("First Name :"+res.getString(0)+"\n");
                    buffer.append("Last name :"+res.getString(1)+"\n");
                    buffer.append("Use id :"+res.getString(2)+"\n");
                    buffer.append("Major :"+res.getString(3)+"\n\n");
                }

                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setCancelable(true);
                builder.setTitle("Register Entries");
                builder.setMessage(buffer.toString());
                builder.show();

            }
        });
        btn_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mysqlDbHelper = new MySQliteHelper(getContext());
                String nameTXT = edt_userfname.getText().toString();
                Boolean checkudeletedata = mysqlDbHelper.deletedatar(nameTXT);
                if(checkudeletedata==true)
                    Toast.makeText(getContext(), "Entry Deleted", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(getContext(), "Entry Not Deleted", Toast.LENGTH_SHORT).show();

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
    public void update()
    {
        String fname=edt_userfname.getText().toString();
        String lname=edt_userlname.getText().toString();
        String userid=edt_userid.getText().toString();
        String major=edt_major.getText().toString();

        mysqlDbHelper = new MySQliteHelper(getContext());

        Boolean checkinsertdata = mysqlDbHelper.updateuserdataf(fname, lname, userid, major);
        if(checkinsertdata==true)
            Toast.makeText(getContext(), "New Entry updated", Toast.LENGTH_SHORT).show();
        else
            Toast.makeText(getContext(), "New Entry Not updated", Toast.LENGTH_SHORT).show();

        mysqlDbHelper.close();
    }

}