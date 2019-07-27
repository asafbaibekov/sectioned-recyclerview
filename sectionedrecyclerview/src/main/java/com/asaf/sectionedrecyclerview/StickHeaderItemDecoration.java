package com.asaf.sectionedrecyclerview;

import android.graphics.Canvas;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

public class StickHeaderItemDecoration extends RecyclerView.ItemDecoration {

    @Override
    public void onDrawOver(@NonNull Canvas c, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        super.onDrawOver(c, parent, state);
        SectionAdapter adapter;
        if (parent.getAdapter() instanceof SectionAdapter)
            adapter = (SectionAdapter) parent.getAdapter();
        else
            throw new RuntimeException("Has to have SectionAdapter in RecyclerView");
        int topChildPosition = parent.getChildAdapterPosition(parent.getChildAt(0));
        RecyclerView.ViewHolder viewHolder = adapter.onCreateSectionViewHolder(parent, adapter.getSectionIndexFromPosition(topChildPosition));

        int widthSpec = View.MeasureSpec.makeMeasureSpec(parent.getWidth(), View.MeasureSpec.EXACTLY);
        int heightSpec = View.MeasureSpec.makeMeasureSpec(parent.getHeight(), View.MeasureSpec.UNSPECIFIED);
        ViewGroup.LayoutParams layoutParams = viewHolder.itemView.getLayoutParams();
        if (layoutParams == null) return;
        int childWidthSpec = ViewGroup.getChildMeasureSpec(widthSpec, parent.getPaddingLeft() + parent.getPaddingRight(), layoutParams.width);
        int childHeightSpec = ViewGroup.getChildMeasureSpec(heightSpec, parent.getPaddingTop() + parent.getPaddingBottom(), layoutParams.height);
        viewHolder.itemView.measure(childWidthSpec, childHeightSpec);
        viewHolder.itemView.layout(0, 0, viewHolder.itemView.getMeasuredWidth(), viewHolder.itemView.getMeasuredHeight());

        View viewOverlappedByHeader = getChildInContact(parent, viewHolder.itemView.getBottom());
        if (viewOverlappedByHeader == null) return;
        int overlappedByHeaderPosition = parent.getChildAdapterPosition(viewOverlappedByHeader);
        int overlappedHeaderPosition = adapter.getSectionIndexFromPosition(overlappedByHeaderPosition);
        int preOverlappedPosition = adapter.getSectionIndexFromPosition(overlappedByHeaderPosition > 0 ? overlappedByHeaderPosition - 1 : topChildPosition);
        adapter.onBindSectionViewHolder(viewHolder, adapter.getSectionIndexFromPosition(topChildPosition));
        if (preOverlappedPosition != overlappedHeaderPosition)
            moveHeader(c, viewHolder, viewOverlappedByHeader);
        else
            drawHeader(c, viewHolder);
    }

    private void moveHeader(Canvas c, RecyclerView.ViewHolder currentHeader, View nextHeader) {
        c.save();
        c.translate(0, nextHeader.getTop() - nextHeader.getHeight());
        currentHeader.itemView.draw(c);
        c.restore();
    }

    private void drawHeader(Canvas c, RecyclerView.ViewHolder currentHeader) {
        c.save();
        c.translate(0, 0);
        currentHeader.itemView.draw(c);
        c.restore();
    }

    @Nullable
    private View getChildInContact(RecyclerView parent, int contactPoint) {
        for (int i = 0; i < parent.getChildCount(); i++) {
            View child = parent.getChildAt(i);
            if (child.getBottom() > contactPoint && child.getTop() <= contactPoint)
                return child;
        }
        return null;
    }
}