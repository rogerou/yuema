package com.wangdeduiwu.Yuema.ui;

import cn.bmob.v3.BmobUser;
import cn.bmob.v3.listener.ResetPasswordListener;

import com.wangdeduiwu.Yuema.R;

import android.content.Context;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnKeyListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ReturnPasswordActivty extends BaseActivity implements
		OnClickListener {
	Button btn_ok, btn_cancel;
	EditText et_registeremail;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.resetpassword);
		btn_ok = (Button) findViewById(R.id.btn_ok);
		btn_cancel = (Button) findViewById(R.id.btn_cancel);
		et_registeremail = (EditText) findViewById(R.id.et_registeremail);

		et_registeremail.setOnKeyListener(new OnKeyListener() {

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

		btn_ok.setOnClickListener(this);
		btn_cancel.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn_ok:
			BmobUser.resetPassword(this, et_registeremail.getText().toString(),
					new ResetPasswordListener() {

						@Override
						public void onSuccess() {
							Toast.makeText(ReturnPasswordActivty.this,
									"邮件已发送到你邮箱，请注意查收", Toast.LENGTH_LONG)
									.show();
							finish();

						}

						@Override
						public void onFailure(int arg0, String arg1) {
							Toast.makeText(ReturnPasswordActivty.this, arg1,
									Toast.LENGTH_LONG).show();
						}
					});

			break;

		case R.id.btn_cancel:
			finish();
			break;
		}
	}
}
