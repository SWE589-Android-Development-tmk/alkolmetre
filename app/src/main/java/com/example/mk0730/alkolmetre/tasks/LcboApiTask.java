package com.example.mk0730.alkolmetre.tasks;

import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import com.example.mk0730.alkolmetre.alcohol.AlcoholAdapter;
import com.example.mk0730.alkolmetre.db.model.ApiQuery;
import com.example.mk0730.alkolmetre.db.model.ApiQueryResult;
import com.example.mk0730.alkolmetre.lcbo.LcboApiResponse;
import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class LcboApiTask extends AsyncTask<String, Void, String> {
    private AlcoholAdapter adapter;
    private AsyncTaskCompleted completed;
    private Context applicationContext;
    private ContentResolver contentResolver;

    public LcboApiTask(AlcoholAdapter adapter, Context applicationContext, AsyncTaskCompleted completed) {
        this.adapter = adapter;
        this.applicationContext = applicationContext;
        this.completed = completed;
        this.contentResolver = applicationContext.getContentResolver();
    }

    @Override
    protected String doInBackground(String... strings) {
        HttpURLConnection urlConnection = null;
        BufferedReader reader = null;
        String forecastJsonStr = null;

        try {
            URL url = new URL(strings[0]);

            /* Check whether this URL was registered in DB or not */
            Cursor curQuery = this.contentResolver.query(ApiQuery.CONTENT_URI,
                    null, "query",
                    new String[]{url.toString()}, null);
            if (curQuery.getCount() > 0){
                /* This query was saved before */
                curQuery.moveToFirst();
                long id = curQuery.getLong(curQuery.getColumnIndex("id"));
                Cursor curResult = this.contentResolver.query(ApiQueryResult.CONTENT_URI, null,
                        "apiQueryId", new String[]{String.valueOf(id)}, null);
            }

            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();

            InputStream inputStream = urlConnection.getInputStream();
            StringBuffer buffer = new StringBuffer();

            if (inputStream != null) {
                reader = new BufferedReader(new InputStreamReader(inputStream));

                String line;
                while ((line = reader.readLine()) != null) {
                    buffer.append(line + "\n");
                }
                if (buffer.length() != 0) {
                    forecastJsonStr = buffer.toString();
                }
            }
        } catch (IOException e) {
            Toast.makeText(applicationContext, e.toString(), Toast.LENGTH_LONG);
            Log.e("LcboApiTask", "Error ", e);
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (reader != null) {
                try {
                    reader.close();
                } catch (final IOException e) {
                    Log.e("LcboApiTask", "Error closing stream", e);
                }
            }
        }

        return forecastJsonStr;
    }

    @Override
    protected void onPostExecute(String result) {
        super.onPostExecute(result);

        try {
            LcboApiResponse response = parse(result);

            //adapter.setAlcohols(response);

            if (completed != null) {
                this.completed.completed();
            }
        } catch (Throwable e) {
            Log.v("LcboApiTask", e.getMessage());
        }
    }

    private LcboApiResponse parse(String json) throws IOException {
        Moshi moshi = new Moshi.Builder().build();
        JsonAdapter<LcboApiResponse> jsonAdapter = moshi.adapter(LcboApiResponse.class);
        LcboApiResponse response = jsonAdapter.fromJson(json);
        return response;
    }
}
