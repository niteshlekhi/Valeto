package lekhicomp.com.valeto.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.Toast;

import info.hoang8f.widget.FButton;
import lekhicomp.com.valeto.R;

public class BookSlotDetails extends Fragment {

    //TextView txtSlot;

    TextInputLayout tilPhone;
    TextInputLayout tilCar;
    TextInputLayout tilName;
    TextInputLayout tilEmail;
    TextInputLayout tilAltPhone;

    EditText txtPhone;
    EditText txtCar;
    EditText txtName;
    EditText txtEmail;
    EditText txtAltPhone;

    FButton btnBookSlot;

    int slot_no;
    String car_no = "";
    String phone = "";
    String name = "";
    String alt_phone = "";
    String email = "";


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_book_slot_details, container, false);
        initViews(view);
        btnBookSlot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                car_no = txtCar.getText().toString().trim();
                phone = txtPhone.getText().toString().trim();
                name = txtName.getText().toString().trim();
                alt_phone = txtAltPhone.getText().toString().trim();
                email = txtEmail.getText().toString().trim();

                if (car_no.isEmpty() || phone.isEmpty())
                    Toast.makeText(getActivity(), "Enter mandatory fields!!", Toast.LENGTH_LONG).show();
                else
                    submitForm();
            }
        });
        txtPhone.addTextChangedListener(new MyTextWatcher(txtPhone));
        txtCar.addTextChangedListener(new MyTextWatcher(txtCar));
        txtEmail.addTextChangedListener(new MyTextWatcher(txtEmail));
        txtPhone.addTextChangedListener(new MyTextWatcher(txtPhone));
        txtAltPhone.addTextChangedListener(new MyTextWatcher(txtAltPhone));
        return view;
    }

    public void initViews(View view) {
        btnBookSlot = view.findViewById(R.id.bookSlot);

        //txtSlot = view.findViewById(R.id.textSlot);
        tilPhone = view.findViewById(R.id.layoutPhone);
        tilCar = view.findViewById(R.id.layoutCar);
        tilName = view.findViewById(R.id.layoutName);
        tilEmail = view.findViewById(R.id.layoutEmail);
        tilAltPhone = view.findViewById(R.id.layoutAltPhone);

        txtPhone = view.findViewById(R.id.textPhone);
        txtCar = view.findViewById(R.id.textCar);
        txtName = view.findViewById(R.id.textName);
        txtEmail = view.findViewById(R.id.textEmail);
        txtAltPhone = view.findViewById(R.id.textAltPhone);
    }

    private boolean validatePhone() {
        txtPhone.setBackgroundResource(R.drawable.edittext_style);
        if (txtPhone.getText().toString().trim().isEmpty()) {
            tilPhone.setError(getString(R.string.err_msg_phone));
            txtPhone.setBackgroundResource(R.drawable.edittext_error);
            requestFocus(txtPhone);
            return false;
        }
        if (txtPhone.getText().toString().trim().length() > 12 || txtPhone.getText().toString().trim().length() < 10) {
            tilPhone.setError(getString(R.string.err_msg_phone));
            txtPhone.setBackgroundResource(R.drawable.edittext_error);
            requestFocus(txtPhone);
            return false;
        }
        if (txtPhone.getText().toString().matches("[0-9]+")) {
            tilPhone.setErrorEnabled(false);
            txtPhone.setBackgroundResource(R.drawable.edittext_style);
        } else {
            tilPhone.setError(getString(R.string.err_msg_phone));
            txtPhone.setBackgroundResource(R.drawable.edittext_error);
            requestFocus(txtPhone);
            return false;

        }
        return true;
    }

    private boolean validateEmail() {
        txtEmail.setBackgroundResource(R.drawable.edittext_style);
        String email = txtEmail.getText().toString().trim();

        if (!isValidEmail(email)) {
            tilEmail.setError(getString(R.string.err_msg_email));
            txtEmail.setBackgroundResource(R.drawable.edittext_error);
            requestFocus(txtEmail);
            return false;
        } else {
            tilEmail.setErrorEnabled(false);
            txtEmail.setBackgroundResource(R.drawable.edittext_style);
        }

        return true;
    }

    private boolean validateCarNumber() {
        txtCar.setBackgroundResource(R.drawable.edittext_style);
        if (txtCar.getText().toString().trim().isEmpty()) {
            tilCar.setError(getString(R.string.err_msg_car));
            requestFocus(txtCar);
            txtCar.setBackgroundResource(R.drawable.edittext_error);
            return false;
        } else if (txtCar.getText().toString().trim().length() < 4){// || txtCar.getText().toString().trim().length() > 10) {
            tilCar.setError(getString(R.string.err_msg_car));
            txtCar.setBackgroundResource(R.drawable.edittext_error);
            requestFocus(txtCar);
            return false;
        } /*else if (txtCar.getText().toString().trim().matches("[A-Z][0-9]+")) {
            tilCar.setErrorEnabled(false);
            txtCar.setBackgroundResource(R.drawable.edittext_style);
        }*/ else {
            /*tilCar.setError(getString(R.string.err_msg_car));
            txtCar.setBackgroundResource(R.drawable.edittext_error);
            requestFocus(txtCar);
            return false;*/
            tilCar.setErrorEnabled(false);
            txtCar.setBackgroundResource(R.drawable.edittext_style);
        }
        return true;
    }

    private boolean validateAltPhone() {
        txtAltPhone.setBackgroundResource(R.drawable.edittext_style);

        if (txtPhone.getText().toString().length() == 0 && txtAltPhone.getText().toString().length() == 0) {
            tilAltPhone.setError(getString(R.string.err_msg_phone));
            txtAltPhone.setBackgroundResource(R.drawable.edittext_error);
            requestFocus(tilAltPhone);
            return false;
        } else {

            if (txtAltPhone.getText().toString().matches("[0-9]+") && txtAltPhone.getText().length() >= 10 && txtAltPhone.getText().length() <= 12) {
                tilAltPhone.setErrorEnabled(false);
                txtAltPhone.setBackgroundResource(R.drawable.edittext_style);
                return true;
            } else {
                tilAltPhone.setError(getString(R.string.err_msg_phone));
                txtAltPhone.setBackgroundResource(R.drawable.edittext_error);
                requestFocus(tilAltPhone);
                return false;
            }
        }
    }

    private static boolean isValidEmail(String email) {
        if (email.isEmpty())
            return true;
        else
            return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    private void requestFocus(View view) {
        if (view.requestFocus()) {
            getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
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
        Bundle bundle = new Bundle();
        bundle.putInt("bundleSlotNo", slot_no);
        bundle.putString("bundlePhone", phone);
        bundle.putString("bundleCar", car_no);
        bundle.putString("bundleAltPhone", alt_phone);
        bundle.putString("bundleName", name);
        bundle.putString("bundleEmail", email);

        Intent intent = new Intent(getActivity(), BookSlot.class);
        intent.putExtras(bundle);
        startActivity(intent);
    }
}