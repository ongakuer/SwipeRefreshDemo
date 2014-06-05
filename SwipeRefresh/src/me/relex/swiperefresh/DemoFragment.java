package me.relex.swiperefresh;

import java.util.Arrays;

import me.relex.swiperefresh.widget.SwipeRefreshLayout;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

public class DemoFragment extends SwipeRefreshListFragment {

    private DemoAdapter mAdapter;

    private int mRefreshTimes;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);
        mSwipeRefreshLayout = (SwipeRefreshLayout) rootView.findViewById(R.id.swipe_refresh);

        // add custom header
        ImageView image = new ImageView(inflater.getContext());
        image.setImageResource(R.drawable.swift);
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(
                FrameLayout.LayoutParams.WRAP_CONTENT, FrameLayout.LayoutParams.WRAP_CONTENT);
        layoutParams.gravity = Gravity.CENTER;
        mSwipeRefreshLayout.setHeaderView(image, layoutParams);

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
