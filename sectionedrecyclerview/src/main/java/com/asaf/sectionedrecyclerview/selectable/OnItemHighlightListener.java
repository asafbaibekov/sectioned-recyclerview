package com.asaf.sectionedrecyclerview.selectable;

import com.asaf.sectionedrecyclerview.IndexPath;

public interface OnItemHighlightListener {
    void onHighlightViewHolder(SelectableViewHolder viewHolder, IndexPath indexPath);
    void onUnhighlightViewHolder(SelectableViewHolder viewHolder, IndexPath indexPath);
}