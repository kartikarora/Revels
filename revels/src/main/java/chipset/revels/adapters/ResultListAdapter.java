package chipset.revels.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import chipset.revels.R;
import chipset.revels.model.revels.Result;
import chipset.revels.model.revels.ResultDatum;

/**
 * Developer: chipset
 * Package : chipset.revels.adapters
 * Project : Revels
 * Date : 20/1/14
 */


public class ResultListAdapter extends BaseAdapter {
    Result result;
    Context context;
    LayoutInflater layoutInflater = null;

    public ResultListAdapter(Context context, Result result) {
        this.result = result;
        this.context = context;
    }


    @Override
    public int getCount() {
        return result.getCount();
    }

    @Override
    public ResultDatum getItem(int position) {
        return result.getData().get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            if (layoutInflater == null) {
                layoutInflater = LayoutInflater.from(context);
            }
            convertView = layoutInflater.inflate(R.layout.result_layout, parent, false);
            ViewHolder viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        }
        ViewHolder viewHolder = (ViewHolder) convertView.getTag();
        ResultDatum resultDatum = getItem(position);
        String res = "";
        for (int i = 0; i < resultDatum.getCount(); i++) {
            String pos = resultDatum.getContent().get(i).getPosition();
            if (pos.equals("q"))
                pos = "Qualified";
            res += pos;
            res += " : ";
            res += resultDatum.getContent().get(i).getDelegate();
            res += "\n";
        }
        viewHolder.resultEventTextView.setText(resultDatum.getEvent());
        viewHolder.resultPositionTextView.setText(res);
        return convertView;
    }

    static class ViewHolder {
        TextView resultEventTextView, resultPositionTextView;

        public ViewHolder(View view) {
            resultEventTextView = (TextView) view.findViewById(R.id.result_event_name_text_view);
            resultPositionTextView = (TextView) view.findViewById(R.id.result_position_text_view);
        }
    }
}
