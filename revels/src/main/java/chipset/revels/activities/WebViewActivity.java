package chipset.revels.activities;

/**
 * Developer: chipset
 * Package : chipset.revels.activities
 * Project : Revels
 * Date : 09/02/15
 */

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;

import chipset.revels.R;
import chipset.revels.resources.Constants;

public class WebViewActivity extends ActionBarActivity {
    WebView registerView;
    ProgressDialog pDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setElevation(0f);
        loadWebView();

    }


    private class MyBrowser extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }

        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            pDialog.show();
            view.setVisibility(View.GONE);
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            pDialog.dismiss();
            view.setVisibility(View.VISIBLE);
        }

        @Override
        public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
            super.onReceivedError(view, errorCode, description, failingUrl);
            setContentView(R.layout.no_connection_layout);
            Button retryButton = (Button) findViewById(R.id.retry_button);
            retryButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    loadWebView();
                }
            });
        }
    }

    @Override
    public void onBackPressed() {

        if (registerView.isFocused() && registerView.canGoBack()) {
            registerView.goBack();
        } else {
            registerView.setVisibility(View.GONE);
            super.onBackPressed();
        }
    }

    @SuppressLint("SetJavaScriptEnabled")
    public void loadWebView() {
        setContentView(R.layout.activity_web_view);

        pDialog = new ProgressDialog(WebViewActivity.this);
        pDialog.setCancelable(true);
        pDialog.setMessage("Loading...");
        pDialog.setTitle("Please Wait");
        pDialog.setIndeterminate(true);

        registerView = (WebView) findViewById(R.id.registerView);
        registerView.setWebViewClient(new MyBrowser());
        registerView.getSettings().setLoadsImagesAutomatically(true);
        registerView.getSettings().setJavaScriptEnabled(true);
        registerView.getSettings().setAllowFileAccess(true);
        registerView.getSettings().setBuiltInZoomControls(true);
        registerView.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
        registerView.loadUrl(Constants.URL_REG);
    }
}
