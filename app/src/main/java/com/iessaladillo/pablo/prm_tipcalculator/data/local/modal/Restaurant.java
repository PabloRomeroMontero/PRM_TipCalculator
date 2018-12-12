package com.iessaladillo.pablo.prm_tipcalculator.data.local.modal;

public class Restaurant {
    private static int contador=1;
    private String name;
    private int bill;
    private int tip;
    private int total;
    private int dinners;
    private int perDinner;
    private int rounded;
    private int tipPerCent;
    private int id;

    public Restaurant(String name, int bill, int tip, int total, int dinners, int perDinner, int rounded,int tipPerCent) {
        id=contador++;
        this.bill=bill;
        this.name=name;
        this.tip=tip;
        this.dinners=dinners;
        this.perDinner=perDinner;
        this.total=total;
        this.rounded=rounded;
        this.tipPerCent=tipPerCent;

    }

    public int getId() {
        return id;
    }

    public int getTipPerCent() {
        return tipPerCent;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getBill() {
        return bill;
    }

    public void setBill(int bill) {
        this.bill = bill;
    }

    public int getTip() {
        return tip;
    }

    public void setTip(int tip) {
        this.tip = tip;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getDinners() {
        return dinners;
    }

    public void setDinners(int dinners) {
        this.dinners = dinners;
    }

    public int getPerDinner() {
        return perDinner;
    }

    public void setPerDinner(int perDinner) {
        this.perDinner = perDinner;
    }

    public int getRounded() {
        return rounded;
    }

    public void setRounded(int rounded) {
        this.rounded = rounded;
    }

    public void setTipPerCent(int tipPertCent) {
        this.tipPerCent=tipPertCent;
    }
}
