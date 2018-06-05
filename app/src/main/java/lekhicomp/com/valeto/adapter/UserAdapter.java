package lekhicomp.com.valeto.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

import lekhicomp.com.valeto.R;
import lekhicomp.com.valeto.holder.UserHolder;
import lekhicomp.com.valeto.model.UserDetails;

public class UserAdapter extends FirebaseRecyclerAdapter<UserDetails, UserHolder> implements RecyclerView.OnClickListener {

  /*  public void setRecyclerAdapterClickListener(AdapterView.OnItemClickListener recyclerAdapterClickListener) {
        this.recyclerAdapterClickListener = recyclerAdapterClickListener;
    }*/

    public UserAdapter(FirebaseRecyclerOptions<UserDetails> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull UserHolder holder, int position, @NonNull UserDetails model) {
        holder.txtName.setText("Name: " + model.getName());
        holder.txtPhone.setText("Phone: " + model.getPhone());
        holder.txtAltPhone.setText("Alternate Phone: " + model.getAlt_phone());
        holder.txtEmail.setText("Email: " + model.getEmail());
        holder.txtLoyaltyPoints.setText("Loyalty Points: " + String.valueOf(model.getLoyaltyPoints()));
    }

    @NonNull
    @Override
    public UserHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.users_list_item, parent, false);

        UserHolder userHolder = new UserHolder(view);
        //userHolder.setOnRecyclerItemClickListener(this);

        return userHolder;
    }

    @Override
    public void onClick(View v) {

    }
}

