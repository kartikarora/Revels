package chipset.revels.adapters;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import chipset.potato.Potato;
import chipset.revels.R;
import chipset.revels.model.revels.Event;
import chipset.revels.model.revels.EventDatum;

/**
 * Developer: chipset
 * Package : chipset.revels.adapters
 * Project : Revels
 * Date : 30/12/14
 */

public class CategoryListAdapter extends BaseAdapter {
    Event event;
    Context context;
    LayoutInflater layoutInflater = null;
    int categoryPosition;

    public CategoryListAdapter(Context context, Event event, int categoryPosition) {
        this.event = event;
        this.context = context;
        this.categoryPosition = categoryPosition;
    }


    @Override
    public int getCount() {
        return event.getCount();
    }

    @Override
    public EventDatum getItem(int position) {
        return event.getData().get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, final ViewGroup parent) {

        if (convertView == null) {
            if (layoutInflater == null) {
                layoutInflater = LayoutInflater.from(context);
            }
            convertView = layoutInflater.inflate(R.layout.event_layout, parent, false);
            ViewHolder viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        }

        ViewHolder viewHolder = (ViewHolder) convertView.getTag();

        final EventDatum eventDatum = getItem(position);
        viewHolder.eventNameTextView.setText(eventDatum.getEvent());
        viewHolder.eventStartTextView.setText("Starts at: " + eventDatum.getStart());
        viewHolder.eventEndTextView.setText("Ends at: " + eventDatum.getStop());
        viewHolder.eventDateTextView.setText("On " + eventDatum.getDate());
        viewHolder.eventLocationTextView.setText("At/In: " + eventDatum.getLocation());
        viewHolder.eventContactTextView.setText(eventDatum.getContact());
        viewHolder.eventCategoryTextView.setText(eventDatum.getCategory());

        if (categoryPosition != 0) viewHolder.eventCategoryTextView.setVisibility(View.GONE);

        viewHolder.eventContactTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String contact = "+91";
                Pattern pattern = Pattern.compile("\\d{10}");
                Matcher matcher = pattern.matcher(eventDatum.getContact());
                if (matcher.find()) {
                    contact = contact + matcher.group(0);
                }
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                final String finalContact = contact;
                builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Potato.potate(context).Intents().callIntent(finalContact);
                    }
                });
                builder.setNegativeButton("NO", null);
                builder.setMessage("Call " + contact + " ?");
                builder.create();
                builder.show();
            }
        });

        return convertView;
    }

    static class ViewHolder {
        TextView eventNameTextView, eventStartTextView, eventEndTextView, eventLocationTextView, eventDateTextView, eventCategoryTextView, eventContactTextView;

        public ViewHolder(View view) {
            eventNameTextView = (TextView) view.findViewById(R.id.event_name_text_view);
            eventStartTextView = (TextView) view.findViewById(R.id.event_start_text_view);
            eventEndTextView = (TextView) view.findViewById(R.id.event_end_text_view);
            eventLocationTextView = (TextView) view.findViewById(R.id.event_location_text_view);
            eventDateTextView = (TextView) view.findViewById(R.id.event_date_text_view);
            eventContactTextView = (TextView) view.findViewById(R.id.event_contact_text_view);
            eventCategoryTextView = (TextView) view.findViewById(R.id.event_category_text_view);
        }
    }
}
