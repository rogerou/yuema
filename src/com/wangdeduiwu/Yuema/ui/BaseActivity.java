package com.wangdeduiwu.Yuema.ui;

import cn.bmob.im.BmobChatManager;
import cn.bmob.im.BmobUserManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.DisplayMetrics;
import android.util.Log;

public class BaseActivity extends FragmentActivity {

	BmobUserManager userManager;
	BmobChatManager manager;
	protected int mScreenWidth;
	protected int mScreenHeight;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		userManager = BmobUserManager.getInstance(this);
		manager = BmobChatManager.getInstance(this);
		DisplayMetrics metric = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(metric);
		mScreenWidth = metric.widthPixels;
		mScreenHeight = metric.heightPixels;

	}

	/**
	 * 打Log ShowLog
	 * 
	 * @return void
	 * @throws
	 */
	public void ShowLog(String msg) {
		Log.i("life", msg);
	}

	public void startAnimActivity(Class<?> cla) {
		this.startActivity(new Intent(this, cla));
	}

	public void startAnimActivity(Intent intent) {
		this.startActivity(intent);
	}
}