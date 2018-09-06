package com.katkov.training_starwars.ui;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

public abstract class OnRecyclerScrollEnd extends RecyclerView.OnScrollListener {

    public LinearLayoutManager layoutManager;

    protected OnRecyclerScrollEnd(LinearLayoutManager layoutManager) {
        this.layoutManager = layoutManager;
    }

    @Override
    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);

        int visibleItems = layoutManager.getChildCount();
        int totalItems = layoutManager.getItemCount();
        int outOfScrollItems = layoutManager.findLastVisibleItemPosition();

        if (visibleItems + outOfScrollItems >= totalItems) {
            onRecyclerScrollEnd();
        }
    }

    protected abstract void onRecyclerScrollEnd();
}
