package com.example.park_in;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.FirebaseException;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class login extends AppCompatActivity {

    EditText inputphone;
    Button button;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        final EditText inputphone= findViewById(R.id.inputphone);
        button = findViewById(R.id.button);
        ProgressBar progressBarsendotp = findViewById(R.id.progressBarsendotp);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!inputphone.getText().toString().trim().isEmpty()){
                    if ((inputphone.getText().toString().trim().length() == 10)){

                        progressBarsendotp.setVisibility(View.VISIBLE);
                        button.setVisibility(View.INVISIBLE);
                        PhoneAuthProvider.getInstance().verifyPhoneNumber(
                                "+91" + inputphone.getText().toString(),
                                60,
                                TimeUnit.SECONDS,
                                login.this,
                                new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                            @Override
                            public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {
                                progressBarsendotp.setVisibility(View.GONE);
                                button.setVisibility(View.INVISIBLE);
                            }

                            @Override
                            public void onVerificationFailed(@NonNull FirebaseException e) {
                                progressBarsendotp.setVisibility(View.GONE);
                                button.setVisibility(View.VISIBLE);
                                Toast.makeText(login.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                            }

                            @Override
                            public void onCodeSent(@NonNull String s, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                                progressBarsendotp.setVisibility(View.GONE);
                                button.setVisibility(View.VISIBLE);
                                Intent intent= new Intent(getApplicationContext(),OTP.class);
                                intent.putExtra("mobile",inputphone.getText().toString());
                                intent.putExtra("s",s);
                                startActivity(intent);
                            }
                        }
                        );

                        Intent intent= new Intent(getApplicationContext(),OTP.class);
                        intent.putExtra("mobile",inputphone.getText().toString());
                        startActivity(intent);
                    }else{
                        Toast.makeText(login.this, "Enter Valid Phone Number", Toast.LENGTH_SHORT).show();
                    }
                }else {
                    Toast.makeText(login.this, "Enter Phone Number", Toast.LENGTH_SHORT).show();

                }


            }
        });





    }

}