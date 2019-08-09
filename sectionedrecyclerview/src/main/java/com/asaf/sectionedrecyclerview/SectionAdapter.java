package com.asaf.sectionedrecyclerview;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

public abstract class SectionAdapter extends RecyclerView.Adapter {

    protected Context context;

    protected SectionAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int position) {
        switch (getItemTypeFromPosition(position)) {
            case IndexPath.SECTION_ITEM_TYPE: return onCreateSectionViewHolder(viewGroup, getSectionIndexFromPosition(position));
            case IndexPath.ROW_ITEM_TYPE: return onCreateItemViewHolder(viewGroup, getIndexPathFromPosition(position));
            case IndexPath.FOOTER_ITEM_TYPE: return onCreateFooterViewHolder(viewGroup, getFooterIndexFromPosition(position));
        }
        throw new IndexOutOfBoundsException();
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        switch (getItemTypeFromPosition(i)) {
            case IndexPath.SECTION_ITEM_TYPE: onBindSectionViewHolder(viewHolder, getSectionIndexFromPosition(i)); break;
            case IndexPath.ROW_ITEM_TYPE: onBindItemViewHolder(viewHolder, getIndexPathFromPosition(i)); break;
            case IndexPath.FOOTER_ITEM_TYPE: onBindFooterViewHolder(viewHolder, getFooterIndexFromPosition(i)); break;
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i, @NonNull List payloads) {
        if (payloads.isEmpty()) { super.onBindViewHolder(viewHolder, i, payloads); return; }
        switch (getItemTypeFromPosition(i)) {
            case IndexPath.SECTION_ITEM_TYPE: onBindSectionViewHolder(viewHolder, getSectionIndexFromPosition(i), payloads); break;
            case IndexPath.ROW_ITEM_TYPE: onBindItemViewHolder(viewHolder, getIndexPathFromPosition(i), payloads); break;
            case IndexPath.FOOTER_ITEM_TYPE: onBindFooterViewHolder(viewHolder, getFooterIndexFromPosition(i), payloads); break;
        }
    }

    @Override
    public int getItemCount() {
        int counter = 0;
        for (int i = 0; i < numbersOfSections(); i++)
            counter += numberOfRowsInSection(i) + 2;
        return counter;
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public void onAttachedToRecyclerView(@NonNull RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        if (recyclerView.getLayoutManager() instanceof GridLayoutManager) {
            final GridLayoutManager gridLayoutManager = (GridLayoutManager) recyclerView.getLayoutManager();
            gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                @Override
                public int getSpanSize(int i) {
                    return getItemTypeFromPosition(i) == IndexPath.ROW_ITEM_TYPE ? 1 : gridLayoutManager.getSpanCount();
                }
            });
        }
    }

    private int getItemTypeFromPosition(int position) {
        int counter = 0;
        for (int i = 0; i < numbersOfSections(); i++) {
            if (counter == position)
                return IndexPath.SECTION_ITEM_TYPE;
            else
                for (int j = 0; j < numberOfRowsInSection(i); j++)
                    if (++counter == position)
                        return IndexPath.ROW_ITEM_TYPE;
            if (++counter == position)
                return IndexPath.FOOTER_ITEM_TYPE;
            counter++;
        }
        throw new IndexOutOfBoundsException();
    }

    int getSectionIndexFromPosition(int position) {
        int counter = 0;
        for (int i = 0; i < numbersOfSections(); i++)
            for (int j = 0; j <= numberOfRowsInSection(i) + 1; j++)
                if (counter++ == position)
                    return i;
        throw new IndexOutOfBoundsException();
    }

    private IndexPath getIndexPathFromPosition(int position) {
        int counter = 0;
        for (int i = 0; i < numbersOfSections(); i++) {
            counter++;
            for (int j = 0; j < numberOfRowsInSection(i); j++)
                if (counter++ == position)
                    return new IndexPath(i, j);
            counter++;
        }
        throw new IndexOutOfBoundsException();
    }

    int getFooterIndexFromPosition(int position) {
        int counter = 0;
        for (int i = 0; i < numbersOfSections(); i++)
            for (int j = 0; j <= numberOfRowsInSection(i) + 1; j++)
                if (counter++ == position)
                    return i;
        throw new IndexOutOfBoundsException();
    }

    public void notifyItemChanged(IndexPath indexPath) {
        notifyItemChanged(getPositionFromIndexPath(indexPath));
    }

    public void notifyItemChanged(IndexPath indexPath, @Nullable Object payload) {
        notifyItemChanged(getPositionFromIndexPath(indexPath), payload);
    }

    private int getPositionFromIndexPath(IndexPath indexPath) {
        int counter = 0;
        if (indexPath.getSection() >= numbersOfSections()) throw new IndexOutOfBoundsException();
        for (int i = 0; i < numbersOfSections(); i++) {
            int numberOfRowsInSection = numberOfRowsInSection(i);
            if (!indexPath.isSectionEqual(i)) {
                counter += numberOfRowsInSection + 2;
                continue;
            }
            if (indexPath.getRow() >= numberOfRowsInSection) throw new IndexOutOfBoundsException();
            for (int j = 0; j < numberOfRowsInSection; j++) {
                counter++;
                if (indexPath.isRowEqual(j))
                    return counter;
            }
        }
        throw new IndexOutOfBoundsException();
    }

    protected abstract int numbersOfSections();
    protected abstract int numberOfRowsInSection(int section);

    protected RecyclerView.ViewHolder onCreateSectionViewHolder(ViewGroup viewGroup, int section) {
        return new RecyclerView.ViewHolder(new View(context)) {};
    }
    protected abstract RecyclerView.ViewHolder onCreateItemViewHolder(ViewGroup viewGroup, IndexPath indexPath);
    protected RecyclerView.ViewHolder onCreateFooterViewHolder(ViewGroup viewGroup, int footer) {
        return new RecyclerView.ViewHolder(new View(context)) {};
    }
    protected void onBindSectionViewHolder(RecyclerView.ViewHolder holder, int section) {}
    protected abstract void onBindItemViewHolder(RecyclerView.ViewHolder holder, IndexPath indexPath);
    protected void onBindFooterViewHolder(RecyclerView.ViewHolder holder, int footer) {}

    protected void onBindSectionViewHolder(@NonNull RecyclerView.ViewHolder holder, int section, @NonNull List payloads) {}
    protected void onBindItemViewHolder(@NonNull RecyclerView.ViewHolder holder, IndexPath indexPath, @NonNull List payloads) {}
    protected void onBindFooterViewHolder(@NonNull RecyclerView.ViewHolder holder, int footer, @NonNull List payloads) {}
}