package com.exam.date;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.UpdateListener;

import com.handmark.pulltorefresh.library.ILoadingLayout;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.handmark.pulltorefresh.library.PullToRefreshBase.OnRefreshListener2;
import com.wangdeduiwu.Yuema.R;
import com.wangdeduiwu.Yuema.data.DateDetails;
import com.wangdeduiwu.Yuema.ui.DateDetailActivity;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.format.DateUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.AbsListView.OnScrollListener;

public class MySearchFragment extends Fragment {
	PullToRefreshListView mPullToRefreshView;
	private ILoadingLayout loadingLayout;
	ListView mMsgListView;
	View view;
	List<DateDetails> bankCards = new ArrayList<DateDetails>();
	MySearchListViewAdapter mySearchListViewAdapter;

	private static final int STATE_REFRESH = 0;// 下拉刷新
	private static final int STATE_MORE = 1;// 加载更多

	private int limit = 10; // 每页的数据是10条
	private int curPage = 0;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		view = inflater.inflate(R.layout.mysearch_fragment, container, false);
		mySearchListViewAdapter = new MySearchListViewAdapter(getActivity(),
				bankCards);

		queryData(0, STATE_REFRESH);
		initListView();
		return view;

	}

	// @Override
	// public void onResume() {
	// super.onResume();
	//
	// mySearchListViewAdapter.notifyDataSetChanged();
	// }

	private void queryData(final int page, final int actionType) {

		BmobQuery<DateDetails> query = new BmobQuery<DateDetails>();
		query.setLimit(limit); // 设置每页多少条数据
		query.setSkip(page * limit);// 从第几条数据开始
		query.include("author");
		query.order("-createdAt");
		query.findObjects(getActivity(), new FindListener<DateDetails>() {

			@Override
			public void onSuccess(List<DateDetails> arg0) {
				// TODO Auto-generated method stub

				if (arg0.size() > 0) {
					if (actionType == STATE_REFRESH) {
						// 当是下拉刷新操作时，将当前页的编号重置为0，并把bankCards清空，重新添加
						curPage = 0;
						bankCards.clear();
					}

					// 将本次查询的数据添加到bankCards中
					for (DateDetails date : arg0) {
						bankCards.add(date);
					}

					Collections.sort(bankCards, new Comparator<DateDetails>() {
						@Override
						public int compare(DateDetails lhs, DateDetails rhs) {
							Date date1 = DateUtil.stringToDate(lhs
									.getCreatedAt());
							Date date2 = DateUtil.stringToDate(rhs
									.getCreatedAt());
							if (date1.before(date2)) {
								return 1;
							}
							return -1;
						}
					});

					// 这里在每次加载完数据后，将当前页码+1，这样在上拉刷新的onPullUpToRefresh方法中就不需要操作curPage了
					curPage++;

				} else if (actionType == STATE_MORE) {
					showToast("没有更多约会了");
				} else if (actionType == STATE_REFRESH) {
					showToast("没有约会");
				}
				mPullToRefreshView.onRefreshComplete();
			}

			@Override
			public void onError(int arg0, String arg1) {
				// TODO Auto-generated method stub
				showToast("查询失败:" + arg1);
				mPullToRefreshView.onRefreshComplete();
			}
		});
	}

	// 当前页的编号，从0开始

	/**
	 * 分页获取数据
	 * 
	 * @param page
	 *            页码
	 * @param actionType
	 *            ListView的操作类型（下拉刷新、上拉加载更多）
	 */
	private void initListView() {

		mPullToRefreshView = (PullToRefreshListView) view
				.findViewById(R.id.mySearchListview);

		loadingLayout = mPullToRefreshView.getLoadingLayoutProxy();
		loadingLayout.setLastUpdatedLabel("");
		loadingLayout
				.setPullLabel(getString(R.string.pull_to_refresh_bottom_pull));
		loadingLayout
				.setRefreshingLabel(getString(R.string.pull_to_refresh_bottom_refreshing));
		loadingLayout
				.setReleaseLabel(getString(R.string.pull_to_refresh_bottom_release));
		// //滑动监听
		mPullToRefreshView.setOnScrollListener(new OnScrollListener() {

			@Override
			public void onScrollStateChanged(AbsListView view, int scrollState) {

			}

			@Override
			public void onScroll(AbsListView view, int firstVisibleItem,
					int visibleItemCount, int totalItemCount) {

				if (firstVisibleItem == 0) {
					loadingLayout.setLastUpdatedLabel("");
					loadingLayout
							.setPullLabel(getString(R.string.pull_to_refresh_top_pull));
					loadingLayout
							.setRefreshingLabel(getString(R.string.pull_to_refresh_top_refreshing));
					loadingLayout
							.setReleaseLabel(getString(R.string.pull_to_refresh_top_release));
				} else if (firstVisibleItem + visibleItemCount + 1 == totalItemCount) {

					loadingLayout.setLastUpdatedLabel("");
					loadingLayout
							.setPullLabel(getString(R.string.pull_to_refresh_bottom_pull));
					loadingLayout
							.setRefreshingLabel(getString(R.string.pull_to_refresh_bottom_refreshing));

					loadingLayout
							.setReleaseLabel(getString(R.string.pull_to_refresh_bottom_release));
				}
			}
		});

		// 下拉刷新监听
		mPullToRefreshView
				.setOnRefreshListener(new OnRefreshListener2<ListView>() {

					@Override
					public void onPullDownToRefresh(
							PullToRefreshBase<ListView> refreshView) {
						// 下拉刷新(从第一页开始装载数据)
						queryData(0, STATE_REFRESH);

					}

					@Override
					public void onPullUpToRefresh(
							PullToRefreshBase<ListView> refreshView) {
						// 上拉加载更多(加载下一页数据)
						queryData(curPage, STATE_MORE);
					}
				});

		mMsgListView = mPullToRefreshView.getRefreshableView();
		// 再设置adapter

		mMsgListView.setAdapter(mySearchListViewAdapter);

		mMsgListView
				.setOnItemClickListener(new AdapterView.OnItemClickListener() {

					@Override
					public void onItemClick(AdapterView<?> parent, View view,
							final int position, long id) {
						DateDetails dateDetails = new DateDetails();
						dateDetails = bankCards.get(position - 1);
						dateDetails.setYue_person(dateDetails.getYue_person() + 1);
						dateDetails.update(getActivity(), new UpdateListener() {

							@Override
							public void onSuccess() {

							}

							@Override
							public void onFailure(int arg0, String arg1) {

							}
						});
						Intent intent = new Intent(getActivity(),
								DateDetailActivity.class);
						intent.putExtra("date", dateDetails);
						startActivity(intent);

					}

				});
	}

	private void showToast(String msg) {
		Toast.makeText(getActivity(), msg, Toast.LENGTH_SHORT).show();
	}

}
