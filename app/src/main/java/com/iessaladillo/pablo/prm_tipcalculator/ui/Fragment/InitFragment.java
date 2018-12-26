package com.iessaladillo.pablo.prm_tipcalculator.ui.Fragment;

import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.iessaladillo.pablo.prm_tipcalculator.R;
import com.iessaladillo.pablo.prm_tipcalculator.data.local.Database;
import com.iessaladillo.pablo.prm_tipcalculator.data.local.modal.Restaurant;
import com.iessaladillo.pablo.prm_tipcalculator.databinding.FragmentInitBinding;
import com.iessaladillo.pablo.prm_tipcalculator.ui.Main.ViewModelFactoryMainActivity;
import com.iessaladillo.pablo.prm_tipcalculator.ui.Main.ViewModelMainActivity;
import com.iessaladillo.pablo.prm_tipcalculator.utils.ValidateUtils;


public class InitFragment extends Fragment {

    private FragmentInitBinding b;
    private ViewModelMainActivity vm;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle saveInstanceState) {
        b = DataBindingUtil.inflate(inflater, R.layout.fragment_init, container, false);
        vm = ViewModelProviders.of(getActivity(), new ViewModelFactoryMainActivity(Database.getInstance())).get(ViewModelMainActivity.class);
        setupViews();
        setHasOptionsMenu(true);
        return b.getRoot();
    }

    private void setupViews() {
        resetAll();

        //He probado ochentamil cosas menos la correcta,hasta cambiar a int todos los valores,, no he conseguido que el TextWatcher funcione.
        b.includeCardBill.textBill.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                b.includeCardBill.textBill.setText(
                        String.valueOf(ValidateUtils.validateBill(b.includeCardBill.textBill.getText().toString())));
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        b.includeCardBill.textTipPerCents.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        b.includeCardRounded.textDinner.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        b.includeCardBill.buttonReset.setOnClickListener(v -> resetOne(true));
        b.includeCardRounded.buttonReset.setOnClickListener(v -> resetTwo(true));
    }

    private void resetAll() {
        resetOne(false);
        resetTwo(false);
        //calculateAll();
    }

    private void resetTwo(boolean forceCalculate) {
        this.b.includeCardRounded.textDinner.setText(String.valueOf(1));
        if (forceCalculate)
            calculateAll();
    }

    private void resetOne(boolean forceCalculate) {
        this.b.includeCardBill.textBill.setText(String.valueOf(0));
        this.b.includeCardBill.textTipPerCents.setText(String.valueOf(10));
        if (forceCalculate)
            calculateAll();
    }

    private void calculateAll() {
        int bill;
        int tipPerCent, dinners;

        bill = ValidateUtils.validateBill(b.includeCardBill.textBill.getText().toString());
        tipPerCent = ValidateUtils.validateTipsPerCent(b.includeCardBill.textTipPerCents.getText().toString());
        dinners = ValidateUtils.validateDinner(b.includeCardRounded.textDinner.getText().toString());
        b.includeCardBill.textBill.setText(String.valueOf(bill));
        b.includeCardBill.textTipPerCents.setText(String.valueOf(tipPerCent));
        b.includeCardRounded.textDinner.setText(String.valueOf(dinners));
        b.includeCardBill.textTip.setText(String.valueOf(calculateTip(bill, tipPerCent)));
        b.includeCardBill.textTotal.setText(String.valueOf(calculateTotal(bill, tipPerCent)));
        b.includeCardRounded.textPerDinner.setText(String.valueOf(calculatePerDinner(bill, tipPerCent, dinners)));
        b.includeCardRounded.textRounded.setText(String.valueOf(calculateRounded(bill, tipPerCent, dinners)));
        recalculateRestaurant();

    }

    private void recalculateRestaurant() {
        vm.getRestaurant().setDinners(Integer.parseInt(b.includeCardRounded.textDinner.getText().toString()));
        vm.getRestaurant().setRounded(Integer.parseInt(b.includeCardRounded.textRounded.getText().toString()));
        vm.getRestaurant().setPerDinner(Integer.parseInt(b.includeCardRounded.textPerDinner.getText().toString()));
        vm.getRestaurant().setTipPerCent(Integer.parseInt(b.includeCardBill.textTipPerCents.getText().toString()));
        vm.getRestaurant().setTip(Integer.parseInt(b.includeCardBill.textTip.getText().toString()));
        vm.getRestaurant().setTotal(Integer.parseInt(b.includeCardBill.textTotal.getText().toString()));
        vm.getRestaurant().setBill(Integer.parseInt(b.includeCardBill.textBill.getText().toString()));
    }

    private int calculateRounded(double bill, int tipPerCent, int dinners1) {
        return calculatePerDinner(bill, tipPerCent, dinners1) % 100 == 0 ? calculatePerDinner(bill, tipPerCent, dinners1) : calculatePerDinner(bill, tipPerCent, dinners1) + 1;

    }

    private int calculatePerDinner(double bill, int tipPerCent, int dinners1) {
        return calculateTotal(bill, tipPerCent) / dinners1;
    }

    private int calculateTotal(double bill, int tipPerCent) {
        return (int) (calculateTip(bill, tipPerCent) + bill);

    }

    private int calculateTip(double bill, int tipPerCent) {
        return (int) ((bill / 100) * tipPerCent);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.mnuSave) {
            save(vm.getRestaurant());
            return true;
        } else if (item.getItemId() == R.id.mnuShowList) {
            vm.getClickChangeFragmentEvent().setValue(true);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void save(Restaurant restaurant) {
        vm.addRestaurant(restaurant);
        resetAll();
    }
}
