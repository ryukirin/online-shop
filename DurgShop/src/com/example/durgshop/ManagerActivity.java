package com.example.durgshop;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;

/**
 * 管理员界面
 * */
public class ManagerActivity extends Activity implements View.OnClickListener {
	private TextView userm, durgm, mesm, aesm;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_manager);

		userm = (TextView) findViewById(R.id.tv_userm_manager);
		durgm = (TextView) findViewById(R.id.tv_durgm_manager);
		mesm = (TextView) findViewById(R.id.tv_dmesm_manager);
		aesm = (TextView) findViewById(R.id.tv_amesm_manager);

		userm.setOnClickListener(this);
		durgm.setOnClickListener(this);
		mesm.setOnClickListener(this);
		aesm.setOnClickListener(this);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.manager, menu);
		return true;
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.tv_userm_manager:
			Intent intent1 = new Intent(ManagerActivity.this,
					UsermanagerActivity.class);
			startActivity(intent1);
			break;
		case R.id.tv_durgm_manager:
			Intent intent2 = new Intent(ManagerActivity.this,
					DurgmanagerActivity.class);
			startActivity(intent2);
			break;
		case R.id.tv_dmesm_manager:
			Intent intent3 = new Intent(ManagerActivity.this,
					DmesmanagerActivity.class);
			startActivity(intent3);
			break;
		case R.id.tv_amesm_manager:
			Intent intent4 = new Intent(ManagerActivity.this,
					AmessActivity.class);
			startActivity(intent4);
			break;
		default:
			break;
		}
	}
}
