package com.asaf.sectionedrecyclerview;

import android.support.annotation.NonNull;

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
}