package com.asaf.sectionedrecyclerview.selectable;

import com.asaf.sectionedrecyclerview.IndexPath;

public interface OnItemSelectListener {
    void onSelectViewHolder(SelectableViewHolder viewHolder, IndexPath indexPath);
    void onDeselectViewHolder(SelectableViewHolder viewHolder, IndexPath indexPath);
}