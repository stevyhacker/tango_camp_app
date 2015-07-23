package me.montecode.app.summertangocamp.kolasin;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * A simple {@link Fragment} subclass.
 */
public class FoodFragment extends Fragment {


    public FoodFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_food, container, false);

//        TextView transportTextView = (TextView) view.findViewById(R.id.fragment_text_view);
//        transportTextView.setText(Html.fromHtml(getString(R.string.food_text)));


        return  view;
    }


}
