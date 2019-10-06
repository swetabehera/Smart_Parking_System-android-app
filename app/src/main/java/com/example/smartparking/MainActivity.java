package com.example.smartparking;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;


public class MainActivity extends AppCompatActivity {
    Button admin,user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        admin=findViewById(R.id.admin_login_button);
        user= findViewById(R.id.user_login_button);

        admin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, AdminLoginActivity.class);
                startActivity(i);

            }
        });

        user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i =new Intent(MainActivity.this, UserLoginActivity.class);
                startActivity(i);
            }
        });
    }
}