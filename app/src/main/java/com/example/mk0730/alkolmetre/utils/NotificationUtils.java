package com.example.mk0730.alkolmetre.utils;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.support.v4.app.NotificationCompat;
import android.support.v4.content.ContextCompat;

import com.example.mk0730.alkolmetre.MainActivity;
import com.example.mk0730.alkolmetre.R;
import com.example.mk0730.alkolmetre.service.ReminderIntentService;
import com.example.mk0730.alkolmetre.service.ReminderIntentService2;
import com.example.mk0730.alkolmetre.service.ReminderTask;
import com.example.mk0730.alkolmetre.service.ReminderTask2;

public class NotificationUtils {

    /*
     * This notification ID can be used to access our notification after we've displayed it. This
     * can be handy when we need to cancel the notification, or perhaps update it. This number is
     * arbitrary and can be set to whatever you like. 1138 is in no way significant.
     */
    private static final int WATER_REMINDER_NOTIFICATION_ID = 1138;
    /**
     * This pending intent id is used to uniquely reference the pending intent
     */
    private static final int WATER_REMINDER_PENDING_INTENT_ID = 3417;
    /**
     * This notification channel id is used to link notifications to this channel
     */

    private static final int RANDOM_ALCOHOL_REMINDER_PENDING_INTENT_ID = 2500;
    private static final String WATER_REMINDER_NOTIFICATION_CHANNEL_ID = "reminder_notification_channel";
    private static final int ACTION_DRINK_PENDING_INTENT_ID = 1;
    private static final int ACTION_IGNORE_PENDING_INTENT_ID = 14;

    public static void clearAllNotifications(Context context) {
        NotificationManager notificationManager = (NotificationManager)
                context.getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.cancelAll();
    }


    public static void findRandomAlcohol(Context context) {
        NotificationManager notificationManager = (NotificationManager)
                context.getSystemService(Context.NOTIFICATION_SERVICE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel mChannel = new NotificationChannel(
                    WATER_REMINDER_NOTIFICATION_CHANNEL_ID,
                    context.getString(R.string.visit_favorites_notification_title),
                    NotificationManager.IMPORTANCE_HIGH);
            notificationManager.createNotificationChannel(mChannel);
        }
        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(context,WATER_REMINDER_NOTIFICATION_CHANNEL_ID)
                .setColor(ContextCompat.getColor(context, R.color.colorPrimary))
                .setSmallIcon(R.drawable.ic_cancel_black_24px)
                .setLargeIcon(largeIcon(context))
                .setContentTitle(context.getString(R.string.alcohol_reminder))
                .setContentText(context.getString(R.string.alcohol_reminder_body))
                .setStyle(new NotificationCompat.BigTextStyle().bigText(
                        context.getString(R.string.alcohol_reminder_body)))
                .setDefaults(Notification.DEFAULT_VIBRATE)
                .setContentIntent(contentIntent(context))
                .addAction(findRandomAlcoholAction(context))
                .addAction(ignoreReminderAction2(context))
                .setAutoCancel(true);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN
                && Build.VERSION.SDK_INT < Build.VERSION_CODES.O) {
            notificationBuilder.setPriority(NotificationCompat.PRIORITY_HIGH);
        }
        notificationManager.notify(0, notificationBuilder.build());
    }

    private static NotificationCompat.Action findRandomAlcoholAction(Context context) {
        Intent findRandomAlcoholIntent = new Intent(context, ReminderIntentService2.class);
        findRandomAlcoholIntent.setAction(ReminderTask2.RANDOM_ALCOHOL_REMINDER_PENDING_INTENT_ID);
        PendingIntent incrementWaterPendingIntent = PendingIntent.getService(
                context,
                RANDOM_ALCOHOL_REMINDER_PENDING_INTENT_ID,
                findRandomAlcoholIntent,
                PendingIntent.FLAG_CANCEL_CURRENT);
        NotificationCompat.Action findRandomAlcoholAction = new NotificationCompat.Action(R.drawable.ic_cancel_black_24px,
                context.getString(R.string.yes),
                incrementWaterPendingIntent);
        return findRandomAlcoholAction;
    }

    private static NotificationCompat.Action ignoreReminderAction2(Context context) {
        Intent ignoreReminderIntent = new Intent(context, ReminderIntentService.class);
        ignoreReminderIntent.setAction(ReminderTask2.ACTION_DISMISS_NOTIFICATION2);
        PendingIntent ignoreReminderPendingIntent = PendingIntent.getService(
                context,
                ACTION_IGNORE_PENDING_INTENT_ID,
                ignoreReminderIntent,
                PendingIntent.FLAG_UPDATE_CURRENT);
        NotificationCompat.Action ignoreReminderAction = new NotificationCompat.Action(R.drawable.ic_cancel_black_24px,
                context.getString(R.string.no),
                ignoreReminderPendingIntent);
        return ignoreReminderAction;
    }

    public static void remindUser(Context context) {
        NotificationManager notificationManager = (NotificationManager)
                context.getSystemService(Context.NOTIFICATION_SERVICE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel mChannel = new NotificationChannel(
                    WATER_REMINDER_NOTIFICATION_CHANNEL_ID,
                    context.getString(R.string.visit_favorites_notification_title),
                    NotificationManager.IMPORTANCE_HIGH);
            notificationManager.createNotificationChannel(mChannel);
        }
        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(context,WATER_REMINDER_NOTIFICATION_CHANNEL_ID)
                .setColor(ContextCompat.getColor(context, R.color.colorPrimary))
                .setSmallIcon(R.drawable.ic_cancel_black_24px)
                .setLargeIcon(largeIcon(context))
                .setContentTitle(context.getString(R.string.visit_favorites_notification_title))
                .setContentText(context.getString(R.string.visit_favorites_notification_body))
                .setStyle(new NotificationCompat.BigTextStyle().bigText(
                        context.getString(R.string.visit_favorites_notification_body)))
                .setDefaults(Notification.DEFAULT_VIBRATE)
                .setContentIntent(contentIntent(context))
                .addAction(drinkWaterAction(context))
                .addAction(ignoreReminderAction(context))
                .setAutoCancel(true);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN
                && Build.VERSION.SDK_INT < Build.VERSION_CODES.O) {
            notificationBuilder.setPriority(NotificationCompat.PRIORITY_HIGH);
        }
        notificationManager.notify(0, notificationBuilder.build());
    }
    private static NotificationCompat.Action ignoreReminderAction(Context context) {
        Intent ignoreReminderIntent = new Intent(context, ReminderIntentService.class);
        ignoreReminderIntent.setAction(ReminderTask.ACTION_DISMISS_NOTIFICATION);
        PendingIntent ignoreReminderPendingIntent = PendingIntent.getService(
                context,
                ACTION_IGNORE_PENDING_INTENT_ID,
                ignoreReminderIntent,
                PendingIntent.FLAG_UPDATE_CURRENT);
        NotificationCompat.Action ignoreReminderAction = new NotificationCompat.Action(R.drawable.ic_cancel_black_24px,
                context.getString(R.string.no_thanks),
                ignoreReminderPendingIntent);
        return ignoreReminderAction;
    }

    private static NotificationCompat.Action drinkWaterAction(Context context) {
        Intent incrementWaterCountIntent = new Intent(context, ReminderIntentService.class);
        incrementWaterCountIntent.setAction(ReminderTask.ACTION_INCREMENT_WATER_COUNT);
        PendingIntent incrementWaterPendingIntent = PendingIntent.getService(
                context,
                ACTION_DRINK_PENDING_INTENT_ID,
                incrementWaterCountIntent,
                PendingIntent.FLAG_CANCEL_CURRENT);
        NotificationCompat.Action drinkWaterAction = new NotificationCompat.Action(R.drawable.ic_cancel_black_24px,
                context.getString(R.string.Okay),
                incrementWaterPendingIntent);
        return drinkWaterAction;
    }

    private static PendingIntent contentIntent(Context context) {
        Intent startActivityIntent = new Intent(context, MainActivity.class);
        return PendingIntent.getActivity(
                context,
                WATER_REMINDER_PENDING_INTENT_ID,
                startActivityIntent,
                PendingIntent.FLAG_UPDATE_CURRENT);
    }

    private static Bitmap largeIcon(Context context) {
        Resources res = context.getResources();
        Bitmap largeIcon = BitmapFactory.decodeResource(res, R.drawable.ic_cancel_black_24px);
        return largeIcon;
    }
}