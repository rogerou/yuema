package com.exam.date;

import java.util.ArrayList;
import java.util.List;

import com.wangdeduiwu.Yuema.R;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

public class MyGouDaFragment extends Fragment {

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.mydatedaipingfragment, container,
				false);

		List<MyGouda> list = new ArrayList<MyGouda>();
		MyGouda myDateDaiPing_1 = new MyGouda("评价功能暂不提供",
				"2015/4/24");

		list.add(myDateDaiPing_1);

		ListView listView = (ListView) view
				.findViewById(R.id.myDateDaiPingFragment_ListView);
		listView.setAdapter(new MyGouDaFragment_ListViewAdapter(
				getActivity(), list));
		// listView.setOnItemClickListener(new OnItemClickListener() {
		//
		// @Override
		// public void onItemClick(AdapterView<?> parent, View view,
		// int position, long id) {
		// getActivity().startActivity(new
		// Intent(getActivity(),DetailActivity.class));
		// }
		// });
		return view;
	}

}
