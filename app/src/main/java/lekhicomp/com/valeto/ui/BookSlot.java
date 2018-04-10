package lekhicomp.com.valeto.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.IOException;

import lekhicomp.com.valeto.R;
import lekhicomp.com.valeto.model.SlotDetails;
import lekhicomp.com.valeto.model.UserDetails;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;

public class BookSlot extends AppCompatActivity {
    DatabaseReference dbref;
    FirebaseDatabase database;
    private String phone;
    private String name;
    private String altPhone;
    private String email;
    private String mBody;
    private OkHttpClient mClient = new OkHttpClient();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_slot);
        database = FirebaseDatabase.getInstance();
        dbref = FirebaseDatabase.getInstance().getReference();
        Intent rcvIntent = getIntent();
        Bundle bundle = rcvIntent.getExtras();
        int slotNo = bundle.getInt("bundleSlotNo");
        String car = bundle.getString("bundleCar");
        phone = bundle.getString("bundlePhone");
        name = bundle.getString("bundleName");
        altPhone = bundle.getString("bundleAltPhone");
        email = bundle.getString("bundleEmail");
        SlotDetails slotDetails = new SlotDetails(slotNo, car, phone, name, altPhone, email);
        Log.i("slotDetails", slotDetails.toString());
        dbref.child("slots").child(String.valueOf(slotNo)).setValue(slotDetails);
        addUser();

        Toast.makeText(this, "Slot " + slotNo + " Booked!", Toast.LENGTH_LONG).show();
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    private void addUser() {
        dbref.addChildEventListener(new ChildEventListener() {

            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                if (dataSnapshot.child("users").child(phone).exists()) {
                } else {
                    UserDetails user = new UserDetails(name, phone, altPhone, email, 0);
                    dbref.child("users").child(phone).setValue(user);
                }
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }

    Call post(String url, Callback callback) throws IOException {
        RequestBody formBody = new FormBody.Builder()
                .add("To", phone)
                .add("Body", mBody)
                .build();
            Request request = new Request.Builder()
                .url(url)
                .post(formBody)
                .build();
        Call response = mClient.newCall(request);
        response.enqueue(callback);
        return response;
    }
}
