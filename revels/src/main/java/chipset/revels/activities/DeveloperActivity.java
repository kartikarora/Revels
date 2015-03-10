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
    ImageView tanayImageView, manishImageView, sakshamImageView, kartikImageView, shubhamImageView, samarthImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_developer);
        getSupportActionBar().setElevation(0f);

        tanayImageView = (ImageView) findViewById(R.id.tan_image);
        manishImageView = (ImageView) findViewById(R.id.man_image);
        kartikImageView = (ImageView) findViewById(R.id.kar_image);
        shubhamImageView = (ImageView) findViewById(R.id.shub_image);
        sakshamImageView = (ImageView) findViewById(R.id.sak_image);
        samarthImageView = (ImageView) findViewById(R.id.sam_image);

        tanayImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DeveloperActivity.this, DevDetailActivity.class).putExtra(Constants.TITLE, getString(R.string.team_lead)).putExtra(Constants.SUBTITLE, getString(R.string.tanay)).putExtra(Constants.IMG, R.drawable.tanay));
            }
        });

        sakshamImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DeveloperActivity.this, DevDetailActivity.class).putExtra(Constants.TITLE, getString(R.string.winphone)).putExtra(Constants.SUBTITLE, getString(R.string.saksham)).putExtra(Constants.IMG, R.drawable.saksham));
            }
        });

        kartikImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DeveloperActivity.this, DevDetailActivity.class).putExtra(Constants.TITLE, getString(R.string.android)).putExtra(Constants.SUBTITLE, getString(R.string.kartik)).putExtra(Constants.IMG, R.drawable.kartik));
            }
        });

        shubhamImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DeveloperActivity.this, DevDetailActivity.class).putExtra(Constants.TITLE, getString(R.string.iOS)).putExtra(Constants.SUBTITLE, getString(R.string.shubham)).putExtra(Constants.IMG, R.drawable.shubham));
            }
        });

        samarthImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DeveloperActivity.this, DevDetailActivity.class).putExtra(Constants.TITLE, getString(R.string.graphics)).putExtra(Constants.SUBTITLE, getString(R.string.samarth)).putExtra(Constants.IMG, R.drawable.samarth));
            }
        });

        manishImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DeveloperActivity.this, DevDetailActivity.class).putExtra(Constants.TITLE, getString(R.string.backend)).putExtra(Constants.SUBTITLE, getString(R.string.manish)).putExtra(Constants.IMG, R.drawable.manish));
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
