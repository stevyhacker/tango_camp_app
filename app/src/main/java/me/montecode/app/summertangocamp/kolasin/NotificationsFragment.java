package me.montecode.app.summertangocamp.kolasin;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.commonsware.cwac.merge.MergeAdapter;

import java.util.ArrayList;

import io.realm.Realm;
import io.realm.RealmQuery;
import io.realm.RealmResults;


/**
 * A simple {@link Fragment} subclass.
 */
public class NotificationsFragment extends Fragment {


    private ArrayList<Schedule> scheduleList = new ArrayList<Schedule>();
    private ListView scheduleListView;

    public NotificationsFragment() {
        // Required empty public constructor
    }

    String notificationType = " ";
    RealmResults<Schedule> scheduleRealmResults;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_notifications, container, false);
        scheduleListView = (ListView) view.findViewById(R.id.notifications_list_view);
        MergeAdapter mergeAdapter = new MergeAdapter();

        Realm realm = Realm.getInstance(getActivity().getApplicationContext());
        RealmQuery<Schedule> query = realm.where(Schedule.class);
        scheduleRealmResults = query.findAllSorted("push_id", false);

        ArrayList<String> dayActivityDescriptions = new ArrayList<String>();
        ArrayList<String> days = new ArrayList<String>();
        ArrayList<String> alerts = new ArrayList<String>();
        ArrayList<String> additionalData = new ArrayList<String>();

        TextView topTextView = new TextView(getActivity().getApplicationContext());
        topTextView.setPadding(5, 5, 5, 5);
        topTextView.setTextColor(getResources().getColor(R.color.primary_text));
        topTextView.setText(getString(R.string.notification_info));

        mergeAdapter.addView(topTextView);
        notificationType = "regular";
        Bundle bundle = this.getArguments();
        if (bundle != null) {
            if(bundle.getString("notification")!=null) {
                notificationType = bundle.getString("notification");
            }

        }
        if (!notificationType.equalsIgnoreCase("parse")) {
            for (Schedule schedule : scheduleRealmResults) {

                days.clear();
                dayActivityDescriptions.clear();
                alerts.clear();
                additionalData.clear();

                alerts.add(schedule.getAlert());
                AlertAdapter alertAdapter = new AlertAdapter(getActivity().getApplicationContext(), R.layout.notification_list_item_alert, new ArrayList<String>(alerts));
                mergeAdapter.addAdapter(alertAdapter);

                for (Day day : schedule.getData()) {
                    days.clear();

                    days.add(day.getDay());
                    DayAdapter adapter = new DayAdapter(getActivity().getApplicationContext(), R.layout.notification_list_item_day, new ArrayList<String>(days));
                    mergeAdapter.addAdapter(adapter);

                    for (Activities activities : day.getActivities()) {
                        dayActivityDescriptions.clear();

                        dayActivityDescriptions.add(activities.getDescription());
                        DayDescriptionsAdapter descriptionsAdapter = new DayDescriptionsAdapter(getActivity().getApplicationContext(), R.layout.notification_list_item_description, new ArrayList<String>(dayActivityDescriptions));
                        mergeAdapter.addAdapter(descriptionsAdapter);

                    }

                }
                additionalData.clear();

                additionalData.add(schedule.getAdditional_data());
                AdditionalDataAdapter additionalDataAdapter = new AdditionalDataAdapter(getActivity().getApplicationContext(), R.layout.notification_list_item_add_data, new ArrayList<String>(additionalData));
                mergeAdapter.addAdapter(additionalDataAdapter);

            }

            scheduleListView.setAdapter(mergeAdapter);

            return view;

        }

        else {

            Log.e("SINGLENOTIFICATIONTIP", notificationType);

            Schedule schedule = scheduleRealmResults.first();
            days.clear();
            dayActivityDescriptions.clear();
            alerts.clear();
            additionalData.clear();

            alerts.add(schedule.getAlert());
            AlertAdapter alertAdapter = new AlertAdapter(getActivity().getApplicationContext(), R.layout.notification_list_item_alert, new ArrayList<String>(alerts));
            mergeAdapter.addAdapter(alertAdapter);

            for (Day day : schedule.getData()) {
                days.clear();

                days.add(day.getDay());
                DayAdapter adapter = new DayAdapter(getActivity().getApplicationContext(), R.layout.notification_list_item_day, new ArrayList<String>(days));
                mergeAdapter.addAdapter(adapter);

                for (Activities activities : day.getActivities()) {
                    dayActivityDescriptions.clear();

                    dayActivityDescriptions.add(activities.getDescription());
                    DayDescriptionsAdapter descriptionsAdapter = new DayDescriptionsAdapter(getActivity().getApplicationContext(), R.layout.notification_list_item_description, new ArrayList<String>(dayActivityDescriptions));
                    mergeAdapter.addAdapter(descriptionsAdapter);

                }

            }
            additionalData.clear();

            additionalData.add(schedule.getAdditional_data());
            AdditionalDataAdapter additionalDataAdapter = new AdditionalDataAdapter(getActivity().getApplicationContext(), R.layout.notification_list_item_add_data, new ArrayList<String>(additionalData));
            mergeAdapter.addAdapter(additionalDataAdapter);
            return view;

        }

    }
}
