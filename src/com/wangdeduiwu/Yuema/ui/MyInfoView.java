package com.wangdeduiwu.Yuema.ui;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.tencent.a.b.i;
import com.wangdeduiwu.Yuema.config.ImageLoadOptions;
import com.wangdeduiwu.Yuema.config.PhotoUtil;

import android.R.integer;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import cn.bmob.im.BmobUserManager;
import cn.bmob.im.util.BmobLog;
import cn.bmob.v3.datatype.BmobFile;
import cn.bmob.v3.listener.UpdateListener;
import cn.bmob.v3.listener.UploadFileListener;

import com.wangdeduiwu.Yuema.R;
import com.wangdeduiwu.Yuema.config.BmobConstants;
import com.wangdeduiwu.Yuema.data.MyUser;

public class MyInfoView extends ActivityBase implements OnClickListener {
	ImageButton ib_edit;
	ImageView iv_back, iv_touxiang, iv_score1, iv_score2;
	TextView tv_age, tv_sex, tv_hobby, tv_sum, tv_phone, tv_school, tv_college,
			tv_marjor, tv_grade, tv_username;

	public String avatar;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.myinfo);
		Initview();
		initData();

	}

	public void initData() {
		userManager = BmobUserManager.getInstance(this);
		MyUser user = userManager.getCurrentUser(MyUser.class);
		avatar = user.getAvatar();
		tv_username.setText(user.getUsername());
		tv_age.setText(user.getAge());
		tv_college.setText(user.getCollege());
		tv_grade.setText(user.getGrade());
		tv_hobby.setText(user.getHobby());
		tv_marjor.setText(user.getMajor());
		tv_phone.setText(user.getPhone());
		tv_school.setText(user.getSchool());
		tv_sex.setText(user.getSex());
		tv_sum.setText(user.getSum());
		int score = user.getScore();
		if (score < 1) {
			iv_score1.setVisibility(View.GONE);
		}
		if (score < 50) {
			iv_score2.setVisibility(View.GONE);
		}

		if (avatar != null && !avatar.equals("")) {
			ImageLoader.getInstance().displayImage(avatar, iv_touxiang,
					ImageLoadOptions.getOptions());
		} else {
			iv_touxiang.setImageResource(R.drawable.cat);
		}
	}

	private void Initview() {
		tv_username = (TextView) findViewById(R.id.tv_username);
		iv_score1 = (ImageView) findViewById(R.id.iv_score1);
		iv_score2 = (ImageView) findViewById(R.id.iv_score2);
		ib_edit = (ImageButton) findViewById(R.id.ib_edit);
		iv_back = (ImageView) findViewById(R.id.iv_back);
		iv_touxiang = (ImageView) findViewById(R.id.iv_touxiang);
		tv_age = (TextView) findViewById(R.id.tv_age);
		tv_college = (TextView) findViewById(R.id.TV_college);
		tv_grade = (TextView) findViewById(R.id.tv_grade);
		tv_hobby = (TextView) findViewById(R.id.tv_hobby);
		tv_marjor = (TextView) findViewById(R.id.tv_marjor);
		tv_phone = (TextView) findViewById(R.id.tv_phone);
		tv_school = (TextView) findViewById(R.id.tv_school);
		tv_sex = (TextView) findViewById(R.id.tv_sex);
		tv_sum = (TextView) findViewById(R.id.tv_sum);
		ib_edit.setOnClickListener(this);
		iv_back.setOnClickListener(this);

	}

	@Override
	protected void onResume() {
		super.onResume();
		if (avatar != null && !avatar.equals("")) {
			ImageLoader.getInstance().displayImage(avatar, iv_touxiang,
					com.wangdeduiwu.Yuema.config.ImageLoadOptions.getOptions());
		} else {
			iv_touxiang.setImageResource(R.drawable.cat);
		}

	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.ib_edit:
			Intent intent = new Intent(this, EditUserInfoActivity.class);
			startActivity(intent);
			break;
		case R.id.iv_back:
			finish();

		}

	}

}
