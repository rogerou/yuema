package com.exam.date;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.im.BmobUserManager;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.listener.FindListener;

import com.wangdeduiwu.Yuema.R;
import com.wangdeduiwu.Yuema.data.DateDetails;
import com.wangdeduiwu.Yuema.data.MyUser;

import android.app.DownloadManager.Query;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

public class MyDateYiFaFragment extends Fragment {
	BmobUserManager userManager;

	@SuppressWarnings("static-access")
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		final View view = inflater.inflate(R.layout.mydateyifafragment,
				container, false);
		userManager = BmobUserManager.getInstance(getActivity());
		MyUser user = userManager.getCurrentUser(MyUser.class);

		final List<DateDetails> bankcards = new ArrayList<DateDetails>();

		BmobQuery<DateDetails> query = new BmobQuery<DateDetails>();
		query.addWhereEqualTo("author", user.getObjectId());
		query.order("-createdAt");
		query.findObjects(getActivity(), new FindListener<DateDetails>() {

			@Override
			public void onSuccess(List<DateDetails> arg0) {
				if (arg0.size() > 0) {
					for (DateDetails date : arg0) {
						bankcards.add(date);
					}
					ListView listView = (ListView) view
							.findViewById(R.id.myDateYiFaFragment_ListView);
					listView.setAdapter(new MyDateYiFaFragment_ListViewAdapter(
							getActivity(), bankcards));
					listView.setOnItemClickListener(new OnItemClickListener() {

						@Override
						public void onItemClick(AdapterView<?> parent,
								View view, int position, long id) {
							DateDetails dateDetails;
							dateDetails = bankcards.get(position);

							Intent intent = new Intent(getActivity(),
									DetailActivity.class);
							intent.putExtra("date", dateDetails);
							getActivity().startActivity(intent);
						}
					});
				}
			}

			@Override
			public void onError(int arg0, String arg1) {
				Toast.makeText(getActivity(), arg1, Toast.LENGTH_SHORT).show();

			}
		});

		return view;
	}
}
