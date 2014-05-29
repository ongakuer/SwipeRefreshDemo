package me.relex.swiperefresh;

import android.view.View;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.Adapter;
import android.widget.ListView;

public abstract class SwipeRefreshListFragment extends BaseSwipeRefreshFragment implements
        OnScrollListener, OnLastItemVisibleListener {

    protected ListView mListView;

    private boolean mLastItemVisible;

    @Override
    public void initSwipeRefreshInternal() {
        if (mListView != null) {
            mListView.setOnScrollListener(this);
        }
    }

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {
        if (mLastItemVisible) {
            onLastItemVisible();
        }
    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount,
            int totalItemCount) {
        mLastItemVisible = (totalItemCount > 0)
                && (firstVisibleItem + visibleItemCount >= totalItemCount - 1);
    }

    protected boolean isReadyForPullStart() {
        return isFirstItemVisible();
    }

    private boolean isFirstItemVisible() {
        if (mListView == null) {
            return false;
        }

        final Adapter adapter = mListView.getAdapter();

        if (null == adapter || adapter.isEmpty()) {
            return true;

        } else {
            if (mListView.getFirstVisiblePosition() <= 1) {
                final View firstVisibleChild = mListView.getChildAt(0);
                if (firstVisibleChild != null) {
                    return firstVisibleChild.getTop() >= mListView.getTop();
                }
            }
        }

        return false;
    }

}
