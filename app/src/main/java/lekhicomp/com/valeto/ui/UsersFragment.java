package lekhicomp.com.valeto.ui;


import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import lekhicomp.com.valeto.R;
import lekhicomp.com.valeto.adapter.UserAdapter;
import lekhicomp.com.valeto.model.UserDetails;

/**
 * A simple {@link Fragment} subclass.
 */
public class UsersFragment extends Fragment {

    private RecyclerView recyclerView;
    private DatabaseReference dbRef;
    private FirebaseDatabase db;
    private Query query;
    private UserAdapter userAdapter;
    private ConnectivityManager conMgr;

    public UsersFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_users, container, false);

        db = FirebaseDatabase.getInstance();
        //db.setPersistenceEnabled(true);
        dbRef = db.getReference().child("users");
        dbRef.keepSynced(true);

        query = dbRef.orderByKey();

        //if (!checkConnection())
        /*if (conMgr.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.DISCONNECTED
            || conMgr.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.DISCONNECTED)*/
       // Toast.makeText(getContext(), "No Network Available!! Showing Saved offline Data..", Toast.LENGTH_LONG).show();

        recyclerView = rootView.findViewById(R.id.rvUser);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        FirebaseRecyclerOptions<UserDetails> options =
                new FirebaseRecyclerOptions.Builder<UserDetails>()
                        .setQuery(query, UserDetails.class)
                        .build();

        userAdapter = new UserAdapter(options);

        recyclerView.setAdapter(userAdapter);
        userAdapter.startListening();

        return rootView;

    }

    private boolean checkConnection() {
        boolean flag = false;

        boolean isNetwork = conMgr.getNetworkInfo(ConnectivityManager.TYPE_MOBILE)
                .isConnectedOrConnecting();

        boolean isWifi = conMgr.getNetworkInfo(ConnectivityManager.TYPE_WIFI)
                .isConnectedOrConnecting();

        /*if (conMgr.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED
                || conMgr.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED) {

            flag = true;

        } else if (conMgr.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.DISCONNECTED
                || conMgr.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.DISCONNECTED) {

            flag = false;
        }*/
        return (isNetwork||isWifi);
    }
}