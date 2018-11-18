package com.example.mk0730.alkolmetre;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.Toast;

import com.example.mk0730.alkolmetre.alcohol.AlcoholAdapter;
import com.example.mk0730.alkolmetre.alcohol.ListItemClickListener;
import com.example.mk0730.alkolmetre.tasks.LcboApiTask;
import com.example.mk0730.alkolmetre.utils.NetworkUtils;

import java.io.IOException;
import java.net.URL;

public class AlcoholListActivity extends AppCompatActivity
        implements ListItemClickListener {

    AlcoholFilter alcoholFilter;
    AlcoholAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alcohol_list);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        /* Load recyclerView */
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(),
                layoutManager.getOrientation());
        recyclerView.addItemDecoration(dividerItemDecoration);

        //AlcoholAdapter adapter = new AlcoholAdapter(this);
        adapter = new AlcoholAdapter();
        recyclerView.setAdapter(adapter);

        Intent intent = getIntent();
        if (intent.hasExtra(Intent.EXTRA_TEXT)){
            String json = intent.getStringExtra(Intent.EXTRA_TEXT);
            try {
                alcoholFilter = NetworkUtils.parse(json);
                URL url = NetworkUtils.buildUrl(alcoholFilter);

                LcboApiTask lcboApiTask = new LcboApiTask(adapter);
                lcboApiTask.execute(url.toString());
            } catch (IOException e) {
                Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
                Log.v("AlcoholActivity", e.getMessage());
            }
        }
    }

    @Override
    public void onListItemClick(int clickedItemIndex) {
//        Intent detailActivityIntent;
//
//        Log.v("MainActivity.onCreate", "Item#"+Integer.toString(clickedItemIndex));
//
//        detailActivityIntent = new Intent(AlcoholListActivity.this, DetailActivity.class);
//        detailActivityIntent.putExtra(Intent.EXTRA_TEXT, mAdapter.getItem(clickedItemIndex));
//
//        startActivity(detailActivityIntent);
    }
}
