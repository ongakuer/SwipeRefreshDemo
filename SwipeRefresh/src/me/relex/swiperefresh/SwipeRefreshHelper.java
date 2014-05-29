package me.relex.swiperefresh;

public interface SwipeRefreshHelper {

    public void initSwipeRefresh();

    public void loadFinished();

    public void initSwipeRefreshInternal();

    public void swipeEnable();

    public void swipeDisable();
}
