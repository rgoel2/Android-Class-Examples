package com.sargent.mark.githubreposearch;

import android.net.Uri;
import android.util.Log;

import com.sargent.mark.githubreposearch.model.Repository;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by mark on 6/12/17.
 */

public class NetworkUtils {
    public static final String TAG = "NetworkUtils";

    public static final String GITHUB_BASE_URL =
            "https://api.github.com/search/repositories";
    public static final String PARAM_QUERY = "q";
    public static final String PARAM_SORT = "sort";

    public static URL makeURL(String searchQuery, String sortBy) {
        Uri uri = Uri.parse(GITHUB_BASE_URL).buildUpon()
                .appendQueryParameter(PARAM_QUERY, searchQuery)
                .appendQueryParameter(PARAM_SORT, sortBy).build();

        URL url = null;
        try {
            String urlString = uri.toString();
            Log.d(TAG, "Url: " + urlString);
            url = new URL(uri.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        return url;
    }

    public static String getResponseFromHttpUrl(URL url) throws IOException {
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        try {
            InputStream in = urlConnection.getInputStream();
            Scanner input = new Scanner(in);

            input.useDelimiter("\\A");
            return (input.hasNext()) ? input.next() : null;

        } finally {
            urlConnection.disconnect();
        }
    }

    public static ArrayList<Repository> parseJSON(String json) throws JSONException{
        ArrayList<Repository> result = new ArrayList<>();
        JSONObject main = new JSONObject(json);
        JSONArray items = main.getJSONArray("items");

        for(int i = 0; i < items.length(); i++){
            JSONObject item = items.getJSONObject(i);
            String name = item.getString("name");
            JSONObject owner = item.getJSONObject("owner");
            String ownerName = owner.getString("login");
            String url = item.getString("html_url");
            Repository repo = new Repository(name, ownerName, url);
            result.add(repo);
        }
        return result;
    }



}
