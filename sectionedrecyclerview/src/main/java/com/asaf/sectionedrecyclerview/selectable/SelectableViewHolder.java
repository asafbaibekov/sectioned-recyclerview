package com.asaf.sectionedrecyclerview.selectable;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.MotionEvent;
import android.view.View;

import com.asaf.sectionedrecyclerview.IndexPath;

public class SelectableViewHolder extends RecyclerView.ViewHolder implements View.OnTouchListener {

    private boolean isSelected = false;

    private boolean selectable = true;

    @Nullable
    private SelectableAdapter selectableAdapter;

    @Nullable
    private OnItemSelectListener onItemSelectListener;
    @Nullable
    private OnItemHighlightListener onItemHighlightListener;

    @Nullable
    private IndexPath indexPath;

    public SelectableViewHolder(@NonNull View itemView) {
        super(itemView);
    }

    public boolean isSelected() {
        return isSelected;
    }

    void setSelected(boolean selected) {
        isSelected = selected;
        if (indexPath == null) return;
        if (selectableAdapter != null) {
            if (selected) selectableAdapter.selectViewHolder(this, indexPath);
            else selectableAdapter.deselectViewHolder(this, indexPath);
        }
        if (onItemSelectListener == null) return;
        if (selected) onItemSelectListener.onSelectViewHolder(this, indexPath);
        else onItemSelectListener.onDeselectViewHolder(this, indexPath);
    }

    public boolean isSelectable() {
        return selectable;
    }

    public void setSelectable(boolean selectable) {
        this.selectable = selectable;
    }

    void setSelectableAdapter(SelectableAdapter selectableAdapter, IndexPath indexPath) {
        this.selectableAdapter = selectableAdapter;
        this.indexPath = indexPath;
        if (selectableAdapter == null || indexPath == null) return;
        this.itemView.setOnTouchListener(this);
    }

    public void setOnItemSelectListener(OnItemSelectListener onItemSelectListener, IndexPath indexPath) {
        this.onItemSelectListener = onItemSelectListener;
        this.indexPath = indexPath;
        if (onItemSelectListener == null || indexPath == null) return;
        this.itemView.setOnTouchListener(this);
    }

    public void setOnItemHighlightListener(OnItemHighlightListener onItemHighlightListener, IndexPath indexPath) {
        this.onItemHighlightListener = onItemHighlightListener;
        this.indexPath = indexPath;
        if (onItemHighlightListener == null || indexPath == null) return;
        this.itemView.setOnTouchListener(this);
    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        if (indexPath == null || !selectable) return false;
        if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
            view.performClick();
            this.setSelected(!this.isSelected);
        }
        if (selectableAdapter != null) {
            switch (motionEvent.getAction()) {
                case MotionEvent.ACTION_DOWN: this.selectableAdapter.highlightViewHolder(this, indexPath); return true;
                case MotionEvent.ACTION_UP:
                case MotionEvent.ACTION_CANCEL: this.selectableAdapter.unhighlightViewHolder(this, indexPath); return true;
            }
        }
        if (onItemHighlightListener == null) return false;
        switch (motionEvent.getAction()) {
            case MotionEvent.ACTION_DOWN: this.onItemHighlightListener.onHighlightViewHolder(this, indexPath); return true;
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_CANCEL: this.onItemHighlightListener.onUnhighlightViewHolder(this, indexPath); return true;
        }
        return false;
    }
}