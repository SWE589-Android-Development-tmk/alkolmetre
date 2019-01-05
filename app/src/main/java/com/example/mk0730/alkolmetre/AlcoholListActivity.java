package com.example.mk0730.alkolmetre;

import android.annotation.SuppressLint;
import android.content.ContentResolver;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
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
import com.example.mk0730.alkolmetre.lcbo.LcboApiResponse;
import com.example.mk0730.alkolmetre.service.IntentServiceResultReceiver;
import com.example.mk0730.alkolmetre.service.LcboIntentService;
import com.example.mk0730.alkolmetre.utils.UrlUtils;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class AlcoholListActivity extends BaseActivity
        implements ListItemClickListener,
        OnBottomReachedListener, IntentServiceResultReceiver.Receiver {
    public static final int LOADER = 0;

    AlcoholFilter alcoholFilter;
    AlcoholAdapter adapter;
    TextView resultTextView;

    ContentResolver contentResolver;

    URL url;
    int page = 1;

    @SuppressLint("ShowToast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            setContentView(R.layout.activity_alcohol_list);
            Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
            setSupportActionBar(toolbar);

            contentResolver = getContentResolver();
            resultTextView = (TextView) findViewById(R.id.txt_results_information);

            /* Load recyclerView */
            RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
            LinearLayoutManager layoutManager = new LinearLayoutManager(this);

            recyclerView.setLayoutManager(layoutManager);
            recyclerView.setHasFixedSize(true);
            //DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(),
            //        layoutManager.getOrientation());
            //recyclerView.addItemDecoration(dividerItemDecoration);

            adapter = new AlcoholAdapter(this, this, getApplicationContext());
            recyclerView.setAdapter(adapter);

            Intent intent = getIntent();
            if (intent.hasExtra("ALCOHOL_FILTER")) {
                String json = intent.getStringExtra(Intent.EXTRA_TEXT);
                try {
                    alcoholFilter = (AlcoholFilter) intent.getSerializableExtra("ALCOHOL_FILTER");

                    url = UrlUtils.buildUrl(alcoholFilter, page);
                    executeApiTask();
                } catch (IOException e) {
                    Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
                    Log.v("AlcoholActivity", e.getMessage());
                }
            }
        } catch (Exception ex) {
            Toast.makeText(getApplicationContext(), ex.toString(), Toast.LENGTH_LONG);
        }
    }

    private void executeApiTask() {
        IntentServiceResultReceiver receiver = new IntentServiceResultReceiver(new Handler());
        receiver.setReceiver(this);
        LcboIntentService.startActionCallApi(getApplicationContext(),
                this.url.toString(), adapter, receiver);
    }

    @Override
    public void onListItemClick(int clickedItemIndex) {
        Intent detailActivityIntent;

        Log.v("MainActivity.onCreate", "Item#" + Integer.toString(clickedItemIndex));

        detailActivityIntent = new Intent(AlcoholListActivity.this, DetailActivity.class);
        detailActivityIntent.putExtra("ALCOHOL_ITEM", AlcoholAdapter.getItem(clickedItemIndex));

        startActivity(detailActivityIntent);
    }

    @SuppressLint("ShowToast")
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

    @Override
    public void onReceiveResult(int resultCode, Bundle resultData) {
        LcboApiResponse response = (LcboApiResponse) resultData.getSerializable("response");
        adapter.setAlcohols(response);

        resultTextView.setText(String.format(getString(R.string.txt_alcohol_list_results),
                adapter.getTotalItemCount()));
    }
}
