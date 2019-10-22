package com.example.smartparking;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {
EditText mEmail,mPassword;

Button email_sign_in_button,email_login_button;
String TAG = "AdmmnLOginActivity";
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_login);

Intent i = getIntent();
final String title = i.getStringExtra("title");

setTitle(title+" Authentication");
//getSupportActionBar().setTitle(title);
        FirebaseApp.initializeApp(getApplicationContext());
    email_login_button = findViewById(R.id.login);

    // Set up the login form.
    mEmail = findViewById(R.id.username);
    //  populateAutoComplete();

    mPassword = findViewById(R.id.password);


    email_sign_in_button = findViewById(R.id.sign_in);

    mAuth = FirebaseAuth.getInstance();


        email_sign_in_button.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            final String email = mEmail.getText().toString().trim();
            String password = mPassword.getText().toString().trim();

            mAuth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {

                            if (task.isSuccessful()) {
                                // Sign in success, update UI with the signed-in user's information
                                Log.d(TAG, "createUserWithEmail:success");
                                // FirebaseUser user = mAuth.getCurrentUser();

                                Toast.makeText(getApplicationContext(), "SUCCESSFUL REGISTRATION", Toast.LENGTH_SHORT).show();

                                //data entry
                                if(title.equalsIgnoreCase("admin")) {
                                    Intent intent = new Intent(LoginActivity.this, AdminDataUpload.class);
                                   intent.putExtra("email",email);
                                   startActivity(intent);
                                }
                                else{
                                    Intent intent = new Intent(LoginActivity.this, UserDataUpload.class);
                                    intent.putExtra("email",email);
                                    startActivity(intent);

                                }

                            } else {
                                // If sign in fails, display a message to the user.
                                Log.w(TAG, "createUserWithEmail:failure", task.getException());
                                Toast.makeText(LoginActivity.this, "Authentication failed.",
                                        Toast.LENGTH_SHORT).show();

                            }

                            // ...
                        }
                    });
        }
    });

        email_login_button.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            final String email = mEmail.getText().toString();
            String password = mPassword.getText().toString();

            mAuth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener( new OnCompleteListener<AuthResult>() {// here ProfloinActivity.this darkar??
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                // Sign in success, update UI with the signed-in user's information
                                Log.d(TAG, "signInWithEmail:success");
                                Toast.makeText(LoginActivity.this, "Authentication successful.",
                                        Toast.LENGTH_SHORT).show();

                                FirebaseUser user = mAuth.getCurrentUser();
                                if(title.equalsIgnoreCase("admin")) {
                                    Intent intent = new Intent(LoginActivity.this, AdminDashboard.class);
                                    intent.putExtra("email",email);
                                    startActivity(intent);
                                }
                                else{
                                    Intent intent = new Intent(LoginActivity.this, UserDashboard.class);
                                    intent.putExtra("email",email);
                                    startActivity(intent);

                                }
                            } else {
                                // If sign in fails, display a message to the user.
                                Log.w(TAG, "signInWithEmail:failure", task.getException());
                                Toast.makeText(LoginActivity.this, "Authentication failed.",
                                        Toast.LENGTH_SHORT).show();

                            }

                            // ...
                        }
                    });


        }
    });


}
}
