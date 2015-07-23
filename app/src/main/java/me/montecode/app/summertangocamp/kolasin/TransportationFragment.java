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
public class TransportationFragment extends Fragment {


    public TransportationFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_transportation, container, false);

        TextView transportTextView = (TextView) view.findViewById(R.id.transport_text_view);
        transportTextView.setText(Html.fromHtml(getString(R.string.transport_guide_html)));


        return  view;
    }


}
