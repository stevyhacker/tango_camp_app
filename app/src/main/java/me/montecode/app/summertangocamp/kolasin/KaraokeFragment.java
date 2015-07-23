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
public class KaraokeFragment extends Fragment {


    public KaraokeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_karaoke, container, false);

        TextView karaokeTextView = (TextView) view.findViewById(R.id.karaoke_text_view);
        karaokeTextView.setText(Html.fromHtml(getString(R.string.karaoke_list)));


        return  view;
    }


}
