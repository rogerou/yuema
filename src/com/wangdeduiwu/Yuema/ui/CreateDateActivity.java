package com.wangdeduiwu.Yuema.ui;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnKeyListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import cn.bmob.im.bean.BmobChatUser;
import cn.bmob.v3.listener.SaveListener;

import com.wangdeduiwu.Yuema.R;
import com.wangdeduiwu.Yuema.config.NetUtils;
import com.wangdeduiwu.Yuema.data.DateDetails;
import com.wangdeduiwu.Yuema.data.Yifabu;
import com.wangdeduiwu.Yuema.data.MyUser;

public class CreateDateActivity extends ActivityBase implements OnClickListener {
	Button btn_creat;
	EditText et_where, et_content, et_month, et_date, et_hour, et_minute,
			et_people, et_request, et_trust;
	ImageView iv_back;
	int m;
	int d;
	int h;
	int mi;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.releasedate);
		init();
		boolean isNetConnected = NetUtils.isOpenNetwork(this);
		if (!isNetConnected) {
			toast("你的网络出现了问题！");
			return;
		}

	}

	public void init() {
		et_where = (EditText) findViewById(R.id.et_where);
		et_content = (EditText) findViewById(R.id.et_content);
		et_request = (EditText) findViewById(R.id.et_request);
		et_people = (EditText) findViewById(R.id.et_people);
		et_month = (EditText) findViewById(R.id.et_month);
		et_trust = (EditText) findViewById(R.id.et_trust);

		et_date = (EditText) findViewById(R.id.et_date);
		et_hour = (EditText) findViewById(R.id.et_hour);
		et_minute = (EditText) findViewById(R.id.et_minute);
		iv_back = (ImageView) findViewById(R.id.iv_back);
		btn_creat = (Button) findViewById(R.id.btn_create);
		btn_creat.setOnClickListener(this);
		iv_back.setOnClickListener(this);

		et_request.setOnKeyListener(new OnKeyListener() {

			@Override
			public boolean onKey(View v, int keyCode, KeyEvent event) {
				if (keyCode == KeyEvent.KEYCODE_ENTER
						&& event.getAction() == KeyEvent.ACTION_DOWN) {
					/* 隐藏软键盘 */
					InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
					if (inputMethodManager.isActive()) {
						inputMethodManager.hideSoftInputFromWindow(
								v.getApplicationWindowToken(), 0);

					}

					return true;
				}
				return false;
			}
		});

	}

	@Override
	public void onClick(View v) {
		if (v == btn_creat) {
			createDate();
		}
		if (v == iv_back) {
			finish();
		}

	}

	String content = "";
	String where = "";
	String people = "";
	String month = "";
	String date = "";
	String hour = "";
	String minute = "";
	String trust = "";
	String request = "";

	private void createDate() {
		content = et_content.getText().toString();
		where = et_where.getText().toString();
		people = et_people.getText().toString();
		trust = et_trust.getText().toString();
		request = et_request.getText().toString();
		month = et_month.getText().toString();
		date = et_date.getText().toString();
		hour = et_hour.getText().toString();
		minute = et_minute.getText().toString();

		if (TextUtils.isEmpty(content)) {
			toast("请填写约会内容");
			return;
		}

		if (TextUtils.isEmpty(where)) {
			toast("请输入地点");
			return;
		}
		if (TextUtils.isEmpty(people)) {
			toast("请输入人数");
			return;
		}
		if (TextUtils.isEmpty(trust)) {
			toast("请输入信物");
			return;
		}
		if (TextUtils.isEmpty(request)) {
			toast("请输入要求");
			return;
		}
		if (TextUtils.isEmpty(month)) {
			toast("请输入月份");
			return;
		} else {
			int m = Integer.valueOf(month);
		}
		if (TextUtils.isEmpty(date)) {
			toast("请输入日期");
			return;

		} else {
			int d = Integer.valueOf(date);
		}
		if (TextUtils.isEmpty(hour)) {
			toast("请输入时间");
			return;
		} else {
			int h = Integer.valueOf(hour);
		}
		if (TextUtils.isEmpty(minute)) {
			toast("请输入分钟");
			return;
		} else {

			int mi = Integer.valueOf(minute);
		}

		if (m > 12 || m < 0) {
			toast("请输入正确的月份");
		}
		if (d > 31 || d < 0) {
			toast("请输入正确的日期");
		}
		if (h > 24 || h < 0) {
			toast("请输入正确的时间");
		}
		if (mi > 60 || mi < 0) {
			toast("请输入正确的分钟");
		}
		final ProgressDialog progress = new ProgressDialog(
				CreateDateActivity.this);
		progress.setMessage("正在发布...");
		progress.setCanceledOnTouchOutside(false);
		progress.show();
		MyUser user = BmobChatUser.getCurrentUser(this, MyUser.class);
		DateDetails dateDetails = new DateDetails();
		Yifabu state = new Yifabu();
		state.setDate(dateDetails);
		state.setUser(user);
		state.setYifabu("已处理");
		state.save(this, new SaveListener() {

			@Override
			public void onSuccess() {

			}

			@Override
			public void onFailure(int arg0, String arg1) {
				toast(arg1);
			}
		});
		dateDetails.setDate_content(content);
		dateDetails.setDate_day(date);
		dateDetails.setDate_hour(hour);
		dateDetails.setDate_month(month);
		dateDetails.setDate_minute(minute);
		dateDetails.setDate_people(people);
		dateDetails.setDate_requet(request);
		dateDetails.setDate_trust(trust);
		dateDetails.setDate_where(where);
		dateDetails.setAuthor(user);
		dateDetails.setYue_person(0);
		dateDetails.setLove(0);
		dateDetails.setZhenYue(0);
		dateDetails.save(this, new SaveListener() {

			@Override
			public void onSuccess() {
				toast("发布约会成功！");
				progress.dismiss();
				setResult(RESULT_OK);
				finish();
			}

			@Override
			public void onFailure(int arg0, String arg1) {
				toast("发布约会失败：" + arg1);
				progress.dismiss();
			}
		});
	}

}
