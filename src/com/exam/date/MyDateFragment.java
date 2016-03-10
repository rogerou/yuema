package com.exam.date;

import com.wangdeduiwu.Yuema.R;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;

public class MyDateFragment extends Fragment implements OnClickListener {
	private ImageView imageView1;
	private ImageView imageView2;
	private ImageView imageView3;
	private ImageView imageView4;
	private MyGouDaFragment myGouDaFragment;
	private MyDateYiFaFragment myDateYiFaFragment;
	private MyDateYiYingFragment myDateYiYingFragment;
	// private MyDateYiPingFragment myDateYiPingFragment;
	private View view;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		view = inflater.inflate(R.layout.mydate_fragment, container, false);
		initViews();
		initListeners();
		showDefaultFragment();
		return view;
	}

	private void showDefaultFragment() {
		FragmentTransaction transaction = getActivity()
				.getSupportFragmentManager().beginTransaction();
		hideFragments(transaction);
		if (myGouDaFragment == null) {
			myGouDaFragment = new MyGouDaFragment();

		}
		imageView1.setSelected(true);
		transaction.show(myGouDaFragment);
		transaction.add(R.id.fragment, myGouDaFragment);
		transaction.addToBackStack(null);
		transaction.commit();
	}

	private void initListeners() {
		imageView1.setOnClickListener(this);
		imageView2.setOnClickListener(this);
		// imageView3.setOnClickListener(this);
		imageView4.setOnClickListener(this);
	}

	private void initViews() {
		imageView1 = (ImageView) view.findViewById(R.id.imageView1);
		imageView2 = (ImageView) view.findViewById(R.id.imageView2);
		// imageView3 = (ImageView) view.findViewById(R.id.imageView3);
		imageView4 = (ImageView) view.findViewById(R.id.imageView4);

	}

	@Override
	public void onClick(View v) {
		FragmentTransaction transaction = getActivity()
				.getSupportFragmentManager()
				.beginTransaction()
				.setCustomAnimations(android.R.anim.slide_in_left,
						android.R.anim.slide_out_right);
		hideFragments(transaction);
		switch (v.getId()) {
		case R.id.imageView1:
			if (myGouDaFragment == null) {
				myGouDaFragment = new MyGouDaFragment();
			}
			transaction.show(myGouDaFragment);
			transaction.add(R.id.fragment, myGouDaFragment);
			imageView1.setSelected(true);
			imageView2.setSelected(false);
			imageView4.setSelected(false);
			transaction.addToBackStack(null);
			break;
		case R.id.imageView2:
			if (myDateYiYingFragment == null) {
				myDateYiYingFragment = new MyDateYiYingFragment();
			}
			transaction.show(myDateYiYingFragment);
			transaction.add(R.id.fragment, myDateYiYingFragment);
			imageView1.setSelected(false);
			imageView2.setSelected(true);
			imageView4.setSelected(false);
			transaction.addToBackStack(null);
			break;

		case R.id.imageView4:
			if (myDateYiFaFragment == null) {
				myDateYiFaFragment = new MyDateYiFaFragment();
			}
			transaction.show(myDateYiFaFragment);
			transaction.add(R.id.fragment, myDateYiFaFragment);
			imageView1.setSelected(false);
			imageView2.setSelected(false);
			imageView4.setSelected(true);
			transaction.addToBackStack(null);
			break;
		default:
			break;
		}
		transaction.commit();
	}

	private void hideFragments(FragmentTransaction transition) {

		if (myGouDaFragment != null) {
			transition.hide(myGouDaFragment);
		}

		if (myDateYiFaFragment != null) {
			transition.hide(myDateYiFaFragment);
		}

		if (myDateYiYingFragment != null) {
			transition.hide(myDateYiYingFragment);
		}
	}
}
