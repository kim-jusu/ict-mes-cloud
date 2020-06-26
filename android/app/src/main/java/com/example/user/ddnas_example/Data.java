package com.example.user.ddnas_example;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by user on 2016-11-26.
 */

public class Data {

        private int userCode;
        private String userName;

        public int getUserCode() {
            return userCode;
        }

        public void setUserCode(int userCode) {
            this.userCode = userCode;
        }

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

    public class Data2{
        private List<Workinfo> workinfoList = new ArrayList<Workinfo>();

        public void setWorkinfoList(List<Workinfo> workinfoList) { this.workinfoList = workinfoList;}
        public List<Workinfo> getWorkinfoList(){ return workinfoList; }


    }
    public class WorkInfo{

        private int timeWage;
        private int workDay;
        private int workHour;
        private int workMin;

        public int getTimeWage() {
            return timeWage;
        }

        public void setTimeWage(int timeWage) {
            this.timeWage= timeWage;
        }

        public int getWorkDay() {
            return workDay;
        }

        public void setWorkDay(int workDay) {
            this.workDay = workDay;
        }

        public int getWorkHour() {
            return workHour;
        }

        public void setWorkHour(int workHour) {
            this.workHour= workHour;
        }

        public int getWorkMin() {
            return workMin;
        }

        public void setWorkMin(int workMin) {
            this.workMin= workMin;
        }

    }

    }

