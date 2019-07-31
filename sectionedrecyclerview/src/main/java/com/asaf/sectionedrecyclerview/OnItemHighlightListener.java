package com.asaf.sectionedrecyclerview;

public interface OnItemHighlightListener {
    void onHighlightViewHolder(SelectableViewHolder viewHolder, IndexPath indexPath);
    void onUnhighlightViewHolder(SelectableViewHolder viewHolder, IndexPath indexPath);
}