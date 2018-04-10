package lekhicomp.com.valeto.ui;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import lekhicomp.com.valeto.R;
import lekhicomp.com.valeto.adapter.SlotAdapter;

/**
 * A simple {@link Fragment} subclass.
 */
public class BookedFragment extends Fragment {
    Query query;
    DatabaseReference dbRef;
    SlotAdapter slotAdapter;

    public BookedFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_booked, container, false);
        dbRef = FirebaseDatabase.getInstance().getReference();
        query = dbRef.orderByKey();
        return rootView;
    }

}
