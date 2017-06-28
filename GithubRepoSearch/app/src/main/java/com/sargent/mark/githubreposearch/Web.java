package com.sargent.mark.githubreposearch;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;

public class Web extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);

        String url = "http://www.google.com";

        Intent intent = getIntent();
        if(intent.hasExtra(MainActivity.URLKEY))
            url = intent.getStringExtra(MainActivity.URLKEY);

        WebView myWebView = (WebView) findViewById(R.id.webview);
        myWebView.loadUrl(url);
    }
}
