package com.exam.date;

import javax.security.auth.PrivateCredentialPermission;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.AnimationUtils;
import android.view.Window;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import cn.bmob.im.BmobUserManager;
import cn.bmob.v3.BmobInstallation;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.listener.BmobUpdateListener;
import cn.bmob.v3.update.BmobUpdateAgent;
import cn.bmob.v3.update.UpdateResponse;
import cn.bmob.v3.update.UpdateStatus;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.tencent.a.b.l;
import com.wangdeduiwu.Yuema.R;
import com.wangdeduiwu.Yuema.config.ImageLoadOptions;
import com.wangdeduiwu.Yuema.data.MyUser;
import com.wangdeduiwu.Yuema.ui.CreateDateActivity;
import com.wangdeduiwu.Yuema.ui.LoginActivity;
import com.wangdeduiwu.Yuema.ui.MyInfoView;
import com.wangdeduiwu.Yuema.ui.SlidingMenu;
import com.wangdeduiwu.Yuema.ui.UserFeedBackActivity;

public class MyMainActivity extends FragmentActivity implements OnClickListener {

	private Button btn_mydate;
	private Button btn_dateSearch;
	private FrameLayout frameLayout;
	private FragmentTransaction transaction;
	private MyDateFragment myDateFragment;
	private MySearchFragment mySearchFragment;
	SlidingMenu mleftMenu;
	TextView tv_username;
	LinearLayout myinfo, createdate, updateversion, userreturn, logout;
	ImageView iv_touxiang, iv_score1, iv_score2, iv_avatar;
	int score;
	BmobUserManager userManager;
	public ImageLoader imageLoader = ImageLoader.getInstance();
	String avatar;
	UpdateResponse ur;
	MyUser user;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.main);
		BmobUpdateAgent.update(this);
		BmobUpdateAgent.setUpdateListener(new BmobUpdateListener() {

			@Override
			public void onUpdateReturned(int updateStatus,
					UpdateResponse updateInfo) {
				if (updateStatus == UpdateStatus.Yes) {
					ur = updateInfo;
				} else if (updateStatus == UpdateStatus.IGNORED) {// 新增忽略版本更新
					Toast.makeText(MyMainActivity.this, "该版本已经被忽略更新",
							Toast.LENGTH_SHORT).show();
				}
			}
		});
		// BmobUpdateAgent.setUpdateOnlyWifi(false);
		imageLoader.init(ImageLoaderConfiguration.createDefault(this));
		initViews();
		initListener();
		setTabSelection(0);
		userManager = BmobUserManager.getInstance(this);
		user = userManager.getCurrentUser(MyUser.class);
		user.setInstallId(BmobInstallation.getInstallationId(this));
		user.update(this);

		score = user.getScore();
		avatar = user.getAvatar();
		if (avatar != null && !avatar.equals("")) {
			ImageLoader.getInstance().displayImage(avatar, iv_touxiang,
					ImageLoadOptions.getOptions());
			ImageLoader.getInstance().displayImage(avatar, iv_avatar,
					ImageLoadOptions.getOptions());
		} else {
			iv_touxiang.setImageResource(R.drawable.cat);
		}

		if (score < 1) {
			iv_score1.setVisibility(View.GONE);
		}
		if (score < 50) {
			iv_score2.setVisibility(View.GONE);
		}
		tv_username.setText(user.getUsername());

		final Handler handler = new Handler();
		final Runnable runnable = new Runnable() {
			@Override
			public void run() {
				if (mySearchFragment == null) {
					mySearchFragment = new MySearchFragment();
				}

				// mySearchFragment.mPullToRefreshView.setRefreshing();
				// // if (mySearchFragment.mPullToRefreshView.isRefreshing()) {
				// // handler.post(new Runnable() {
				// //
				// // @Override
				// // public void run() {
				// // mySearchFragment.mPullToRefreshView
				// // .onRefreshComplete();
				// // }
				// // });
				// //
				// // }
				setTabSelection(0);

			}

		};
		handler.postDelayed(runnable, 500);

	}

	public void toggleMenu(View view) {
		mleftMenu.toggle();
		shake();

	}

	@Override
	protected void onResume() {
		super.onResume();
		user = userManager.getCurrentUser(MyUser.class);
		if (user.getAvatar() != null && !user.getAvatar().equals("")) {
			ImageLoader.getInstance().displayImage(user.getAvatar(),
					iv_touxiang,
					com.wangdeduiwu.Yuema.config.ImageLoadOptions.getOptions());
			ImageLoader.getInstance().displayImage(user.getAvatar(), iv_avatar,
					com.wangdeduiwu.Yuema.config.ImageLoadOptions.getOptions());

		} else {
			iv_touxiang.setImageResource(R.drawable.cat);
		}

	}

	private void shake() {
		iv_touxiang.startAnimation(AnimationUtils.loadAnimation(this,
				R.anim.shake));
	}

	private void initViews() {

		iv_avatar = (ImageView) findViewById(R.id.iv_avatar);
		// tv_createdate = (TextView) findViewById(R.id.tv_createdate);
		// tv_logout = (TextView) findViewById(R.id.tv_logout);
		// tv_mydate = (TextView) findViewById(R.id.tv_mydate);
		// tv_update = (TextView) findViewById(R.id.tv_update);
		// tv_userreturn = (TextView) findViewById(R.id.tv_userreturn);
		tv_username = (TextView) findViewById(R.id.tv_username);
		iv_touxiang = (ImageView) findViewById(R.id.iv_touxiang);
		iv_score1 = (ImageView) findViewById(R.id.iv_score1);
		iv_score2 = (ImageView) findViewById(R.id.iv_score2);
		mleftMenu = (SlidingMenu) findViewById(R.id.id_menu);
		btn_mydate = (Button) findViewById(R.id.btn_mydate);
		btn_mydate.setBackgroundResource(R.drawable.myqian);
		btn_dateSearch = (Button) findViewById(R.id.btn_finddate);
		btn_dateSearch.setBackgroundResource(R.drawable.findqian);
		frameLayout = (FrameLayout) findViewById(R.id.FragmentContainer);
		myinfo = (LinearLayout) findViewById(R.id.myinfo);
		createdate = (LinearLayout) findViewById(R.id.createdate);
		updateversion = (LinearLayout) findViewById(R.id.updateversion);
		userreturn = (LinearLayout) findViewById(R.id.userreturn);
		logout = (LinearLayout) findViewById(R.id.logout);

	}

	private void initListener() {
		btn_mydate.setOnClickListener(this);
		btn_dateSearch.setOnClickListener(this);
		iv_avatar.setOnClickListener(this);
		// tv_createdate.setOnClickListener(this);
		// tv_update.setOnClickListener(this);
		// tv_mydate.setOnClickListener(this);
		// tv_logout.setOnClickListener(this);
		// tv_userreturn.setOnClickListener(this);
		myinfo.setOnClickListener(this);
		createdate.setOnClickListener(this);
		updateversion.setOnClickListener(this);
		userreturn.setOnClickListener(this);
		logout.setOnClickListener(this);

	}

	@Override
	public void onClick(View v) {

		switch (v.getId()) {
		case R.id.btn_mydate:
			setTabSelection(1);
			break;
		case R.id.btn_finddate:
			setTabSelection(0);
			break;
		case R.id.myinfo:
			startActivity(new Intent(this, MyInfoView.class));
			break;
		case R.id.createdate:
			Intent intent = new Intent(MyMainActivity.this,
					CreateDateActivity.class);
			startActivity(intent);
			break;
		case R.id.updateversion:
			BmobUpdateAgent.forceUpdate(this);
			BmobUpdateAgent.setUpdateListener(new BmobUpdateListener() {

				@Override
				public void onUpdateReturned(int updateStatus,
						UpdateResponse updateInfo) {
					if (updateStatus == UpdateStatus.No) {
						Toast.makeText(MyMainActivity.this, "目前版本为最新",
								Toast.LENGTH_SHORT).show();
					}
				}
			});
			break;
		case R.id.logout:
			BmobUser.logOut(this);
			startActivity(new Intent(this, LoginActivity.class));
			break;
		case R.id.userreturn:
			Intent intent3 = new Intent(this, UserFeedBackActivity.class);
			startActivity(intent3);
			break;
		case R.id.iv_avatar:
			Intent intent2 = new Intent(this, MyInfoView.class);
			startActivity(intent2);
			break;
		}

	}

	private static long firstTime;

	/**
	 * 连续按两次返回键就退出
	 */
	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		if (firstTime + 2000 > System.currentTimeMillis()) {
			super.onBackPressed();
		} else {
			Toast.makeText(this, "再按一次退出程序", Toast.LENGTH_SHORT).show();
		}
		firstTime = System.currentTimeMillis();
	}

	private void setTabSelection(int index) {
		clearSelection();
		FragmentTransaction transaction = getSupportFragmentManager()
				.beginTransaction().setCustomAnimations(android.R.anim.fade_in,
						android.R.anim.fade_out);
		hideFragments(transaction);
		switch (index) {
		case 0:
			btn_dateSearch.setBackgroundResource(R.drawable.findhou);
			if (mySearchFragment == null) {
				mySearchFragment = new MySearchFragment();
				transaction.add(R.id.FragmentContainer, mySearchFragment);

			} else {
				transaction.show(mySearchFragment);
			}

			break;

		case 1:
			btn_mydate.setBackgroundResource(R.drawable.myhou);
			if (myDateFragment == null) {
				myDateFragment = new MyDateFragment();
				transaction.add(R.id.FragmentContainer, myDateFragment);
			} else {
				transaction.show(myDateFragment);
			}
			break;
		}
		transaction.commit();
	}

	private void hideFragments(FragmentTransaction transaction2) {
		if (mySearchFragment != null) {
			transaction2.hide(mySearchFragment);
		}
		if (myDateFragment != null) {
			transaction2.hide(myDateFragment);
		}

	}

	private void clearSelection() {
		btn_dateSearch.setBackgroundResource(R.drawable.findqian);
		btn_mydate.setBackgroundResource(R.drawable.myqian);

	}

}
