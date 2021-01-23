package com.example.otp;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;

import java.util.Objects;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener, TextWatcher {
    Button next_btn_gray_bg;
    TextInputEditText editText;
    Context context;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        context = this;
        next_btn_gray_bg = findViewById(R.id.next_btn_gray_bg);
        editText = findViewById(R.id.edit_txt);

        next_btn_gray_bg.setOnClickListener(this);

        editText.setShowSoftInputOnFocus(true);


        editText.addTextChangedListener(this);

    }

    @Override
    public void onClick(View v) {
        if (v.getId() == next_btn_gray_bg.getId()) {
            if (editText.getText().toString().isEmpty()) {
                Toast.makeText(context, "Please Enter number", Toast.LENGTH_SHORT).show();
            } else if (editText.getText().toString().length() < 10) {
                Toast.makeText(context, "Please Enter 10 digit number", Toast.LENGTH_SHORT).show();

            } else {
                generateOTP();
            }
        }

    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {
        int length = Objects.requireNonNull(editText.getText()).length();
        if (length == 10) {


            next_btn_gray_bg.setBackgroundResource(R.drawable.rounded_corner_red_bg_btn);
            next_btn_gray_bg.setTextColor(Color.WHITE);


        } else {

//
            next_btn_gray_bg.setBackgroundResource(R.drawable.next_button_gray_background);
            next_btn_gray_bg.setTextColor(Color.BLACK);
        }

    }

    private void generateOTP() {
        int randomPin = (int) (Math.random() * 9000) + 1000;

        String otp = String.valueOf(randomPin);

        Toast.makeText(context, "OTP is Generated" + otp, Toast.LENGTH_SHORT).show();


        //return otp; //returning value of otp
        Intent in = new Intent(LoginActivity.this, SetPinActivity.class);
        in.putExtra("otp", otp);
        startActivity(in);
    }
}

