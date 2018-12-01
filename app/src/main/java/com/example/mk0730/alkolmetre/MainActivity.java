package com.example.mk0730.alkolmetre;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ToggleButton;

import com.example.mk0730.alkolmetre.utils.NetworkUtils;
import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;

public class MainActivity extends AppCompatActivity implements SharedPreferences.OnSharedPreferenceChangeListener {
    Button beer_button;
    Button sprits_button;
    Button wine_button;
    Button search_detail_button;

    Boolean is_discontinued;
    Boolean is_vqa;
    Boolean order_price;
    Boolean order_alcohol_content;
    Boolean order_price_per_liter;

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
                buildSearch("sprits");
            }
        });
        search_detail_button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SearchInDetailActivity.class);
                startActivity(intent);
            }
        });

        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        // Default is discontinued is true.
        is_discontinued = sharedPreferences.getBoolean("is_discontinued", true);

        // Default is vqa is false. That means it is going to get alcoholic drinks that are not vqa.
        is_vqa = sharedPreferences.getBoolean("is_vqa", false);

        // Default order price is false which means descending order.
        order_price = sharedPreferences.getBoolean("order_price", false);

        // Default order alcohol content is false which means descending order.
        order_alcohol_content = sharedPreferences.getBoolean("order_alcohol_content", false);

        // Default order price per liter is false which means descending order.
        order_price_per_liter = sharedPreferences.getBoolean("order_price_per_liter", false);

        sharedPreferences.registerOnSharedPreferenceChangeListener(this);
    }

    public void buildSearch(String category) {
        String alchoholFilter = networkUtils.setCategory(category).build();
        Intent intent = new Intent(MainActivity.this, AlcoholListActivity.class);
        intent.putExtra(Intent.EXTRA_TEXT, alchoholFilter);
        startActivity(intent);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int selectedItemIndexId = item.getItemId();
        if (selectedItemIndexId == R.id.action_search) {
            Intent startSettingsActivity = new Intent(this, SettingsActivity.class);
            startActivity(startSettingsActivity);
            return true;
        }
        // TODO: logout will be implemented in here. else if(selectedItemIndexId = logout)
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        PreferenceManager.getDefaultSharedPreferences(this).unregisterOnSharedPreferenceChangeListener(this);
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        if(key.equals(getString(R.string.is_discontinued))) {
            is_discontinued = sharedPreferences.getBoolean("is_discontinued", true);
            System.out.println(is_discontinued);
        }
    }
}
