package com.exam.date;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.wangdeduiwu.Yuema.R;
import com.wangdeduiwu.Yuema.data.DateDetails;

public class MySearchListViewAdapter extends BaseAdapter {

	List<DateDetails> date;
	Context mconContext;

	public MySearchListViewAdapter(Context mContext, List<DateDetails> date) {
		this.date = date;
		this.mconContext = mContext;
	}

	@Override
	public int getCount() {
		return date.size();
	}

	@Override
	public Object getItem(int position) {
		return date.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		LayoutInflater inflater = LayoutInflater.from(mconContext);
		ViewHolder viewHolder = null;
		if (convertView == null) {
			viewHolder = new ViewHolder();
			convertView = inflater.inflate(R.layout.mysearch_listview_item,
					null);
			viewHolder.tv_content = (TextView) convertView
					.findViewById(R.id.tv_content);
			viewHolder.tv_time = (TextView) convertView
					.findViewById(R.id.tv_time);
			viewHolder.tv_zan = (TextView) convertView
					.findViewById(R.id.tv_zan);
			viewHolder.tv_people = (TextView) convertView
					.findViewById(R.id.tv_people);
			convertView.setTag(viewHolder);
		} else {
			viewHolder = (ViewHolder) convertView.getTag();
		}

		DateDetails date = (DateDetails) getItem(position);
		viewHolder.tv_content.setText(date.getDate_content());
		viewHolder.tv_time.setText(date.getCreatedAt());
		viewHolder.tv_zan.setText(Integer.toString(date.getLove()));
		viewHolder.tv_people.setText(Integer.toString(date.getYue_person()));
		return convertView;
	}

	class ViewHolder {
		private TextView tv_content;
		private TextView tv_time;
		private TextView tv_zan;
		private TextView tv_people;

	}

}
