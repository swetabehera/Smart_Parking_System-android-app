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
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class UserDataUpload extends AppCompatActivity {
TextView txtvw;
    private LinearLayout parentLinearLayout;

    String pushingIdUser;
    Button add,del,done,upload;
    private DatabaseReference
            vehRef,userRef;

    EditText name;
    String
            vehicle="VEHICLE",
                user="USER";

    UserObject userObject;
    VehicleObj vehicleObj;
    private String userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_data_upload);
        txtvw=findViewById(R.id.area);
        parentLinearLayout = (LinearLayout) findViewById(R.id.parent_linear_layout);

        userRef= FirebaseDatabase.getInstance().getReference("USER");

        vehRef=FirebaseDatabase.getInstance().getReference(vehicle);




        txtvw.setText("Registration Numbers of Different Vehicles");


        name=findViewById(R.id.name_edit);
        upload=findViewById(R.id.upload_button);
        done= findViewById(R.id.button_done);
        add=findViewById(R.id.add_field_button);
        del=findViewById(R.id.delete_button);

        userObject= new UserObject();
        vehicleObj= new VehicleObj();

        pushingIdUser= userRef.push().getKey();
        userId=pushingIdUser;

        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                userObject.setKey(pushingIdUser);
                userObject.setName(name.getText().toString());
                Log.v("retrieval","reached here1");

                if (true)//chk1Vehicle())
                {
                    Log.v("retrieval","reached here");
                    Log.v("retrieval","reached there"+userObject.getKey()+" \t"+userObject.getName());

                    userRef.child(pushingIdUser).setValue(userObject);

                    Toast.makeText(getApplicationContext(), "UPLOADED DATA TO DB", Toast.LENGTH_SHORT).show();

                    Intent i = new Intent(UserDataUpload.this, UserDashboard.class);
                    startActivity(i);
                } else {
                    Log.v("retrieval","reached here else");

                    Toast.makeText(UserDataUpload.this, "Give at least 1 vehicle", Toast.LENGTH_SHORT).show();

                }
            }
        });


    }

    boolean x;
    boolean chk1Vehicle() {

        x = false;
        vehRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                //  Log.v("retrieval","error here");

                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    ParkingSlotObject value = postSnapshot.getValue(ParkingSlotObject.class);
                    Log.v("retrieval", "error" + value.getNameOfArea() + "\n" + value.getAdminId() + "\n" + userId);

                    x = value.getAdminId().equals(userId);
                    if (x) {
                        userRef.child(pushingIdUser).setValue(userObject);

                        Toast.makeText(getApplicationContext(), "UPLOADED DATA TO DB", Toast.LENGTH_SHORT).show();

                        Intent i = new Intent(UserDataUpload.this, AdminDashboard.class);
                        startActivity(i);

                        break;
                    }
                }

                if (x)
                    Log.v("retrieval returned:", "h true");
                else
                    Log.v("retrieval returned:", "false h");


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(UserDataUpload.this, "error in retrieving", Toast.LENGTH_SHORT).show();
                Log.v("retrieval", " Retreival error" + databaseError);


            }
        });
        if(x)
            Log.v("retrieval returned:","true" );
        else
            Log.v("retrieval returned:","false" );

        return x;
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
        deleteVehicle(v);
        parentLinearLayout.removeView((View) v.getParent());
        
    }

    public void uploadField(View v) {
        LinearLayout view = (LinearLayout)v.getParent();

        EditText etd = view.findViewById(R.id.area_edit_text);
        String name=etd.getText().toString();

        Log.v("uploading area",etd.getText().toString()+" hela?");

        String pushingId = vehRef.push().getKey();
        Log.v("uploading area",pushingId+" push id hela?");

        vehicleObj.setVehId(pushingId);
        vehicleObj.setVehno(name);
        vehicleObj.setUserId(userId);

        Log.v("uploading area",vehicleObj.getUserId()+"\n"+vehicleObj.getVehId()+"\n"+vehicleObj.getVehno()+"\n"+vehicleObj.getDimension());

        vehRef.child(name+userId+"__").setValue(vehicleObj);//name+adminid+__
        v.setVisibility(View.INVISIBLE);
        Toast.makeText(this, "area uploaded", Toast.LENGTH_SHORT).show();
        Log.v("uploading area","child created");

    }


    void deleteVehicle(View v)
    {
        // TO DO: delete a row from areas table
        LinearLayout view = (LinearLayout)v.getParent();

        EditText etd = view.findViewById(R.id.area_edit_text);
        String name=etd.getText().toString();

        DatabaseReference dtl=FirebaseDatabase.getInstance().getReference(vehicle).child(name+userId+"__");
        dtl.removeValue();
        Toast.makeText(this, "area node deleted", Toast.LENGTH_SHORT).show();

    }
}
