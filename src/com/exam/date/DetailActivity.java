package com.exam.date;

import cn.bmob.im.BmobUserManager;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.listener.GetListener;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.wangdeduiwu.Yuema.R;
import com.wangdeduiwu.Yuema.config.ImageLoadOptions;
import com.wangdeduiwu.Yuema.data.DateDetails;
import com.wangdeduiwu.Yuema.data.MyUser;
import com.wangdeduiwu.Yuema.ui.ActivityBase;

import B.t;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class DetailActivity extends ActivityBase {

	TextView tv_username, tv_content, tv_time, tv_where, tv_people, tv_request,
			tv_createtime, tv_trust;
	ImageView iv_touxiang;
	BmobUserManager userManager;
	String avatarUrl;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.mydatedetail);
		userManager = BmobUserManager.getInstance(this);
		MyUser user = userManager.getCurrentUser(MyUser.class);
		initView();
		initData();

		avatarUrl = user.getAvatar();
		if (avatarUrl != null && !avatarUrl.equals("")) {
			ImageLoader.getInstance().displayImage(avatarUrl, iv_touxiang,
					ImageLoadOptions.getOptions());
		} else {
			iv_touxiang.setImageResource(R.drawable.cat);
		}

		ImageView imageView = (ImageView) findViewById(R.id.iv_back);
		imageView.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				finish();
				
			}
		});
	}

	private void initData() {
		DateDetails dateDetails = new DateDetails();
		Intent intent = getIntent();
		dateDetails = (DateDetails) intent.getSerializableExtra("date");

		tv_content.setText(dateDetails.getDate_content());
		tv_createtime.setText(dateDetails.getCreatedAt());
		tv_people.setText(dateDetails.getDate_people());
		tv_request.setText(dateDetails.getDate_requet());
		tv_time.setText(dateDetails.getDate_month() + "月"
				+ dateDetails.getDate_day() + "日" + dateDetails.getDate_hour()
				+ "时" + dateDetails.getDate_minute() + "分");
		tv_trust.setText(dateDetails.getDate_trust());
		tv_where.setText(dateDetails.getDate_where());
		BmobQuery<MyUser> query = new BmobQuery<MyUser>();
		query.getObject(this, dateDetails.getAuthor().getObjectId(),
				new GetListener<MyUser>() {

					@Override
					public void onSuccess(MyUser arg0) {
						tv_username.setText(arg0.getUsername());
					}

					@Override
					public void onFailure(int arg0, String arg1) {
						toast(arg1);
					}
				});

	}

	private void initView() {
		tv_content = (TextView) findViewById(R.id.tv_content);
		tv_createtime = (TextView) findViewById(R.id.tv_createtime);
		tv_people = (TextView) findViewById(R.id.tv_people);
		tv_request = (TextView) findViewById(R.id.tv_request);
		tv_time = (TextView) findViewById(R.id.tv_time);
		tv_trust = (TextView) findViewById(R.id.tv_trust);
		tv_username = (TextView) findViewById(R.id.tv_username);
		tv_where = (TextView) findViewById(R.id.tv_where);
		iv_touxiang = (ImageView) findViewById(R.id.iv_touxiang);

	}
}
