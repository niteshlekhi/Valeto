package lekhicomp.com.valeto.ui;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.jpardogo.android.googleprogressbar.library.FoldingCirclesDrawable;
import com.jpardogo.android.googleprogressbar.library.GoogleProgressBar;
import com.jpardogo.android.googleprogressbar.library.NexusRotationCrossDrawable;
import com.wang.avi.AVLoadingIndicatorView;

import lekhicomp.com.valeto.R;
import lekhicomp.com.valeto.adapter.BookedAdapter;
import lekhicomp.com.valeto.model.SlotDetails;

/**
 * A simple {@link Fragment} subclass.
 */
public class BookedFragment extends Fragment implements RecyclerView.OnItemTouchListener{
    private RecyclerView recyclerView;
    private DatabaseReference dbRef;
    private FirebaseDatabase db;
    private Query query;
    private BookedAdapter bookedAdapter;
    private AVLoadingIndicatorView progressBar;

    public BookedFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_booked, container, false);


        db = FirebaseDatabase.getInstance();
        //db.setPersistenceEnabled(true);
        dbRef = db.getReference().child("slots");



        dbRef.keepSynced(true);
        if(progressBar==null){
            progressBar=new AVLoadingIndicatorView(getContext());
            progressBar.setVisibility(View.VISIBLE);

        }
        else
            progressBar.show();

        query = dbRef.orderByKey();

        recyclerView = rootView.findViewById(R.id.rvBooked);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        FirebaseRecyclerOptions<SlotDetails> options =
                new FirebaseRecyclerOptions.Builder<SlotDetails>()
                        .setQuery(query, SlotDetails.class)
                        .build();

        bookedAdapter = new BookedAdapter(options);

        recyclerView.setAdapter(bookedAdapter);
        bookedAdapter.startListening();

        progressBar.hide();
        progressBar.setVisibility(View.GONE);

        return rootView;
    }

    @Override
    public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
        return false;
    }

    @Override
    public void onTouchEvent(RecyclerView rv, MotionEvent e) {
       /* Bundle bundle=new Bundle();
        bundle.putString("bundlePhone", phone);
        bundle.putString("bundleCar", car_no);
        bundle.putString("bundleAltPhone", alt_phone);
        bundle.putString("bundleName", name);
        bundle.putString("bundleEmail", email);
        Intent intent=new Intent(getActivity(),PaymentActivity.class);
        intent.putExtras()
        startActivity(intent);*/
    }

    @Override
    public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

    }
}
