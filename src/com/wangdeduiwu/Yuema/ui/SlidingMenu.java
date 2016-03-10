package com.wangdeduiwu.Yuema.ui;

import com.nineoldandroids.view.ViewHelper;
import com.wangdeduiwu.Yuema.R;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class SlidingMenu extends HorizontalScrollView {

	private LinearLayout mWrapper;
	private ViewGroup mMenu;
	private ViewGroup mContent;
	private int mScreenWidth;
	private int mMenuRightPadding = 150;
	private int mMenuWidth;

	private boolean once = false;
	private boolean isOpen;
	ImageView iv_touxiang;
	public SlidingMenu(Context context, AttributeSet attrs) {
		this(context, attrs, 0);

	}

	public SlidingMenu(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);

		TypedArray a = context.getTheme().obtainStyledAttributes(attrs,
				R.styleable.SlidingMenu, defStyle, 0);

		int n = a.getIndexCount();
		for (int i = 0; i < n; i++) {
			int attr = a.getIndex(i);
			switch (attr) {
			case R.styleable.SlidingMenu_rightPadding:
				mMenuRightPadding = a.getDimensionPixelSize(attr,
						(int) TypedValue.applyDimension(
								TypedValue.COMPLEX_UNIT_DIP, 100, context
										.getResources().getDisplayMetrics()));
				break;

			default:
				break;
			}

		}

		a.recycle();

		WindowManager wm = (WindowManager) context
				.getSystemService(context.WINDOW_SERVICE);
		DisplayMetrics outMetrics = new DisplayMetrics();
		wm.getDefaultDisplay().getMetrics(outMetrics);

		mScreenWidth = outMetrics.widthPixels;
		mMenuRightPadding = (int) TypedValue.applyDimension(
				TypedValue.COMPLEX_UNIT_DIP, 120, context.getResources()
						.getDisplayMetrics());
	}

	public SlidingMenu(Context context) {
		this(context, null);
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		if (!once) {
			mWrapper = (LinearLayout) getChildAt(0);
			mMenu = (ViewGroup) mWrapper.getChildAt(0);
			mContent = (ViewGroup) mWrapper.getChildAt(1);
			mMenuWidth = mMenu.getLayoutParams().width = mScreenWidth
					- mMenuRightPadding;
			mContent.getLayoutParams().width = mScreenWidth;
			once = true;
		}

		super.onMeasure(widthMeasureSpec, heightMeasureSpec);

	}

	@Override
	protected void onLayout(boolean changed, int l, int t, int r, int b) {
		super.onLayout(changed, l, t, r, b);
		if (changed) {
			this.scrollTo(mMenuWidth, 0);
		}

	}

	@Override
	public boolean onTouchEvent(MotionEvent ev) {
		int action = ev.getAction();
		switch (action) {
		case MotionEvent.ACTION_UP:
			int scrollX = getScrollX();

			if (scrollX >= mMenuWidth / 2) {
				this.smoothScrollTo(mMenuWidth, 0);
				isOpen = false;
			} else {
				this.smoothScrollTo(0, 0);
				isOpen = true;
			}

			return true;
		}

		return super.onTouchEvent(ev);
	}

	public void openMenu() {
		if (isOpen)
			return;
		this.smoothScrollTo(0, 0);
		
		isOpen = true;

	}

	public void closeMenu() {
		if (!isOpen) {
			return;
		}
		this.smoothScrollTo(mMenuWidth, 0);
		isOpen = false;
	}

	public void toggle() {
		if (isOpen) {
			closeMenu();
		} else {
			openMenu();
			
		}
	}

	@Override
	protected void onScrollChanged(int l, int t, int oldl, int oldt) {
		super.onScrollChanged(l, t, oldl, oldt);
		float scale = l * 1.0f / mMenuWidth;

		float leftScale = 1.0f - scale * 0.3f;
		float leftAlpha = 0.6f + 0.4f * (1 - scale);
		float rightScale = 0.7f + 0.3f * scale;

		ViewHelper.setTranslationX(mMenu, mMenuWidth * scale * 0.8f);

		ViewHelper.setScaleX(mMenu, leftScale);
		ViewHelper.setScaleY(mMenu, leftScale);
		ViewHelper.setAlpha(mMenu, leftAlpha);
		ViewHelper.setPivotX(mContent, 0);
		ViewHelper.setPivotY(mContent, mContent.getHeight() / 2);
		ViewHelper.setScaleX(mContent, rightScale);
		ViewHelper.setScaleY(mContent, rightScale);

	}

}
