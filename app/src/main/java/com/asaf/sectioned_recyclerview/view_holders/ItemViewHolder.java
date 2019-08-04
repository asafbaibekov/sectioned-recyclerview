package com.asaf.sectioned_recyclerview.view_holders;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.TextView;

import com.asaf.sectioned_recyclerview.R;
import com.asaf.sectionedrecyclerview.selectable.SelectableViewHolder;

public class ItemViewHolder extends SelectableViewHolder {
    private final Context context;
    private TextView textView;
    public ItemViewHolder(@NonNull View itemView, Context context) {
        super(itemView);
        this.context = context;
        textView = (TextView) itemView;
    }
    public void setTitle(String title) {
        textView.setText(title);
    }
    @Override
    protected void setSelected(boolean selected) {
        super.setSelected(selected);
        itemView.setBackgroundColor(selected ? Color.parseColor("#ff0000") : context.getResources().getColor(R.color.colorPrimary));
    }
}