package lekhicomp.com.valeto.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupMenu;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

import lekhicomp.com.valeto.R;
import lekhicomp.com.valeto.holder.BookedHolder;
import lekhicomp.com.valeto.model.SlotDetails;
import lekhicomp.com.valeto.ui.BookSlot;
import lekhicomp.com.valeto.ui.PaymentActivity;

public class BookedAdapter extends FirebaseRecyclerAdapter<SlotDetails, BookedHolder> {

    private PopupMenu menu;
    private MenuInflater inflater;

  /*  public void setRecyclerAdapterClickListener(AdapterView.OnItemClickListener recyclerAdapterClickListener) {
        this.recyclerAdapterClickListener = recyclerAdapterClickListener;
    }*/

    public BookedAdapter(FirebaseRecyclerOptions<SlotDetails> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull BookedHolder holder, int position, @NonNull SlotDetails model) {
        holder.txtCar.setText("Car Number: " + model.getCar_no());
        holder.txtPhone.setText("Phone: " + model.getPhone());
        holder.txtName.setText("Name: " + model.getName());
        holder.txtAltPhone.setText("Alternate Phone: " + model.getAlt_phone());
        holder.txtEmail.setText("Email: " + model.getEmail());
    }

    @NonNull
    @Override
    public BookedHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.booked_list_item, parent, false);

        final BookedHolder bookedHolder = new BookedHolder(view);

        bookedHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(v.getContext(), "Working!!", Toast.LENGTH_LONG).show();

                String phone,car_no,alt_phone,email,name;
                phone=bookedHolder.txtPhone.getText().toString();
                car_no=bookedHolder.txtCar.getText().toString();
                alt_phone=bookedHolder.txtAltPhone.getText().toString();
                email=bookedHolder.txtEmail.getText().toString();
                name=bookedHolder.txtName.getText().toString();
                Bundle bundle = new Bundle();
                bundle.putString("bundlePhone", phone);
                bundle.putString("bundleCar", car_no);
                bundle.putString("bundleAltPhone", alt_phone);
                bundle.putString("bundleName",name);
                bundle.putString("bundleEmail", email);

                Intent intent=new Intent(v.getContext(), PaymentActivity.class);
                intent.putExtras(bundle);
                v.getContext().startActivity(intent);

            }
        });

        bookedHolder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Vibrator vibrator= (Vibrator) v.getContext().getSystemService(Context.VIBRATOR_SERVICE);
                vibrator.vibrate(40);

                menu=new PopupMenu(v.getContext(),v);
                inflater= menu.getMenuInflater();
                inflater.inflate(R.menu.actions,menu.getMenu());
                menu.show();

                menu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.slotOpen:
                                //handle menu1 click
                                return true;
                            case R.id.slotDelete:
                                //handle menu2 click
                                return true;
                            default:
                                return false;
                        }
                    }
                });

                return true;
            }
        });



        return bookedHolder;
    }

}

