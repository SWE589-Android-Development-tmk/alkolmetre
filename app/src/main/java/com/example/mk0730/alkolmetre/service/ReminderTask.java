package com.example.mk0730.alkolmetre.service;

import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;

import com.example.mk0730.alkolmetre.AlcoholFilter;
import com.example.mk0730.alkolmetre.AlcoholListActivity;
import com.example.mk0730.alkolmetre.data.FavoriteContract;
import com.example.mk0730.alkolmetre.utils.NotificationUtils;
import com.example.mk0730.alkolmetre.utils.UrlUtils;

import java.util.Random;

public class ReminderTask {

    private static ContentResolver contentResolver;

    public static final String ACTION_INCREMENT_WATER_COUNT = "increment-water-count";
    public static final String ACTION_DISMISS_NOTIFICATION = "dismiss-notification";
    static final String ACTION_RECOMENDER_REMINDER = "charging-reminder";

    public static void executeTask(Context context, String action) {
        if (ACTION_INCREMENT_WATER_COUNT.equals(action)) {
            incrementWaterCount(context);
        } else if (ACTION_DISMISS_NOTIFICATION.equals(action)) {
            NotificationUtils.clearAllNotifications(context);
        } else if (ACTION_RECOMENDER_REMINDER.equals(action)) {
            issueRemindUser(context);
        }
    }

    private static void incrementWaterCount(Context context) {
        contentResolver = context.getContentResolver();

        Cursor query = contentResolver.query(
                FavoriteContract.FavoriteEntry.CONTENT_URI,
                new String[]{FavoriteContract.FavoriteEntry.COLUMN_NAME},
                null,
                null,
                null);

        if (query.getCount() > 0) {
            Random rnd = new Random();
            int randomIndex = rnd.nextInt(query.getCount() -1);
            query.moveToPosition(randomIndex);
            String name = query.getString(0);
            int selectedWordIndex = rnd.nextInt(name.split(" ").length);
            String selectedWord = name.split(" ")[selectedWordIndex];

            AlcoholFilter alcoholFilter = new UrlUtils().setSearch(selectedWord).build();
            Intent intent = new Intent(context, AlcoholListActivity.class);
            intent.putExtra("ALCOHOL_FILTER", alcoholFilter);
            context.startActivity(intent);
        }


        NotificationUtils.clearAllNotifications(context);
    }

    private static void issueRemindUser(Context context) {
        NotificationUtils.remindUser(context);
    }
}
