package com.example.mk0730.alkolmetre.service;

import android.content.Context;
import android.os.AsyncTask;

import com.firebase.jobdispatcher.JobParameters;
import com.firebase.jobdispatcher.JobService;

public class ReminderFirebaseJobService2 extends JobService {
    private AsyncTask mBackgroundTask;

    @Override
    public boolean onStartJob(final JobParameters job) {
        mBackgroundTask = new AsyncTask() {
            @Override
            protected Object doInBackground(Object[] params) {
                Context context = ReminderFirebaseJobService2.this;
                ReminderTask.executeTask(context, ReminderTask2.RANDOM_ALCOHOL_REMINDER_PENDING_INTENT_ID);
                return null;
            }

            @Override
            protected void onPostExecute(Object o) {
                jobFinished(job, false);
            }
        };

        mBackgroundTask.execute();
        return true;
    }

    @Override
    public boolean onStopJob(JobParameters job) {
        if (mBackgroundTask != null) mBackgroundTask.cancel(true);
        return true;
    }
}
