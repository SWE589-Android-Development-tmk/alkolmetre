package com.example.mk0730.alkolmetre.service;

import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;

import com.example.mk0730.alkolmetre.AlcoholFilter;
import com.example.mk0730.alkolmetre.DetailActivity;
import com.example.mk0730.alkolmetre.lcbo.LcboApiResponse;
import com.example.mk0730.alkolmetre.lcbo.LcboApiResponseResult;
import com.example.mk0730.alkolmetre.utils.NotificationUtils;
import com.example.mk0730.alkolmetre.utils.UrlUtils;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Random;

public class ReminderTask2 {
    private static ContentResolver contentResolver;

    public static final int TOTAL_PAGE_COUNT = 650;
    public static final String RANDOM_ALCOHOL_REMINDER_PENDING_INTENT_ID = "random-alcohol-notification";
    public static final String ACTION_DISMISS_NOTIFICATION2 = "dismiss-notification2";
    public static final String ACTION_FEEL_LUCKY_REMINDER = "feel-lucky";

    public static void executeTask(Context context, String action) {
        if (RANDOM_ALCOHOL_REMINDER_PENDING_INTENT_ID.equals(action)) {
            findRandomAlcohol(context);
        } else if (ACTION_DISMISS_NOTIFICATION2.equals(action)) {
            NotificationUtils.clearAllNotifications(context);
        } else if (ACTION_FEEL_LUCKY_REMINDER.equals(action)) {
            feelLucky(context);
        }
    }

    private static void findRandomAlcohol(Context context) {
        try {

            Random rnd = new Random();
            int randomPage = rnd.nextInt(TOTAL_PAGE_COUNT) + 1;
            URL url = UrlUtils.buildUrl(new AlcoholFilter(), randomPage);

            LcboApiResponse lcboApiResponse = LcboIntentService.callApi(url.toString());
            LcboApiResponseResult result = lcboApiResponse.getResult().get(rnd.nextInt(lcboApiResponse.getPager().getRecordsPerPage() - 1));

            Intent detailActivityIntent = new Intent(context, DetailActivity.class);
            detailActivityIntent.putExtra("ALCOHOL_ITEM", result);

            context.startActivity(detailActivityIntent);

            NotificationUtils.clearAllNotifications(context);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }
    private static void feelLucky(Context context) {
        NotificationUtils.remindUser(context);
    }

}
