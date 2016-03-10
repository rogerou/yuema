package com.wangdeduiwu.Yuema.ui;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.listener.GetListener;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.wangdeduiwu.Yuema.R;
import com.wangdeduiwu.Yuema.config.ImageLoadOptions;
import com.wangdeduiwu.Yuema.data.DateDetails;
import com.wangdeduiwu.Yuema.data.MyUser;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class PushActivity extends ActivityBase implements OnClickListener {
	ImageView iv_back, iv_touxiang, iv_score1, iv_score2;
	TextView tv_age, tv_sex, tv_hobby, tv_sum, tv_phone, tv_school, tv_college,
			tv_marjor, tv_grade, tv_username;
	Button btn_accept, btn_refuse;
	Intent intent;
	String dateid, userid;
	BmobQuery<MyUser> queryu = new BmobQuery<MyUser>();
	BmobQuery<DateDetails> queryd = new BmobQuery<DateDetails>();
	DateDetails date;
	MyUser user;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.otherinfo);
		initView();
		intent = getIntent();
		dateid = intent.getStringExtra("dateid");
		userid = intent.getStringExtra("userid");

		queryu.getObject(this, userid, new GetListener<MyUser>() {

			@Override
			public void onFailure(int arg0, String arg1) {
				toast(arg1);
			}

			@Override
			public void onSuccess(MyUser arg0) {
				user = arg0;

				tv_username.setText(user.getUsername());
				tv_college.setText(user.getCollege());
				tv_grade.setText(user.getGrade());
				tv_hobby.setText(user.getHobby());
				tv_marjor.setText(user.getMajor());
				tv_phone.setText(user.getPhone());
				tv_school.setText(user.getSchool());
				tv_sex.setText(user.getSex());
				tv_sum.setText(user.getSum());
				tv_age.setText(user.getAge());
				if (user.getAvatar() != null && !user.getAvatar().equals("")) {
					ImageLoader.getInstance().displayImage(user.getAvatar(),
							iv_touxiang, ImageLoadOptions.getOptions());
				} else {
					iv_touxiang.setImageResource(R.drawable.cat);
				}
				if (user.getScore() < 1) {
					iv_score1.setVisibility(View.GONE);

				}
				if (user.getScore() < 50) {
					iv_score2.setVisibility(View.GONE);
				}

			}
		});

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
		btn_accept = (Button) findViewById(R.id.btn_accept);
		btn_refuse = (Button) findViewById(R.id.btn_refuse);
		btn_accept.setOnClickListener(this);
		btn_refuse.setOnClickListener(this);
		iv_back.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn_accept:

			queryu.getObject(this, userid, new GetListener<MyUser>() {

				@Override
				public void onFailure(int arg0, String arg1) {
					toast(arg1);
				}

				@Override
				public void onSuccess(MyUser arg0) {
					toast("应约成功，赶紧去联系这个小伙伴吧");
					arg0.setScore(arg0.getScore() + 1);
					arg0.update(PushActivity.this);

				}

			});

			queryd.getObject(this, dateid, new GetListener<DateDetails>() {

				@Override
				public void onSuccess(DateDetails arg0) {
					arg0.setZhenYue(arg0.getZhenYue() + 1);
					arg0.update(PushActivity.this);
				}

				@Override
				public void onFailure(int arg0, String arg1) {

					toast(arg1);
				}
			});

			break;

		case R.id.btn_refuse:
			toast("真可怜，这位小伙伴被拒绝了。");

			break;

		case R.id.iv_back:
			finish();
			break;
		}
	}
}