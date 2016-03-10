package com.wangdeduiwu.Yuema.data;

import java.io.Serializable;

import android.R.integer;
import android.os.Parcelable;

import cn.bmob.v3.BmobObject;
import cn.bmob.v3.listener.GetListener;
import cn.bmob.v3.listener.UpdateListener;

public class DateDetails extends BmobObject implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1156382360097063835L;
	/**
	 * 
	 */

	private MyUser author;
	private String date_content;
	private String date_where;
	private String date_month;
	private String date_day;
	private String date_hour;
	private String date_minute;
	private String date_people;
	private String date_trust;
	private String date_requet;
	private int love;
	private int yue_person;
	private int zhenYue;

	public String getDate_content() {
		return date_content;
	}

	public void setDate_content(String date_content) {
		this.date_content = date_content;
	}

	public String getDate_where() {
		return date_where;
	}

	public void setDate_where(String date_where) {
		this.date_where = date_where;
	}

	public String getDate_month() {
		return date_month;
	}

	public void setDate_month(String date_month) {
		this.date_month = date_month;
	}

	public String getDate_day() {
		return date_day;
	}

	public void setDate_day(String date_day) {
		this.date_day = date_day;
	}

	public String getDate_hour() {
		return date_hour;
	}

	public void setDate_hour(String date_hour) {
		this.date_hour = date_hour;
	}

	public String getDate_minute() {
		return date_minute;
	}

	public void setDate_minute(String date_minute) {
		this.date_minute = date_minute;
	}

	public String getDate_people() {
		return date_people;
	}

	public void setDate_people(String date_people) {
		this.date_people = date_people;
	}

	public String getDate_trust() {
		return date_trust;
	}

	public void setDate_trust(String date_trust) {
		this.date_trust = date_trust;
	}

	public String getDate_requet() {
		return date_requet;
	}

	public void setDate_requet(String date_requet) {
		this.date_requet = date_requet;
	}

	public int getYue_person() {
		return yue_person;
	}

	public void setYue_person(int yue_person) {
		this.yue_person = yue_person;
	}

	public MyUser getAuthor() {
		return author;
	}

	public void setAuthor(MyUser author) {
		this.author = author;
	}

	public int getLove() {
		return love;
	}

	public void setLove(int love) {
		this.love = love;
	}

	public int getZhenYue() {
		return zhenYue;
	}

	public void setZhenYue(int zhenYue) {
		this.zhenYue = zhenYue;
	}

}
