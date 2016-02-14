package chipset.revels.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import chipset.potato.Potato;
import chipset.revels.R;
import chipset.revels.model.revels.Event;
import chipset.revels.model.revels.EventDatum;
import chipset.revels.resources.Constants;

/**
 * Developer: chipset
 * Package : chipset.revels.activities
 * Project : Revels
 * Date : 19/1/15
 */
public class DetailDialogActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_dialog);

        TextView eventDetailDialogTextView = (TextView) findViewById(R.id.event_detail_dialog_text_view);
        TextView eventNameDialogTextView = (TextView) findViewById(R.id.event_name_dialog_text_view);
        CheckBox eventFollowCheckBox = (CheckBox) findViewById(R.id.event_follow_checkbox);

        final Event followingEvent = new Gson().fromJson(Potato.potate(getApplicationContext()).Preferences().getSharedPreferenceString(Constants.FOLLOWING), Event.class);
        final EventDatum eventDatum = new Gson().fromJson(getIntent().getStringExtra(Constants.EVENT_DATA), EventDatum.class);

        eventDetailDialogTextView.setText(eventDatum.getDescription());
        eventNameDialogTextView.setText(eventDatum.getEvent());

        for (int i = 0; i < followingEvent.getData().size(); i++) {
            if (followingEvent.getData().get(i).getEvent().equals(eventDatum.getEvent())) {
                eventFollowCheckBox.setChecked(true);
                break;
            } else eventFollowCheckBox.setChecked(false);
        }

        eventFollowCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    followingEvent.getData().add(eventDatum);
                    followingEvent.setCount(followingEvent.getCount() + 1);
                    Potato.potate(getApplicationContext()).Preferences().putSharedPreference(Constants.FOLLOWING, new Gson().toJson(followingEvent));
                    Toast t = Toast.makeText(getApplicationContext(), eventDatum.getEvent() + " added to following list", Toast.LENGTH_SHORT);
                    t.setGravity(Gravity.CENTER_HORIZONTAL | Gravity.BOTTOM, 0, 0);
                    t.show();
                } else {
                    for (int i = 0; i < followingEvent.getCount(); i++) {
                        if (eventDatum.getEvent().equals(followingEvent.getData().get(i).getEvent())) {
                            followingEvent.getData().remove(i);
                            followingEvent.setCount(followingEvent.getCount() - 1);
                            Potato.potate(getApplicationContext()).Preferences().putSharedPreference(Constants.FOLLOWING, new Gson().toJson(followingEvent));
                            break;
                        }
                    }
                    Toast t = Toast.makeText(getApplicationContext(), eventDatum.getEvent() + " removed from following list", Toast.LENGTH_SHORT);
                    t.setGravity(Gravity.CENTER_HORIZONTAL | Gravity.BOTTOM, 0, 0);
                    t.show();
                }
            }
        });
    }
}
