package com.example.smartparking;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;
import java.util.concurrent.TimeUnit;

public class UserLoginActivity extends AppCompatActivity {

  EditText vehicleNo, phnNo, password;
  Button generateOtp, verifyPhoneNumber;
    PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallback;
    FirebaseAuth auth;
    String phoneNumber,verificationCode,otp;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_login);

     verifyPhoneNumber=findViewById(R.id.verify);
     generateOtp=findViewById(R.id.gen_otp);
     vehicleNo=findViewById(R.id.vehicleno);
     phnNo=findViewById(R.id.phn_num);
     password=findViewById(R.id.password);

        registerReceiver(receiver2,new IntentFilter("Message Receiver"));
        if(ContextCompat.checkSelfPermission(getBaseContext(),"android.permission.RECEIVE_SMS")!= PackageManager.PERMISSION_GRANTED)
        {
            int CODEREQ = 124;
            ActivityCompat.requestPermissions(this,new String[]{"android.permission.RECEIVE_SMS"},CODEREQ);
        }

        if(ContextCompat.checkSelfPermission(getBaseContext(),"android.permission.READ_SMS")!= PackageManager.PERMISSION_GRANTED)
        {
            int CODEREQ = 123;
            ActivityCompat.requestPermissions(this,new String[]{"android.permission.READ_SMS"},CODEREQ);
        }

     //   findViews();
        StartFirebaseLogin();

        password.setInputType(InputType.TYPE_NULL);

        generateOtp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                phoneNumber=phnNo.getText().toString();
                PhoneAuthProvider.getInstance().verifyPhoneNumber(
                        phoneNumber,                     // Phone number to verify
                        60,                           // Timeout duration
                        TimeUnit.SECONDS,                // Unit of timeout
                        UserLoginActivity.this,        // Activity (for callback binding)
                        mCallback);                      // OnVerificationStateChangedCallbacks
            }
        });
        //Above method will send an SMS to the provided phone number. As verifyPhoneNumber() is reentrant, it will not send another SMS on button click until the original request is timed out.

        password.setInputType(InputType.TYPE_CLASS_TEXT);
        password.requestFocus();
        InputMethodManager mgr = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        mgr.showSoftInput(password, InputMethodManager.SHOW_FORCED);


        verifyPhoneNumber.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 otp=password.getText().toString();
                PhoneAuthCredential credential = PhoneAuthProvider.getCredential(verificationCode, otp);
                SigninWithPhone(credential);
            }
        });
    }
    private void SigninWithPhone(PhoneAuthCredential credential) {
        auth.signInWithCredential(credential)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Intent i =new Intent(UserLoginActivity.this,UserDashboard.class);
                           // i.putExtra("website",website.getText().toString());
                            startActivity(i);
                            finish();
                        } else {
                            Toast.makeText(UserLoginActivity.this,"Incorrect OTP",Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }





    private void StartFirebaseLogin() {
        auth = FirebaseAuth.getInstance();
        mCallback = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
            @Override
            public void onVerificationCompleted(PhoneAuthCredential phoneAuthCredential) {
                Toast.makeText(UserLoginActivity.this,"verification completed",Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onVerificationFailed(FirebaseException e) {
                Toast.makeText(UserLoginActivity.this,"verification failed",Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onCodeSent(String s, PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                super.onCodeSent(s, forceResendingToken);
                verificationCode = s;
                Toast.makeText(UserLoginActivity.this,"Code sent",Toast.LENGTH_SHORT).show();
            }
        };
    }
    BroadcastReceiver receiver2 = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            try
            {
                if(intent !=null && intent.getExtras()!=null && intent.getStringExtra("msgs")!=null)
                {
                    final String message = intent.getStringExtra("msgs");
                    String otp = message.replaceAll("\\D+","");
                    password.setText(otp);
                }

            }
            catch (Exception e)
            {
                e.printStackTrace();
                //Toast.makeText(this,"ERROR!!",Toast.LENGTH_SHORT).show();
            }
        }
    };

    @Override
    public void onBackPressed() {
        super.onBackPressed();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(receiver2);

    }
}