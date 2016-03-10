package com.wangdeduiwu.Yuema.data;

import cn.bmob.v3.BmobObject;

public class FeedBack extends BmobObject {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String content;
	private String contract;

	public String getContract() {
		return contract;
	}

	public void setContract(String contract) {
		this.contract = contract;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
}
