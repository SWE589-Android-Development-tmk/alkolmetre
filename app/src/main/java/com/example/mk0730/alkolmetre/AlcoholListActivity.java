package com.example.mk0730.alkolmetre;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mk0730.alkolmetre.alcohol.AlcoholAdapter;
import com.example.mk0730.alkolmetre.alcohol.ListItemClickListener;
import com.example.mk0730.alkolmetre.alcohol.OnBottomReachedListener;
import com.example.mk0730.alkolmetre.base.BaseActivity;
import com.example.mk0730.alkolmetre.db.model.ApiQuery;
import com.example.mk0730.alkolmetre.tasks.AsyncTaskCompleted;
import com.example.mk0730.alkolmetre.tasks.LcboApiTask;
import com.example.mk0730.alkolmetre.utils.UrlUtils;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class AlcoholListActivity extends BaseActivity
        implements ListItemClickListener, OnBottomReachedListener,
        LoaderManager.LoaderCallbacks<Cursor> {
    public static final int LOADER = 0;

    AlcoholFilter alcoholFilter;
    AlcoholAdapter adapter;
    TextView resultTextView;
    URL url;
    int page = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            setContentView(R.layout.activity_alcohol_list);
            Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
            setSupportActionBar(toolbar);

            resultTextView = (TextView) findViewById(R.id.txt_results_information);

            /* Load recyclerView */
            RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
            LinearLayoutManager layoutManager = new LinearLayoutManager(this);

            recyclerView.setLayoutManager(layoutManager);
            recyclerView.setHasFixedSize(true);
            //DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(),
            //        layoutManager.getOrientation());
            //recyclerView.addItemDecoration(dividerItemDecoration);

            adapter = new AlcoholAdapter(this, this);
            adapter.clear();
            recyclerView.setAdapter(adapter);

            Intent intent = getIntent();
            if (intent.hasExtra(Intent.EXTRA_TEXT)){
                String json = intent.getStringExtra(Intent.EXTRA_TEXT);
                try {
                    alcoholFilter = UrlUtils.parse(json);
                    url = UrlUtils.buildUrl(alcoholFilter, page);

                    //executeApiTask();
                    getLoaderManager().initLoader(LOADER, null,
                            (android.app.LoaderManager.LoaderCallbacks<Cursor>) this);
                } catch (IOException e) {
                    Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
                    Log.v("AlcoholActivity", e.getMessage());
                }
            }
        } catch (Exception ex){
            Toast.makeText(getApplicationContext(), ex.toString(), Toast.LENGTH_LONG);
        }
    }

    private void executeApiTask() {
        LcboApiTask lcboApiTask = new LcboApiTask(adapter, getApplicationContext(), new AsyncTaskCompleted() {
            @Override
            public void completed() {
                resultTextView.setText(String.format(getString(R.string.txt_alcohol_list_results),
                        adapter.getTotalItemCount()));
            }
        });
        lcboApiTask.execute(this.url.toString());
    }

    @Override
    public void onListItemClick(int clickedItemIndex) {
        Intent detailActivityIntent;

        Log.v("MainActivity.onCreate", "Item#"+Integer.toString(clickedItemIndex));

        detailActivityIntent = new Intent(AlcoholListActivity.this, DetailActivity.class);
        detailActivityIntent.putExtra(Intent.EXTRA_TEXT, clickedItemIndex);

        startActivity(detailActivityIntent);
    }

    @Override
    public void onBottomReached() {
        try {
            page++;
            this.url = UrlUtils.nextPage(this.alcoholFilter, page);
            executeApiTask();
        } catch (MalformedURLException e) {
            Toast.makeText(getApplicationContext(), e.toString(), Toast.LENGTH_LONG);
        }
    }

    @NonNull
    @Override
    public Loader<Cursor> onCreateLoader(int i, @Nullable Bundle bundle) {
        /* Should be getting all records. */
        CursorLoader query = new CursorLoader(this, ApiQuery.CONTENT_URI,
                new String[]{"*"},
                "query",
                new String[]{url.toString()},
                null);
        return query;
    }

    @Override
    public void onLoadFinished(@NonNull Loader<Cursor> loader, Cursor cursor) {

    }

    @Override
    public void onLoaderReset(@NonNull Loader<Cursor> loader) {
        adapter.swapCursor(null, false, 0);
    }
}
