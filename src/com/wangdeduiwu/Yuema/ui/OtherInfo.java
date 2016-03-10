package com.wangdeduiwu.Yuema.ui;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.wangdeduiwu.Yuema.R;
import com.wangdeduiwu.Yuema.config.ImageLoadOptions;
import com.wangdeduiwu.Yuema.data.MyUser;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;

public class OtherInfo extends ActivityBase implements OnClickListener {
	ImageView iv_back, iv_touxiang, iv_score1, iv_score2;
	TextView tv_age, tv_sex, tv_hobby, tv_sum, tv_phone, tv_school, tv_college,
			tv_marjor, tv_grade, tv_username;
	String avatarUrl;
	int score;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.otherinfo1);
		initView();
		initData();
	}

	public void initView() {
		tv_username = (TextView) findViewById(R.id.tv_username);
		iv_score1 = (ImageView) findViewById(R.id.iv_score1);
		iv_score2 = (ImageView) findViewById(R.id.iv_score2);
		iv_back = (ImageView) findViewById(R.id.iv_back);
		iv_touxiang = (ImageView) findViewById(R.id.iv_touxiang);
		tv_age = (TextView) findViewById(R.id.tv_age);
		tv_college = (TextView) findViewById(R.id.tv_college);
		tv_grade = (TextView) findViewById(R.id.tv_grade);
		tv_hobby = (TextView) findViewById(R.id.tv_hobby);
		tv_marjor = (TextView) findViewById(R.id.tv_marjor);
		tv_phone = (TextView) findViewById(R.id.tv_phone);
		tv_school = (TextView) findViewById(R.id.tv_school);
		tv_sex = (TextView) findViewById(R.id.tv_sex);
		tv_sum = (TextView) findViewById(R.id.tv_sum);
		iv_back.setOnClickListener(this);

	}

	public void initData() {
		MyUser author = new MyUser();
		Intent intent = getIntent();
		author = (MyUser) intent.getSerializableExtra("author");
		if (author.getAvatar() != null) {
			avatarUrl = author.getAvatar();
			ImageLoader.getInstance().displayImage(avatarUrl, iv_touxiang,
					ImageLoadOptions.getOptions());
		} else {
			iv_touxiang.setImageResource(R.drawable.cat);
		}

		tv_username.setText(author.getUsername());
		tv_age.setText(author.getAge());
		tv_college.setText(author.getCollege());
		tv_grade.setText(author.getGrade());
		tv_hobby.setText(author.getHobby());
		tv_marjor.setText(author.getHobby());
		tv_phone.setText(author.getPhone());
		tv_school.setText(author.getSchool());
		tv_sex.setText(author.getSex());
		tv_sum.setText(author.getSum());
		score = author.getScore();
		if (score < 1) {
			iv_score1.setVisibility(View.GONE);
		}
		if (score < 50) {
			iv_score2.setVisibility(View.GONE);
		}
	}

	@Override
	public void onClick(View v) {
		if (v == iv_back) {
			finish();
		}
	}
}
