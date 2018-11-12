package com.example.mk0730.alkolmetre;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.example.mk0730.alkolmetre.utils.NetworkUtils;
import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;

public class MainActivity extends AppCompatActivity {
    Button beer_button;
    Button sprits_button;
    Button wine_button;
    Button search_detail_button;

    NetworkUtils networkUtils = new NetworkUtils();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        beer_button = (Button) findViewById(R.id.beer_button);
        sprits_button = (Button) findViewById(R.id.sprits_button);
        wine_button = (Button) findViewById(R.id.wine_button);
        search_detail_button = (Button) findViewById(R.id.search_in_details_button);

        beer_button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                buildSearch("beer");
            }
        });
        wine_button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                buildSearch("wine");
            }
        });
        sprits_button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                buildSearch("sprints");
            }
        });
        search_detail_button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

            }
        });

    }

    public void buildSearch(String category) {
        String alchoholFilter = networkUtils.setCategory(category).build();
        Intent intent = new Intent(MainActivity.this, null);
        intent.putExtra(Intent.EXTRA_TEXT, alchoholFilter);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int selectedItemIndex = item.getItemId();
        return super.onOptionsItemSelected(item);
    }
}
