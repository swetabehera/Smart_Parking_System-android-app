package com.example.smartparking;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class UserDataUpload extends AppCompatActivity {
TextView txtvw;
    private LinearLayout parentLinearLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_data_upload);
        txtvw=findViewById(R.id.area);
        parentLinearLayout = (LinearLayout) findViewById(R.id.parent_linear_layout);


        txtvw.setText("registration numbers of vehicles");

    }

    public void addField(View v) {
        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final View rowView = inflater.inflate(R.layout.new_field, null);
        // Add the new row before the add field button.
        parentLinearLayout.addView(rowView, parentLinearLayout.getChildCount() - 1);
        //TO-DO: add a row to areas table
    }

    public void deleteField(View v) {
        // TO DO: delete a row from areas table
        parentLinearLayout.removeView((View) v.getParent());
    }

    public void uploadField(View view) {
    }
}
