package com.sargent.mark.githubreposearch;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.AsyncTaskLoader;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.sargent.mark.githubreposearch.model.Repository;

import org.json.JSONException;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity
        implements LoaderManager.LoaderCallbacks<ArrayList<Repository>>{
    static final String TAG = "mainactivity";
    private ProgressBar progress;
    private EditText search;
    private RecyclerView rv;
    public final static String URLKEY = "url";
    private static final int GITHUB_SEARCH_LOADER = 1;
    public final static String SEARCH_QUERY_EXTRA = "query";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        progress = (ProgressBar) findViewById(R.id.progressBar);
        search = (EditText) findViewById(R.id.searchQuery);
        rv = (RecyclerView)findViewById(R.id.recyclerView);

        rv.setLayoutManager(new LinearLayoutManager(this));

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemNumber = item.getItemId();

        if (itemNumber == R.id.search) {
            String query = search.getText().toString();
            Bundle queryBundle = new Bundle();
            queryBundle.putString(SEARCH_QUERY_EXTRA, query);
            LoaderManager loaderManager = getSupportLoaderManager();
            Loader<ArrayList<Repository>> loader = loaderManager.getLoader(GITHUB_SEARCH_LOADER);
            if(loader == null){
                loaderManager.initLoader(GITHUB_SEARCH_LOADER, queryBundle, this).forceLoad();
            }else{
                loaderManager.restartLoader(GITHUB_SEARCH_LOADER, queryBundle, this);
            }
        }

        return true;
    }

<<<<<<< HEAD
    class NetworkTask extends AsyncTask<URL, Void, ArrayList<Repository>> {
        String query;

        NetworkTask(String s) {
            query = s;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progress.setVisibility(View.VISIBLE);

        }

        @Override
        protected ArrayList<Repository> doInBackground(URL... params) {
            ArrayList<Repository> result = null;
            URL url = NetworkUtils.makeURL(query, "stars");
            Log.d(TAG, "url: " + url.toString());
            try {
                String json = NetworkUtils.getResponseFromHttpUrl(url);
                result = NetworkUtils.parseJSON(json);
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
=======
    @Override
    public Loader<ArrayList<Repository>> onCreateLoader(int id, final Bundle args) {
        return new AsyncTaskLoader<ArrayList<Repository>>(this) {

            @Override
            protected void onStartLoading() {
                super.onStartLoading();
                if(args == null) return;
                progress.setVisibility(View.VISIBLE);
>>>>>>> 118dc4890801290979a897ea0770a036c802364b
            }

<<<<<<< HEAD
        @Override
        protected void onPostExecute(final ArrayList<Repository> data) {
            super.onPostExecute(data);
            progress.setVisibility(View.GONE);
            if (data != null) {
                GithubAdapter adapter = new GithubAdapter(data, new GithubAdapter.ItemClickListener() {
                    @Override
                    public void onItemClick(int clickedItemIndex) {
                        String url = data.get(clickedItemIndex).getUrl();
                        Log.d(TAG, String.format("Url %s", url));
                    }
                });
                rv.setAdapter(adapter);

            }
=======
            @Override
            public ArrayList<Repository> loadInBackground() {
                String query = args.getString(SEARCH_QUERY_EXTRA);

                if(query == null || TextUtils.isEmpty(query)) return null;

                ArrayList<Repository> result = null;
                URL url = NetworkUtils.makeURL(query, "stars");
                Log.d(TAG, "url: " + url.toString());
                try {
                    String json = NetworkUtils.getResponseFromHttpUrl(url);
                    result = NetworkUtils.parseJSON(json);
                } catch (IOException e) {
                    e.printStackTrace();
                    return null;
                } catch (JSONException e) {
                    e.printStackTrace();
                    return null;
                }
                return result;

            }
        };
    }

    @Override
    public void onLoadFinished(Loader<ArrayList<Repository>> loader, final ArrayList<Repository> data) {
        progress.setVisibility(View.GONE);
        if (data != null) {
            GithubAdapter adapter = new GithubAdapter(data, new GithubAdapter.ItemClickListener() {
                @Override
                public void onItemClick(int clickedItemIndex) {
                    String url = data.get(clickedItemIndex).getUrl();
                    Log.d(TAG, String.format("Url %s", url));

                    Intent intent = new Intent(MainActivity.this, Web.class);
                    intent.putExtra(URLKEY, url);
                    startActivity(intent);
                }
            });
            rv.setAdapter(adapter);
>>>>>>> 118dc4890801290979a897ea0770a036c802364b
        }
    }

    @Override
    public void onLoaderReset(Loader<ArrayList<Repository>> loader) {}


    ///The old AsyncTask: we are not using it now, kept for reference
//    class NetworkTask extends AsyncTask<URL, Void, ArrayList<Repository>>{
//        private String query;
//
//        NetworkTask(String s) {
//            query = s;
//        }
//
//        @Override
//        protected void onPreExecute() {
//            super.onPreExecute();
//            progress.setVisibility(View.VISIBLE);
//
//        }
//
//        @Override
//        protected ArrayList<Repository> doInBackground(URL... params) {
//            ArrayList<Repository> result = null;
//            URL url = NetworkUtils.makeURL(query, "stars");
//            Log.d(TAG, "url: " + url.toString());
//            try {
//                String json = NetworkUtils.getResponseFromHttpUrl(url);
//                result = NetworkUtils.parseJSON(json);
//            } catch (IOException e) {
//                e.printStackTrace();
//            } catch (JSONException e) {
//                e.printStackTrace();
//            }
//            return result;
//        }
//
//        @Override
//        protected void onPostExecute(final ArrayList<Repository> data) {
//            super.onPostExecute(data);
//            progress.setVisibility(View.GONE);
//            if (data != null) {
//                GithubAdapter adapter = new GithubAdapter(data, new GithubAdapter.ItemClickListener() {
//                    @Override
//                    public void onItemClick(int clickedItemIndex) {
//                        String url = data.get(clickedItemIndex).getUrl();
//                        Log.d(TAG, String.format("Url %s", url));
//
//                        Intent intent = new Intent(MainActivity.this, Web.class);
//                        intent.putExtra(URLKEY, url);
//                        startActivity(intent);
//                    }
//                });
//                rv.setAdapter(adapter);
//
//            }
//        }
//
//    }
}
