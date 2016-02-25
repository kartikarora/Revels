package chipset.revels.activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.gson.Gson;

import chipset.potato.Potato;
import chipset.revels.R;
import chipset.revels.model.revels.Category;
import chipset.revels.model.revels.Event;
import chipset.revels.model.revels.Image;
import chipset.revels.model.revels.Schedule;
import chipset.revels.network.APIClient;
import chipset.revels.resources.Constants;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Developer: chipset
 * Package : chipset.revels.activities
 * Project : Revels
 * Date : 7/1/15
 */

public class SplashActivity extends ActionBarActivity {

    ImageView das2, das3, das4, das5, das6, das1, revels, volatus;

    TextView gettingReadyTextView, continueTextView;
    ProgressBar gettingReadyProgressBar;
    Button gettingReadyButton;
    ImageView gettingReadyImageView;
    Animation fadeInAnimationSecond;
    Animation fadeOutAnimation;

    Animation bounceAnimation, moveRightToLeftAnimation, moveRightToLeftAnimationTwo, zoomInAnimation3, zoomInAnimation4, zoomInAnimation5, zoomInAnimation6, fadeInAnimation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        das2 = (ImageView) findViewById(R.id.das2_image);
        das3 = (ImageView) findViewById(R.id.das3_image);
        das4 = (ImageView) findViewById(R.id.das4_image);
        das5 = (ImageView) findViewById(R.id.das5_image);
        das6 = (ImageView) findViewById(R.id.das6_image);
        das1 = (ImageView) findViewById(R.id.das1_image);
        revels = (ImageView) findViewById(R.id.revels_image);
        volatus = (ImageView) findViewById(R.id.volatus_image);


        bounceAnimation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.animation_bounce);
        moveRightToLeftAnimation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.animation_move_right_to_left);
        moveRightToLeftAnimationTwo = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.animation_move_right_to_left);
        zoomInAnimation3 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.animation_zoom_in);
        zoomInAnimation4 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.animation_zoom_in);
        zoomInAnimation5 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.animation_zoom_in);
        zoomInAnimation6 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.animation_zoom_in);
        fadeInAnimation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.animation_fade_in);
        fadeInAnimationSecond = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.animation_fade_in);
        fadeOutAnimation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.animation_fade_out);

        das3.startAnimation(zoomInAnimation3);

        zoomInAnimation3.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        das4.setVisibility(View.VISIBLE);
                        das4.startAnimation(zoomInAnimation4);
                    }
                }, 200);
            }

            @Override
            public void onAnimationEnd(Animation animation) {
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        zoomInAnimation4.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        das5.setVisibility(View.VISIBLE);
                        das5.startAnimation(zoomInAnimation5);
                    }
                }, 150);
            }

            @Override
            public void onAnimationEnd(Animation animation) {

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        zoomInAnimation5.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        das6.setVisibility(View.VISIBLE);
                        das6.startAnimation(zoomInAnimation6);
                    }
                }, 150);
            }

            @Override
            public void onAnimationEnd(Animation animation) {

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        zoomInAnimation6.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                das2.setVisibility(View.VISIBLE);
                das2.startAnimation(bounceAnimation);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        bounceAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                das1.setVisibility(View.VISIBLE);
                das1.startAnimation(moveRightToLeftAnimation);
                volatus.setVisibility(View.VISIBLE);
                volatus.startAnimation(moveRightToLeftAnimationTwo);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }
        });

        moveRightToLeftAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                revels.setVisibility(View.VISIBLE);
                revels.startAnimation(fadeInAnimation);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        fadeInAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        if (Potato.potate().getPreferences().getSharedPreferenceBoolean(getApplicationContext(), Constants.FIRST_RUN)) {
                            startLoad();
                        } else {
                            startActivity(new Intent(SplashActivity.this, MainActivity.class));
                            finish();
                        }
                    }
                }, 1000);

            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }
        });
    }

    public void startLoad() {
        setContentView(R.layout.getting_ready_layout);
        gettingReadyTextView = (TextView) findViewById(R.id.getting_ready_text_view);
        gettingReadyProgressBar = (ProgressBar) findViewById(R.id.getting_ready_progress_bar);
        gettingReadyButton = (Button) findViewById(R.id.getting_ready_button);
        gettingReadyImageView = (ImageView) findViewById(R.id.getting_ready_image_view);
        continueTextView = (TextView) findViewById(R.id.continue_text_view);
        Log.d("category", "getting ready");
        APIClient.getRevels().getCategories(new Callback<Category>() {
            @Override
            public void success(Category category, Response response) {
                storeEndPoints(category);
            }

            @Override
            public void failure(RetrofitError error) {
                Log.d("category", "error");
                error.printStackTrace();
            }
        });
       /* APIClient.getRevels().getCategory(new Callback<Category>() {
            @Override
            public void success(Category category, Response response) {
                Image image = new Image().setData(category.getCount());
                Potato.potate().getPreferences().putSharedPreference(getApplicationContext(), Constants.CATEG, new Gson().toJson(category));
                Potato.potate().getPreferences().putSharedPreference(getApplicationContext(), Constants.IMAGE, new Gson().toJson(image));
                Potato.potate().getPreferences().putSharedPreference(getApplicationContext(), Constants.FOLLOWING, new Gson().toJson(new Event()));
                storeEndPoints(category);
            }

            @Override
            public void failure(RetrofitError error) {
                error.printStackTrace();
                onFail();
            }
        });*/
    }

    public void storeEndPoints(final Category category) {
        /*APIClient.getRevels().getEvents(new Callback<Event>() {
            @Override
            public void success(Event event, Response response) {
                for (int i = 0; i < category.getData().size(); i++) {
                    final int finalI = i;
                    Log.d("category name", category.getData().get(i).getCname());

                    Potato.potate().getPreferences().putSharedPreference(getApplicationContext(), Constants.CATEG + category.getData().get(finalI).getCid(), new Gson().toJson(event.getData().get()));
                }

            }

            @Override
            public void failure(RetrofitError error) {
                error.printStackTrace();
                onFail();
            }
        });*/
        Image image = new Image().setData(category.getData().size()+1);
        Potato.potate().getPreferences().putSharedPreference(getApplicationContext(), Constants.CATEG, new Gson().toJson(category));
        Potato.potate().getPreferences().putSharedPreference(getApplicationContext(), Constants.IMAGE, new Gson().toJson(image));
        startEvents();
    }

    public void startEvents() {
        APIClient.getRevels().getEvents(new Callback<Event>() {
            @Override
            public void success(Event event, Response response) {
                Potato.potate().getPreferences().putSharedPreference(getApplicationContext(), Constants.EVENT, new Gson().toJson(event));
                getSchedule();
            }

            @Override
            public void failure(RetrofitError error) {
                error.printStackTrace();
                onFail();
            }
        });
    }
    public void getSchedule(){
        APIClient.getRevels().getSchedule(new Callback<Schedule>() {
            @Override
            public void success(Schedule schedule, Response response) {
                Potato.potate().getPreferences().putSharedPreference(getApplicationContext(), Constants.SCHEDULE, new Gson().toJson(schedule));
                onSuccess();
            }

            @Override
            public void failure(RetrofitError error) {
            error.printStackTrace();
                onFail();
            }
        });
    }
    public void onFail() {
        setContentView(R.layout.no_connection_layout);
        Button retryButton = (Button) findViewById(R.id.retry_button);
        retryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startLoad();
            }
        });
    }

    public void onSuccess() {
        gettingReadyProgressBar.setVisibility(View.INVISIBLE);
        gettingReadyProgressBar.startAnimation(fadeOutAnimation);
        gettingReadyImageView.setVisibility(View.VISIBLE);
        gettingReadyImageView.startAnimation(fadeInAnimationSecond);
        continueTextView.setVisibility(View.VISIBLE);
        continueTextView.startAnimation(fadeInAnimationSecond);
        gettingReadyButton.setVisibility(View.VISIBLE);
        gettingReadyButton.startAnimation(fadeInAnimationSecond);
        gettingReadyTextView.setText(getResources().getString(R.string.app_setuped));
        gettingReadyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Potato.potate().getPreferences().putSharedPreference(getApplicationContext(), Constants.FIRST_RUN, false);
                startActivity(new Intent(SplashActivity.this, MainActivity.class));
                finish();
            }
        });
    }
}
