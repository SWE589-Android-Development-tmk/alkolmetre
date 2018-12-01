package com.example.mk0730.alkolmetre.utils;

import android.net.Uri;

import com.example.mk0730.alkolmetre.AlcoholFilter;
import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;

import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

/**
 * These utilities will be used to communicate with the network.
 */
public class UrlUtils {

    private static Moshi moshi = new Moshi.Builder().build();

    final static String LCBO_BASE_URL = "http://lcboapi.com/products";
    final static String PARAM_QUERY = "q";
    final static String PARAM_PAGE = "page";
    final static String PARAM_WHERE = "where";
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

    private static URL url;

    private AlcoholFilter filter = new AlcoholFilter();

    public UrlUtils setSearch(String search){
        this.filter.setSearch(search);
        return this;
    }

    public UrlUtils setCategory(String category){
        this.filter.setCategory(category);
        return this;
    }

    public UrlUtils setIsDiscontinued(Boolean isDiscontinued){
        this.filter.setIs_continued(isDiscontinued);
        return this;
    }

    public UrlUtils setIsVqa(Boolean isVqa){
        this.filter.setIs_vqa(isVqa);
        return this;
    }

    public UrlUtils setOrderPrice(Boolean price){
        this.filter.setOrder_price(price);
        return this;
    }

    public UrlUtils setOrderAlcoholContent(Boolean alcoholContent){
        this.filter.setOrder_alcohol_content(alcoholContent);
        return this;
    }

    public UrlUtils setOrderPricePerLiter(Boolean pricePerLiter){
        this.filter.setOrder_price_per_liter(pricePerLiter);
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
     * Builds the url used to query LCBO.
     *
     * @param alcoholFilter The keyword that will be queried for.
     * @return The url to use to query the weather server.
     */
    public static URL buildUrl(AlcoholFilter alcoholFilter, int page) throws MalformedURLException {
        String query = "";
        if (alcoholFilter.getCategory() != null && !alcoholFilter.getCategory().isEmpty()){
            query = alcoholFilter.getCategory() + "+";
        }
        if (alcoholFilter.getSearch() != null && !alcoholFilter.getSearch().isEmpty()){
            query += alcoholFilter.getSearch();
        }

        String where = "";
        if (alcoholFilter.getIs_continued()){
            where = "is_discontinued" + ",";
        }
        if (alcoholFilter.getIs_vqa()){
            where += "is_vqa";
        }

        String sort = "";
        if (alcoholFilter.getOrder_price()){
            sort = "price_in_cents" + ",";
        }
        if (alcoholFilter.getOrder_alcohol_content()){
            sort += "alcohol_content" + ",";
        }
        if (alcoholFilter.getOrder_price_per_liter()){
            sort += "price_per_liter_of_alcohol_in_cent";
        }

        Uri buildUri = Uri.parse(LCBO_BASE_URL).buildUpon()
                .appendQueryParameter(PARAM_QUERY, query)
                .appendQueryParameter(PARAM_PAGE, String.valueOf(page))
                .appendQueryParameter(PARAM_WHERE, where)
                .appendQueryParameter(PARAM_SORT, sort)
                .build();

        url = new URL(buildUri.toString());
        return url;
    }

    public static URL nextPage(AlcoholFilter alcoholFilter, int page) throws MalformedURLException {
        return buildUrl(alcoholFilter, page);
    }

    /**
     * This method returns the entire result from the HTTP response.
     *
     * @param url The url to fetch the HTTP response from.
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
