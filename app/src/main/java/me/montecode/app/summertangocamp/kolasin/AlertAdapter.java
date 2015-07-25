package me.montecode.app.summertangocamp.kolasin;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by stevyhacker on 25.7.15..
 */
public class AlertAdapter extends ArrayAdapter<String> {

    Context context;
    int layoutResID;
    private LayoutInflater layoutinflater;
    private ArrayList<String> scheduleList;

    public AlertAdapter(Context context, int resource, ArrayList<String> scheduleList) {
        super(context, resource, scheduleList);
        this.context = context;
        layoutinflater = LayoutInflater.from(context);
        this.scheduleList = scheduleList;
        this.layoutResID = resource;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder viewHolder;
        View view = convertView;

        if (view == null) {

            viewHolder = new ViewHolder();
            view = layoutinflater.inflate(layoutResID, parent, false);
            viewHolder.notificationItemTextView = (TextView) view.findViewById(R.id.notification_item_alert_text_view);
            view.setTag(viewHolder);

        } else {
            viewHolder = (ViewHolder) view.getTag();
        }

        String day = this.scheduleList.get(position);


        viewHolder.notificationItemTextView.setText(day);

        return view;
    }

    private static class ViewHolder {
        TextView notificationItemTextView;
    }

}
