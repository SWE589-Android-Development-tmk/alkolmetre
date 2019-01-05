package com.example.mk0730.alkolmetre.base;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.mk0730.alkolmetre.FavoriteActivity;
import com.example.mk0730.alkolmetre.R;
import com.example.mk0730.alkolmetre.SettingsActivity;
import com.example.mk0730.alkolmetre.utils.ReminderUtilities;
import com.example.mk0730.alkolmetre.utils.ReminderUtilities2;

import java.util.Locale;

public class BaseActivity extends AppCompatActivity implements SharedPreferences.OnSharedPreferenceChangeListener {
    public Boolean is_discontinued;
    public Boolean is_vqa;
    public Boolean order_price;
    public Boolean order_alcohol_content;
    public Boolean order_price_per_liter;
    public String language;
    Locale myLocale;
    private IntentFilter intentFilter;
    WifiBroadcastReceiver wifiBroadcastReceiver;

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        if(key.equals(getString(R.string.is_discontinued))) {
            is_discontinued = sharedPreferences.getBoolean("is_discontinued", true);
            System.out.println(is_discontinued);
        }
    }

    public void setLocale(String lang) {
        myLocale = new Locale(lang);
        Resources res = getResources();
        DisplayMetrics dm = res.getDisplayMetrics();
        Configuration conf = res.getConfiguration();
        conf.locale = myLocale;
        res.updateConfiguration(conf, dm);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

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

        language = sharedPreferences.getString("language_preference", "EN");

        setLocale(language);

        sharedPreferences.registerOnSharedPreferenceChangeListener(this);
        ReminderUtilities.scheduleRecommendationReminder(this);
        ReminderUtilities2.scheduleFeelLuckyReminder(this);

        /* Register WIFI Broadcast Receiver */
        this.intentFilter = new IntentFilter();
        this.intentFilter.addAction(WifiManager.SUPPLICANT_CONNECTION_CHANGE_ACTION);
        this.wifiBroadcastReceiver = new WifiBroadcastReceiver();
        registerReceiver(this.wifiBroadcastReceiver, this.intentFilter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        registerReceiver(this.wifiBroadcastReceiver, this.intentFilter);
    }

    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(this.wifiBroadcastReceiver);
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
            Intent intent = new Intent(this, SettingsActivity.class);
            startActivity(intent);
            return true;
        }
        if (selectedItemIndexId == R.id.favorites) {
            Intent intent = new Intent(this, FavoriteActivity.class);
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public class WifiBroadcastReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            final String action = intent.getAction();
            if (action.equals(WifiManager.SUPPLICANT_CONNECTION_CHANGE_ACTION)) {
                if (intent.getBooleanExtra(WifiManager.EXTRA_SUPPLICANT_CONNECTED, false)) {
                    alertWifiStatus("WIFI connected!");
                } else {
                    alertWifiStatus("We lost WIFI connection");
                }
            }
        }
    }

    private void alertWifiStatus(String status){
        Toast.makeText(this, status, Toast.LENGTH_LONG).show();
    }
}
