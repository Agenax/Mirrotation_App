package com.sinric.esp32_android_app;

import android.app.Application;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.content.Intent;
/**
 * Created by WGH on 2017/4/10.
 */

public class MyApplication extends Application{

    private static Context context;
    private static int mPId;

    private String Recieved = "0";



    @Override
    public void onCreate() {
        context = getApplicationContext();
        mPId = android.os.Process.myPid();
    }

    public static class Global {
        public  static int ivar1;
        public  static String svar1;
        public  int[] myarray1 = new int[10];
    }

    public String getRecieved() {
        return Recieved;
    }

    public void setRecieved(String someVariable) {
        this.Recieved = someVariable;
    }

    public static Context context() {
        return context;
    }

    public static int getmPId() {
        return mPId;
    }
}
