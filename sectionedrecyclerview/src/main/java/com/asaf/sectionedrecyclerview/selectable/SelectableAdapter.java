package com.asaf.sectionedrecyclerview.selectable;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;

import com.asaf.sectionedrecyclerview.IndexPath;
import com.asaf.sectionedrecyclerview.SectionAdapter;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public abstract class SelectableAdapter extends SectionAdapter {

    @Nullable
    private CopyOnWriteArrayList<IndexPath> indexPathsForSelectedItems;

    private boolean singleSelection;

    protected SelectableAdapter(Context context) {
        super(context);
        this.singleSelection = false;
    }

    public void setSingleSelection(boolean singleSelection) {
        this.singleSelection = singleSelection;
    }

    public boolean isSingleSelection() {
        return singleSelection;
    }

    @Nullable
    public List<IndexPath> getIndexPathsForSelectedItems() {
        return indexPathsForSelectedItems != null ? Collections.unmodifiableList(indexPathsForSelectedItems) : null;
    }

    @Override
    protected void onBindItemViewHolder(RecyclerView.ViewHolder holder, IndexPath indexPath) {
        if (!(holder instanceof SelectableViewHolder)) return;
        SelectableViewHolder selectableViewHolder = (SelectableViewHolder) holder;
        selectableViewHolder.setSelectableAdapter(this, indexPath);
        if (getIndexPathsForSelectedItems() != null)
            selectableViewHolder.setSelected(getIndexPathsForSelectedItems().contains(indexPath));
    }

    protected abstract void onSelectViewHolder(SelectableViewHolder viewHolder, IndexPath indexPath);
    protected abstract void onDeselectViewHolder(SelectableViewHolder viewHolder, IndexPath indexPath);
    protected abstract void onHighlightViewHolder(SelectableViewHolder viewHolder, IndexPath indexPath);
    protected abstract void onUnhighlightViewHolder(SelectableViewHolder viewHolder, IndexPath indexPath);

    void selectViewHolder(SelectableViewHolder viewHolder, IndexPath indexPath) {
        if (indexPathsForSelectedItems == null) indexPathsForSelectedItems = new CopyOnWriteArrayList<>();
        indexPathsForSelectedItems.add(indexPath);
        this.onSelectViewHolder(viewHolder, indexPath);
        if (!singleSelection) return;
        if (indexPathsForSelectedItems == null) return;
        for (IndexPath indexPath1 : indexPathsForSelectedItems) {
            if (!indexPath.equals(indexPath1)) {
                indexPathsForSelectedItems.remove(indexPath1);
                notifyItemChanged(indexPath1);
            }
        }
    }

    void deselectViewHolder(SelectableViewHolder viewHolder, IndexPath indexPath) {
        if (indexPathsForSelectedItems == null) return;
        indexPathsForSelectedItems.remove(indexPath);
        if (indexPathsForSelectedItems.isEmpty()) indexPathsForSelectedItems = null;
        this.onDeselectViewHolder(viewHolder, indexPath);
    }

    void highlightViewHolder(SelectableViewHolder viewHolder, IndexPath indexPath) {
        this.onHighlightViewHolder(viewHolder, indexPath);
    }

    void unhighlightViewHolder(SelectableViewHolder viewHolder, IndexPath indexPath) {
        this.onUnhighlightViewHolder(viewHolder, indexPath);
    }
}