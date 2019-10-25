package com.example.durgshop;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

/**
 * 药品管理
 * */
public class DurgmanagerActivity extends Activity {
	private ListView lv;
	private Button add, deletedurg;
	private DBAdapter dbAdepter;
	private Durgs[] durgs;
	private TextView did, dname, dprice;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_durgmanager);
		add = (Button) findViewById(R.id.bt_adddurg_durgmanager);
		add.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Intent intent = new Intent(DurgmanagerActivity.this,
						AdddurgActivity.class);
				startActivity(intent);
			}
		});
		dbAdepter = new DBAdapter(this);
		dbAdepter.open();
		init();
	}

	private void init() {
		durgs = dbAdepter.queryAllDurgs();
		if (durgs != null) {
			System.out.println("durgs[0].dname=" + durgs[0].name);
			System.out.println("durgs[0].price=" + durgs[0].price);
			lv = (ListView) findViewById(R.id.durglist);
			lv.setAdapter(new BaseAdapter() {
				/*
				 * 为ListView设置一个适配器 getCount()返回数据个数 getView()为每一行设置一个条目
				 */
				@Override
				public int getCount() {
					return durgs.length;
				}

				@Override
				public Object getItem(int position) {
					return null;
				}

				@Override
				public long getItemId(int position) {
					return 0;
				}

				@Override
				public View getView(int position, View convertView,
						ViewGroup parent) {

					View view;
					/*
					 * 对ListView的优化，convertView为空时，创建一个新视图；
					 * convertView不为空时，代表它是滚出, 放入Recycler中的视图,若需要用到其他layout，
					 * 则用inflate(),同一视图，用fiindViewBy() *
					 */
					if (convertView == null) {
						LayoutInflater inflater = DurgmanagerActivity.this
								.getLayoutInflater();
						convertView = inflater.inflate(R.layout.durgs, null);
						deletedurg = (Button) convertView
								.findViewById(R.id.bt_durgdelete_durg);
						did = (TextView) convertView
								.findViewById(R.id.tv_did_durg);
						dname = (TextView) convertView
								.findViewById(R.id.tv_dname_durg);
						dprice = (TextView) convertView
								.findViewById(R.id.tv_price_durg);
						view = convertView;
					} else {
						view = convertView;
					}
					// position相当于数组下标,可以实现逐行取数据
					did.setText(durgs[position].did);
					System.out.println(durgs[position].name);
					System.out.println(durgs[position].price);
					dname.setText(durgs[position].name);
					dprice.setText(durgs[position].price);
					deletedurg.setTag(position);
					deletedurg.setOnClickListener(new OnClickListener() {
						public void onClick(View v) {
							int pos = (Integer) v.getTag();
							String id = String.valueOf(durgs[pos].did);
							long result = dbAdepter.deleteOneDurg(id);
							String msg = "删除ID为" + id + "的数据"
									+ (result > 0 ? "成功" : "失败");
							Toast.makeText(DurgmanagerActivity.this, msg,
									Toast.LENGTH_SHORT).show();
						}
					});
					return convertView;
				}
			});
		} else {
			Toast.makeText(DurgmanagerActivity.this, "什么都没有",
					Toast.LENGTH_SHORT).show();
		}
	}
}