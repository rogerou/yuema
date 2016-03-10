package com.wangdeduiwu.Yuema.data;

import cn.bmob.v3.BmobObject;

public class Yifabu extends BmobObject {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private DateDetails date;
	private MyUser user;
	private String yifabu;
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

	public String getYifabu() {
		return yifabu;
	}

	public void setYifabu(String yifabu) {
		this.yifabu = yifabu;
	}

}
