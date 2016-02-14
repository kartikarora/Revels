package chipset.revels.activities;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import chipset.potato.Potato;
import chipset.revels.R;

public class ContactActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);
        getSupportActionBar().setElevation(0f);

        Toast toast = Toast.makeText(getApplicationContext(),"Tap to call", Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER_HORIZONTAL | Gravity.BOTTOM, 0, 0);
        toast.show();

        CardView satyamCardView = (CardView) findViewById(R.id.satyam_card);
        CardView sanikaCardView = (CardView) findViewById(R.id.sanika_card);
        CardView raviCardView = (CardView) findViewById(R.id.ravi_card);
        CardView aditiCardView = (CardView) findViewById(R.id.aditi_card);

        ImageView fbImageView = (ImageView) findViewById(R.id.fb_image_view);
        ImageView twImageView = (ImageView) findViewById(R.id.tw_image_view);
        ImageView instaImageView = (ImageView) findViewById(R.id.insta_image_view);
        ImageView ytImageView = (ImageView) findViewById(R.id.yt_image_view);
        ImageView gplusImageView = (ImageView) findViewById(R.id.gplus_image_view);
        ImageView webImageView = (ImageView) findViewById(R.id.web_image_view);

        satyamCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(ContactActivity.this);
                builder.setNegativeButton("NO", null);
                builder.setMessage("Call Satyam?");
                builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Potato.potate(getApplicationContext()).Intents().callIntent("+918867666977");
                    }
                });
                builder.create();
                builder.show();
            }
        });

        sanikaCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(ContactActivity.this);
                builder.setNegativeButton("NO", null);
                builder.setMessage("Call Sanika?");
                builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Potato.potate(getApplicationContext()).Intents().callIntent("+919869572246");
                    }
                });
                builder.create();
                builder.show();
            }
        });

        raviCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(ContactActivity.this);
                builder.setNegativeButton("NO", null);
                builder.setMessage("Call Ravi?");
                builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Potato.potate(getApplicationContext()).Intents().callIntent("+919740562736");
                    }
                });
                builder.create();
                builder.show();
            }
        });

        aditiCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(ContactActivity.this);
                builder.setNegativeButton("NO", null);
                builder.setMessage("Call Aditi?");
                builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Potato.potate(getApplicationContext()).Intents().callIntent("+919448954991");
                    }
                });
                builder.create();
                builder.show();
            }
        });

        fbImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Potato.potate(getApplicationContext()).Intents().browserIntent("https://www.facebook.com/mitrevels");
            }
        });

        twImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Potato.potate(getApplicationContext()).Intents().browserIntent("https://twitter.com/RevelsMIT");
            }
        });

        ytImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Potato.potate(getApplicationContext()).Intents().browserIntent("https://www.youtube.com/channel/UC9gwWd47a0q042qwEgutjWw");
            }
        });

       instaImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Potato.potate(getApplicationContext()).Intents().browserIntent("http://instagram.com/revelsmit/");
            }
        });

        gplusImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Potato.potate(getApplicationContext()).Intents().browserIntent("https://plus.google.com/107584695406538298114/");
            }
        });

        webImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Potato.potate(getApplicationContext()).Intents().browserIntent("http://mitrevels.in/");
            }
        });
    }
}
