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

import com.example.mk0730.alkolmetre.R;
import com.example.mk0730.alkolmetre.base.BaseActivity;
import com.example.mk0730.alkolmetre.scheduler.ReminderTasks;
import com.example.mk0730.alkolmetre.scheduler.VisitFavoriteSchedulerService;

public class NotificationUtils {

    private static final int    FAVORITE_NOTIFICATION_ID         = 1138;
    private static final int    FAVORITE_PENDING_INTENT_ID       = 3417;
    private static final String FAVORITE_NOTIFICATION_CHANNEL_ID = "reminder_notification_channel";

    public static void clearAllNotifications(Context context) {
        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.cancelAll();
    }

    public static void remindUserForFavorites(Context context) {

        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel mChannel = new NotificationChannel(
                    FAVORITE_NOTIFICATION_CHANNEL_ID,
                    "Primary",
                    NotificationManager.IMPORTANCE_HIGH);

            notificationManager.createNotificationChannel(mChannel);
        }
        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(context,FAVORITE_NOTIFICATION_CHANNEL_ID);
        notificationBuilder
                .setColor(ContextCompat.getColor(context, R.color.colorPrimary))
                //.setSmallIcon(R.drawable.ic_drink_notification)
                .setLargeIcon(largeIcon(context))
                .setContentTitle(context.getString(R.string.visit_favorites_notification_title))
                .setContentText(context.getString(R.string.visit_favorites_notification_body))
                .setStyle(new NotificationCompat.BigTextStyle().bigText(
                        context.getString(R.string.visit_favorites_notification_body)))
                .setDefaults(Notification.DEFAULT_VIBRATE)
                .setContentIntent(contentIntent(context))
                .setAutoCancel(true);
        notificationBuilder
                .addAction(drinkWaterAction(context))
                .addAction(ignoreReminderAction(context));

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN && Build.VERSION.SDK_INT < Build.VERSION_CODES.O) {
            notificationBuilder.setPriority(NotificationCompat.PRIORITY_HIGH);
        }

        notificationManager.notify(FAVORITE_NOTIFICATION_ID, notificationBuilder.build());
    }

    private static final int    ACTION_IGNORE_PENDING_INTENT_ID        = 14;

    private static NotificationCompat.Action ignoreReminderAction(Context context) {

        Intent ignoreReminderIntent = new Intent(context, VisitFavoriteSchedulerService.class);
        ignoreReminderIntent.setAction(ReminderTasks.ACTION_DISMISS_NOTIFICATION);

        PendingIntent ignoreReminderPendingIntent = PendingIntent.getService(
                context,
                ACTION_IGNORE_PENDING_INTENT_ID,
                ignoreReminderIntent,
                PendingIntent.FLAG_UPDATE_CURRENT);

        NotificationCompat.Action ignoreReminderAction = new NotificationCompat.Action(R.drawable.ic_cancel_black_24px,
                "No, thanks.",
                ignoreReminderPendingIntent);

        return ignoreReminderAction;
    }

    private static final int    ACTION_DRINK_PENDING_INTENT_ID         = 1;

    private static NotificationCompat.Action drinkWaterAction(Context context) {

        Intent incrementWaterCountIntent = new Intent(context, VisitFavoriteSchedulerService.class);
        incrementWaterCountIntent.setAction(ReminderTasks.ACTION_INCREMENT_WATER_COUNT);

        PendingIntent incrementWaterPendingIntent = PendingIntent.getService(
                context,
                ACTION_DRINK_PENDING_INTENT_ID,
                incrementWaterCountIntent,
                PendingIntent.FLAG_CANCEL_CURRENT);

        NotificationCompat.Action drinkWaterAction = new NotificationCompat.Action(R.drawable.ic_cancel_black_24px,
                "I did it!",
                incrementWaterPendingIntent);

        return drinkWaterAction;
    }

    private static PendingIntent contentIntent(Context context) {
        Intent startActivityIntent = new Intent(context, BaseActivity.class);
        return PendingIntent.getActivity(
                context,
                FAVORITE_PENDING_INTENT_ID,
                startActivityIntent,
                PendingIntent.FLAG_UPDATE_CURRENT);
    }

    private static Bitmap largeIcon(Context context) {

        Resources res       = context.getResources();
        Bitmap    largeIcon = BitmapFactory.decodeResource(res, R.drawable.ic_cancel_black_24px);
        return largeIcon;
    }
}
