package com.example.mk0730.alkolmetre.utils;

import android.net.Uri;

import com.example.mk0730.alkolmetre.AlcoholFilter;
import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

/**
 * These utilities will be used to communicate with the network.
 */
public class NetworkUtils {

    private static Moshi moshi = new Moshi.Builder().build();

    final static String LCBO_BASE_URL = "http://lcboapi.com/products";

    final static String PARAM_QUERY = "q";

    final static String PARAM_CATEGORIES = "primary_category";

    /*
     * The sort field. One of price in cents, alcohol contents,
     * price per liter of alcohol in cents.
     * Ascending or descending order is specified by adding
     * .asc or .desc to the end of the attribute name.
     * LcboApiResponse: lcboapi.com/products?order=id.desc
     */
    final static String PARAM_SORT = "order";
    final static String[] SORT_PARAMETERS = {
                        "price_per_liter_of_alcohol_in_cent",
                        "alcohol_content",
                        "price_in_cents"
    };
    final static String[] SORT_BY= {"desc", "asc"};

    /*
     * Return only products where the specified attributes are true.
     */
    final static String[] WHERE_SORT = {"where", "where_not"};

    /*
     * Return only products where the specified attributes are true.
     * is_discontinued, is_vqa
     */
    final static String[] WHERE_SORT_BY = {"is_discontinued", "is_vqa"};

    private AlcoholFilter filter = new AlcoholFilter();

    public NetworkUtils setSearch(String search){
        this.filter.setSearch(search);
        return this;
    }

    public NetworkUtils setCategory(String category){
        this.filter.setCategory(category);
        return this;
    }

    public String build(){
        JsonAdapter<AlcoholFilter> jsonAdapter = moshi.adapter(AlcoholFilter.class);
        String alcoholFilter = jsonAdapter.toJson(this.filter);
        return alcoholFilter;
    }

    public static AlcoholFilter parse(String json) throws IOException {
        JsonAdapter<AlcoholFilter> jsonAdapter = moshi.adapter(AlcoholFilter.class);
        AlcoholFilter alcoholFilter = jsonAdapter.fromJson(json);
        return alcoholFilter;
    }

    /**
     * Builds the URL used to query LCBO.
     *
     * @param alcoholFilter The keyword that will be queried for.
     * @return The URL to use to query the weather server.
     */
    public static URL buildUrl(AlcoholFilter alcoholFilter) {
        Uri buildUri = Uri.parse(LCBO_BASE_URL).buildUpon()
                //.appendQueryParameter(PARAM_QUERY, alcoholFilter.getSearch())
                .appendQueryParameter(PARAM_CATEGORIES, alcoholFilter.getCategory())
                .build();

        URL url = null;
        try {
            url = new URL(buildUri.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return url;
    }

    /**
     * This method returns the entire result from the HTTP response.
     *
     * @param url The URL to fetch the HTTP response from.
     * @return The contents of the HTTP response.
     * @throws IOException Related to network and stream reading
     */
    public static String getResponseFromHttpUrl(URL url) throws IOException {
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        try {
            InputStream in = urlConnection.getInputStream();

            Scanner scanner = new Scanner(in);
            scanner.useDelimiter("\\A");

            boolean hasInput = scanner.hasNext();
            if (hasInput) {
                return scanner.next();
            } else {
                return null;
            }
        } finally {
            urlConnection.disconnect();
        }
    }
}
