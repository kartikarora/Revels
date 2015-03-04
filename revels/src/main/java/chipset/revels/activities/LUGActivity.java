package chipset.revels.activities;

import android.content.Context;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.ImageView;

import com.squareup.seismic.ShakeDetector;

import chipset.potato.Potato;
import chipset.revels.R;
import chipset.revels.resources.Constants;

public class LUGActivity extends ActionBarActivity {
    Vibrator vibrator;
    ShakeDetector shakeDetector;
    SensorManager sensorManager;
    ShakeDetector.Listener listener;

    ImageView lugFbImageView, lugGitHubImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lug);

        lugFbImageView = (ImageView) findViewById(R.id.lug_fb_image_view);
        lugGitHubImageView = (ImageView) findViewById(R.id.lug_github_image_view);

        lugFbImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Potato.potate().getIntents().browserIntent(getApplicationContext(), Constants.URL_LUG_FB);
            }
        });

        lugGitHubImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Potato.potate().getIntents().browserIntent(getApplicationContext(), Constants.URL_LUG_GITHUB);
            }
        });
    }


    @Override
    protected void onResume() {
        super.onResume();
        vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        vibrator.vibrate(500);

        listener = new ShakeDetector.Listener() {
            @Override
            public void hearShake() {
                finish();

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
