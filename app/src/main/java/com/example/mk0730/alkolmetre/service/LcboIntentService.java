package com.example.mk0730.alkolmetre.service;

import android.app.IntentService;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.ResultReceiver;
import android.util.Log;

import com.example.mk0730.alkolmetre.alcohol.AlcoholAdapter;
import com.example.mk0730.alkolmetre.data.FavoriteContract;
import com.example.mk0730.alkolmetre.lcbo.LcboApiResponse;
import com.example.mk0730.alkolmetre.lcbo.LcboApiResponseResult;
import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * An {@link IntentService} subclass for handling asynchronous task requests in
 * a service on a separate handler thread.
 * <p>
 * helper methods.
 */
public class LcboIntentService extends IntentService {
    // IntentService can perform, e.g. ACTION_FETCH_NEW_ITEMS
    private static final String ACTION_CALL_API = "action_call_api";
    private static final String ACTION_DOWNLOAD_IMAGE = "action_download_image";

    private static final String PARAM_IMAGE_VIEW = "param_image_view";
    private static final String PARAM_URL = "param_url";
    private static final String PARAM_RECEIVER = "param_receiver";

    private static final int STATUS_COMPLETED = 0;

    private static AlcoholAdapter adapter;
    private static ContentResolver contentResolver;

    public LcboIntentService() {
        super("LcboIntentService");
    }

    /**
     * Starts this service to perform action Foo with the given parameters. If
     * the service is already performing a task this action will be queued.
     *
     * @see IntentService
     */
    public static void startActionCallApi(Context context, String url, AlcoholAdapter pAdapter,
                                          IntentServiceResultReceiver receiver) {
        adapter = pAdapter;
        contentResolver = context.getContentResolver();

        Intent intent = new Intent(context, LcboIntentService.class);
        intent.setAction(ACTION_CALL_API);
        intent.putExtra(PARAM_URL, url);
        intent.putExtra(PARAM_RECEIVER, receiver);
        context.startService(intent);
    }

    /**
     * Starts this service to perform action Baz with the given parameters. If
     * the service is already performing a task this action will be queued.
     *
     * @see IntentService
     */
    // TODO: Customize helper method
    public static void startActionDownloadImage(Context context, String url, IntentServiceResultReceiver receiver) {
        Intent intent = new Intent(context, LcboIntentService.class);
        intent.setAction(ACTION_DOWNLOAD_IMAGE);
        intent.putExtra(PARAM_URL, url);
        intent.putExtra(PARAM_RECEIVER, receiver);
        context.startService(intent);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        final ResultReceiver receiver = intent.getParcelableExtra(PARAM_RECEIVER);

        if (intent != null) {
            final String action = intent.getAction();
            if (ACTION_CALL_API.equals(action)) {
                final String url = intent.getStringExtra(PARAM_URL);
                handleActionCallApi(url, receiver);
            } else if (ACTION_DOWNLOAD_IMAGE.equals(action)) {
                final String url = intent.getStringExtra(PARAM_URL);
                handleActionDownloadImage(url, receiver);
            }
        }
    }

    public static LcboApiResponse callApi(String paramUrl){
        HttpURLConnection urlConnection = null;
        BufferedReader reader = null;
        String json = null;

        try {
            URL url = new URL(paramUrl);

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
                    json = buffer.toString();
                }
            }

            LcboApiResponse response = parse(json);

            return response;
        } catch (IOException e) {
            //Toast.makeText(applicationContext, e.toString(), Toast.LENGTH_LONG);
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
        return null;
    }

    /**
     * Handle action Foo in the provided background thread with the provided
     * parameters.
     */
    private void handleActionCallApi(String paramUrl, ResultReceiver receiver) {
        Bundle bundle = new Bundle();
        LcboApiResponse response = callApi(paramUrl);

        for (int i=0; i<response.getResult().size(); i++) {
            LcboApiResponseResult lcboResult = response.getResult().get(i);
            checkFavoriteAndSetId(lcboResult);
        }

        bundle.putSerializable("response", response);
        receiver.send(STATUS_COMPLETED, bundle);
    }

    private static LcboApiResponse parse(String json) throws IOException {
        Moshi moshi = new Moshi.Builder().build();
        JsonAdapter<LcboApiResponse> jsonAdapter = moshi.adapter(LcboApiResponse.class);
        LcboApiResponse response = jsonAdapter.fromJson(json);
        return response;
    }

    private void checkFavoriteAndSetId(LcboApiResponseResult lcboResult) {
        Cursor query = contentResolver.query(
                FavoriteContract.FavoriteEntry.buildFavoriteWithAPIId(lcboResult.getId()),
                new String[]{FavoriteContract.FavoriteEntry._ID},
                FavoriteContract.FavoriteEntry._ID,
                new String[]{lcboResult.getId().toString()},
                null);

        lcboResult.setFavorite(false);
        if (query.getCount() > 0) {
            query.moveToFirst();
            lcboResult.setFavoriteId(query.getInt(0));
            lcboResult.setFavorite(true);
        }
        query.close();
    }

    /**
     * Handle action Baz in the provided background thread with the provided
     * parameters.
     */
    private void handleActionDownloadImage(String url, ResultReceiver receiver) {
        Bundle bundle = new Bundle();

        Bitmap bmp = null;
        try {
            InputStream in = new java.net.URL(url).openStream();
            bmp = BitmapFactory.decodeStream(in);
            bundle.putParcelable("image", bmp);
            receiver.send(STATUS_COMPLETED, bundle);
            //bmImage.setImageBitmap(bmp);
        } catch (Exception e) {
            Log.e("Error", e.getMessage());
            e.printStackTrace();
        }
    }
}
