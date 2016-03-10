package com.wangdeduiwu.Yuema.data;

import cn.bmob.v3.BmobObject;

public class Yiyingyue extends BmobObject {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private DateDetails date;
	private MyUser user;
	private String yiyingyue;

	public DateDetails getDate() {
		return date;
	}

	public void setDate(DateDetails date) {
		this.date = date;
	}

	public MyUser getUser() {
		return user;
	}

	public void setUser(MyUser user) {
		this.user = user;
	}

	public String getYiyingyue() {
		return yiyingyue;
	}

	public void setYiyingyue(String yiyingyue) {
		this.yiyingyue = yiyingyue;
	}
}
