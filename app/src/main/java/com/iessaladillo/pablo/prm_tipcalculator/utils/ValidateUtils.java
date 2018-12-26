package com.iessaladillo.pablo.prm_tipcalculator.utils;

import android.text.Editable;
import android.text.TextUtils;
import android.view.MenuItem;

import com.iessaladillo.pablo.prm_tipcalculator.R;

public class ValidateUtils {
    public static int validateBill(String text) {
        int result=0;
        if(!TextUtils.isEmpty(text) && (Integer.parseInt(text)>=0)){
            result = Integer.parseInt(text);
        }
        return result;
    }
    public static int validateDinner(String text) {
        int result=1;
        if(!text.isEmpty() && !(Integer.parseInt(text)>=0)){
            result = Integer.parseInt(text);
        }
        return result;
    }
    public static int validateTipsPerCent(String text) {
        int result=10;
        if(!text.isEmpty() && !(Integer.parseInt(text)>=0)){
            result = Integer.parseInt(text);
        }
        return result;
    }


}
