package com.example.mychatapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.firebase.FirebaseException;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;
import com.hbb20.CountryCodePicker;

import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {


    EditText mgetphonenumber;
    android.widget.Button msendotp;
    CountryCodePicker mcountrycodepicker;
    String coutrycode;
    String phonenumber;

    FirebaseAuth firebaseAuth;
    ProgressBar mprogressbarmain;


    PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbacks;
    String codesent;
    


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mcountrycodepicker=findViewById(R.id.countrycodepicker);
        msendotp=findViewById(R.id.sendOTP);
        mgetphonenumber=findViewById(R.id.getphonenumber);
        mprogressbarmain=findViewById(R.id.progressbarmain);

        firebaseAuth=FirebaseAuth.getInstance();

        coutrycode=mcountrycodepicker.getSelectedCountryCodeWithPlus();

        mcountrycodepicker.setOnCountryChangeListener(new CountryCodePicker.OnCountryChangeListener() {
            @Override
            public void onCountrySelected() {
                coutrycode=mcountrycodepicker.getSelectedCountryCodeWithPlus();
            }
        });

        msendotp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String number;
                number=mgetphonenumber.getText().toString();
                if(number.isEmpty())
                {
                    Toast.makeText(getApplicationContext(),"PLease Enter your number",Toast.LENGTH_SHORT).show();
                }
                else if(number.length()<10)
                {
                    Toast.makeText(getApplicationContext(),"PLease Enter correct number",Toast.LENGTH_SHORT).show();
                }
                else
                {
                    mprogressbarmain.setVisibility(View.VISIBLE);
                    phonenumber=coutrycode+number;

                    PhoneAuthOptions options=PhoneAuthOptions.newBuilder(firebaseAuth)
                            .setPhoneNumber(phonenumber)
                            .setTimeout(60L, TimeUnit.SECONDS)
                            .setActivity(MainActivity.this)
                            .setCallbacks(mCallbacks)
                            .build();

                    PhoneAuthProvider.verifyPhoneNumber(options);



                }
            }
        });


        mCallbacks=new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
            @Override
            public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {
                //how to aytomatically fetch otp

            }

            @Override
            public void onVerificationFailed(@NonNull FirebaseException e) {

            }


            @Override
            public void onCodeSent(@NonNull String s, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                super.onCodeSent(s, forceResendingToken);
                Toast.makeText(getApplicationContext(),"OTP is Sent",Toast.LENGTH_SHORT).show();
                mprogressbarmain.setVisibility(View.INVISIBLE);
                codesent=s;
                Intent intent=new Intent(MainActivity.this, otp_authentication.class);
                intent.putExtra("otp",codesent);
                startActivity(intent);
            }
        };

    }


    @Override
    protected void onStart() {
        super.onStart();
        if(FirebaseAuth.getInstance().getCurrentUser()!= null)
        {
            Intent intent=new Intent(MainActivity.this,chatActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        }
    }
}