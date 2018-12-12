package com.iessaladillo.pablo.prm_tipcalculator.ui.Main;

import com.iessaladillo.pablo.prm_tipcalculator.data.local.Database;
import com.iessaladillo.pablo.prm_tipcalculator.data.local.modal.Restaurant;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ViewModelMainActivity extends ViewModel {
    private MutableLiveData<Boolean> clickChangeFragmentEvent = new MutableLiveData<>();

    private Database database;
    private Restaurant restaurant;

    public MutableLiveData<Boolean> getClickChangeFragmentEvent() {
        return clickChangeFragmentEvent;
    }

    public ViewModelMainActivity(Database database) {
        this.database = database;

        if(restaurant==null){
            restaurant=new Restaurant("",0,0,0,0,0,0,0);
        }
    }

    public void addRestaurant(Restaurant restaurant) {
        database.addRestaurant(restaurant);
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }
}
