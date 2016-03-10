package com.wangdeduiwu.Yuema.ui;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import cn.bmob.im.BmobUserManager;
import cn.bmob.im.util.BmobLog;
import cn.bmob.v3.datatype.BmobFile;
import cn.bmob.v3.listener.SaveListener;
import cn.bmob.v3.listener.UpdateListener;
import cn.bmob.v3.listener.UploadFileListener;

import com.wangdeduiwu.Yuema.config.BmobConstants;
import com.wangdeduiwu.Yuema.config.PhotoUtil;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.wangdeduiwu.Yuema.R;
import com.wangdeduiwu.Yuema.data.MyUser;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.text.InputType;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class EditUserInfoActivity extends ActivityBase implements OnClickListener {
	ImageView iv_touxiang, iv_back, iv_score1, iv_score2;
	Button btn_ok;
	EditText et_sex, et_age, et_hobby, et_sum, et_phone, et_school, et_college,
			et_marjor, et_grade;
	TextView tv_username;
	BmobUserManager userManager;
	MyUser user;
	LinearLayout layout_all;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		int currentapiVersion = android.os.Build.VERSION.SDK_INT;
		if (currentapiVersion >= 14) {
			getWindow().getDecorView().setSystemUiVisibility(
					View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
		}
		setContentView(R.layout.setmyinfo);
		initView();
		userManager = BmobUserManager.getInstance(this);
		user = userManager.getCurrentUser(MyUser.class);
		tv_username.setText(user.getUsername());
		tv_username.setText(user.getUsername());
		et_age.setText(user.getAge());
		et_college.setText(user.getCollege());
		et_grade.setText(user.getGrade());
		et_hobby.setText(user.getHobby());
		et_marjor.setText(user.getMajor());
		et_phone.setText(user.getPhone());
		et_school.setText(user.getSchool());
		et_sex.setText(user.getSex());
		et_sum.setText(user.getSum());
		refreshAvatar(user.getAvatar());
		if (user.getScore() < 1) {
			iv_score1.setVisibility(View.GONE);
		}
		if (user.getScore() < 50) {
			iv_score2.setVisibility(View.GONE);
		}
	
	}

	private void initView() {
		layout_all = (LinearLayout) findViewById(R.id.layout_all);
		tv_username = (TextView) findViewById(R.id.tv_username);
		iv_score1 = (ImageView) findViewById(R.id.iv_score1);
		iv_score2 = (ImageView) findViewById(R.id.iv_score2);
		iv_touxiang = (ImageView) findViewById(R.id.iv_touxiang);
		iv_back = (ImageView) findViewById(R.id.iv_back);
		btn_ok = (Button) findViewById(R.id.btn_ok);
		et_age = (EditText) findViewById(R.id.et_age);
		et_age.setInputType(InputType.TYPE_CLASS_NUMBER);
		et_college = (EditText) findViewById(R.id.et_college);
		et_grade = (EditText) findViewById(R.id.et_grade);
		et_hobby = (EditText) findViewById(R.id.et_hobby);
		et_marjor = (EditText) findViewById(R.id.et_marjor);
		et_phone = (EditText) findViewById(R.id.et_phone);
		et_phone.setInputType(InputType.TYPE_CLASS_PHONE);
		et_school = (EditText) findViewById(R.id.et_school);
		et_sex = (EditText) findViewById(R.id.et_sex);
		et_sum = (EditText) findViewById(R.id.et_sum);
		iv_touxiang.setOnClickListener(this);
		iv_back.setOnClickListener(this);
		btn_ok.setOnClickListener(this);
		
	}

	@Override
	public void onClick(View v) {
		if (v == iv_touxiang) {
			showAvatarPop();

		} else if (v == iv_back) {
			finish();

		} else if (v == btn_ok) {
			udateinfo();

		}

	}

	String college, phone, sex, sum, grade, hobby, age, marjor, school;

	private void udateinfo() {
		userManager = BmobUserManager.getInstance(this);
		user = userManager.getCurrentUser(MyUser.class);
		college = et_college.getText().toString();
		phone = et_phone.getText().toString();
		sex = et_sex.getText().toString();
		sum = et_sum.getText().toString();
		grade = et_grade.getText().toString();
		hobby = et_hobby.getText().toString();
		age = et_age.getText().toString();
		marjor = et_marjor.getText().toString();
		school = et_school.getText().toString();
		if (TextUtils.isEmpty(age)) {
			toast("请输入你的年龄");
			return;
		}
		if (TextUtils.isEmpty(college)) {
			toast("请输入你的学院");
			return;
		}
		if (TextUtils.isEmpty(grade)) {
			toast("请输入你的年级");
			return;
		}
		if (TextUtils.isEmpty(hobby)) {
			toast("请输入你的爱好让别人更好的了解你哦");
			return;
		}
		if (TextUtils.isEmpty(marjor)) {
			toast("请输入你的专业");
			return;
		}
		if (TextUtils.isEmpty(phone)) {
			toast("请输入你的手机号码");
			return;
		}
		if (TextUtils.isEmpty(school)) {
			toast("请输入你的学校");
			return;
		}
		if (TextUtils.isEmpty(sex)) {
			toast("请输入你的性别");
			return;
		}
		if (TextUtils.isEmpty(sum)) {
			toast("请输入你的简介");
			return;
		}
		int a = Integer.valueOf(age);
		if (a < 0 || a > 150) {
			toast("请输入正确的年龄，不要突破天际");
			return;
		}

		user.setAge(age);
		user.setGrade(grade);
		user.setCollege(college);
		user.setHobby(hobby);
		user.setMajor(marjor);
		user.setPhone(phone);
		user.setSum(sum);
		user.setSex(sex);
		user.setSchool(school);
		final ProgressDialog progress = new ProgressDialog(
				EditUserInfoActivity.this);
		progress.setMessage("正在发布...");
		progress.setCanceledOnTouchOutside(false);
		progress.show();
		user.update(this, new UpdateListener() {

			@Override
			public void onSuccess() {
				toast("资料修改成功");
				progress.dismiss();
				finish();

			}

			@Override
			public void onFailure(int arg0, String arg1) {
				// TODO Auto-generated method stub
				toast("资料修改失败:" + arg1);
				progress.dismiss();
			}
		});

	}
	
	
	/**
	 * 更新头像 refreshAvatar
	 * 
	 * @return void
	 * @throws
	 */
	private void refreshAvatar(String avatar) {
		if (avatar != null && !avatar.equals("")) {
			ImageLoader.getInstance().displayImage(avatar, iv_touxiang,
					com.wangdeduiwu.Yuema.config.ImageLoadOptions.getOptions());
		} else {
			iv_touxiang.setImageResource(R.drawable.cat);
		}
	}
	

	RelativeLayout layout_choose;
	RelativeLayout layout_photo;
	PopupWindow avatorPop;

	public String filePath = "";

	private void showAvatarPop() {
		View view = LayoutInflater.from(this).inflate(R.layout.pop_showavator,
				null);
		layout_choose = (RelativeLayout) view.findViewById(R.id.layout_choose);
		layout_photo = (RelativeLayout) view.findViewById(R.id.layout_photo);
		layout_photo.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				ShowLog("点击拍照");
				// TODO Auto-generated method stub
				layout_choose.setBackgroundColor(getResources().getColor(
						R.color.base_color_text_white));
				layout_photo.setBackgroundDrawable(getResources().getDrawable(
						R.drawable.pop_bg_press));
				File dir = new File(BmobConstants.MyAvatarDir);
				if (!dir.exists()) {
					dir.mkdirs();
				}
				// 原图
				File file = new File(dir, new SimpleDateFormat("yyMMddHHmmss")
						.format(new Date()));
				filePath = file.getAbsolutePath();// 获取相片的保存路径
				Uri imageUri = Uri.fromFile(file);

				Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
				intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
				startActivityForResult(intent,
						BmobConstants.REQUESTCODE_UPLOADAVATAR_CAMERA);
			}
		});
		layout_choose.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				ShowLog("点击相册");
				layout_photo.setBackgroundColor(getResources().getColor(
						R.color.base_color_text_white));
				layout_choose.setBackgroundDrawable(getResources().getDrawable(
						R.drawable.pop_bg_press));
				Intent intent = new Intent(Intent.ACTION_PICK, null);
				intent.setDataAndType(
						MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
				startActivityForResult(intent,
						BmobConstants.REQUESTCODE_UPLOADAVATAR_LOCATION);
			}
		});

		avatorPop = new PopupWindow(view, mScreenWidth, 600);
		avatorPop.setTouchInterceptor(new OnTouchListener() {
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				if (event.getAction() == MotionEvent.ACTION_OUTSIDE) {
					avatorPop.dismiss();
					return true;
				}
				return false;
			}
		});

		avatorPop.setWidth(WindowManager.LayoutParams.MATCH_PARENT);
		avatorPop.setHeight(WindowManager.LayoutParams.WRAP_CONTENT);
		avatorPop.setTouchable(true);
		avatorPop.setFocusable(true);
		avatorPop.setOutsideTouchable(true);
		avatorPop.setBackgroundDrawable(new BitmapDrawable());
		// 动画效果 从底部弹起
		avatorPop.setAnimationStyle(R.style.Animations_GrowFromBottom);
		avatorPop.showAtLocation(layout_all, Gravity.BOTTOM, 0, 0);
	}

	/**
	 * @Title: startImageAction
	 * @return void
	 * @throws
	 */
	private void startImageAction(Uri uri, int outputX, int outputY,
			int requestCode, boolean isCrop) {
		Intent intent = null;
		if (isCrop) {
			intent = new Intent("com.android.camera.action.CROP");
		} else {
			intent = new Intent(Intent.ACTION_GET_CONTENT, null);
		}
		intent.setDataAndType(uri, "image/*");
		intent.putExtra("crop", "true");
		intent.putExtra("aspectX", 1);
		intent.putExtra("aspectY", 1);
		intent.putExtra("outputX", outputX);
		intent.putExtra("outputY", outputY);
		intent.putExtra("scale", true);
		intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
		intent.putExtra("return-data", true);
		intent.putExtra("outputFormat", Bitmap.CompressFormat.JPEG.toString());
		intent.putExtra("noFaceDetection", true); // no face detection
		startActivityForResult(intent, requestCode);
	}

	Bitmap newBitmap;
	boolean isFromCamera = false;// 区分拍照旋转
	int degree = 0;

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		switch (requestCode) {
		case BmobConstants.REQUESTCODE_UPLOADAVATAR_CAMERA:// 拍照修改头像
			if (resultCode == RESULT_OK) {
				if (!Environment.getExternalStorageState().equals(
						Environment.MEDIA_MOUNTED)) {
					toast("SD不可用");
					return;
				}
				isFromCamera = true;
				File file = new File(filePath);
				degree = PhotoUtil.readPictureDegree(file.getAbsolutePath());
				Log.i("life", "拍照后的角度：" + degree);
				startImageAction(Uri.fromFile(file), 200, 200,
						BmobConstants.REQUESTCODE_UPLOADAVATAR_CROP, true);
			}
			break;
		case BmobConstants.REQUESTCODE_UPLOADAVATAR_LOCATION:// 本地修改头像
			if (avatorPop != null) {
				avatorPop.dismiss();
			}
			Uri uri = null;
			if (data == null) {
				return;
			}
			if (resultCode == RESULT_OK) {
				if (!Environment.getExternalStorageState().equals(
						Environment.MEDIA_MOUNTED)) {
					toast("SD不可用");
					return;
				}
				isFromCamera = false;
				uri = data.getData();
				startImageAction(uri, 200, 200,
						BmobConstants.REQUESTCODE_UPLOADAVATAR_CROP, true);
			} else {
				toast("照片获取失败");
			}

			break;
		case BmobConstants.REQUESTCODE_UPLOADAVATAR_CROP:// 裁剪头像返回
			// TODO sent to crop
			if (avatorPop != null) {
				avatorPop.dismiss();
			}
			if (data == null) {
				// Toast.makeText(this, "取消选择", Toast.LENGTH_SHORT).show();
				return;
			} else {
				saveCropAvator(data);
			}
			// 初始化文件路径
			filePath = "";
			// 上传头像
			uploadAvatar();
			break;
		default:
			break;

		}
	}

	private void uploadAvatar() {
		BmobLog.i("头像地址：" + path);
		final BmobFile bmobFile = new BmobFile(new File(path));
		bmobFile.upload(this, new UploadFileListener() {

			@Override
			public void onSuccess() {
				// TODO Auto-generated method stub
				String url = bmobFile.getFileUrl(EditUserInfoActivity.this);
				// 更新BmobUser对象
				updateUserAvatar(url);
			}

			@Override
			public void onProgress(Integer arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onFailure(int arg0, String msg) {
				// TODO Auto-generated method stub
				toast("头像上传失败：" + msg);
			}
		});
	}

	private void updateUserAvatar(final String url) {
		MyUser  u =new MyUser();
		u.setAvatar(url);
		updateUserData(u,new UpdateListener() {
			@Override
			public void onSuccess() {
				// TODO Auto-generated method stub
				toast("头像更新成功！");
				// 更新头像
				refreshAvatar(url);
			}

			@Override
			public void onFailure(int code, String msg) {
				// TODO Auto-generated method stub
				toast("头像更新失败：" + msg);
			}
		});
	}

	String path;

	/**
	 * 保存裁剪的头像
	 * 
	 * @param data
	 */
	private void saveCropAvator(Intent data) {
		Bundle extras = data.getExtras();
		if (extras != null) {
			Bitmap bitmap = extras.getParcelable("data");
			Log.i("life", "avatar - bitmap = " + bitmap);
			if (bitmap != null) {
				bitmap = PhotoUtil.toRoundCorner(bitmap, 10);
				if (isFromCamera && degree != 0) {
					bitmap = PhotoUtil.rotaingImageView(degree, bitmap);
				}
				iv_touxiang.setImageBitmap(bitmap);
				// 保存图片
				String filename = new SimpleDateFormat("yyMMddHHmmss")
						.format(new Date())+".png";
				path = BmobConstants.MyAvatarDir + filename;
				PhotoUtil.saveBitmap(BmobConstants.MyAvatarDir, filename,
						bitmap, true);
				// 上传头像
				if (bitmap != null && bitmap.isRecycled()) {
					bitmap.recycle();
				}
			}
		}
	}
	
//	/** 测试关联关系是否可用
//	  * @Title: addBlog
//	  * @Description: TODO
//	  * @param  
//	  * @return void
//	  * @throws
//	  */
//	public void addBlog(){
//		//		BmobRelation relation = new BmobRelation();
//		//		blog.setObjectId("c7a9ca9c0c");
//		//		relation.add(blog);
//		//		user.setBlogs(relation);
//		final BmobLog blog = new BmobLog();
//		blog.setBrief("你好");
//		blog.save(this, new SaveListener() {
//			
//			@Override
//			public void onSuccess() {
//				// TODO Auto-generated method stub
//				BmobLog.i("blog保存成功");
//				User  u =new User();
//				u.setBlog(blog);
//				updateUserData(u, new UpdateListener() {
//					
//					@Override
//					public void onSuccess() {
//						// TODO Auto-generated method stub
//						BmobLog.i("user更新成功");
//					}
//					
//					@Override
//					public void onFailure(int code, String msg) {
//						// TODO Auto-generated method stub
//						BmobLog.i("code = "+code+",msg = "+msg);
//					}
//				});
//				
//			}
//			
//			@Override
//			public void onFailure(int arg0, String arg1) {
//				// TODO Auto-generated method stub
//				
//			}
//		});
//	}
	
	private void updateUserData(MyUser user,UpdateListener listener){
		MyUser current = (MyUser) userManager.getCurrentUser(MyUser.class);
		user.setObjectId(current.getObjectId());
		user.update(this, listener);
	}

}