package lekhicomp.com.valeto.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import lekhicomp.com.valeto.R;
import lekhicomp.com.valeto.model.SlotDetails;

public class BookSlot extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_slot);
        Intent rcvIntent=getIntent();
        Bundle bundle=rcvIntent.getExtras();
        int slotNo=bundle.getInt("bundleSlotNo");
        String car=bundle.getString("bundleCar");
        String phone=bundle.getString("bundlePhone");
        String name=bundle.getString("bundleName");
        String altPhone=bundle.getString("bundleAltPhone");
        String email=bundle.getString("bundleEmail");
        SlotDetails slotDetails=new SlotDetails(slotNo,car,phone,name,altPhone,email);
        Log.i("slotDetails",slotDetails.toString());

        Intent intent=new Intent(this,BookSlotDetails.class);
        startActivity(intent);
        finish();
    }
}
