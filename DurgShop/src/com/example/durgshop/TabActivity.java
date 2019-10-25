package com.example.durgshop;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;

/**
 * 整个程序最底层的框架Activity，所有的Fragment都是依赖于此Activity而存在的
 */
public class TabActivity extends FragmentActivity implements OnClickListener {

	// 界面底部的菜单按钮
	private ImageView[] bt_menu = new ImageView[2];
	// 界面底部的菜单按钮id
	private int[] bt_menu_id = { R.id.iv_home_tab, R.id.iv_user_tab };
	// 界面底部的选中菜单按钮资源
	private int[] select_off = { R.drawable.home_no, R.drawable.user_no };
	// 界面底部的未选中菜单按钮资源
	private int[] select_on = { R.drawable.home_select, R.drawable.user_select };

	/** 主界面 */
	private HomeFragment home_F;
	/** 我的淘宝界面 */
	private UserFragment user_F;
	private String uid;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_tab);
		uid = Datauser.userid;
		initView();
	}

	@Override
	protected void onSaveInstanceState(Bundle outState) {
		// 如果用以下这种做法则不保存状态，再次进来的话会显示默认tab
		// 总是执行这句代码来调用父类去保存视图层的状态
		// super.onSaveInstanceState(outState);
	}

	// 初始化组件
	private void initView() {
		// 找到底部菜单的按钮并设置监听
		for (int i = 0; i < bt_menu.length; i++) {
			bt_menu[i] = (ImageView) findViewById(bt_menu_id[i]);
			bt_menu[i].setOnClickListener(this);
		}

		// 初始化默认显示的界面
		if (home_F == null) {
			home_F = new HomeFragment();
			addFragment(home_F);
			showFragment(home_F);
		} else if (!home_F.isHidden()) {
			if (!user_F.isHidden()) {
				removeFragment(user_F);
			}
			removeFragment(home_F);
			removeFragment(user_F);
			showFragment(home_F);
		} else if (!user_F.isHidden()) {
			if (!home_F.isHidden()) {
				removeFragment(home_F);
			}
			removeFragment(user_F);
			removeFragment(home_F);
			showFragment(home_F);
		}
		// 设置默认首页为点击时的图片
		bt_menu[0].setImageResource(select_on[0]);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.iv_home_tab:
			// 主界面
			home_F = new HomeFragment();
			removeFragment(user_F);
			addFragment(home_F);
			showFragment(home_F);
			break;
		case R.id.iv_user_tab:
			// 我的淘宝界面
			user_F = new UserFragment();
			// 判断当前界面是否隐藏，如果隐藏就进行添加显示，false表示显示，true表示当前界面隐藏
			removeFragment(home_F);
			addFragment(user_F);
			showFragment(user_F);
			break;
		}

		// 设置按钮的选中和未选中资源
		for (int i = 0; i < bt_menu.length; i++) {
			bt_menu[i].setImageResource(select_off[i]);
			if (v.getId() == bt_menu_id[i]) {
				bt_menu[i].setImageResource(select_on[i]);
			}
		}
	}

	/** 添加Fragment **/
	public void addFragment(Fragment fragment) {
		FragmentTransaction ft = this.getSupportFragmentManager()
				.beginTransaction();
		ft.add(R.id.show_layout, fragment);
		ft.commit();
	}

	/** 删除Fragment **/
	public void removeFragment(Fragment fragment) {
		FragmentTransaction ft = this.getSupportFragmentManager()
				.beginTransaction();
		ft.remove(fragment);
		ft.commit();
	}

	/** 显示Fragment **/
	public void showFragment(Fragment fragment) {
		FragmentTransaction ft = this.getSupportFragmentManager()
				.beginTransaction();

		// 判断页面是否已经创建，如果已经创建，那么就隐藏掉
		if (home_F != null) {
			ft.hide(home_F);
		}
		if (user_F != null) {
			ft.hide(user_F);
		}
		ft.show(fragment);
		ft.commitAllowingStateLoss();
	}

	/** 返回按钮的监听 */
	@Override
	public void onBackPressed() {
		Intent intent = new Intent(Intent.ACTION_MAIN);
		intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		intent.addCategory(Intent.CATEGORY_HOME);
		startActivity(intent);
	}
}