package chipset.revels.adapters;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.parse.ParseObject;

import java.util.ArrayList;

import chipset.revels.R;

/**
 * Created by saketh on 7/3/16.
 */
public class NotificationsAdapter extends BaseAdapter {
    Activity activity;
    ArrayList<ParseObject> objectArrayList;

    public NotificationsAdapter(Activity activity, ArrayList<ParseObject> objectArrayList) {
        this.activity = activity;
        this.objectArrayList = objectArrayList;
    }

    @Override
    public int getCount() {
        return objectArrayList.size();
    }

    @Override
    public ParseObject getItem(int position) {
        return objectArrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(activity).inflate(R.layout.notification_item, parent, false);
        }
        TextView titleTV = (TextView) convertView.findViewById(R.id.notification_title_text_view);
        titleTV.setText(getItem(position).getString("title"));
        return convertView;
    }
}
