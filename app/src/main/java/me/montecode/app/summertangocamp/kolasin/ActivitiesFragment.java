package me.montecode.app.summertangocamp.kolasin;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class ActivitiesFragment extends Fragment {


    public ActivitiesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_activities, container, false);

        TextView activitiesTextView = (TextView) view.findViewById(R.id.activities_text_view);
        activitiesTextView.setText(Html.fromHtml(getString(R.string.activities_text)));


        return  view;
    }


}
