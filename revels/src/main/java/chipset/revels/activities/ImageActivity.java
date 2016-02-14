package chipset.revels.activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.ShareActionProvider;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;

import chipset.potato.Potato;
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
public class ImageActivity extends AppCompatActivity {
    boolean isVisible = true;
    ImageView mImageView;
    static int count = 1;
    boolean f = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image);
        getSupportActionBar().setTitle("");

        InstagramDatum instagramDatum = new Gson().fromJson(getIntent().getStringExtra(Constants.INSTA_DATA), InstagramDatum.class);

        mImageView = (ImageView) findViewById(R.id.image_view);
        final LinearLayout imageDataLinearLayout = (LinearLayout) findViewById(R.id.image_data_linear_layout);
        TextView imageUserTextView = (TextView) findViewById(R.id.image_user_text_view);
        TextView imageLikeTextView = (TextView) findViewById(R.id.image_like_text_view);
        TextView imageCommentTextView = (TextView) findViewById(R.id.image_comment_text_view);
        final ProgressBar imageProgressBar = (ProgressBar) findViewById(R.id.image_progress_bar);

        Picasso.with(getApplicationContext()).load(instagramDatum.getImages().getStandardResolution().getUrl()).into(mImageView, new Callback() {
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

        mImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Animation fadeOutAnimation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.animation_fade_out);
                Animation fadeInAnimation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.animation_fade_in);
                if (isVisible) {
                    imageDataLinearLayout.startAnimation(fadeOutAnimation);
                    imageDataLinearLayout.setVisibility(View.GONE);
                    getSupportActionBar().hide();
                } else {

                    imageDataLinearLayout.startAnimation(fadeInAnimation);
                    imageDataLinearLayout.setVisibility(View.VISIBLE);
                    getSupportActionBar().show();
                }
                isVisible = !isVisible;
            }
        });

        PhotoViewAttacher photoViewAttacher = new PhotoViewAttacher(mImageView);
        photoViewAttacher.update();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_insta, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_save_image) {
            File file = getImageBitmap();
            ProgressDialog dialog = new ProgressDialog(ImageActivity.this);
            dialog.setMessage("Please Wait");
            dialog.setIndeterminate(true);
            dialog.show();
            MediaScannerConnection.scanFile(ImageActivity.this,
                    new String[]{file.getAbsolutePath()}, null,
                    new MediaScannerConnection.OnScanCompletedListener() {

                        public void onScanCompleted(String path, Uri uri) {
                            Log.i("TAG", "Finished scanning " + path);
                        }
                    });
            if (f) {
                Toast toast = Toast.makeText(getApplicationContext(), "Image Saved", Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);
                toast.show();
                Potato.potate(getApplicationContext()).Preferences().putSharedPreference("COUNT", ++count);
            } else {
                Toast toast = Toast.makeText(getApplicationContext(), "Couldn\'t Save Image ", Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);
                toast.show();
            }
            dialog.cancel();
        } else if (item.getItemId() == R.id.action_image_share) {
            File file = getImageBitmap();
            Uri uri = Uri.fromFile(file);
            Intent sendIntent = new Intent();
            sendIntent.setAction(Intent.ACTION_SEND);
            sendIntent.putExtra(Intent.EXTRA_STREAM,uri);
            sendIntent.setType("image/*");
            startActivity(Intent.createChooser(sendIntent, "Share Image"));
        }
        return super.onOptionsItemSelected(item);
    }

    private File getImageBitmap() {
        mImageView.setDrawingCacheEnabled(true);
        Bitmap bitmap = mImageView.getDrawingCache();
        File filepath = Environment.getExternalStorageDirectory();
        File dir = new File(filepath.getAbsolutePath()
                + "/Pictures/Revels15/");
        dir.mkdirs();
        count = Potato.potate(getApplicationContext()).Preferences().getSharedPreferenceInteger("COUNT");
        File file = new File(dir, "IMG_INSTA_" + String.valueOf(count) + ".png");
        OutputStream output;
        try {
            output = new FileOutputStream(file);
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, output);
            output.flush();
            output.close();
            f = true;
        } catch (Exception e) {
            e.printStackTrace();
            f = false;
        }
        return file;
    }
}
