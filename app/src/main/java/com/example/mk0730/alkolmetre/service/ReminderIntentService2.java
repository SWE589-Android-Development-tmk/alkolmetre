package com.example.mk0730.alkolmetre.service;

import android.app.IntentService;
import android.content.Intent;

public class ReminderIntentService2 extends IntentService {
    public ReminderIntentService2() {
        super("ReminderIntentService2");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        String action = intent.getAction();
        ReminderTask2.executeTask(this, action);
    }
}
