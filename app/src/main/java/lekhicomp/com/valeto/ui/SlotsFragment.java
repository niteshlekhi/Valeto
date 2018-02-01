package lekhicomp.com.valeto.ui;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.Spinner;
import android.widget.Toast;

import info.hoang8f.widget.FButton;
import lekhicomp.com.valeto.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class SlotsFragment extends Fragment implements View.OnClickListener {
    GridView gridView;
    FButton btnAdd, btnDelete, btnBook;
    Spinner spinner;
    public int n = 10;
    public int slot_no = 1;
    ArrayAdapter adapter;
    SharedPreferences preferences;
    SharedPreferences.Editor editor;
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
        // gridView = view.findViewById(R.id.gridView1);
        spinner = view.findViewById(R.id.spinner);

        //FButton cb = null;

           /* for (int i =0; i<10; i++) {
                cb = new FButton(getContext());
                cb.setText(Integer.toString(i+1));
                cb.setButtonColor(R.color.fbutton_color_peter_river);
                //cb.setOnClickListener(this);
                cb.setId(i+1);
                mButtons.add(cb);
            }
*/

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
        for (int i = 1; i <= n; i++)
            adapter.add("SLOT  " + i);

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
