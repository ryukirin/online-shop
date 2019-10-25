package com.example.durgshop;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
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
 * 用户界面
 * */
public class UserFragment extends Fragment {
	private String uid;
	private TextView uname, paid, sendmess,exit;
	private DBAdapter dbAdepter;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = LayoutInflater.from(getActivity()).inflate(
				R.layout.user_fragment, null);
		uname = (TextView) view.findViewById(R.id.tv_name_user);
		paid = (TextView) view.findViewById(R.id.tv_paid_user);
		sendmess = (TextView) view.findViewById(R.id.tv_sendmess_user);
		exit = (TextView) view.findViewById(R.id.tv_exit_user);
		dbAdepter = new DBAdapter(getActivity());
		dbAdepter.open();
		initView(view);
		return view;
	}

	private void initView(View view) {
		// TODO Auto-generated method stub
		uid = Datauser.userid;
		Users[] user;
		user = dbAdepter.queryUserbyid(uid);
		uname.setText(user[0].name);

		paid.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				// 跳转到订单界面
				Intent intent = new Intent(getActivity(), MypaidActivity.class);
				getActivity().startActivity(intent);
			}
		});

		sendmess.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				CreatDialog(uid);
			}
		});
		exit.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				Intent intent = new Intent(getActivity(), MainActivity.class);
				getActivity().startActivity(intent);
			}
		});
	}

	public void CreatDialog(String uid) {
		final EditText editText = new EditText(getActivity());
		final Appmess mess = new Appmess();
		final String userid = uid;
		AlertDialog.Builder inputDialog = new AlertDialog.Builder(getActivity());
		inputDialog.setTitle("请输入反馈").setView(editText);
		inputDialog.setPositiveButton("确定",
				new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						String cont = editText.getText().toString();
						mess.cont = cont;
						mess.uid = userid;
						long colunm = dbAdepter.addappmess(mess);
						if (colunm == -1) {
							Toast.makeText(getActivity(), "添加失败！",
									Toast.LENGTH_SHORT).show();
						} else {
							Toast.makeText(getActivity(), "添加成功！",
									Toast.LENGTH_SHORT).show();
						}
					}
				}).show();
	}
}
