package com.asaf.sectioned_recyclerview;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.asaf.sectioned_recyclerview.view_holders.ItemViewHolder;
import com.asaf.sectionedrecyclerview.IndexPath;
import com.asaf.sectionedrecyclerview.selectable.OnItemHighlightListener;
import com.asaf.sectionedrecyclerview.SectionAdapter;
import com.asaf.sectionedrecyclerview.selectable.SelectableViewHolder;

public class MySectionAdapter extends SectionAdapter implements OnItemHighlightListener {

    protected MySectionAdapter(Context context) {
        super(context);
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
    protected RecyclerView.ViewHolder onCreateItemViewHolder(ViewGroup viewGroup, IndexPath indexPath) {
        View view = LayoutInflater.from(context).inflate(R.layout.view_holder_item, viewGroup, false);
        return new ItemViewHolder(view);
    }

    @Override
    protected void onBindItemViewHolder(RecyclerView.ViewHolder holder, IndexPath indexPath) {
        if (holder instanceof ItemViewHolder) {
            ItemViewHolder itemViewHolder = ((ItemViewHolder) holder);
            itemViewHolder.setTitle("Item " + indexPath.getRow());
            itemViewHolder.setOnItemHighlightListener(this, indexPath);
        }
    }

    @Override
    public void onHighlightViewHolder(SelectableViewHolder viewHolder, IndexPath indexPath) {
        viewHolder.itemView.animate().setDuration(50).scaleX(0.875f).scaleY(0.8f);
    }

    @Override
    public void onUnhighlightViewHolder(SelectableViewHolder viewHolder, IndexPath indexPath) {
        viewHolder.itemView.animate().setDuration(50).scaleX(1f).scaleY(1f);
    }
}