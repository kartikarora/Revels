package chipset.revels.activities;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AbsListView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ProgressBar;

import chipset.revels.R;
import chipset.revels.adapters.ResultListAdapter;
import chipset.revels.model.revels.Result;
import chipset.revels.network.APIClient;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Developer: chipset
 * Package : chipset.revels.activities
 * Project : Revels
 * Date : 19/1/15
 */

public class ResultsActivity extends AppCompatActivity {
    ListView resultsListView;
    ProgressBar progressBar;
    SwipeRefreshLayout mSwipeRefreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setElevation(0f);
        loadResults();
    }

    public void loadResults() {
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        resultsListView = (ListView) findViewById(R.id.results_list_view);
        progressBar = (ProgressBar) findViewById(R.id.results_progress_bar);
        mSwipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.results_swipe_refresh);

        mSwipeRefreshLayout.setColorSchemeResources(R.color.teal_primary, R.color.teal_primary_dark);

        APIClient.getRevels().getResults(new Callback<Result>() {
            @Override
            public void success(Result result, Response response) {
                if (result == null || result.getCount() == 0) {
                    setContentView(R.layout.no_resutls);
                } else {
                    ResultListAdapter adapter = new ResultListAdapter(getApplicationContext(), result);
                    adapter.notifyDataSetChanged();
                    resultsListView.setAdapter(adapter);
                    resultsListView.setVisibility(View.VISIBLE);
                    progressBar.setVisibility(View.GONE);
                }
            }

            @Override
            public void failure(RetrofitError error) {
                progressBar.setVisibility(View.GONE);
                setContentView(R.layout.no_connection_layout);
                Button retryButton = (Button) findViewById(R.id.retry_button);
                retryButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        loadResults();
                    }
                });
            }
        });

        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mSwipeRefreshLayout.setRefreshing(true);
                APIClient.getRevels().getResults(new Callback<Result>() {
                    @Override
                    public void success(Result result, Response response) {

                        ResultListAdapter adapter = new ResultListAdapter(getApplicationContext(), result);
                        adapter.notifyDataSetChanged();
                        resultsListView.setAdapter(adapter);
                        resultsListView.setVisibility(View.VISIBLE);
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
                                loadResults();
                            }
                        });
                    }
                });
            }
        });


        resultsListView.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {

            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                if (firstVisibleItem == 0 && visibleItemCount > 0 && resultsListView.getChildAt(0).getTop() >= 0)
                    mSwipeRefreshLayout.setEnabled(true);
                else mSwipeRefreshLayout.setEnabled(false);
            }
        });
    }
}
