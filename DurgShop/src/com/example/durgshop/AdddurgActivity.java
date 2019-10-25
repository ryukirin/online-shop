package com.example.durgshop;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AdddurgActivity extends Activity {
	private DBAdapter dbAdepter;
	private EditText dname, dprice;
	private String name, price;
	private Button add;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_adddurg);
		dbAdepter = new DBAdapter(this);
		dbAdepter.open();
		dname = (EditText) findViewById(R.id.et_dname_adddurg);
		dprice = (EditText) findViewById(R.id.et_price_adddurg);
		add = (Button) findViewById(R.id.bt_add_adddurg);
		init();
	}

	private void init() {
		// TODO Auto-generated method stub
		add.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Durgs durg = new Durgs();
				name = dname.getText().toString();
				System.out.println(name);
				price = dprice.getText().toString();
				System.out.println(price);
				if (name != null && price != null) {
					durg.name = name;
					System.out.println(durg.name);
					durg.price = price;
					System.out.println(durg.price);
					dbAdepter.adddurg(durg);
					Toast.makeText(AdddurgActivity.this, "Ìí¼Ó³É¹¦£¡",
							Toast.LENGTH_SHORT).show();
					Intent intent = new Intent(AdddurgActivity.this,
							DurgmanagerActivity.class);
					startActivity(intent);
				}
			}
		});
	}
}