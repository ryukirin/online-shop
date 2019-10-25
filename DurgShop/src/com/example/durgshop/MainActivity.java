package com.example.durgshop;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

/**
 * 登录
 * */
public class MainActivity extends Activity implements View.OnClickListener {

	private EditText edit_account, edit_password;
	private TextView text_msg;
	private Button btn_login, btn_register;
	private ImageButton openpwd;
	private boolean flag = false;
	private DBAdapter dbAdepter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		dbAdepter = new DBAdapter(this);
		dbAdepter.open();
		init();
	}

	private void init() {
		edit_account = (EditText) findViewById(R.id.edit_account_main);
		edit_account
				.setOnEditorActionListener(new TextView.OnEditorActionListener() {
					@Override
					public boolean onEditorAction(TextView v, int actionId,
							KeyEvent event) {
						if (actionId == EditorInfo.IME_ACTION_DONE) {
							edit_account.clearFocus();
						}
						return false;
					}
				});
		edit_password = (EditText) findViewById(R.id.edit_password_main);
		edit_password
				.setOnEditorActionListener(new TextView.OnEditorActionListener() {
					@Override
					public boolean onEditorAction(TextView v, int actionId,
							KeyEvent event) {
						if (actionId == EditorInfo.IME_ACTION_DONE) {
							edit_password.clearFocus();
							InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
							imm.hideSoftInputFromWindow(
									edit_password.getWindowToken(), 0);
						}
						return false;
					}
				});
		text_msg = (TextView) findViewById(R.id.text_msg_main);
		btn_login = (Button) findViewById(R.id.btn_login_main);
		btn_register = (Button) findViewById(R.id.btn_register_main);
		openpwd = (ImageButton) findViewById(R.id.btn_openpwd_main);
		text_msg.setOnClickListener(this);
		btn_login.setOnClickListener(this);
		btn_register.setOnClickListener(this);
		openpwd.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn_login_main:
			if (edit_account.getText().toString().trim().equals("")
					| edit_password.getText().toString().trim().equals("")) {
				Toast.makeText(this, "请输入账号或者注册账号！", Toast.LENGTH_SHORT).show();
			} else {
				readUserInfo();
			}
			break;
		case R.id.btn_register_main:
			Intent intent = new Intent(MainActivity.this,
					RegisterActivity.class);
			startActivity(intent);
			break;
		case R.id.btn_openpwd_main:
			if (flag == true) {// 不可见
				edit_password
						.setTransformationMethod(HideReturnsTransformationMethod
								.getInstance());
				flag = false;
				openpwd.setBackgroundResource(R.drawable.invisible);
			} else {
				edit_password
						.setTransformationMethod(PasswordTransformationMethod
								.getInstance());
				flag = true;
				openpwd.setBackgroundResource(R.drawable.visible);
			}
			break;
		case R.id.text_msg_main:
			Intent i = new Intent(MainActivity.this, ForgotInfoActivity.class);
			startActivity(i);
			break;
		}
	}

	/**
	 * 读取用户信息
	 * */
	protected void readUserInfo() {
		if (login(edit_account.getText().toString(), edit_password.getText()
				.toString())) {
			Toast.makeText(this, "登陆成功！", Toast.LENGTH_SHORT).show();
			Users[] user = dbAdepter.queryOneData(edit_account.getText()
					.toString(), edit_password.getText().toString());
			Intent intent = new Intent(MainActivity.this, TabActivity.class);
			Datauser.username = edit_account.getText().toString();
			Datauser.userid = String.valueOf(user[0].id);
			startActivity(intent);
		} else if (edit_account.getText().toString().equals("admin")//管理员登陆
				&& edit_password.getText().toString().equals("123456")) {
			Toast.makeText(this, "进入管理员界面！", Toast.LENGTH_SHORT).show();
			Intent intent = new Intent(MainActivity.this, ManagerActivity.class);
			startActivity(intent);
		} else {
			Toast.makeText(this, "账户或密码错误，请重新输入！！", Toast.LENGTH_SHORT).show();
		}
	}

	/**
	 * 验证登录信息
	 * */
	public boolean login(String username, String password) {
		Users[] user = dbAdepter.queryOneData(username, password);
		if (user == null) {
			return false;
		}
		return true;
	}
}
