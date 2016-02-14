package chipset.revels.activities;

/**
 * Developer: chipset
 * Package : chipset.revels.activities
 * Project : Revels
 * Date : 23/12/14
 */

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.gson.Gson;

import chipset.potato.Potato;
import chipset.revels.R;
import chipset.revels.adapters.CategoryTabsAdapter;
import chipset.revels.layouts.SlidingTabLayout;
import chipset.revels.model.revels.Category;
import chipset.revels.network.APIClient;
import chipset.revels.resources.Constants;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class MainActivity extends AppCompatActivity {

    ViewPager mViewPager;
    SlidingTabLayout mSlidingTabLayout;
    PagerAdapter mTabsAdapter;
    ProgressBar progressBar;
    boolean doubleBackToExitPressedOnce = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setElevation(0f);
        loadMain();
    }



    public void loadMain() {
        setContentView(R.layout.activity_main);
        mViewPager = (ViewPager) findViewById(R.id.view_pager);
        mSlidingTabLayout = (SlidingTabLayout) findViewById(R.id.sliding_tabs);
        if (Build.VERSION.SDK_INT == Build.VERSION_CODES.LOLLIPOP)
            mSlidingTabLayout.setElevation(10f);
        progressBar = (ProgressBar) findViewById(R.id.main_progress_bar);

        APIClient.getRevels().getCategory(new Callback<Category>() {

            @Override
            public void success(Category category, Response response) {
                setCategory(category);
                progressBar.setVisibility(View.GONE);
                Potato.potate(getApplicationContext()).Preferences().putSharedPreference(Constants.CATEG, new Gson().toJson(category));
            }

            @Override
            public void failure(RetrofitError error) {
                progressBar.setVisibility(View.GONE);
                if (!Potato.potate(getApplicationContext()).Preferences().getSharedPreferenceBoolean(Constants.FIRST_RUN)) {
                    Category category = new Gson().fromJson(Potato.potate(getApplicationContext()).Preferences().getSharedPreferenceString(Constants.CATEG), Category.class);
                    setCategory(category);
                } else {
                    setContentView(R.layout.no_connection_layout);
                    Button retryButton = (Button) findViewById(R.id.retry_button);
                    error.printStackTrace();
                    retryButton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            loadMain();
                        }
                    });
                }
            }
        });
    }

    @Override
    public void onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            super.onBackPressed();
            return;
        }

        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(this, "Press back again to exit", Toast.LENGTH_SHORT).show();

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                doubleBackToExitPressedOnce = false;
            }
        }, 2000);
    }

    public void setCategory(Category category) {
        mSlidingTabLayout.setVisibility(View.VISIBLE);
        mTabsAdapter = new CategoryTabsAdapter(getSupportFragmentManager(), getApplicationContext(), category);
        mViewPager.setAdapter(mTabsAdapter);
        mSlidingTabLayout.setViewPager(mViewPager);
        mSlidingTabLayout.setCustomTabColorizer(new SlidingTabLayout.TabColorizer() {
            @Override
            public int getIndicatorColor(int position) {
                return Color.WHITE;

            }
        });
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemId = item.getItemId();
        switch (itemId) {
            case R.id.action_instagram: {
                startActivity(new Intent(MainActivity.this, InstaFeedActivity.class));
                break;
            }
            case R.id.action_following: {
                startActivity(new Intent(MainActivity.this, FollowingActivity.class));
                break;
            }
            case R.id.action_results: {
                startActivity(new Intent(MainActivity.this, ResultsActivity.class));
                break;
            }
            case R.id.action_register_cultural: {
                startActivity(new Intent(MainActivity.this, WebViewActivity.class).putExtra("URL", Constants.URL_REG_CUL));
                break;
            }
            case R.id.action_register_sports: {
                startActivity(new Intent(MainActivity.this, WebViewActivity.class).putExtra("URL", Constants.URL_REG_SPO));
                break;
            }
            case R.id.action_contact: {
                startActivity(new Intent(MainActivity.this, ContactActivity.class));
                break;
            }
            case R.id.action_devs: {
                startActivity(new Intent(MainActivity.this, DeveloperActivity.class));
                break;
            }
        }
        return super.onOptionsItemSelected(item);
    }
}
