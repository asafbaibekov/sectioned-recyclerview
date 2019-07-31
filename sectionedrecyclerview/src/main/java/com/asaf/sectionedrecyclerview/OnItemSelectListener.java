package com.asaf.sectionedrecyclerview;

public interface OnItemSelectListener {
    void onSelectViewHolder(SelectableViewHolder viewHolder, IndexPath indexPath);
    void onDeselectViewHolder(SelectableViewHolder viewHolder, IndexPath indexPath);
}