使用[SwipeRefreshLayout](http://developer.android.com/reference/android/support/v4/widget/SwipeRefreshLayout.html) 下拉刷新控件做的一个Demo

封装了一下，方便在Fragment中使用。

------

**这个分支将v4包中的SwipeRefreshLayout提取出来。**

1. 增加了自定义header。
2. 调整了下拉刷新逻辑（下拉到100%后不立刻刷新，松手后才刷新）。
3. 微调了一些细节。
