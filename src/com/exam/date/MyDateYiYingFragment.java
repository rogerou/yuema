package com.exam.date;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.im.BmobUserManager;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.listener.FindListener;

import com.wangdeduiwu.Yuema.R;
import com.wangdeduiwu.Yuema.data.DateDetails;
import com.wangdeduiwu.Yuema.data.IsYue;
import com.wangdeduiwu.Yuema.data.MyUser;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class MyDateYiYingFragment extends Fragment {
	BmobUserManager userManager;
	List<IsYue> bankCards = new ArrayList<IsYue>();

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		final View view = inflater.inflate(R.layout.mydateyiyingfragment,
				container, false);

		userManager = BmobUserManager.getInstance(getActivity());
		MyUser user = userManager.getCurrentUser(MyUser.class);

		BmobQuery<IsYue> query = new BmobQuery<IsYue>();
		query.addWhereEqualTo("user", user.getObjectId());
		query.include("Dateobjectid");
		query.order("-createdAt");
		query.findObjects(getActivity(), new FindListener<IsYue>() {

			@Override
			public void onError(int arg0, String arg1) {
				Toast.makeText(getActivity(), arg1, Toast.LENGTH_LONG).show();
			}

			@Override
			public void onSuccess(List<IsYue> arg0) {
				if (arg0.size() > 0) {
					for (IsYue yue : arg0) {
						bankCards.add(yue);
					}

					ListView listView = (ListView) view
							.findViewById(R.id.myDateYiYingFragment_ListView);
					listView.setAdapter(new MyDateYiYingFragment_ListViewAdapter(
							getActivity(), bankCards));
					listView.setOnItemClickListener(new OnItemClickListener() {

						@Override
						public void onItemClick(AdapterView<?> parent,
								View view, int position, long id) {
					
							DateDetails dateDetails;
							dateDetails = bankCards.get(position)
									.getDateobjectid();
							Intent intent = new Intent(getActivity(),
									DetailActivity.class);
							intent.putExtra("date", dateDetails);
							getActivity().startActivity(intent);

						}
					});
				}

			}
		});

		return view;
	}
}
