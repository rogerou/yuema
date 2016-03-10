package com.wangdeduiwu.Yuema.ui;

import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import cn.bmob.im.BmobUserManager;
import cn.bmob.v3.BmobInstallation;
import cn.bmob.v3.BmobPushManager;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.listener.CountListener;
import cn.bmob.v3.listener.PushListener;
import cn.bmob.v3.listener.SaveListener;
import cn.bmob.v3.listener.UpdateListener;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.wangdeduiwu.Yuema.R;
import com.wangdeduiwu.Yuema.config.ImageLoadOptions;
import com.wangdeduiwu.Yuema.data.DateDetails;
import com.wangdeduiwu.Yuema.data.IsLove;
import com.wangdeduiwu.Yuema.data.IsYue;
import com.wangdeduiwu.Yuema.data.MyUser;

public class DateDetailActivity extends ActivityBase implements OnClickListener {
	ImageView iv_back, iv_touxiang;
	TextView tv_username, tv_content, tv_time, tv_where, tv_people, tv_request,
			tv_createtime, tv_trust;
	Button btn_zan, btn_yue;
	String avatarUrl = "";
	BmobUserManager userManager;
	MyUser user;
	String objectid;
	int Yueperson, Yuepeople;
	JSONObject aps = new JSONObject();
	DateDetails dateDetails;
	BmobPushManager<BmobInstallation> bmobPushManager;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.datedetail);
		initView();
		initData(false, false, false);
		userManager = BmobUserManager.getInstance(this);
		user = userManager.getCurrentUser(MyUser.class);

		bmobPushManager = new BmobPushManager<BmobInstallation>(this);
	}

	public void initView() {
		tv_trust = (TextView) findViewById(R.id.tv_trust);
		iv_back = (ImageView) findViewById(R.id.iv_back);
		iv_touxiang = (ImageView) findViewById(R.id.iv_touxiang);
		btn_yue = (Button) findViewById(R.id.btn_yue);
		btn_zan = (Button) findViewById(R.id.btn_zan);
		tv_createtime = (TextView) findViewById(R.id.tv_createtime);
		tv_content = (TextView) findViewById(R.id.tv_content);
		tv_people = (TextView) findViewById(R.id.tv_people);
		tv_request = (TextView) findViewById(R.id.tv_request);
		tv_time = (TextView) findViewById(R.id.tv_time);
		tv_username = (TextView) findViewById(R.id.tv_username);
		tv_where = (TextView) findViewById(R.id.tv_where);
		iv_back.setOnClickListener(this);
		iv_touxiang.setOnClickListener(this);
		btn_zan.setOnClickListener(this);
		btn_yue.setOnClickListener(this);

	}

	private void initData(boolean setlove, boolean setYue, boolean sendauthor) {
		DateDetails dateDetails = new DateDetails();
		Intent intent = getIntent();
		dateDetails = (DateDetails) intent.getSerializableExtra("date");
		objectid = dateDetails.getObjectId();
		avatarUrl = dateDetails.getAuthor().getAvatar();
		if (avatarUrl != null && !avatarUrl.equals("")) {
			ImageLoader.getInstance().displayImage(avatarUrl, iv_touxiang,
					ImageLoadOptions.getOptions());
		} else {
			iv_touxiang.setImageResource(R.drawable.cat);
		}
		tv_username = (TextView) findViewById(R.id.tv_username);
		tv_username.setText(dateDetails.getAuthor().getUsername());
		Yuepeople = Integer.valueOf(dateDetails.getDate_people());
		Yueperson = dateDetails.getZhenYue();
		tv_content.setText(dateDetails.getDate_content());
		tv_people.setText(dateDetails.getDate_people());
		tv_request.setText(dateDetails.getDate_requet());
		tv_time.setText(dateDetails.getDate_month() + "月"
				+ dateDetails.getDate_day() + "日" + dateDetails.getDate_hour()
				+ "时" + dateDetails.getDate_minute() + "分");
		tv_where.setText(dateDetails.getDate_where());
		tv_createtime.setText(dateDetails.getCreatedAt());
		tv_trust.setText(dateDetails.getDate_trust());

		if (setlove) {
			IsLove love = new IsLove();
			love.setUser(user);
			love.setDateobjectid(dateDetails);
			love.save(DateDetailActivity.this, new SaveListener() {

				@Override
				public void onSuccess() {
					toast("点赞成功");

				}

				@Override
				public void onFailure(int arg0, String arg1) {
					toast("点赞失败");
				}
			});
			dateDetails.setLove(dateDetails.getLove() + 1);
			dateDetails.update(DateDetailActivity.this, new UpdateListener() {
				@Override
				public void onSuccess() {

				}

				@Override
				public void onFailure(int arg0, String arg1) {

				}
			});
		}

		if (setYue) {
			IsYue isYue = new IsYue();
			isYue.setDateobjectid(dateDetails);
			isYue.setUser(user);
			isYue.save(DateDetailActivity.this, new SaveListener() {

				@Override
				public void onSuccess() {

				}

				@Override
				public void onFailure(int arg0, String arg1) {
					toast("应约失败");

				}
			});
			try {
				aps.put("alert", "你的约会" + dateDetails.getDate_content()
						+ "有人应约啦");
				aps.put("userid", user.getObjectId());
				aps.put("dateid", dateDetails.getObjectId());
			} catch (JSONException e) {
				e.printStackTrace();
			}

			// BmobQuery<MyUser> query = new BmobQuery<MyUser>();
			// BmobPushManager<MyUser> pushManager = new
			// BmobPushManager<MyUser>(
			// this);
			// pushManager.getQuery();
			// query.addWhereEqualTo("objectId", "4e58245abd");
			// pushManager.setQuery(query);
			// pushManager.pushMessage(aps);

			BmobQuery<BmobInstallation> query = BmobInstallation.getQuery();
			query.addWhereEqualTo("installationId", dateDetails.getAuthor()
					.getInstallId());
			bmobPushManager.setQuery(query);
			bmobPushManager.pushMessage(aps, new PushListener() {

				@Override
				public void onSuccess() {
					toast("应约成功,如果害羞就等待消息吧，大胆的就点他/她的头像主动约起");

				}

				@Override
				public void onFailure(int arg0, String arg1) {
					toast("" + arg1);
				}
			});

		}
		if (sendauthor) {
			MyUser author = new MyUser();
			author = dateDetails.getAuthor();
			Intent intent2 = new Intent(DateDetailActivity.this,
					OtherInfo.class);
			intent2.putExtra("author", author);
			startActivity(intent2);

		}

	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.iv_back:
			finish();
			break;
		case R.id.btn_zan:
			zan();
			break;
		case R.id.btn_yue:
			yue();
			break;
		case R.id.iv_touxiang:
			initData(false, false, true);
			break;
		}

	}

	public void yue() {
		if (Yuepeople == Yueperson) {
			toast("此约会已经满员啦");

		} else {

			BmobQuery<IsYue> query = new BmobQuery<IsYue>();
			query.addWhereEqualTo("user", user.getObjectId());
			query.addWhereEqualTo("Dateobjectid", objectid);
			query.count(this, IsYue.class, new CountListener() {

				@Override
				public void onSuccess(int arg0) {
					if (arg0 > 0) {
						toast("你已经应约啦");
					}
					if (arg0 == 0) {

						initData(false, true, false);

					}
				}

				@Override
				public void onFailure(int arg0, String arg1) {
					toast("应约失败");
				}
			});
		}
	}

	public void zan() {

		BmobQuery<IsLove> query = new BmobQuery<IsLove>();
		query.addWhereEqualTo("user", user.getObjectId());
		query.addWhereEqualTo("Dateobjectid", objectid);
		query.count(this, IsLove.class, new CountListener() {

			@Override
			public void onSuccess(int arg0) {
				if (arg0 > 0) {
					toast("你已经点过赞啦");
					return;

				}
				if (arg0 == 0)

				{
					initData(true, false, false);
				}

			}

			@Override
			public void onFailure(int arg0, String arg1) {
				toast("点赞失败");
			}
		});
	}

}
