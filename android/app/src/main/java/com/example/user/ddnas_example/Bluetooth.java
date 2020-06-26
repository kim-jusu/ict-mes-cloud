package com.example.user.ddnas_example;

import android.content.Context;
import android.graphics.Color;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.os.Vibrator;
import android.support.v7.app.ActionBarActivity;
import android.graphics.drawable.ColorDrawable;
import android.app.AlertDialog;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.view.menu.ExpandedMenuView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import static android.R.interpolator.linear;

/**
 * Created by user on 2016-12-01.
 */

public class Bluetooth extends AppCompatActivity{

    static final String coDensity = "android/coDensity";
    static final String logout = "android/logout";
    static final String authentication = "android/authentication";
    static final int safeState = 0;
    static final int dangerState = 1;
    static final int REQUEST_ENABLE_BT = 10;
    int mPairedDeviceCount = 0;
    Set mDevices;

    BluetoothAdapter mBluetoothAdapter;
    BluetoothDevice mRemoteDevice;
    BluetoothSocket mSocket = null;

    OutputStream mOutputStream = null;
    InputStream mInputStream = null;

    String mStrDelimiter = "\n";
    char mCharDelimiter = '\n';

    Thread mWorkerThread = null;
    byte[] readBuffer;
    int readBufferPosition;
    int intdata;
    int security;
    int serverSecurity;
    String strserverSecurity;
    String strsecurity;


    Vibrator mVib;
    MediaPlayer player;
    boolean ringflag = false;
    boolean vibflag =false;
    ImageView stop, workinfo;
    TextView mname;
    TextView mEditReceive, mServerReceive;
    EditText mEditSend;
    NetworkTask networkTask;
    Map<String, String> params;

    AlertDialog in;
    LinearLayout linear,linear2;
    String ddName = null;
    String userCode = null;
    String userName = null;
    Button sendbtn, researchbtn, disconnectbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mVib = (Vibrator)getSystemService(Context.VIBRATOR_SERVICE);
        linear = (LinearLayout) View.inflate(this, R.layout.mainpg, null);
        Intent receive = getIntent();
        userCode = receive.getStringExtra("userCode");
        userName = receive.getStringExtra("userName");
        mEditReceive = (TextView)linear.findViewById(R.id.receiveString);
        mServerReceive = (TextView)linear.findViewById(R.id.serverString);
        mname = (TextView)linear.findViewById(R.id.mainname);
        mname.setText(userName + " 님");
        stop = (ImageView) linear.findViewById(R.id.stop);
        workinfo = (ImageView) linear.findViewById(R.id.workinfo2);
        workinfo.setOnClickListener(mClickListener);
        stop.setOnClickListener(mClickListener);
        setContentView(linear);

        linear2 = (LinearLayout) View.inflate(this, R.layout.in, null);
        in = new AlertDialog.Builder(this)
                .setView(linear2)
                .create();
        in.setCancelable(false);
        in.setCanceledOnTouchOutside(false);
        in.show();

        sendbtn = (Button) linear2.findViewById(R.id.sendbtn);
        researchbtn = (Button) linear2.findViewById(R.id.research);
        disconnectbtn = (Button) linear2.findViewById(R.id.disconnect);

        sendbtn.setOnClickListener(mClickListener);
        researchbtn.setOnClickListener(mClickListener);
        disconnectbtn.setOnClickListener(mClickListener);


        checkBluetooth();
    }

    Button.OnClickListener mClickListener = new View.OnClickListener() {
        public void onClick(View v) {
            switch (v.getId()) {
                    case R.id.sendbtn:
                    mEditSend = (EditText) linear2.findViewById(R.id.sendnum); // 보내는 문자열
                    sendData(mEditSend.getText().toString());
                    mEditSend.setText("");
                    sendparams(authentication, "ddCode", "userCode", ddName, userCode);
                    in.hide();
                break;
                case R.id.stop:
                    sendData("z");
                    sendparams(logout, "userCode","userName",userCode,userName);

                    if(vibflag == true) {
                        mVib.cancel();
                        vibflag = false;
                    }
                    if(ringflag == true) {
                        if(player.isLooping()) {
                            player.stop();
                            ringflag = false;
                        }
                    }

                    finish();
                    Intent login = new Intent(Bluetooth.this, login.class);
                    login.putExtra("userCode",userCode);
                    login.putExtra("userName",userName);
                    startActivity(login);
                break;
                case R.id.workinfo2:
                    Intent workinfo = new Intent(Bluetooth.this, Workinfo.class);
                    workinfo.putExtra("userCode",userCode);
                    workinfo.putExtra("userName",userName);
                    startActivity(workinfo);
                    break;
                case R.id.disconnect:
                    Toast.makeText(getApplicationContext(), ddName +"과 연결이 해제되었습니다.", Toast.LENGTH_LONG).show();
                    sendbtn.setEnabled(false);
                    try{
                    mInputStream.close();
                    mSocket.close();} catch (Exception e){}
                    break;
                case R.id.research:
                    selectDevice();
                    break;
            }
        }
    };


    public String sendparams(String url ,String send1, String send2, String param1, String param2) {
        String result = null;
        networkTask = new NetworkTask(url);
        params = new HashMap<String, String>();
        params.put(send1, param1);
        params.put(send2, param2);
        try {
            result = networkTask.execute(params).get();
        }catch (Exception e){}
        return result;
    }

    public BluetoothDevice getDeviceFromBondedList(String name) {
        BluetoothDevice selectedDevice = null;
        BluetoothDevice tmp_device;

        for (Object device : mDevices) {
            tmp_device = (BluetoothDevice) device;
            if (name.equals(tmp_device.getName())) {
                selectedDevice = tmp_device;
                break;
            }
        }
        return selectedDevice;
    }

    public void sendData(String msg) {
        msg += mStrDelimiter;

        try {
            mOutputStream.write(msg.getBytes()); // 문자열 전송
        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), "데이터 전송중 오류가 발생", Toast.LENGTH_LONG).show();
        }
    }

    public void connectToSelectedDevice(String selectedDeviceName) {
        mRemoteDevice = getDeviceFromBondedList(selectedDeviceName);
        UUID uuid = java.util.UUID.fromString("00001101-0000-1000-8000-00805f9b34fb");

        try {
            mSocket = mRemoteDevice.createRfcommSocketToServiceRecord(uuid);
            mSocket.connect();
            mOutputStream = mSocket.getOutputStream();
            mInputStream = mSocket.getInputStream();

            beginListenForData();
        } catch(Exception e) {
            Toast.makeText(getApplicationContext(), "블루투스 연결 중 오류가 발생했습니다.", Toast.LENGTH_LONG).show();
            finish();
        }
    }

    public void beginListenForData() {
        final Handler handler = new Handler();
        readBufferPosition = 0;
        readBuffer = new byte[1024];
        Toast.makeText(getApplicationContext(), ddName + "과 연결되었습니다.", Toast.LENGTH_LONG).show();
        // 문자열 수신 쓰레드
        mWorkerThread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1500);
                }catch (Exception e){}
                while(!Thread.currentThread().isInterrupted()) {
                    try {
                        int byteAvailable = mInputStream.available();
                        if(byteAvailable > 0) {
                            byte[] packetBytes = new byte[byteAvailable];
                            mInputStream.read(packetBytes);
                            for(int i = 0; i < packetBytes.length; i++) {
                                byte b = packetBytes[i];
                                if(b == mCharDelimiter) {
                                    byte[] encodedBytes = new byte[readBufferPosition];
                                    System.arraycopy(readBuffer, 0, encodedBytes, 0, encodedBytes.length);
                                    final String data = new String(encodedBytes, "US-ASCII");
                                    readBufferPosition = 0;
                                    intdata = (int)Float.parseFloat(data);

                                    if(intdata < 400) {security = safeState; strsecurity =" 안전";}
                                    else {security = dangerState; strsecurity = " 경고";}

                                    strserverSecurity = sendparams(coDensity,"userCode","coDensity", userCode, data);
                                    serverSecurity = Integer.parseInt(strserverSecurity);

                                    handler.post(new Runnable() {
                                        // 수신된 문자열 데이터에 대한 처리
                                        @Override
                                        public void run() {
                                            switch(security) {
                                                case safeState:
                                                    mEditReceive.setTextColor(Color.BLACK);
                                                    break;
                                                case dangerState:
                                                    mEditReceive.setTextColor(Color.rgb(255, 94, 0));
                                                    break;
                                            }
                                            switch (serverSecurity)
                                            {
                                                case safeState:
                                                    mServerReceive.setTextColor(Color.BLACK);
                                                    mServerReceive.setText("안전");
                                                    break;
                                                case dangerState:
                                                    mServerReceive.setTextColor(Color.rgb(255,94,0));
                                                    mServerReceive.setText("경고");
                                                    break;
                                            }
                                                mEditReceive.setText( strsecurity +"   " + data + " ppm");

                                        }
                                    });
                                }
                                else {
                                    readBuffer[readBufferPosition++] = b;
                                }
                            }
                        }
                    } catch(Exception e) {
                        Toast.makeText(getApplicationContext(), "데이터 수신 중 오류가 발생했습니다.", Toast.LENGTH_LONG).show();
                        finish();
                    }

                    if(serverSecurity == safeState){
                        if(vibflag == true){
                            mVib.cancel();
                            vibflag = false;
                        }
                        if(ringflag == true)
                        {
                            if(player.isLooping())
                            {
                                player.stop();
                                ringflag = false;
                            }
                        }
                    }
                    else if(serverSecurity == dangerState) {
                        if(vibflag == false) {
                            mVib.vibrate(new long[]{3000, 1000, 3000, 1000}, 0);
                            vibflag = true;
                        }
                        if(ringflag == false)
                        {
                            player = MediaPlayer.create(Bluetooth.this, R.raw.warning);
                            player.start();
                            player.setLooping(true);
                            ringflag = true;
                        }
                    }
                }
            }
        });
        mWorkerThread.start();
    }

    public void selectDevice() {
        mDevices = mBluetoothAdapter.getBondedDevices();
        mPairedDeviceCount = mDevices.size();

        if(mPairedDeviceCount == 0) {
            Toast.makeText(getApplicationContext(), "페어링된 장치가 없습니다.", Toast.LENGTH_LONG).show();
            finish();
        }

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("블루투스 장치 선택");

        List listItems = new ArrayList();

        BluetoothDevice tmp_device;

        for(Object device : mDevices) {
            tmp_device = (BluetoothDevice)device;
            listItems.add(tmp_device.getName());
        }
        listItems.add("취소"); // 취소 항목 추가

        final CharSequence[] items = (CharSequence[]) listItems.toArray(new CharSequence[listItems.size()]);

        listItems.toArray(new CharSequence[listItems.size()]);

        builder.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int item) {
                if (item == mPairedDeviceCount) {
                    Toast.makeText(getApplicationContext(), "연결할 장치를 선택하지 않았습니다.", Toast.LENGTH_SHORT).show();
                    Toast.makeText(getApplicationContext(), "인증번호 보내기, 기기 연결끊기 버튼 비활성화", Toast.LENGTH_LONG).show();
                    sendbtn.setEnabled(false);
                    disconnectbtn.setEnabled(false);
                } else {
                    sendbtn.setEnabled(true);
                    disconnectbtn.setEnabled(true);
                    ddName = items[item].toString();
                    connectToSelectedDevice(items[item].toString());
                }
            }
        });

        builder.setCancelable(false); // 뒤로 가기 버튼 사용 금지
        AlertDialog alert = builder.create();
        alert.show();
    }

    public void checkBluetooth() {
        mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();

        if(mBluetoothAdapter == null) {
            Toast.makeText(getApplicationContext(), "기기가 블루투스를 지원하지 않습니다.", Toast.LENGTH_LONG).show();
        }
        else {
            if (!mBluetoothAdapter.isEnabled()) { // 블루투스 지원하며 비활성 상태인 경우
                Toast.makeText(getApplicationContext(), "현재 블루투스가 비활성 상태입니다.", Toast.LENGTH_LONG).show();
                Intent enableBtnIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
                startActivityForResult(enableBtnIntent, REQUEST_ENABLE_BT);
            }
            else { // 블루투스 지원하면 활성 상태인 경우
                selectDevice();
            }
        }
    }

    @Override
    protected  void onDestroy() {
        try {
            super.onDestroy();
            mVib.cancel();
            mWorkerThread.interrupt(); // 데이터 수신 쓰레드 종료
            mInputStream.close();
            mSocket.close();
        } catch(Exception e) {}
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch(requestCode) {
            case REQUEST_ENABLE_BT:
                if (resultCode == RESULT_OK) { //블루투스 활성화 상태
                    selectDevice();
                } else if (resultCode == RESULT_CANCELED) { //블루투스 비활성화 상태 (종료)
                    Toast.makeText(getApplicationContext(), "블루투스를 사용할 수 없어 프로그램을 종료합니다.", Toast.LENGTH_LONG).show();
                    finish();
                }
                break;
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}