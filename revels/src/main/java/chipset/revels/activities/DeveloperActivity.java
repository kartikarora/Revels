package chipset.revels.activities;

/**
 * Developer: chipset
 * Package : chipset.revels.activities
 * Project : Revels
 * Date : 03/02/15
 */

import android.content.Context;
import android.content.Intent;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.ImageView;

import com.squareup.seismic.ShakeDetector;

import chipset.revels.R;
import chipset.revels.resources.Constants;

public class DeveloperActivity extends ActionBarActivity {
    Vibrator vibrator;
    ShakeDetector shakeDetector;
    SensorManager sensorManager;
    ShakeDetector.Listener listener;
    ImageView kartikImageView, anuraagImageView, sakethImageView, yashImageView, avikantImageView, sorteImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_developer);
        getSupportActionBar().setElevation(0f);

        kartikImageView = (ImageView) findViewById(R.id.kartik_image);
        anuraagImageView = (ImageView) findViewById(R.id.anuraag_image);
        yashImageView = (ImageView) findViewById(R.id.yash_image);
        avikantImageView = (ImageView) findViewById(R.id.avikant_image);
        sakethImageView = (ImageView) findViewById(R.id.saketh_image);
        sorteImageView = (ImageView) findViewById(R.id.shubham_image);

        kartikImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DeveloperActivity.this, DevDetailActivity.class).putExtra(Constants.TITLE, getString(R.string.team_lead)).putExtra(Constants.SUBTITLE, getString(R.string.kartik)).putExtra(Constants.IMG, R.drawable.kartik));
            }
        });

        sakethImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DeveloperActivity.this, DevDetailActivity.class).putExtra(Constants.TITLE, getString(R.string.android)).putExtra(Constants.SUBTITLE, getString(R.string.saketh)).putExtra(Constants.IMG, R.drawable.saketh));
            }
        });

        yashImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DeveloperActivity.this, DevDetailActivity.class).putExtra(Constants.TITLE, getString(R.string.iOS)).putExtra(Constants.SUBTITLE, getString(R.string.yash)).putExtra(Constants.IMG, R.drawable.yash));
            }
        });

        avikantImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DeveloperActivity.this, DevDetailActivity.class).putExtra(Constants.TITLE, getString(R.string.iOS)).putExtra(Constants.SUBTITLE, getString(R.string.avikant)).putExtra(Constants.IMG, R.drawable.avikant));
            }
        });

        sorteImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DeveloperActivity.this, DevDetailActivity.class).putExtra(Constants.TITLE, getString(R.string.co_ordinator)).putExtra(Constants.SUBTITLE, getString(R.string.shubham)).putExtra(Constants.IMG, R.drawable.shubham));
            }
        });

        anuraagImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DeveloperActivity.this, DevDetailActivity.class).putExtra(Constants.TITLE, getString(R.string.android)).putExtra(Constants.SUBTITLE, getString(R.string.anuraag)).putExtra(Constants.IMG, R.drawable.anuraag));
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);

        listener = new ShakeDetector.Listener() {
            @Override
            public void hearShake() {
                startActivity(new Intent(DeveloperActivity.this, LUGActivity.class));
                vibrator.vibrate(1000);

            }
        };
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        shakeDetector = new ShakeDetector(listener);
        shakeDetector.start(sensorManager);
    }

    @Override
    protected void onPause() {
        super.onPause();
        shakeDetector.stop();
    }
}

