package com.asaf.sectioned_recyclerview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.asaf.sectioned_recyclerview.view_holders.FooterViewHolder;
import com.asaf.sectioned_recyclerview.view_holders.HeaderViewHolder;
import com.asaf.sectioned_recyclerview.view_holders.ItemViewHolder;
import com.asaf.sectionedrecyclerview.IndexPath;
import com.asaf.sectionedrecyclerview.SectionAdapter;
import com.asaf.sectionedrecyclerview.StickHeaderItemDecoration;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        SectionAdapter adapter = new SectionAdapter(this) {
            @Override
            protected int numbersOfSections() {
                return 100;
            }

            @Override
            protected int numberOfRowsInSection(int section) {
                return 10;
            }

            @Override
            protected RecyclerView.ViewHolder onCreateSectionViewHolder(ViewGroup viewGroup, int section) {
                View view = LayoutInflater.from(MainActivity.this).inflate(R.layout.view_holder_header, viewGroup, false);
                return new HeaderViewHolder(view);
            }

            @Override
            protected RecyclerView.ViewHolder onCreateItemViewHolder(ViewGroup viewGroup, IndexPath indexPath) {
                View view = LayoutInflater.from(MainActivity.this).inflate(R.layout.view_holder_item, viewGroup, false);
                return new ItemViewHolder(view);
            }

            @Override
            protected RecyclerView.ViewHolder onCreateFooterViewHolder(ViewGroup viewGroup, int section) {
                View view = LayoutInflater.from(MainActivity.this).inflate(R.layout.view_holder_footer, viewGroup, false);
                return new FooterViewHolder(view);
            }

            @Override
            protected void onBindSectionViewHolder(RecyclerView.ViewHolder holder, int section) {
                if (holder instanceof HeaderViewHolder)
                    ((HeaderViewHolder) holder).setTitle("section " + section);
            }

            @Override
            protected void onBindItemViewHolder(RecyclerView.ViewHolder holder, IndexPath indexPath) {
                if (holder instanceof ItemViewHolder)
                    ((ItemViewHolder) holder).setTitle("Item " + indexPath.getRow());
            }

            @Override
            protected void onBindFooterViewHolder(RecyclerView.ViewHolder holder, int footer) {
                if (holder instanceof FooterViewHolder)
                    ((FooterViewHolder) holder).setTitle("footer " + footer);
            }
        };
        recyclerView.setAdapter(adapter);
        StickHeaderItemDecoration decoration = new StickHeaderItemDecoration();
        recyclerView.addItemDecoration(decoration);
    }
}