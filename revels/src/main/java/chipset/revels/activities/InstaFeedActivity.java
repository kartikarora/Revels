package chipset.revels.activities;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.AbsListView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ProgressBar;

import chipset.revels.R;
import chipset.revels.adapters.InstaFeedListAdapter;
import chipset.revels.model.instagram.InstaFeed;
import chipset.revels.network.APIClient;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Developer: chipset
 * Package : chipset.revels.activities
 * Project : Revels
 * Date : 18/1/14
 */

public class InstaFeedActivity extends ActionBarActivity {

    ListView instaFeedListView;
    ProgressBar progressBar;
    SwipeRefreshLayout mSwipeRefreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loadInstaFeed();
    }

    public void loadInstaFeed() {
        setContentView(R.layout.activity_insta_feed);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        instaFeedListView = (ListView) findViewById(R.id.insta_feed_list_view);
        progressBar = (ProgressBar) findViewById(R.id.insta_feed_progress_bar);
        mSwipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.insta_feed_swipe_refresh);

        mSwipeRefreshLayout.setColorSchemeResources(R.color.teal_primary,R.color.teal_primary_dark);

        APIClient.getInstagram().getFeed(new Callback<InstaFeed>() {
            @Override
            public void success(InstaFeed instaFeed, Response response) {
                instaFeedListView.setAdapter(new InstaFeedListAdapter(getApplicationContext(), instaFeed));
                instaFeedListView.setVisibility(View.VISIBLE);
                progressBar.setVisibility(View.GONE);
            }

            @Override
            public void failure(RetrofitError error) {
                progressBar.setVisibility(View.GONE);
                setContentView(R.layout.no_connection_layout);
                Button retryButton = (Button) findViewById(R.id.retry_button);
                retryButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        loadInstaFeed();
                    }
                });
            }
        });

        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mSwipeRefreshLayout.setRefreshing(true);
                APIClient.getInstagram().getFeed(new Callback<InstaFeed>() {
                    @Override
                    public void success(InstaFeed instaFeed, Response response) {
                        InstaFeedListAdapter adapter = new InstaFeedListAdapter(getApplicationContext(), instaFeed);
                        adapter.notifyDataSetChanged();
                        instaFeedListView.setAdapter(adapter);
                        instaFeedListView.setVisibility(View.VISIBLE);
                        mSwipeRefreshLayout.setRefreshing(false);
                    }

                    @Override
                    public void failure(RetrofitError error) {
                        progressBar.setVisibility(View.GONE);
                        mSwipeRefreshLayout.setRefreshing(false);
                        setContentView(R.layout.no_connection_layout);
                        Button retryButton = (Button) findViewById(R.id.retry_button);
                        retryButton.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                loadInstaFeed();
                            }
                        });
                    }
                });
            }
        });

        instaFeedListView.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {

            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                if (firstVisibleItem == 0 && visibleItemCount > 0 && instaFeedListView.getChildAt(0).getTop() >= 0)
                    mSwipeRefreshLayout.setEnabled(true);
                else mSwipeRefreshLayout.setEnabled(false);
            }
        });
    }

}
