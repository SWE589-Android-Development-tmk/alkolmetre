package com.example.mk0730.alkolmetre;

import android.app.LoaderManager;
import android.content.ContentResolver;
import android.content.CursorLoader;
import android.content.Intent;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.example.mk0730.alkolmetre.alcohol.ListItemClickListener;
import com.example.mk0730.alkolmetre.base.BaseActivity;
import com.example.mk0730.alkolmetre.data.FavoriteContract.FavoriteEntry;
import com.example.mk0730.alkolmetre.favorite.FavoriteAdapter;

import com.example.mk0730.alkolmetre.data.FavoriteContract.FavoriteDetailEntry;

public class FavoriteActivity extends BaseActivity implements
        ListItemClickListener,
        LoaderManager.LoaderCallbacks<Cursor> {

    Uri favoriteUri = FavoriteEntry.CONTENT_URI;
    ContentResolver contentResolver;
    public static final int FORECAST_LOADER = 0;
    FavoriteAdapter adapter;
    TextView txtItemCount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        contentResolver = getContentResolver();

        txtItemCount = (TextView) findViewById(R.id.txt_favorite_count);

        /* Load recyclerView */
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.favoriteRecyclerView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);

        adapter = new FavoriteAdapter(this, getApplicationContext());
        recyclerView.setAdapter(adapter);

        getLoaderManager().initLoader(FORECAST_LOADER, null,this);
    }

    @Override
    public void onListItemClick(int clickedItemIndex) {
        Intent detailActivityIntent;

        detailActivityIntent = new Intent(FavoriteActivity.this, DetailActivity.class);
        detailActivityIntent.putExtra("ALCOHOL_ITEM", FavoriteAdapter.getItem(clickedItemIndex));

        startActivity(detailActivityIntent);
    }

    @NonNull
    @Override
    public Loader<Cursor> onCreateLoader(int i, @Nullable Bundle bundle) {
        CursorLoader query = new CursorLoader(this,
                favoriteUri,
                new String[]{
                        FavoriteEntry.COLUMN_API_ID,
                        FavoriteEntry.COLUMN_IMAGE_URL,
                        FavoriteEntry.COLUMN_NAME,
                        FavoriteEntry.COLUMN_ORIGIN,
                        FavoriteDetailEntry.COLUMN_ALCOHOL_CONTENT,
                        FavoriteDetailEntry.COLUMN_SUGAR_CONTENT,
                        FavoriteDetailEntry.COLUMN_RELEASE_DATE,
                        FavoriteDetailEntry.COLUMN_TASTING_NOTE

                },
                null,
                null,
                null);
        return query;
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor cursor) {
        txtItemCount.setText(String.format(getString(R.string.txt_alcohol_list_results),
                cursor.getCount()));
        adapter.swapCursor(cursor, cursor.getCount());
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        adapter.swapCursor(null, 0);
    }
}
