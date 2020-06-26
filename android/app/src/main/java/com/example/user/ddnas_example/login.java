package com.example.user.ddnas_example;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by user on 2016-12-01.
 */

public class login extends AppCompatActivity {

    String userCode = null;
    String userName = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent receive = getIntent();
        userCode = receive.getStringExtra("userCode");
        userName = receive.getStringExtra("userName");


        LinearLayout linear = (LinearLayout) View.inflate(this, R.layout.first, null);
        TextView name = (TextView)linear.findViewById(R.id.name);
        name.setText(userName + " 님");

        setContentView(linear);
    }

    public void bClick(View v) {
        switch (v.getId()) {
            case R.id.in:
                Intent inpage = new Intent(login.this, Bluetooth.class);
                inpage.putExtra("userCode",userCode);
                inpage.putExtra("userName",userName);
                startActivity(inpage);
                finish();
                break;
            case R.id.workinfo:
                Intent workinfo = new Intent(login.this, Workinfo.class);
                workinfo.putExtra("userCode",userCode);
                workinfo.putExtra("userName",userName);
                startActivity(workinfo);
                break;
            case R.id.logout:
                Intent logout = new Intent(login.this, Main.class);
                startActivity(logout);
                finish();
        }
    }
}