package chipset.revels.activities;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.widget.TextView;

import com.google.gson.Gson;

import chipset.revels.R;
import chipset.revels.model.revels.EventDatum;
import chipset.revels.resources.Constants;

/**
 * Developer: chipset
 * Package : chipset.revels.activities
 * Project : Revels
 * Date : 19/1/15
 */
public class DetailDialogActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_dialog);

        TextView eventDetailDialogTextView = (TextView) findViewById(R.id.event_detail_dialog_text_view);
        TextView eventNameDialogTextView = (TextView) findViewById(R.id.event_name_dialog_text_view);

        final EventDatum eventDatum = new Gson().fromJson(getIntent().getStringExtra(Constants.EVENT_DATA), EventDatum.class);

        eventDetailDialogTextView.setText(eventDatum.getEdesc());
        eventNameDialogTextView.setText(eventDatum.getEname());

    }
}
