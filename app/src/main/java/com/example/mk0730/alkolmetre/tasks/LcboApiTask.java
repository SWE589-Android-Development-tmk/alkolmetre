package com.example.mk0730.alkolmetre.tasks;

import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import com.example.mk0730.alkolmetre.AlcoholFilter;
import com.example.mk0730.alkolmetre.alcohol.AlcoholAdapter;
import com.example.mk0730.alkolmetre.lcbo.LcboApiResponse;
import com.example.mk0730.alkolmetre.utils.NetworkUtils;
import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;

import org.json.JSONException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class LcboApiTask extends AsyncTask<String, Void, String[]> {
    private AlcoholAdapter adapter;

    public LcboApiTask(AlcoholAdapter adapter) {
        this.adapter = adapter;
    }

    @Override
    protected String[] doInBackground(String... strings) {
        HttpURLConnection urlConnection   = null;
        BufferedReader reader          = null;
        String 		      forecastJsonStr = null;

        try {
            URL url = new URL(strings[0]);
            urlConnection  = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();

            InputStream inputStream = urlConnection.getInputStream();
            StringBuffer buffer     = new StringBuffer();

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
            Log.e("LcboApiTask", "Error ", e);
        } finally{
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

        return new String[]{forecastJsonStr};
    }

    @Override
    protected void onPostExecute(String[] result) {
        super.onPostExecute(result);

        try {
            adapter.setAlcohols(parse(result[0]));
        }
        catch (Throwable e) {
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
