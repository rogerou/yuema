package com.exam.date;

import java.util.List;

import com.wangdeduiwu.Yuema.R;
import com.wangdeduiwu.Yuema.data.DateDetails;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class MyDateYiFaFragment_ListViewAdapter extends BaseAdapter {
	List<DateDetails> list;
	Context mconContext;

	public MyDateYiFaFragment_ListViewAdapter(Context mContext,
			List<DateDetails> list) {
		this.list = list;
		this.mconContext = mContext;
	}

	@Override
	public int getCount() {
		return list.size();
	}

	@Override
	public Object getItem(int position) {
		return list.get(position);
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
			convertView = inflater.inflate(R.layout.mydate_listview_item, null);
			viewHolder.tv_left = (TextView) convertView
					.findViewById(R.id.tv_left);
			viewHolder.tv_right = (TextView) convertView
					.findViewById(R.id.tv_right);
			convertView.setTag(viewHolder);
		} else {
			viewHolder = (ViewHolder) convertView.getTag();
		}

		viewHolder.tv_left.setText(list.get(position).getDate_content());
		viewHolder.tv_right.setText(list.get(position).getCreatedAt());
		return convertView;
	}

	class ViewHolder {
		private TextView tv_left;
		private TextView tv_right;
	}

}
