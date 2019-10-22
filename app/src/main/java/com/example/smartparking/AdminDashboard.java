package com.example.smartparking;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class AdminDashboard extends AppCompatActivity {
    DatabaseReference adminRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_dashboard);
        Intent i=getIntent();
        String adminPath = i.getStringExtra("adminkey");
        Log.v("retrieval","reached here path"+adminPath);
if(adminPath==null) {
    String email = i.getStringExtra("email");
    String newemail=email.replace('.','_');
    Log.v("retrieval", "reached here email" + newemail);

    adminRef = FirebaseDatabase.getInstance().getReference("ADMIN/"+newemail);

}
else {
    adminRef = FirebaseDatabase.getInstance().getReference("ADMIN/" + adminPath);
}
        adminRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                AdminObject value = dataSnapshot.getValue(AdminObject.class);
                Log.d("retrieval", "Value is: " + value.getName()+"\n"+value.getKey());
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w("retrieval", "Failed to read value.", error.toException());
            }
        });

    }
}
