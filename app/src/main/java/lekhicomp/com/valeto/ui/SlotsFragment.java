package lekhicomp.com.valeto.ui;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import fr.ganfra.materialspinner.MaterialSpinner;
import info.hoang8f.widget.FButton;
import lekhicomp.com.valeto.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class SlotsFragment extends Fragment implements View.OnClickListener {
    GridView gridView;
    FButton btnAdd, btnDelete, btnBook;
    MaterialSpinner spinner;
    ArrayList<Integer> keys;
    public int n = 10;
    public int slot_no = 1;
    ArrayAdapter adapter;
    SharedPreferences preferences;
    SharedPreferences.Editor editor;

    private DatabaseReference dbRef;
    //public ArrayList mButtons = new ArrayList();


    public SlotsFragment() {
        // Required empty public constructor
        /*for (int i = 0; 1 < n; i++) {
            Button b = new Button(getContext());
            b.setText(i + 1);
            b.setLayoutParams(new ViewGroup.LayoutParams(80, 80));
            b.setId(R.id.btn);
            gridView.addView(b);
        }*/


    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_slots, container, false);
        spinner = view.findViewById(R.id.spinner);
        dbRef = FirebaseDatabase.getInstance().getReference();

        keys = new ArrayList();

        dbRef.child("slots").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    int i = 0;
                    for (DataSnapshot d : dataSnapshot.getChildren()) {
                        keys.add(Integer.parseInt(d.getKey()));
                        Log.i("keys Array", "keys:" + keys);
                        i++;
                    }
                }
            }//onDataChange

            @Override
            public void onCancelled(DatabaseError error) {

            }//onCancelled
        });

        btnAdd = view.findViewById(R.id.gridButtonAdd);
        btnDelete = view.findViewById(R.id.gridButtonDelete);
        btnBook = view.findViewById(R.id.buttonBook);

        btnAdd.setOnClickListener(this);
        btnDelete.setOnClickListener(this);
        btnBook.setOnClickListener(this);

        //gridView.setAdapter(new ButtonAdapter(mButtons));

        preferences = getContext().getSharedPreferences("slots", Context.MODE_PRIVATE);
        editor = preferences.edit();

        n = preferences.getInt("keySlots", 10);
        refreshSlots(n);

        return view;

    }

    public void refreshSlots(int n) {

        adapter = new ArrayAdapter(getActivity(), android.R.layout.simple_spinner_dropdown_item);
        adapter.add("---Please Select a SLOT---");

        for (int i = 1; i <= n; i++) {
            int[] tmpSlot = new int[keys.size()];
            for (int j = 0; j < keys.size(); j++)
                tmpSlot[j] = keys.get(j);
            if (tmpSlot.equals(i)) {
                continue;
            } else
                adapter.add("SLOT  " + i);
        }

        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                slot_no = i;
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                slot_no = 0;
            }
        });

    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        switch (id) {
            case R.id.gridButtonAdd:
                createButton();
                break;
            case R.id.gridButtonDelete:
                deleteButton();
                break;
            case R.id.buttonBook:
                bookSlot();

        }
    }

    private void bookSlot() {
        if (slot_no == 0)
            Toast.makeText(getContext(), "Please Select a Slot!", Toast.LENGTH_LONG).show();
        else {
            Intent intent = new Intent(getContext(), BookSlotDetails.class);
            intent.putExtra("slotNo", slot_no);
            startActivity(intent);
        }

    }

    void createButton() {
        n = n + 1;
        // adapter.add("SLOT " + n);
        editor.clear();
        editor.commit();
        editor.putInt("keySlots", n);
        editor.commit();
        refreshSlots(n);
    }

    void deleteButton() {
        //gridView.removeViewAt(n);
        n = n - 1;
        editor.clear();
        editor.commit();
        editor.putInt("keySlots", n);
        editor.commit();
        refreshSlots(n);
    }

}
