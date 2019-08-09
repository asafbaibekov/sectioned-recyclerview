package com.asaf.sectioned_recyclerview;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.asaf.sectioned_recyclerview.view_holders.FooterViewHolder;
import com.asaf.sectioned_recyclerview.view_holders.HeaderViewHolder;
import com.asaf.sectioned_recyclerview.view_holders.ItemViewHolder;
import com.asaf.sectionedrecyclerview.IndexPath;
import com.asaf.sectionedrecyclerview.selectable.SelectableAdapter;
import com.asaf.sectionedrecyclerview.selectable.SelectableViewHolder;
import com.asaf.sectionedrecyclerview.selectable.SelectionMode;

public class MySelectableAdapter extends SelectableAdapter {

    private final String TAG = "MySelectableAdapter";

    MySelectableAdapter(Context context) {
        super(context);
        setSelectionMode(SelectionMode.SINGLE);
    }

    @Override
    protected int numbersOfSections() {
        return 100;
    }

    @Override
    protected int numberOfRowsInSection(int section) {
        return 10;
    }

    @Override
    protected RecyclerView.ViewHolder onCreateSectionViewHolder(ViewGroup viewGroup, int section) {
        View view = LayoutInflater.from(context).inflate(R.layout.view_holder_header, viewGroup, false);
        return new HeaderViewHolder(view);
    }

    @Override
    protected RecyclerView.ViewHolder onCreateItemViewHolder(ViewGroup viewGroup, IndexPath indexPath) {
        View view = LayoutInflater.from(context).inflate(R.layout.view_holder_item, viewGroup, false);
        return new ItemViewHolder(view, context);
    }

    @Override
    protected RecyclerView.ViewHolder onCreateFooterViewHolder(ViewGroup viewGroup, int section) {
        View view = LayoutInflater.from(context).inflate(R.layout.view_holder_footer, viewGroup, false);
        return new FooterViewHolder(view);
    }

    @Override
    protected void onBindSectionViewHolder(RecyclerView.ViewHolder holder, int section) {
        if (holder instanceof HeaderViewHolder)
            ((HeaderViewHolder) holder).setTitle("section " + section);
    }

    @Override
    protected void onBindItemViewHolder(RecyclerView.ViewHolder holder, IndexPath indexPath) {
        super.onBindItemViewHolder(holder, indexPath);
        if (holder instanceof ItemViewHolder)
            ((ItemViewHolder) holder).setTitle("Item " + indexPath.getRow());
    }

    @Override
    protected void onBindFooterViewHolder(RecyclerView.ViewHolder holder, int footer) {
        if (holder instanceof FooterViewHolder)
            ((FooterViewHolder) holder).setTitle("footer " + footer);
    }

    @Override
    protected void onSelectViewHolder(SelectableViewHolder viewHolder, IndexPath indexPath) {
        Log.d(TAG, "selectViewHolder: " + getIndexPathsForSelectedItems());
    }

    @Override
    protected void onDeselectViewHolder(SelectableViewHolder viewHolder, IndexPath indexPath) {
        Log.d(TAG, "deselectViewHolder: " + getIndexPathsForSelectedItems());
    }

    @Override
    protected void onHighlightViewHolder(SelectableViewHolder viewHolder, IndexPath indexPath) {
        viewHolder.itemView.animate().setDuration(50).scaleX(0.975f).scaleY(0.9f);
        Log.d(TAG, "onHighlightViewHolder: " + indexPath);
    }

    @Override
    protected void onUnhighlightViewHolder(SelectableViewHolder viewHolder, IndexPath indexPath) {
        viewHolder.itemView.animate().setDuration(50).scaleX(1).scaleY(1);
        Log.d(TAG, "onUnhighlightViewHolder: " + indexPath);
    }
}