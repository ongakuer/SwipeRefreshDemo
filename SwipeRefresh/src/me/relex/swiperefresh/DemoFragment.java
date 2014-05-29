package me.relex.swiperefresh;

import java.util.Arrays;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

public class DemoFragment extends SwipeRefreshListFragment {

    private DemoAdapter mAdapter;

    private int mRefreshTimes;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);
        mSwipeRefreshLayout = (SwipeRefreshLayout) rootView.findViewById(R.id.swipe_refresh);
        mListView = (ListView) rootView.findViewById(R.id.list);
        mAdapter = new DemoAdapter();
        mListView.setAdapter(mAdapter);
        swipeDisable();
        super.onCreateView(inflater, container, savedInstanceState);
        return rootView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        loadDefaultData();
    }

    private void loadDefaultData() {
        String[] defaultData = getResources().getStringArray(R.array.list_default);
        mAdapter.addList(Arrays.asList(defaultData));
        mAdapter.notifyDataSetChanged();
        swipeEnable();
    }

    @Override
    public void onRefresh() {
        swipeDisable();

        mHandler.postDelayed(new Runnable() {

            @Override
            public void run() {
                String[] listData = mRefreshTimes % 2 == 0 ? getResources().getStringArray(
                        R.array.list_new) : getResources().getStringArray(R.array.list_default);
                mAdapter.clear();
                mAdapter.addList(Arrays.asList(listData));
                mAdapter.notifyDataSetChanged();
                loadFinished();
                swipeEnable();

                mRefreshTimes++;
            }
        }, 2000L);

    }

    @Override
    public void onLastItemVisible() {
        Toast.makeText(getActivity(), "滚动底部了", Toast.LENGTH_SHORT).show();
    }
}
