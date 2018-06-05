package lekhicomp.com.valeto.holder;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import lekhicomp.com.valeto.R;

public class BookedHolder extends RecyclerView.ViewHolder{

    public TextView txtCar;
    public TextView txtName;
    public TextView txtPhone;
    public TextView txtEmail;
    public TextView txtAltPhone;

    public BookedHolder(View itemView) {
        super(itemView);
         txtPhone = itemView.findViewById(R.id.blistPhone);
         txtEmail = itemView.findViewById(R.id.blistEmail);
         txtAltPhone = itemView.findViewById(R.id.blistAltPhone);
         txtCar = itemView.findViewById(R.id.blistCar);
         txtName=itemView.findViewById(R.id.blistName);
        Log.i("name", txtPhone.getText().toString());
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
