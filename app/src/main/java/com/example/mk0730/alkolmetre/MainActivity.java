package com.example.mk0730.alkolmetre;

import android.content.Intent;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;

import com.example.mk0730.alkolmetre.base.BaseActivity;
import com.example.mk0730.alkolmetre.utils.NotificationUtils;
import com.example.mk0730.alkolmetre.utils.UrlUtils;

public class MainActivity extends BaseActivity {
    Button beer_button;
    Button sprits_button;
    Button wine_button;
    Button search_detail_button;

    UrlUtils urlUtils = new UrlUtils();

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
                buildSearch("spirits");
            }
        });
        search_detail_button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SearchInDetailActivity.class);
                startActivity(intent);
            }
        });
    }

    public void buildSearch(String category) {
        AlcoholFilter alcoholFilter = urlUtils.setCategory(category).build();
        Intent intent = new Intent(MainActivity.this, AlcoholListActivity.class);
        intent.putExtra("ALCOHOL_FILTER", alcoholFilter);
        startActivity(intent);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        PreferenceManager.getDefaultSharedPreferences(this).unregisterOnSharedPreferenceChangeListener(this);
    }

    public void testNotification(View view) {
        NotificationUtils.remindUser(this);
    }

    public void testNotification2(View view) {
        NotificationUtils.findRandomAlcohol(this);
    }
}
