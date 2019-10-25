package com.example.durgshop;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
/**
 * 没有时显示
 * */
public class NullActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_null);
    }
}
