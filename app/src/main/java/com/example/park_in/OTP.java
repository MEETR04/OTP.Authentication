package com.example.park_in;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class OTP extends AppCompatActivity {
    EditText inputcode1, inputcode2, inputcode3, inputcode4, inputcode5, inputcode6;
    String getOTPbackend;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.otpactivity);

         final Button button = findViewById(R.id.button);

        inputcode1 = findViewById(R.id.inputcode1);
        inputcode2 = findViewById(R.id.inputcode2);
        inputcode3 = findViewById(R.id.inputcode3);
        inputcode4 = findViewById(R.id.inputcode4);
        inputcode5 = findViewById(R.id.inputcode5);
        inputcode6 = findViewById(R.id.inputcode6);

        TextView textView = findViewById(R.id.showphone);
        textView.setText(String.format("+91-%s", getIntent().getStringExtra("mobile")
        ));

        getOTPbackend=getIntent().getStringExtra("s");
        final ProgressBar progressBarreceiveOTP=findViewById(R.id.progressBarreceiveotp);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              if (!inputcode1.getText().toString().trim().isEmpty() && !inputcode2.getText().toString().trim().isEmpty() && !inputcode3.getText().toString().trim().isEmpty() && !inputcode4.getText().toString().trim().isEmpty() && !inputcode5.getText().toString().trim().isEmpty() && !inputcode6.getText().toString().trim().isEmpty() );
              String entercodeOTP = inputcode1.getText().toString() +
                                    inputcode2.getText().toString() +
                                    inputcode3.getText().toString() +
                                    inputcode4.getText().toString() +
                                    inputcode5.getText().toString() +
                                    inputcode6.getText().toString();

              if (getOTPbackend!=null){
                  progressBarreceiveOTP.setVisibility(View.VISIBLE);
                  button.setVisibility(View.INVISIBLE);

                  PhoneAuthCredential phoneAuthCredential = PhoneAuthCredential.zzc(
                          getOTPbackend, entercodeOTP
                  );
                  FirebaseAuth.getInstance().signInWithCredential(phoneAuthCredential)
                          .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                              @Override
                              public void onComplete(@NonNull Task<AuthResult> task) {
                                  progressBarreceiveOTP.setVisibility(View.GONE);
                                  button.setVisibility(View.VISIBLE);
                                  if (task.isSuccessful()){
                                      Intent intent= new Intent(getApplicationContext(),Home.class);
                                      intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                      startActivity(intent);
                                  }else {
                                      Toast.makeText(OTP.this, "Enter the correct OTP", Toast.LENGTH_SHORT).show();
                                  }
                              }
                          });

              }else {
                  Toast.makeText(OTP.this, "Please, Check your Internet Connection", Toast.LENGTH_SHORT).show();
              }
                Toast.makeText(OTP.this, "OTP verified", Toast.LENGTH_SHORT).show();
            }


        });

        numberotpmove();
        findViewById(R.id.resendOTP).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PhoneAuthProvider.getInstance().verifyPhoneNumber(
                        "+91" + getIntent().getStringExtra("s"),
                        60,
                        TimeUnit.SECONDS,
                        OTP.this,
                        new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                            @Override
                            public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {

                            }

                            @Override
                            public void onVerificationFailed(@NonNull FirebaseException e) {

                                Toast.makeText(OTP.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                            }

                            @Override
                            public void onCodeSent(@NonNull String newbackendOTP, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                                    getOTPbackend=newbackendOTP;
                                Toast.makeText(OTP.this, "OTP sent successfully", Toast.LENGTH_SHORT).show();

                            }
                        }
                );
            }
        });
    }

    private void numberotpmove() {
        inputcode1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (!charSequence.toString().trim().isEmpty()){
                    inputcode2.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        inputcode2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (!charSequence.toString().trim().isEmpty()){
                    inputcode3.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        inputcode3.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (!charSequence.toString().trim().isEmpty()){
                    inputcode4.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        inputcode4.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (!charSequence.toString().trim().isEmpty()){
                    inputcode5.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        inputcode5.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (!charSequence.toString().trim().isEmpty()){
                    inputcode6.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }
}










