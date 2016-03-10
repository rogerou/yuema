package com.wangdeduiwu.Yuema.ui;

import cn.bmob.v3.listener.SaveListener;

import com.wangdeduiwu.Yuema.R;
import com.wangdeduiwu.Yuema.data.FeedBack;

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

public class UserFeedBackActivity extends ActivityBase implements
		OnClickListener {

	EditText et_content, et_contract;
	Button btn_feedback;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_sendfeedback);
		btn_feedback = (Button) findViewById(R.id.btn_feedback);
		et_content = (EditText) findViewById(R.id.et_content);
		et_contract = (EditText) findViewById(R.id.et_contract);
		btn_feedback.setOnClickListener(this);
		et_content.setOnKeyListener(new OnKeyListener() {

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
		et_contract.setOnKeyListener(new OnKeyListener() {

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
		FeedBack feedBack = new FeedBack();
		feedBack.setContent(et_content.getText().toString());
		feedBack.setContract(et_contract.getText().toString());
		if (TextUtils.isEmpty(et_content.getText().toString())) {
			toast("请填写反馈的内容");
			return;
		}
		feedBack.save(this, new SaveListener() {

			@Override
			public void onSuccess() {
				toast("感谢你的反馈");
				finish();
			}

			@Override
			public void onFailure(int arg0, String arg1) {
				toast(arg1);
			}
		});
	}
}
