package lekhicomp.com.valeto.ui;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.telephony.SmsManager;
import android.util.Log;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import lekhicomp.com.valeto.model.Otp;
import lekhicomp.com.valeto.model.SlotDetails;
import lekhicomp.com.valeto.model.UserDetails;

public class BookSlot extends AppCompatActivity {
    DatabaseReference dbref;
    FirebaseDatabase database;
    private String phone;
    private String name;
    private String otpNo;
    private String altPhone;
    private String email;
    private int randomPIN;
    private ConnectivityManager conMgr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_book_slot);
        conMgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        database = FirebaseDatabase.getInstance();
       // database.setPersistenceEnabled(true);
        dbref = database.getReference();
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

        if (!checkConnection())
            Toast.makeText(getApplicationContext(), "Internet not Available!! Sending OTP...", Toast.LENGTH_LONG).show();
        dbref.child("slots").child(phone).setValue(slotDetails);
        addUser();
        sendSms();

        Toast.makeText(this, "Slot Booked!", Toast.LENGTH_LONG).show();
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    private void generateOtp() {
        //generate a 4 digit integer 1000 <10000
        randomPIN = (int) (Math.random() * 9000) + 1000;

        //Store integer in a string
        otpNo = String.valueOf(randomPIN);
    }

    private void sendSms() {
        otpNo = "";

        generateOtp();

        Otp otp = new Otp(otpNo, phone);
        //dbref.child("otp").child(otpNo).setValue(otpNo, phone);


        dbref.child("otp").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                while (dataSnapshot.hasChild(otpNo)) {
                    generateOtp();
                }

                dbref.child("otp").child(otpNo).setValue(otpNo, phone);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        Log.i("otp", otpNo);

        SmsManager smsManager = SmsManager.getDefault();
        smsManager.sendTextMessage(phone, null, "Your OTP for Valeto is " + otpNo, null, null);

    }

    private void addUser() {
        dbref.addChildEventListener(new ChildEventListener() {

            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                if (dataSnapshot.child("users").child(phone).exists()) {
                } else {
                    UserDetails user = new UserDetails(name, phone, altPhone, email, 0);
                    if (name.equals(""))
                        dbref.child("users").child(name).setValue(user);
                    else
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

    private boolean checkConnection() {
        boolean flag = false;
        if (conMgr.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED
                || conMgr.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED) {

            flag = true;

        } else if (conMgr.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.DISCONNECTED
                || conMgr.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.DISCONNECTED) {

            flag = false;
        }
        return flag;
    }


   /* Call post(String url, Callback callback) throws IOException {
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
    }*/
}
