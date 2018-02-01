package lekhicomp.com.valeto.ui;

import android.content.Intent;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.Serializable;

import butterknife.BindView;
import butterknife.ButterKnife;
import info.hoang8f.widget.FButton;
import lekhicomp.com.valeto.R;
import lekhicomp.com.valeto.model.SlotDetails;

public class BookSlotDetails extends AppCompatActivity implements View.OnClickListener {
    @BindView(R.id.textSlot)
    TextView txtSlot;
    @BindView(R.id.layoutPhone)
    TextInputLayout tilPhone;
    @BindView(R.id.layoutCar)
    TextInputLayout tilCar;
    @BindView(R.id.layoutName)
    TextInputLayout tilName;
    @BindView(R.id.layoutEmail)
    TextInputLayout tilEmail;
    @BindView(R.id.layoutAltPhone)
    TextInputLayout tilAltPhone;

    @BindView(R.id.textPhone)
    EditText txtPhone;
    @BindView(R.id.textCar)
    EditText txtCar;
    @BindView(R.id.textName)
    EditText txtName;
    @BindView(R.id.textEmail)
    EditText txtEmail;
    @BindView(R.id.textAltPhone)
    EditText txtAltPhone;
    @BindView(R.id.bookSlot)
    FButton btnBookSlot;

    int slot_no;
    String car_no = "";
    String phone = "";
    String name = "";
    String alt_phone = "";
    String email = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_slot_details);
        ButterKnife.bind(this);

        Intent rcv = getIntent();
        slot_no = rcv.getIntExtra("slotNo", 1);
        txtSlot.append(String.valueOf(slot_no));
        btnBookSlot.setOnClickListener(this);
        txtPhone.addTextChangedListener(new MyTextWatcher(txtPhone));
        txtCar.addTextChangedListener(new MyTextWatcher(txtCar));
        txtEmail.addTextChangedListener(new MyTextWatcher(txtEmail));
        txtPhone.addTextChangedListener(new MyTextWatcher(txtPhone));
        txtAltPhone.addTextChangedListener(new MyTextWatcher(txtAltPhone));

    }


    private boolean validatePhone() {
        if (txtPhone.getText().toString().trim().isEmpty()) {
            tilPhone.setError(getString(R.string.err_msg_phone));
            requestFocus(txtPhone);
            return false;
        }
        if (txtPhone.getText().toString().trim().length() > 12 || txtPhone.getText().toString().trim().length() < 10) {
            tilPhone.setError(getString(R.string.err_msg_phone));
            requestFocus(txtPhone);
            return false;
        }
        if (txtPhone.getText().toString().matches("[0-9]+")) {
            tilPhone.setErrorEnabled(false);
        } else {
            tilPhone.setError(getString(R.string.err_msg_phone));
            requestFocus(txtPhone);
            return false;

        }

        return true;
    }

    private boolean validateEmail() {
        String email = txtEmail.getText().toString().trim();

        if (!isValidEmail(email)) {
            tilEmail.setError(getString(R.string.err_msg_email));
            requestFocus(txtEmail);
            return false;
        } else {
            tilEmail.setErrorEnabled(false);
        }

        return true;
    }

    private boolean validateCarNumber() {
        if (txtCar.getText().toString().trim().isEmpty()) {
            tilCar.setError(getString(R.string.err_msg_car));
            requestFocus(txtCar);
            return false;
        } else if(txtCar.getText().toString().trim().length()<4 ||txtCar.getText().toString().trim().length()>10){
            tilCar.setError(getString(R.string.err_msg_car));
            requestFocus(txtCar);
            return false;
        }
        else
            tilCar.setEnabled(false);

        return true;
    }

    private boolean validateAltPhone() {
        if (txtAltPhone.getText().toString().matches("[0-9]+") && txtAltPhone.getText().length() >= 10) {
            tilAltPhone.setErrorEnabled(false);
        } else {
            tilAltPhone.setError(getString(R.string.err_msg_phone));
            requestFocus(tilAltPhone);
            return false;
        }

        return true;
    }

    private static boolean isValidEmail(String email) {
        if (email.isEmpty())
            return true;
        else
            return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    private void requestFocus(View view) {
        if (view.requestFocus()) {
            getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
        }
    }

    private class MyTextWatcher implements TextWatcher {

        private View view;

        private MyTextWatcher(View view) {
            this.view = view;
        }

        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        }

        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        }

        public void afterTextChanged(Editable editable) {
            switch (view.getId()) {
                case R.id.textPhone:
                    validatePhone();
                    break;
                case R.id.textEmail:
                    validateEmail();
                    break;
                case R.id.textCar:
                    validateCarNumber();
                    break;
                case R.id.textAltPhone:
                    validateAltPhone();
                    break;
            }
        }
    }

    @Override
    public void onClick(View view) {
        car_no = txtCar.getText().toString().trim();
        phone = txtPhone.getText().toString().trim();
        name = txtName.getText().toString().trim();
        alt_phone = txtAltPhone.getText().toString().trim();
        email = txtEmail.getText().toString().trim();

        if (car_no.isEmpty() || phone.isEmpty())
            Toast.makeText(this, "Enter mandatory fields!!", Toast.LENGTH_LONG).show();
        else
            submitForm();
    }

    private void submitForm() {
        if (!validatePhone()) {
            return;
        }

        if (!validateEmail()) {
            return;
        }

        if (!validateCarNumber()) {
            return;
        }

        Toast.makeText(this, "Slot !" + slot_no + " Booked", Toast.LENGTH_LONG).show();
        Bundle bundle = new Bundle();
        bundle.putInt("bundleSlotNo", slot_no);
        bundle.putString("bundlePhone", phone);
        bundle.putString("bundleCar", car_no);
        bundle.putString("bundleAltPhone", alt_phone);
        bundle.putString("bundleName", name);
        bundle.putString("bundleEmail", email);

        Intent intent = new Intent(getApplicationContext(), BookSlot.class);
        intent.putExtras(bundle);
        startActivity(intent);
    }
}
