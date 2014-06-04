package me.relex.swiperefresh;

import me.relex.swiperefresh.widget.SwipeRefreshLayout;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public abstract class BaseSwipeRefreshFragment extends Fragment implements
        SwipeRefreshLayout.OnRefreshListener, SwipeRefreshHelper {

    protected ActionBar mActionbar;

    protected SwipeRefreshLayout mSwipeRefreshLayout;

    protected Handler mHandler;

    public BaseSwipeRefreshFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mHandler = new Handler();
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        initSwipeRefresh();
        initSwipeRefreshInternal();

        return null;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mActionbar = ((ActionBarActivity) getActivity()).getSupportActionBar();
    }

    @Override
    public abstract void onRefresh();

    @Override
    public void initSwipeRefresh() {
        if (mSwipeRefreshLayout != null) {
            mSwipeRefreshLayout.setOnRefreshListener(this);
            mSwipeRefreshLayout.setColorScheme(R.color.orange, R.color.yellow, R.color.orange,
                    R.color.yellow);
        }
    }

    @Override
    public void initSwipeRefreshInternal() {

    }

    @Override
    public void loadFinished() {
        mSwipeRefreshLayout.setRefreshing(false);
    }

    public void swipeEnable() {
        mSwipeRefreshLayout.setEnabled(true);
    }

    public void swipeDisable() {
        mSwipeRefreshLayout.setEnabled(false);
    }

}
