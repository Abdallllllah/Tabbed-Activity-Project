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



public class FeesFragment extends Fragment {
    EditText edt_tfees,edt_pfees, edt_userid, edt_bfees, edt_cdate;
    // Context context=this;
    MySQliteHelper mysqlDbHelper;
    SQLiteDatabase sqLiteDatabase;
    Button btn_add,btn_delete,btn_update,btn_view;

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
        btn_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mysqlDbHelper = new MySQliteHelper(getContext());
                String nameTXT = edt_userid.getText().toString();
                Boolean checkudeletedata = mysqlDbHelper.deletedata(nameTXT);
                if(checkudeletedata==true)
                    Toast.makeText(getContext(), "Entry Deleted", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(getContext(), "Entry Not Deleted", Toast.LENGTH_SHORT).show();
            }
        });
        btn_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mysqlDbHelper = new MySQliteHelper(getContext());
                update();
                Cursor res = mysqlDbHelper.getdataf();
                if(res.getCount()==0){
                    Toast.makeText(getContext(), "No Entry Exists", Toast.LENGTH_SHORT).show();
                    return;
                }
                StringBuffer buffer = new StringBuffer();
                while(res.moveToNext()){
                    buffer.append("ID :"+res.getString(0)+"\n");
                    buffer.append("Total Fees :"+Double.parseDouble(res.getString(1))+"\n");
                    buffer.append("Fees paid :"+Double.parseDouble(res.getString(2))+"\n");
                    buffer.append("Fees balance :"+Double.parseDouble(res.getString(3))+"\n");
                    buffer.append("Clearance data :"+res.getString(4)+"\n\n");
                }

                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setCancelable(true);
                builder.setTitle("Fees Entries");
                builder.setMessage(buffer.toString());
                builder.show();
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
    public  void update(){
        mysqlDbHelper = new MySQliteHelper(getContext());
        String id=edt_userid.getText().toString();
        String tfees=edt_tfees.getText().toString();
        String pfees=edt_pfees.getText().toString();
        String bfees=edt_bfees.getText().toString();
        String cdate=edt_cdate.getText().toString();



        Boolean checkinsertdata = mysqlDbHelper.updateuserdata(id, tfees, pfees, bfees,cdate);
        if(checkinsertdata==true)
            Toast.makeText(getContext(), "New Entry updated", Toast.LENGTH_SHORT).show();
        else
            Toast.makeText(getContext(), "New Entry Not updated", Toast.LENGTH_SHORT).show();

        mysqlDbHelper.close();
    }
}