package com.example.mk0730.alkolmetre.service;

import android.app.IntentService;
import android.content.Intent;

public class ReminderIntentService extends IntentService {

    public ReminderIntentService() {
        super("ReminderIntentService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        String action = intent.getAction();
        ReminderTask.executeTask(this, action);
    }
}