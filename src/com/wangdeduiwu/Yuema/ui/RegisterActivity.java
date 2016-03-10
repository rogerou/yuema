package com.wangdeduiwu.Yuema.ui;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.exam.date.MyMainActivity;
import com.wangdeduiwu.Yuema.R;
import com.wangdeduiwu.Yuema.config.NetUtils;
import com.wangdeduiwu.Yuema.data.MyUser;

import cn.bmob.v3.BmobInstallation;
import cn.bmob.v3.listener.SaveListener;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;

import android.os.Bundle;
import android.text.InputType;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegisterActivity extends Activity {
	public static final String ACTION_REGISTER_SUCCESS_FINISH = "register.success.finish";// 注册成功之后登陆页面退出
	private Button btn_register;
	EditText et_account, et_password, et_email, et_ensurepwd, et_phone;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.register);
		et_account = (EditText) findViewById(R.id.et_account);
		et_password = (EditText) findViewById(R.id.et_password);
		et_email = (EditText) findViewById(R.id.et_email);
		et_phone = (EditText) findViewById(R.id.et_phone);
		et_phone.setInputType(InputType.TYPE_CLASS_PHONE);
		et_ensurepwd = (EditText) findViewById(R.id.et_ensurepwd);
		btn_register = (Button) findViewById(R.id.btn_register);
		btn_register.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {

				register();
			}
		});

	}

	public static boolean isEmail(String strEmail) {
		String strPattern = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";

		Pattern p = Pattern.compile(strPattern);
		Matcher m = p.matcher(strEmail);
		return m.matches();
	}

	private void register() {
		String name = et_account.getText().toString();
		String password = et_password.getText().toString();
		String pwd_again = et_ensurepwd.getText().toString();
		String email = et_email.getText().toString();
		String phone = et_phone.getText().toString();
		if (TextUtils.isEmpty(name)) {
			Toast.makeText(this, "用户名不能为空！", Toast.LENGTH_SHORT).show();
			return;
		}

		if (TextUtils.isEmpty(password)) {
			Toast.makeText(this, "密码不能为空！", Toast.LENGTH_SHORT).show();
			return;
		}

		if (TextUtils.isEmpty(pwd_again)) {
			Toast.makeText(this, "请确认密码！", Toast.LENGTH_SHORT).show();
			return;
		}
		if (!pwd_again.equals(password)) {
			Toast.makeText(this, "两段密码不一致！", Toast.LENGTH_SHORT).show();
			return;
		}
		if (TextUtils.isEmpty(email)) {
			Toast.makeText(this, "请填写你的邮箱！", Toast.LENGTH_SHORT).show();
			return;
		}
		if (!isEmail(email)) {
			Toast.makeText(this, "请输入正确的邮箱格式！", Toast.LENGTH_SHORT).show();
			return;
		}
		if (TextUtils.isEmpty(phone)) {
			Toast.makeText(this, "请填写你的手机号码！", Toast.LENGTH_SHORT).show();
			return;
		}
		boolean isNetConnected = NetUtils.isOpenNetwork(this);
		if (!isNetConnected) {
			Toast.makeText(this, "你的网络出现了问题！", Toast.LENGTH_SHORT).show();
			return;
		}
		final ProgressDialog progress = new ProgressDialog(
				RegisterActivity.this);
		progress.setMessage("正在注册...");
		progress.setCanceledOnTouchOutside(false);
		progress.show();
		MyUser user = new MyUser();
		user.setUsername(name);
		user.setEmail(email);
		user.setPassword(password);
		user.setPhone(phone);
		user.setDeviceType("android");
		user.setAge("20");
		user.setCollege("医学信息工程学院");
		user.setGrade("2012");
		user.setHobby("敲代码");
		user.setMajor("计算机专业与技术");
		user.setSchool("广州中医药大学");
		user.setScore(0);
		user.setSex("男");
		user.setSum("修得一手好电脑");
		user.setInstallId(BmobInstallation.getInstallationId(this));
		user.signUp(RegisterActivity.this, new SaveListener() {

			@Override
			public void onSuccess() {
				progress.dismiss();
				Toast.makeText(RegisterActivity.this, "注册成功",
						Toast.LENGTH_SHORT).show();
				sendBroadcast(new Intent(ACTION_REGISTER_SUCCESS_FINISH));
				// 启动主页
				Intent intent = new Intent(RegisterActivity.this,
						MyMainActivity.class);
				startActivity(intent);
				finish();
			}

			@Override
			public void onFailure(int arg0, String arg1) {

				Toast.makeText(RegisterActivity.this, "注册失败:" + arg1,
						Toast.LENGTH_SHORT).show();
				progress.dismiss();

			}
		});

	}
}
