package com.example.smartparking;

import android.content.Context;
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
    Button add,del,done;
    private DatabaseReference
            //vehRef,userRef,
            adminRef, parkingSlotRef
            //,vehParkedRef,userParkedRef
            ;

    EditText name,area;
   public static String
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

//error in these2 lines
       adminRef= FirebaseDatabase.getInstance().getReference(admin);

        parkingSlotRef=FirebaseDatabase.getInstance().getReference(parkingSlot);

        //but these r not null references

if(adminRef==null )
   // Toast.makeText(this, "null references", Toast.LENGTH_LONG).show();
    Log.v("ethi","null references");
else
    Log.v(("this"),"not null id refen");

        area=findViewById(R.id.area_edit_text);
        name=findViewById(R.id.name_edit);
        done= findViewById(R.id.button_done);
        add=findViewById(R.id.add_field_button);
        /*add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addField(view);
            }
        });
*/

        del=findViewById(R.id.delete_button);
  /*      del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deleteField(view);
            }
        });
  */

        parentLinearLayout = (LinearLayout) findViewById(R.id.parent_linear_layout);

        adminObject = new AdminObject();
        parkingSlotObject= new ParkingSlotObject();
       // uploadParkSlot();

/*
        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String pushingId = adminRef.push().getKey();
                adminId=pushingId;
                adminObject.setKey(pushingId);
                adminObject.setName(name.getText().toString());

                adminRef.child(pushingId).setValue(adminObject);

                Toast.makeText(getApplicationContext(),"UPLOADED DATA TO DB",Toast.LENGTH_SHORT).show();

                Intent i= new Intent(AdminDataUpload.this,AdminDashboard.class);
                //  i.putExtra("new",true);
                startActivity(i);


            }
        });
*/
    }

    private void uploadParkSlot() {
        String pushingId = parkingSlotRef.push().getKey();

        parkingSlotObject.setKey(pushingId);
        parkingSlotObject.setName(area.getText().toString());
        parkingSlotObject.setAdminId(adminId);

        /*
        !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
        by location tracker gps
        parkingObj.setLocation()  use kar k values dena h.......................................

        by ml area sense karna h
        use
        parkingObj.setArea()
*/
        parkingSlotRef.child(area.getText().toString()).setValue(parkingSlotObject);


    }

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
        EditText edt= v.findViewById(R.id.value_edit_text);

        DatabaseReference dtl=FirebaseDatabase.getInstance().getReference(parkingSlot).child(edt.getText().toString());
        dtl.removeValue();
        Toast.makeText(this, "area node deleted", Toast.LENGTH_SHORT).show();

    }

    public void deleteField(View v) {

    //deleteParkingSlot(v);
    parentLinearLayout.removeView((View) v.getParent());
    }
}
