package com.asaf.sectioned_recyclerview.view_holders;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

public class FooterViewHolder extends RecyclerView.ViewHolder {
    private TextView textView;
    public FooterViewHolder(@NonNull View itemView) {
        super(itemView);
        textView = (TextView) itemView;
    }
    public void setTitle(String title) {
        textView.setText(title);
    }
}