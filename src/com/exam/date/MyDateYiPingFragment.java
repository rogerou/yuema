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
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;

public class MyDateYiPingFragment extends Fragment {

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.mydateyipingfragment, container,
				false);

		List<MyDateYiPing> list = new ArrayList<MyDateYiPing>();
		MyDateYiPing MyDateYiPing_1 = new MyDateYiPing("评价功能暂不提供", "2015/4/24");

		list.add(MyDateYiPing_1);

		ListView listView = (ListView) view
				.findViewById(R.id.myDateYiPingFragment_ListView);
		listView.setAdapter(new MyDateYiPingFragment_ListViewAdapter(
				getActivity(), list));
		// listView.setOnItemClickListener(new OnItemClickListener() {

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
