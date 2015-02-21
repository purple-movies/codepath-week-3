package com.codepath.apps.mysimpletweets;

import android.widget.AbsListView;

public abstract class EndlessScrollListener implements AbsListView.OnScrollListener {
    
    private int visibleThreshold = 5;
    private int currentPage = 0;
    private  int startingPageIndex = 0;
    private int previousTotalItemCount = 0;
    private boolean loading = true;

    public EndlessScrollListener(){
    }
    
    public EndlessScrollListener(int visibleThreshold){
        this.visibleThreshold = visibleThreshold;
    }
    
    public EndlessScrollListener(int visibleThreshold, int startingPageIndex){
        this.visibleThreshold = visibleThreshold;
        this.startingPageIndex = startingPageIndex;
        currentPage = startingPageIndex;
    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
        
        if (totalItemCount < previousTotalItemCount) {
            currentPage = startingPageIndex;
            previousTotalItemCount = totalItemCount;
            if (totalItemCount == 0) {
                loading = true;
            }
        }
        
        if (loading && (totalItemCount > previousTotalItemCount)) {
            loading = false;
            previousTotalItemCount = totalItemCount;
            currentPage++;
        }
        
        if (!loading && (totalItemCount - visibleItemCount) <= (firstVisibleItem + visibleThreshold)) {
            onLoadMore(currentPage + 1, totalItemCount);
            loading = true;
        }
    }

    protected abstract void onLoadMore(int page, int totalItemCount);

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {
    }
}
