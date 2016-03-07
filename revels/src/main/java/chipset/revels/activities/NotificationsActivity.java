package chipset.revels.activities;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.List;

import chipset.potato.Potato;
import chipset.revels.R;
import chipset.revels.adapters.NotificationsAdapter;

public class NotificationsActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notifications);
        final ArrayList<ParseObject> objects = new ArrayList<>();
        final ListView listView = (ListView) findViewById(R.id.notifications_list_view);
        ParseQuery<ParseObject> query = ParseQuery.getQuery("Notifications");
        if (Potato.potate().getUtils().isInternetConnected(this)) {
            final ProgressDialog progressDialog = new ProgressDialog(getApplicationContext());
            progressDialog.setMessage("Loading...");
            progressDialog.show();
            query.findInBackground(new FindCallback<ParseObject>() {
                public void done(List<ParseObject> notificationsList, ParseException e) {
                    progressDialog.dismiss();
                    if (e == null) {
                        Log.d("notifications", "Retrieved " + notificationsList.size() + " scores");
                        objects.addAll(notificationsList);
                        final NotificationsAdapter notificationsAdapter = new NotificationsAdapter(NotificationsActivity.this, objects);
                        listView.setAdapter(notificationsAdapter);
                        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                AlertDialog.Builder builder = new AlertDialog.Builder(NotificationsActivity.this);
                                builder.setTitle(notificationsAdapter.getItem(position).getString("title"));
                                builder.setMessage(notificationsAdapter.getItem(position).getString("detail"));
                                builder.create().show();
                            }
                        });
                    } else {
                        Log.d("notifications", "Error: " + e.getMessage());
                    }
                }
            });
        } else {
            Toast.makeText(NotificationsActivity.this, "Please connect to the internet", Toast.LENGTH_SHORT).show();
        }
    }

}
