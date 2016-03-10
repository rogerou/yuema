package com.wangdeduiwu.Yuema.data;

import cn.bmob.v3.BmobObject;

public class IsLove extends BmobObject {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4227110587486436976L;
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
		Dateobjectid = dateobjectid;
	}

}
