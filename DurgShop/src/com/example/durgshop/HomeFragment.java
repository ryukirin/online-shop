package com.example.durgshop;

import java.util.ArrayList;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * 主页
 * */
public class HomeFragment extends Fragment {
	private int imageIds[] = { R.drawable.homepic1, R.drawable.homepic2,
			R.drawable.homepic3, R.drawable.homepic4 };
	private ArrayList<ImageView> images = new ArrayList<ImageView>();
	private ViewPager vp;
	private int currentItem; // 当前页面
	private ScheduledExecutorService scheduledExecutorService;
	// 图片标题
	private String titles[] = new String[] { "季节更替", "注意保暖", "多喝热水", "不要感冒" };
	private ArrayList<View> dots = new ArrayList<View>();;
	private TextView title;
	// 顶部标题栏
	private ImageButton search;
	private EditText top_title;
	private String dname;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = LayoutInflater.from(getActivity()).inflate(
				R.layout.home_fragment, null);
		initView(view);
		return view;
	}

	private void initView(View view) {
		// TODO Auto-generated method stub
		search = (ImageButton) view.findViewById(R.id.iv_search_home);
		top_title = (EditText) view.findViewById(R.id.tv_top_home);
		search.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				// 跳转到宝贝搜索界面
				dname = top_title.getText().toString();
				Datadurg.durgname = dname;
				Intent intent = new Intent(getActivity(), SearchActivity.class);
				getActivity().startActivity(intent);
			}
		});

		for (int i = 0; i < imageIds.length; i++) {
			ImageView imageView = new ImageView(getActivity());
			imageView.setImageResource(imageIds[i]);
			images.add(imageView);
		}

		// 显示的点 加入集合
		dots.add(view.findViewById(R.id.dot_0));
		dots.add(view.findViewById(R.id.dot_1));
		dots.add(view.findViewById(R.id.dot_2));
		dots.add(view.findViewById(R.id.dot_3));

		// 获取图片标题的id
		title = (TextView) view.findViewById(R.id.tv_test_title);

		// 获取ViewPager 的id
		vp = (ViewPager) view.findViewById(R.id.vp);
		vp.setAdapter(new ViewPagerAdapter());
		vp.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
			@Override
			public void onPageScrolled(int position, float positionOffset,
					int positionOffsetPixels) {
			}

			@Override
			public void onPageSelected(int position) {
				// 设置页面刷新后的图片标题
				title.setText(titles[position]);
				currentItem = position;
			}

			@Override
			public void onPageScrollStateChanged(int state) {
			}
		});
	}

	class ViewPagerAdapter extends PagerAdapter {

		@Override
		public int getCount() {
			return images.size();
		}

		@Override
		public boolean isViewFromObject(View view, Object object) {
			return view == object;
		}

		@Override
		public void destroyItem(ViewGroup container, int position, Object object) {
			// 将试图移除试图组
			View v = images.get(position);
			container.removeView(v);
		}

		@Override
		public Object instantiateItem(ViewGroup container, int position) {
			// 将试图添加进试图组
			View v = images.get(position);
			container.addView(v);
			return v;
		}
	}

	@Override
	public void onStart() {
		super.onStart();
		scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
		// 每隔两秒换一张图片
		scheduledExecutorService.scheduleWithFixedDelay(new ViewPagerTask(), 2,
				2, TimeUnit.SECONDS);

	}

	// 实现一个碎片的接口
	class ViewPagerTask implements Runnable {
		@Override
		public void run() {
			currentItem = (currentItem + 1) % imageIds.length;
			// 更新界面
			handler.obtainMessage().sendToTarget();
		}
	}

	// 在handler进行碎片跳转
	Handler handler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			vp.setCurrentItem(currentItem);
		}
	};
}
