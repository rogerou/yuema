package com.wangdeduiwu.Yuema.ui;

import cn.bmob.im.BmobUserManager;
import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobInstallation;

import com.exam.date.MyMainActivity;
import com.wangdeduiwu.Yuema.R;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

public class SplashActivity extends BaseActivity {
	private static final int GO_HOME = 100;
	private static final int GO_LOGIN = 200;
	private String APPID = "a0d51d9b8ba001b258df4d494fc01b04";// --外网
	private static final long DELAY_TIME = 2000L;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.loading);

		userManager = BmobUserManager.getInstance(this);
		Bmob.initialize(this, APPID);
		BmobInstallation.getCurrentInstallation(this).save();
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		if (userManager.getCurrentUser() != null) {

			mHandler.sendEmptyMessageDelayed(GO_HOME, 2000);
		} else {
			mHandler.sendEmptyMessageDelayed(GO_LOGIN, 2000);
		}
	}

	@SuppressLint("HandlerLeak")
	private Handler mHandler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case GO_HOME:
				startAnimActivity(MyMainActivity.class);
				finish();
				break;
			case GO_LOGIN:
				new Handler().postDelayed(new Runnable() {

					@Override
					public void run() {
						startAnimActivity(LoginActivity.class);
						finish();
					}
				}, DELAY_TIME);
				break;
			}
		}
	};

	/**
	 * 根据时间进行页面跳转
	 */
	private void redirectByTime() {
		new Handler().postDelayed(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub

				finish();
			}
		}, DELAY_TIME);
	}

}
