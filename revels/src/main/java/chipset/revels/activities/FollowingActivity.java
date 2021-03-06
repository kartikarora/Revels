package chipset.revels.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.google.gson.Gson;

import chipset.potato.Potato;
import chipset.revels.R;
import chipset.revels.adapters.CategoryListAdapter;
import chipset.revels.model.revels.Event;
import chipset.revels.resources.Constants;

/**
 * Developer: chipset
 * Package : chipset.revels.activities
 * Project : Revels
 * Date : 25/1/15
 */

public class FollowingActivity extends ActionBarActivity {
    int position;
    CategoryListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_following);
        getSupportActionBar().setElevation(0f);
    }

    @Override
    protected void onResume() {
        super.onResume();
        ListView followingListView = (ListView) findViewById(R.id.following_list_view);
        final Event event = new Gson().fromJson(Potato.potate().getPreferences().getSharedPreferenceString(getApplicationContext(), Constants.FOLLOWING), Event.class);
        if (event.getData().size() == 0) {
            setContentView(R.layout.no_followed_events);
        } else {
            adapter = new CategoryListAdapter(FollowingActivity.this, event, 0);
            adapter.notifyDataSetChanged();
            followingListView.setAdapter(adapter);
            followingListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    startActivity(new Intent(getApplicationContext(), DetailDialogActivity.class).putExtra(Constants.EVENT_DATA, new Gson().toJson(adapter.getItem(position))).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
                }
            });
        }
    }
}
