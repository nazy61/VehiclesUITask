package com.nazycodes.vehiclesuitask;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;
import com.google.android.gms.tasks.TaskExecutors;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class RegisterWithPhoneNumberActivity extends AppCompatActivity {

    private EditText editTextPhone;
    private RelativeLayout sendOTP;

    private String verificationCodeBySystem;
    private PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbacks;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_with_phone_number);

        editTextPhone = findViewById(R.id.editTextPhone);
        sendOTP = findViewById(R.id.sendOTP);

        mCallbacks = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
            @Override
            public void onCodeSent(String s, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                super.onCodeSent(s, forceResendingToken);
                verificationCodeBySystem = s;
                navigateToOTPActivity();
            }

            @Override
            public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {

            }

            @Override
            public void onVerificationFailed(FirebaseException e) {
                Toast.makeText(RegisterWithPhoneNumberActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        };

        sendOTP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(validateNumber()) {
                    String phoneNo = editTextPhone.getText().toString();
                    sendVerificationCodeToUser(phoneNo);
                }
            }
        });
    }

    private void sendVerificationCodeToUser(String phoneNo) {
        String number = phoneNo.substring(1);

        PhoneAuthProvider.getInstance().verifyPhoneNumber(
                "+234" + number,        // Phone number to verify
                60,                 // Timeout duration
                TimeUnit.SECONDS,   // Unit of timeout
                TaskExecutors.MAIN_THREAD,   // Activity (for callback binding)
                mCallbacks);    // OnVerificationStateChangedCallbacks
    }

    private boolean validateNumber() {
        if(editTextPhone.getText().toString().length() == 11){
            return true;
        } else {
            editTextPhone.setError("Please enter 11 digit number");
            return false;
        }
    }

    private void navigateToOTPActivity() {
        Intent intent = new Intent(RegisterWithPhoneNumberActivity.this, OTPActivity.class);
        intent.putExtra("verificationCode", verificationCodeBySystem);
        intent.putExtra("phoneNumber", editTextPhone.getText().toString());
        startActivity(intent);
    }
}