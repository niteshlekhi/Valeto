package lekhicomp.com.valeto.ui;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import lekhicomp.com.valeto.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class BookedFragment extends Fragment {


    public BookedFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_booked, container, false);
        return rootView;
    }

}
