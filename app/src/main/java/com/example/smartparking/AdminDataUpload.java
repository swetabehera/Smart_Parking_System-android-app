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

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class AdminDataUpload extends AppCompatActivity {
    private LinearLayout parentLinearLayout;
    String pushingIdAdmin;
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

        pushingIdAdmin= adminRef.push().getKey();
        adminId=pushingIdAdmin;

        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                adminObject.setKey(pushingIdAdmin);
                adminObject.setName(name.getText().toString());
                Log.v("retrieval","reached here1");

                if (chk1Area())
                {
                    Log.v("retrieval","reached here");

                    adminRef.child(pushingIdAdmin).setValue(adminObject);

                    Toast.makeText(getApplicationContext(), "UPLOADED DATA TO DB", Toast.LENGTH_SHORT).show();

                    Intent i = new Intent(AdminDataUpload.this, AdminDashboard.class);
                    startActivity(i);
                } else {
                    Log.v("retrieval","reached here else");

                    Toast.makeText(AdminDataUpload.this, "Give at least 1 area", Toast.LENGTH_SHORT).show();

                }
            }
        });

    }
boolean x;
     boolean chk1Area() {

x=false;
        parkingSlotRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
              //  Log.v("retrieval","error here");

               for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                   ParkingSlotObject value = postSnapshot.getValue(ParkingSlotObject.class);
                   Log.v("retrieval", "error" + value.getNameOfArea()+"\n"+value.getAdminId()+"\n"+adminId);

                   x=value.getAdminId().equals(adminId);
                   if (x)
                   {
                       adminRef.child(pushingIdAdmin).setValue(adminObject);

                       Toast.makeText(getApplicationContext(), "UPLOADED DATA TO DB", Toast.LENGTH_SHORT).show();

                       Intent i = new Intent(AdminDataUpload.this, AdminDashboard.class);
                       startActivity(i);

                       break;
                    }
               }

                if(x)
                Log.v("retrieval returned:","h true" );
else
                Log.v("retrieval returned:","false h" );



            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(AdminDataUpload.this, "error in retrieving", Toast.LENGTH_SHORT).show();
                Log.v("retrieval"," Retreival error"+databaseError);


            }
        });
if(x)
Log.v("retrieval returned:","true" );
else
    Log.v("retrieval returned:","false" );

    return x;
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
     void deleteParkingSlot(View v)
    {
        // TO DO: delete a row from areas table
        LinearLayout view = (LinearLayout)v.getParent();

        EditText etd = view.findViewById(R.id.area_edit_text);
        String name=etd.getText().toString();

        DatabaseReference dtl=FirebaseDatabase.getInstance().getReference(parkingSlot).child(name+"__");
        dtl.removeValue();
        Toast.makeText(this, "area node deleted", Toast.LENGTH_SHORT).show();

    }


    public void deleteField(View v) {

    deleteParkingSlot(v);
    parentLinearLayout.removeView((View) v.getParent());
    }

    public void uploadField(View v) {
       // v.setVisibility(View.INVISIBLE);

        LinearLayout view = (LinearLayout)v.getParent();

        EditText etd = view.findViewById(R.id.area_edit_text);
        String name=etd.getText().toString();

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
            parkingSlotRef.child(name+"__").setValue(parkingSlotObject);
        v.setVisibility(View.INVISIBLE);
            Toast.makeText(this, "area uploaded", Toast.LENGTH_SHORT).show();}else
        {
            v.setVisibility(View.VISIBLE);
            uploadField(v);
        }

        Log.v("uploading area","child created");


    }
}
