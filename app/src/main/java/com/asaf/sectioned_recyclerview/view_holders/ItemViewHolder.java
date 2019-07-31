package com.asaf.sectioned_recyclerview.view_holders;

import android.support.annotation.NonNull;
import android.view.View;
import android.widget.TextView;

import com.asaf.sectionedrecyclerview.SelectableViewHolder;

public class ItemViewHolder extends SelectableViewHolder {
    private TextView textView;
    public ItemViewHolder(@NonNull View itemView) {
        super(itemView);
        textView = (TextView) itemView;
    }
    public void setTitle(String title) {
        textView.setText(title);
    }
}