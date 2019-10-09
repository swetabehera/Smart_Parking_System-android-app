package com.example.smartparking;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AdminDataUpload extends AppCompatActivity {
    private LinearLayout parentLinearLayout;
    Button add,del,done,upload;
    private DatabaseReference
            //vehRef,userRef,
            adminRef, parkingSlotRef
            //,vehParkedRef,userParkedRef
            ;

    EditText name,area;
   String
            //vehicle="VEHICLE",
    adminId,
    //user="USER",
    admin="ADMIN",
    parkingSlot="PARKING_SLOT";
    //vehParked="VEHICLE_PARKED",
    //userParked="USER_PARKED";

AdminObject adminObject;
ParkingSlotObject parkingSlotObject;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_data_upload);

//SOLVED error in these2 lines by upgrading the dependency
       adminRef= FirebaseDatabase.getInstance().getReference(admin);

        parkingSlotRef=FirebaseDatabase.getInstance().getReference(parkingSlot);


        area=findViewById(R.id.area_edit_text);
        name=findViewById(R.id.name_edit);
        upload=findViewById(R.id.upload_button);
        done= findViewById(R.id.button_done);
        add=findViewById(R.id.add_field_button);
        del=findViewById(R.id.delete_button);
        parentLinearLayout = (LinearLayout) findViewById(R.id.parent_linear_layout);

        adminObject = new AdminObject();
        parkingSlotObject= new ParkingSlotObject();
       // uploadParkSlot();

        final String pushingIdAdmin= adminRef.push().getKey();
        adminId=pushingIdAdmin;

        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.v("ethi","button pressed");

                Log.v("ethi","key extracted"+pushingIdAdmin);


                adminObject.setKey(pushingIdAdmin);
                adminObject.setName(name.getText().toString());
                Log.v("ethi","values assigned");


                Log.v("ethi",adminObject.getKey()+"\n"+adminObject.getName()+"\n"+adminObject.getAvgUsage()+"\n"+adminObject.getPricePerHour());

                adminRef.child(pushingIdAdmin).setValue(adminObject);
                Log.v("ethi","child created");


                Toast.makeText(getApplicationContext(),"UPLOADED DATA TO DB",Toast.LENGTH_SHORT).show();

                Intent i= new Intent(AdminDataUpload.this,AdminDashboard.class);
                //  i.putExtra("new",true);
                startActivity(i);


            }
        });

    }
/*
    private void uploadParkingSlot() {
        String pushingId = parkingSlotRef.push().getKey();

        parkingSlotObject.setKey(pushingId);
        parkingSlotObject.setName(area.getText().toString());
        parkingSlotObject.setAdminId(adminId);

        *//*
        !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
        by location tracker gps
        parkingObj.setLocation()  use kar k values dena h.......................................

        by ml area sense karna h
        use
        parkingObj.setArea()
*//*
        parkingSlotRef.child(area.getText().toString()).setValue(parkingSlotObject);


    }*/


    //different table for areas;
    public void addField(View v) {

        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        final View rowView = inflater.inflate(R.layout.new_field, null);
        // Add the new row before the add field button.

        parentLinearLayout.addView(rowView, parentLinearLayout.getChildCount() - 1);
        //TO-DO: add a row to areas table

      //  uploadParkSlot();



    }
     /*void deleteParkingSlot(View v)
    {
        // TO DO: delete a row from areas table
        EditText edt= v.findViewById(R.id.area_edit_text);

        DatabaseReference dtl=FirebaseDatabase.getInstance().getReference(parkingSlot).child(edt.getText().toString());
        dtl.removeValue();
        Toast.makeText(this, "area node deleted", Toast.LENGTH_SHORT).show();

    }*/


    public void deleteField(View v) {

    //deleteParkingSlot(v);
    parentLinearLayout.removeView((View) v.getParent());
    }

    public void uploadField(View v) {
       // v.setVisibility(View.INVISIBLE);

        LinearLayout view = (LinearLayout)v.getParent();

        EditText etd = view.findViewById(R.id.area_edit_text);
        String name=etd.getText().toString();
        if(name==null)
            Toast.makeText(this, "Name of the area is required!", Toast.LENGTH_SHORT).show();

        Log.v("uploading area",etd.getText().toString()+" hela?");

        String pushingId = parkingSlotRef.push().getKey();
        Log.v("uploading area",pushingId+" push id hela?");

        parkingSlotObject.setKey(pushingId);
        parkingSlotObject.setNameOfArea(name);
        parkingSlotObject.setAdminId(adminId);

        Log.v("uploading area",parkingSlotObject.getAdminId()+"\n"+parkingSlotObject.getKey()+"\n"+parkingSlotObject.getNameOfArea()+"\n"+parkingSlotObject.getLocation());

        /*
        !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
        by location tracker gps
        parkingObj.setLocation()  use kar k values dena h.......................................

        by ml area sense karna h
        use
        parkingObj.setArea()
*/
        //error here!!!!!!!!!! admin obj accept karuchi but parking slot nuhain ...authare obj baneili

        if(name!=null) {
            parkingSlotRef.child(name).setValue(parkingSlotObject);
        v.setVisibility(View.INVISIBLE);}else
        {
            v.setVisibility(View.VISIBLE);
            uploadField(v);
        }

        Log.v("uploading area","child created");


    }
}
