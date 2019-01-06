package com.example.mk0730.alkolmetre.utils;

import android.content.Context;
import android.support.annotation.NonNull;

import com.example.mk0730.alkolmetre.service.ReminderFirebaseJobService;
import com.firebase.jobdispatcher.Driver;
import com.firebase.jobdispatcher.FirebaseJobDispatcher;
import com.firebase.jobdispatcher.GooglePlayDriver;
import com.firebase.jobdispatcher.Job;
import com.firebase.jobdispatcher.Lifetime;
import com.firebase.jobdispatcher.Trigger;

import java.util.concurrent.TimeUnit;

public class ReminderUtilities {
        private static final int REMINDER_INTERVAL_SECONDS = 60;

        private static final String REMINDER_JOB_TAG = "recommend_favorite_reminder_tag";
        private static boolean sInitialized;
        synchronized public static void scheduleRecommendationReminder(@NonNull final Context context) {
            if (sInitialized) return;
            Driver driver = new GooglePlayDriver(context);
            FirebaseJobDispatcher dispatcher = new FirebaseJobDispatcher(driver);
            Job constraintReminderJob = dispatcher.newJobBuilder()
                    /* The Service that will be used to write to preferences */
                    .setService(ReminderFirebaseJobService.class)
                    .setTag(REMINDER_JOB_TAG)
                    .setRecurring(true)
                    .setTrigger(Trigger.executionWindow(0, REMINDER_INTERVAL_SECONDS))
                    .build();
            dispatcher.mustSchedule(constraintReminderJob);
            sInitialized = true;
        }
    }
