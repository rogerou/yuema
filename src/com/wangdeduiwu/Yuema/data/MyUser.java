package com.wangdeduiwu.Yuema.data;

import java.io.Serializable;

import cn.bmob.im.bean.BmobChatUser;
import cn.bmob.v3.BmobInstallation;

public class MyUser extends BmobChatUser implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 9144049232833820166L;
	/**
	 * 
	 */

	private BmobInstallation installationId;
	private String comment;
	private String phone;
	private String school;
	private String age;
	private String college;
	private String major;
	private String grade;
	private String sex;
	private String hobby;
	public int score = 0;

	public String getSchool() {
		return school;
	}

	public void setSchool(String school) {
		this.school = school;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public String getCollege() {
		return college;
	}

	public void setCollege(String college) {
		this.college = college;
	}

	public String getMajor() {
		return major;
	}

	public void setMajor(String major) {
		this.major = major;
	}

	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getHobby() {
		return hobby;
	}

	public void setHobby(String hobby) {
		this.hobby = hobby;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public String getSum() {
		return sum;
	}

	public void setSum(String sum) {
		this.sum = sum;
	}

	private String sum;

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

}
