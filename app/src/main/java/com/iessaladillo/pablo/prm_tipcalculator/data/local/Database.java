package com.iessaladillo.pablo.prm_tipcalculator.data.local;

import com.iessaladillo.pablo.prm_tipcalculator.data.local.modal.Restaurant;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

public class Database {
    private static Database instance;
    private MutableLiveData<List<Restaurant>> restaurantLiveData = new MutableLiveData<>();
    private ArrayList<Restaurant> restaurants = new ArrayList<>();

    public void addRestaurant(Restaurant restaurant) {
        restaurants.add(restaurant);
        updateUserLiveData();
    }

    public void deleteUser(Restaurant restaurant) {
        restaurants.remove(restaurant);
        updateUserLiveData();
    }

    private void updateUserLiveData() {
        this.restaurantLiveData.setValue(new ArrayList<>(restaurants));
    }

    public static Database getInstance() {
        if (instance == null) {
            synchronized (Database.class) {
                if (instance == null) {
                    instance = new Database();
                }
            }
        }
        return instance;
    }
}
