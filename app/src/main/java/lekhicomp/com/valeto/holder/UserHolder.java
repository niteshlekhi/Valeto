package lekhicomp.com.valeto.holder;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import lekhicomp.com.valeto.R;

public class UserHolder extends RecyclerView.ViewHolder{

    public TextView txtName;
    public TextView txtPhone;
    public TextView txtEmail;
    public TextView txtAltPhone;
    public TextView txtLoyaltyPoints;

    //OnRecyclerItemClickListener recyclerItemClickListener = null;

    public UserHolder(View itemView) {
        super(itemView);
        txtName = itemView.findViewById(R.id.listName);
         txtPhone = itemView.findViewById(R.id.listPhone);
         txtEmail = itemView.findViewById(R.id.listEmail);
         txtAltPhone = itemView.findViewById(R.id.listAltPhone);
         txtLoyaltyPoints = itemView.findViewById(R.id.listLoyalty);
        Log.i("name", txtName.getText().toString());
    }

   /* public void setName(String name) {

        txtName.setText(name);

    }

    public void setPhone(String phone) {

        txtPhone.setText(phone);
    }

    public void setEmail(String email) {

        txtEmail.setText(email);
    }

    public void setAlt_phone(String alt_phone) {

        txtAltPhone.setText(alt_phone);
    }

    public void setLoyaltyPoints(int loyaltyPoints) {

        txtLoyaltyPoints.setText(loyaltyPoints);
    }*/
}
