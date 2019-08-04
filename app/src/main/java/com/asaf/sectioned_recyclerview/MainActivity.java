package com.asaf.sectioned_recyclerview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import com.asaf.sectionedrecyclerview.StickHeaderItemDecoration;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        MySelectableAdapter adapter = new MySelectableAdapter(this);
        recyclerView.setAdapter(adapter);
        StickHeaderItemDecoration decoration = new StickHeaderItemDecoration();
        recyclerView.addItemDecoration(decoration);
    }
}