package com.example.mk0730.alkolmetre.scheduler;

import android.app.job.JobParameters;
import android.app.job.JobService;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.widget.Toast;

@RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
public class VisitFavoriteSchedulerService extends JobService {
    private AsyncTask mBackgroundTask;

    @Override
    public boolean onStartJob(final JobParameters jobParameters) {

        Toast.makeText(this, "Fired!", Toast.LENGTH_SHORT).show();
        mBackgroundTask = new AsyncTask() {

            @Override
            protected Object doInBackground(Object[] params) {
                Context context = VisitFavoriteSchedulerService.this;
                ReminderTasks.executeTask(context, ReminderTasks.ACTION_CHARGING_REMINDER);
                return null;
            }

            @Override
            protected void onPostExecute(Object o) {
                jobFinished(jobParameters, false);
            }
        };

        mBackgroundTask.execute();
        return true;
    }

    @Override
    public boolean onStopJob(JobParameters jobParameters) {
        if (mBackgroundTask != null) {
            mBackgroundTask.cancel(true);
        }
        return true;
    }
}
