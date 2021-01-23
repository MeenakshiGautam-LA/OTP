package com.example.otp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class SetPinActivity extends AppCompatActivity implements View.OnClickListener {
    String otp;
    EditText editText;
    Button confirm_btn_blue_bg;
    Context context;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_set_pin);

        context=this;
        editText = findViewById(R.id.oTP_ET);
        confirm_btn_blue_bg = findViewById(R.id.confirm_btn_blue_bg);


        Intent in = getIntent();
        otp = in.getStringExtra("otp");
        editText.setText(otp);

        confirm_btn_blue_bg.setOnClickListener(this);



    }

    @Override
    public void onClick(View v) {
        if(v.getId()==confirm_btn_blue_bg.getId())
        {
        if (editText.getText().toString().equals(otp))
        {

           Intent in = new Intent(SetPinActivity.this,MainActivity.class);
           startActivity(in);



        }
        else
        {
            Toast.makeText(this, "Please Enter Valid OTP", Toast.LENGTH_SHORT).show();

        }
        }

    }
}
