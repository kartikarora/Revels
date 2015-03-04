package chipset.revels.activities;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.gson.Gson;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import chipset.revels.R;
import chipset.revels.model.instagram.InstagramDatum;
import chipset.revels.resources.Constants;
import uk.co.senab.photoview.PhotoViewAttacher;

/**
 * Developer: chipset
 * Package : chipset.revels.activities
 * Project : Revels
 * Date : 19/1/15
 */
public class ImageActivity extends ActionBarActivity {
    boolean isVisible = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image);

        InstagramDatum instagramDatum = new Gson().fromJson(getIntent().getStringExtra(Constants.INSTA_DATA), InstagramDatum.class);

        ImageView imageView = (ImageView) findViewById(R.id.image_view);
        final LinearLayout imageDataLinearLayout = (LinearLayout) findViewById(R.id.image_data_linear_layout);
        TextView imageUserTextView = (TextView) findViewById(R.id.image_user_text_view);
        TextView imageLikeTextView = (TextView) findViewById(R.id.image_like_text_view);
        TextView imageCommentTextView = (TextView) findViewById(R.id.image_comment_text_view);
        final ProgressBar imageProgressBar = (ProgressBar) findViewById(R.id.image_progress_bar);

        Picasso.with(getApplicationContext()).load(instagramDatum.getImages().getStandardResolution().getUrl()).into(imageView, new Callback() {
            @Override
            public void onSuccess() {
                imageProgressBar.setVisibility(View.GONE);
            }

            @Override
            public void onError() {
                setContentView(R.layout.no_connection_layout);
                imageProgressBar.setVisibility(View.GONE);
            }
        });
        imageCommentTextView.setText("" + instagramDatum.getComments().getCount());
        imageLikeTextView.setText("" + instagramDatum.getLikes().getCount());
        imageUserTextView.setText("@" + instagramDatum.getUser().getUsername());

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Animation fadeOutAnimation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.animation_fade_out);
                Animation fadeInAnimation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.animation_fade_in);
                if (isVisible) {
                    imageDataLinearLayout.startAnimation(fadeOutAnimation);
                    imageDataLinearLayout.setVisibility(View.GONE);
                } else {

                    imageDataLinearLayout.startAnimation(fadeInAnimation);
                    imageDataLinearLayout.setVisibility(View.VISIBLE);
                }
                isVisible = !isVisible;
            }
        });

        PhotoViewAttacher photoViewAttacher = new PhotoViewAttacher(imageView);
        photoViewAttacher.update();
    }
}
