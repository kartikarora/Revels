package chipset.revels.activities;

/**
 * Developer: chipset
 * Package : chipset.revels.activities
 * Project : Revels
 * Date : 09/02/15
 */

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.widget.ImageView;
import android.widget.TextView;

import chipset.revels.R;
import chipset.revels.resources.Constants;

public class DevDetailActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dev_detail);

        TextView titleTextView = (TextView) findViewById(R.id.dev_title_text_view);
        TextView subtitleTextView = (TextView) findViewById(R.id.dev_subtitle_text_view);
        ImageView imageView = (ImageView) findViewById(R.id.dev_detail_image_view);

        titleTextView.setText(getIntent().getStringExtra(Constants.TITLE));
        subtitleTextView.setText(getIntent().getStringExtra(Constants.SUBTITLE));
        imageView.setImageDrawable(getResources().getDrawable(getIntent().getIntExtra(Constants.IMG, R.drawable.app_icon)));
    }
}
