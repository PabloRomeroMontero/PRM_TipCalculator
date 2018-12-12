package com.iessaladillo.pablo.prm_tipcalculator.ui.Main;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.FragmentTransaction;
import androidx.fragment.app.ListFragment;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;
import android.view.Menu;

import com.iessaladillo.pablo.prm_tipcalculator.R;
import com.iessaladillo.pablo.prm_tipcalculator.data.local.Database;
import com.iessaladillo.pablo.prm_tipcalculator.databinding.ActivityMainBinding;
import com.iessaladillo.pablo.prm_tipcalculator.ui.Fragment.InitFragment;
import com.iessaladillo.pablo.prm_tipcalculator.utils.FragmentUtils;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding b;
    private ViewModelMainActivity vm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        b = DataBindingUtil.setContentView(this, R.layout.activity_main);
        vm = ViewModelProviders.of(this, new ViewModelFactoryMainActivity(Database.getInstance())).get(ViewModelMainActivity.class);
        // We load initial fragment
        if (savedInstanceState == null) {
            FragmentUtils.replaceFragment(getSupportFragmentManager(), R.id.FLinitFragment, new InitFragment(), InitFragment.class.getSimpleName());
        }
        vm.getClickChangeFragmentEvent().observe(this, this::clickChangeFragment);
    }

    private void clickChangeFragment(Boolean bool) {
        if (bool != null) {
            FragmentUtils.replaceFragmentAddToBackstack(getSupportFragmentManager(), R.id.FLinitFragment, new ListFragment(), ListFragment.class.getSimpleName(), "backstackMain", FragmentTransaction.TRANSIT_FRAGMENT_FADE);
            vm.getClickChangeFragmentEvent().setValue(null);
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return super.onCreateOptionsMenu(menu);
    }
}
