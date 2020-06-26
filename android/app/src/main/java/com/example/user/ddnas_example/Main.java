package com.example.user.ddnas_example;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Build;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Layout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Main extends AppCompatActivity {

    Data data;
    NetworkTask networkTask;
    Map<String, String> params;
    final String Login = "android/login";
    final String Regist = "android/regist";
    LinearLayout linear, linear2;
    AlertDialog in;
    TextView id, pw;
    boolean registflag = false;
    String result;


    EditText inputid, inputpw, inputrepw, name, phone, picture; // Regist 페이지에서 쓰임.
    EditText find1name, find1phone, find2name, find2id, find2phone; // Find 페이지에서 쓰임.

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.loginpg);

        //Login 페이지
        id = (TextView)findViewById(R.id.userid);
        pw = (TextView)findViewById(R.id.userpassword);

        //Regist 페이지
        linear = (LinearLayout)View.inflate(this, R.layout.regist, null);
        inputid = (EditText)linear.findViewById(R.id.inputid);
        inputpw = (EditText)linear.findViewById(R.id.inputpw);
        inputrepw = (EditText)linear.findViewById(R.id.inputrepw);
        name = (EditText)linear.findViewById(R.id.name);
        phone = (EditText)linear.findViewById(R.id.phone);
        picture = (EditText)linear.findViewById(R.id.picture);

        //Find 페이지
        linear2 = (LinearLayout)View.inflate(this, R.layout.find, null);
        find1name = (EditText)linear2.findViewById(R.id.find1name);
        find1phone = (EditText)linear2.findViewById(R.id.find1phone);
        find2name = (EditText)linear2.findViewById(R.id.find2name);
        find2id = (EditText)linear2.findViewById(R.id.find2id);
        find2phone = (EditText)linear2.findViewById(R.id.find2phone);

    }

    public void loginClick(View v) {
        switch (v.getId()) {
            case R.id.btnlogin:

                result = sendloginparams(Login, "id", "password", id.getText().toString(), pw.getText().toString());

                if(result != null) {
                    Gson gson = new Gson();
                    data = gson.fromJson(result, Data.class);
                }
                else
                {
                    Toast.makeText(getApplicationContext(), "네트워크 연결이 실패하였습니다.", Toast.LENGTH_SHORT).show();
                    finish();
                }


                if (data.getUserCode() == 0) {
                    Toast.makeText(getApplicationContext(), "ID / PW 를 확인하세요.", Toast.LENGTH_SHORT).show();
                }else if(result == null){
                    Toast.makeText(getApplicationContext(), "네트워크 통신이 실패하였습니다.", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(getApplicationContext(), "로그인에 성공하셨습니다.", Toast.LENGTH_SHORT).show();
                    finish();
                    Intent mainpage = new Intent(Main.this, login.class);
                    mainpage.putExtra("userCode", "" + data.getUserCode());
                    mainpage.putExtra("userName", data.getUserName());
                    startActivity(mainpage);
                }
                break;
            case R.id.btnregist:
                // 회원가입 페이지
                if (registflag == false)
                {
                    in = new AlertDialog.Builder(this)
                            .setView(linear)
                            .setCancelable(false)
                            .create();
                in.setCanceledOnTouchOutside(false);
                registflag = true;
                }
                in.show();
                break;
            case R.id.btnfind:
                // ID/PW 찾기
                in = new AlertDialog.Builder(this)
                        .setView(linear2)
                        .create();
                in.setCancelable(true);
                in.setCanceledOnTouchOutside(false);
                in.show();
                break;
        }
    }
    //Regist화면에서 작동되는 버튼 클릭.
    public String sendloginparams(String url ,String sendid1, String sendid2, String param1, String param2) {
        String resultmsg = null;
        networkTask = new NetworkTask(url);
        params = new HashMap<String, String>();
        params.put(sendid1, param1);
        params.put(sendid2, param2);

        try {
            resultmsg = networkTask.execute(params).get();
        }catch (Exception e){ return resultmsg;}
        return resultmsg;
    }


    public void RegistClick(View v) {
        switch (v.getId())
        {
            case R.id.btnjoin:
                NetworkTask networkTask = new NetworkTask(Regist);
                Map<String, String> params = new HashMap<String, String>();
                params.put("id", inputid.getText().toString());
                params.put("password", inputpw.getText().toString());
//                java.net.URLEncoder.encode(new String(contentText.getBytes("UTF-8")));

                try {params.put("name", URLEncoder.encode(name.getText().toString(),"EUC-KR"));} catch (UnsupportedEncodingException e) {}
                params.put("phone", phone.getText().toString());
                params.put("picture", picture.getText().toString());
                try{result = networkTask.execute(params).get();}catch (Exception e){}
                Toast.makeText(getApplicationContext(),result,Toast.LENGTH_SHORT).show();

                if(!inputrepw.getText().toString().equals(inputpw.getText().toString())) {
                    Toast.makeText(getApplicationContext(), "입력한 두개의 비밀번호가 다릅니다.", Toast.LENGTH_SHORT).show();
                }else{
                    if (Integer.parseInt(result) == 1) {
                        inputid.setText("");
                        inputpw.setText("");
                        inputrepw.setText("");
                        name.setText("");
                        phone.setText("");
                        picture.setText("");
                        Toast.makeText(getApplicationContext(), "가입이 완료되었습니다.", Toast.LENGTH_SHORT).show();
                        in.hide();
                    }
                }
                break;
            case R.id.btnback:
                inputid.setText("");
                inputpw.setText("");
                inputrepw.setText("");
                name.setText("");
                phone.setText("");
                picture.setText("");
                in.hide();
                Toast.makeText(getApplicationContext(), "뒤로가기",Toast.LENGTH_SHORT).show();
                break;
        }
    }


    //Find화면에서 작동되는 버튼 클릭.
    public void FindClick(View v) {
        switch (v.getId())
        {
            case R.id.btnfind1:
                break;
            case R.id.btnfind2:
                break;
        }
    }

}
