package com.example.user.ddnas_example;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by user on 2016-12-03.
 */

public class Workinfo extends AppCompatActivity {

    final static String Workinfo="android/workInfo";

    float totalSalary = 0;
    String userCode = null;
    String userName = null;
    String result = null;
    NetworkTask networkTask;
    Map<String, String> params;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent receive = getIntent();
        userCode = receive.getStringExtra("userCode");
        userName = receive.getStringExtra("userName");

        LinearLayout linear = (LinearLayout) View.inflate(this, R.layout.workinfo, null);
        TextView name = (TextView)linear.findViewById(R.id.username);
        TextView currentmonth = (TextView)linear.findViewById(R.id.currentmonth);
        TextView salary = (TextView)linear.findViewById(R.id.salary);

        result = sendloginparams(Workinfo,"month","userCode","1612",userCode);

        Gson gson = new Gson();

        Type type = new TypeToken<List<Data.WorkInfo>>() {}.getType();
        List<Data.WorkInfo> workinfolist = gson.fromJson(result, type);


        for(Data.WorkInfo workInfo : workinfolist)
            totalSalary = totalSalary + workInfo.getTimeWage() * workInfo.getWorkHour() + (float)workInfo.getTimeWage() / (60 * (float)workInfo.getWorkMin());

        name.setText(userName + " 님");
        currentmonth.setText("12 월 총 급여 정보");
        salary.setText((int)totalSalary + " 원");

        preparechart(linear, workinfolist);
        setContentView(linear);
    }


    public String sendloginparams(String url ,String sendid1, String sendid2, String param1, String param2) {
        String resultmsg = null;
        networkTask = new NetworkTask(url);
        params = new HashMap<String, String>();
        params.put(sendid1, param1);
        params.put(sendid2, param2);
        try {
            resultmsg = networkTask.execute(params).get();
        }catch (Exception e){ return resultmsg; }
        return resultmsg;
    }


    public void preparechart(LinearLayout linear, List<Data.WorkInfo> workinfolist)
    {
        ArrayList<String> labels = new ArrayList<String>();
        int i;
        for(i = 0; i< workinfolist.size(); i++)
        {
            labels.add("" + workinfolist.get(i).getWorkDay());
            labels.add("");
        }
        String lastday = "" + (workinfolist.get(i-1).getWorkDay() + 1);
        labels.add(lastday);



        //3. 표시할 데이타 추가
        int count = 1;
        ArrayList<Entry> entries = new ArrayList<Entry>();
        entries.add(new Entry(0, 0));
        for(i=0; i<workinfolist.size(); i++) {
            entries.add(new Entry(workinfolist.get(i).getWorkHour() + (1/60) * workinfolist.get(i).getWorkMin() , count++));
            entries.add(new Entry(0, count++));
        }

        //4. 데이타셋 설정
        LineChart lineChart = (LineChart)linear.findViewById(R.id.chart);

        LineDataSet lineDataSet = new LineDataSet(entries, null);
        lineDataSet.setColors(ColorTemplate.VORDIPLOM_COLORS);
        lineDataSet.setLineWidth(5F);
        lineDataSet.setColor(Color.BLACK);
        lineDataSet.setDrawStepped(true);
        lineDataSet.setDrawCubic(true);
        lineDataSet.setDrawFilled(true); //선아래로 색상표시
        lineDataSet.setDrawValues(false);

        LineData lineData = new LineData(labels, lineDataSet);
        lineChart.setData(lineData); // set the data and list of lables into chart

        YAxis y = lineChart.getAxisRight();
        y.setTextColor(Color.WHITE);
        y.setShowOnlyMinMax(true);

        XAxis x = lineChart.getXAxis();
        x.setTextColor(Color.BLACK);
        x.setPosition(XAxis.XAxisPosition.BOTTOM);
        x.setSpaceBetweenLabels(0);


    }
}
