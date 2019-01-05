package com.example.mk0730.alkolmetre.utils;

import android.content.Context;
import android.support.annotation.NonNull;

import com.example.mk0730.alkolmetre.service.ReminderFirebaseJobService2;
import com.firebase.jobdispatcher.Driver;
import com.firebase.jobdispatcher.FirebaseJobDispatcher;
import com.firebase.jobdispatcher.GooglePlayDriver;
import com.firebase.jobdispatcher.Job;
import com.firebase.jobdispatcher.Lifetime;
import com.firebase.jobdispatcher.Trigger;

import java.util.concurrent.TimeUnit;

public class ReminderUtilities2 {
        private static final int REMINDER_INTERVAL_MINUTES = 1;
        private static final int REMINDER_INTERVAL_SECONDS = (int) (TimeUnit.MINUTES.toSeconds(REMINDER_INTERVAL_MINUTES));
        private static final int SYNC_FLEXTIME_SECONDS = REMINDER_INTERVAL_SECONDS;

        private static final String FEEL_LUCKY_JOB_TAG = "feel_lucky_reminder_tag";
        private static boolean sInitialized;
        synchronized public static void scheduleFeelLuckyReminder(@NonNull final Context context) {
            if (sInitialized) return;
            Driver driver = new GooglePlayDriver(context);
            FirebaseJobDispatcher dispatcher = new FirebaseJobDispatcher(driver);
            Job constraintReminderJob = dispatcher.newJobBuilder()
                    /* The Service that will be used to write to preferences */
                    .setService(ReminderFirebaseJobService2.class)
                    .setTag(FEEL_LUCKY_JOB_TAG)
                    .setLifetime(Lifetime.FOREVER)
                    .setRecurring(true)
                    .setTrigger(Trigger.executionWindow(
                            REMINDER_INTERVAL_SECONDS,
                            REMINDER_INTERVAL_SECONDS + SYNC_FLEXTIME_SECONDS))
                    .setReplaceCurrent(true)
                    .build();
            dispatcher.schedule(constraintReminderJob);
            sInitialized = true;
        }
    }
