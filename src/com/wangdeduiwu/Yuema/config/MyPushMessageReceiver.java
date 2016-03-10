package com.wangdeduiwu.Yuema.config;

import org.json.JSONObject;

import cn.bmob.im.BmobNotifyManager;
import cn.bmob.im.BmobUserManager;
import cn.bmob.im.bean.BmobChatUser;
import cn.bmob.im.bean.BmobMsg;
import cn.bmob.im.util.BmobJsonUtil;

import com.wangdeduiwu.Yuema.R;
import com.wangdeduiwu.Yuema.config.PushConstants;
import com.wangdeduiwu.Yuema.ui.PushActivity;

import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

public class MyPushMessageReceiver extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
		if (intent.getAction().equals(PushConstants.ACTION_MESSAGE)) {
			String json = intent.getStringExtra("msg");
			boolean isNetConnected = NetUtils.isOpenNetwork(context);
			if (isNetConnected) {
				parseMessage(context, json);
			} else {
				Toast.makeText(context, "无法连接到网络", Toast.LENGTH_LONG).show();
			}
		}

	}

	private void parseMessage(Context context, String json) {
		JSONObject jo;

		try {
			jo = new JSONObject(json);
			final String userid = BmobJsonUtil.getString(jo, "userid");
			final String dateid = BmobJsonUtil.getString(jo, "dateid");
			final String messagecontent = BmobJsonUtil.getString(jo, "alert");

			shownotify(context, messagecontent, userid, dateid);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private void shownotify(Context context, String message, String userid,
			String dateid) {
		String title = "约吗";
		String tickertext = message;
		Intent intent = new Intent(context, PushActivity.class);
		intent.putExtra("dateid", dateid);
		intent.putExtra("userid", userid);

		boolean isAllowVoice = true;
		boolean isAllowVibrate = true;
		BmobNotifyManager.getInstance(context).showNotifyWithExtras(
				isAllowVoice, isAllowVibrate, R.drawable.icon, tickertext,
				title, tickertext, intent);
	}

}
