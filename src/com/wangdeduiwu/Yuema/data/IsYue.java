package com.wangdeduiwu.Yuema.data;

import cn.bmob.v3.BmobObject;

public class IsYue extends BmobObject{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6398797556349210272L;
	private DateDetails Dateobjectid;
	private MyUser user;
	public MyUser getUser() {
		return user;
	}
	public void setUser(MyUser user) {
		this.user = user;
	}
	public DateDetails getDateobjectid() {
		return Dateobjectid;
	}
	public void setDateobjectid(DateDetails dateobjectid) {
		this.Dateobjectid = dateobjectid;
	}
	
}
