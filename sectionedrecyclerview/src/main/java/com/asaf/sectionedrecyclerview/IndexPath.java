package com.asaf.sectionedrecyclerview;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

public class IndexPath {
    static final int SECTION_ITEM_TYPE = 0;
    static final int ROW_ITEM_TYPE = 1;
    static final int FOOTER_ITEM_TYPE = 2;

    private int section;
    private int row;

    public IndexPath(int section, int row) {
        this.section = section;
        this.row = row;
    }

    public int getRow() {
        return row;
    }

    public int getSection() {
        return section;
    }

    @NonNull
    @Override
    public String toString() {
        return "IndexPath{" +
                "section=" + section +
                ", row=" + row +
                "}";
    }

    public boolean isSectionEqual(int section) {
        return this.getSection() == section;
    }

    public boolean isRowEqual(int row) {
        return this.getRow() == row;
    }

    @Override
    public boolean equals(@Nullable Object obj) {
        if (!(obj instanceof IndexPath)) return false;
        return this.getSection() == ((IndexPath) obj).getSection() && this.getRow() == ((IndexPath) obj).getRow();
    }
}