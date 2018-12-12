package com.iessaladillo.pablo.prm_tipcalculator.ui.Main;

import com.iessaladillo.pablo.prm_tipcalculator.data.local.Database;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

public class ViewModelFactoryMainActivity implements ViewModelProvider.Factory {
    private Database database;

    public ViewModelFactoryMainActivity(Database database) {
        this.database = database;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new ViewModelMainActivity(database);
    }

}


