package com.test.bank.atm.models;

import org.json.JSONObject;

public class DenomiationHolder {

    private int denomination;
    private int dollarCount;

    public DenomiationHolder(int denomination, int availableCount) {
        this.denomination = denomination;
        this.dollarCount = availableCount;
    }

    public int getDenominator() {
        return denomination;
    }

    public int getAvailableCount() {
        return dollarCount;
    }

    public void incrementDollarCount(int numberOfDollarstoAdd) {
        this.dollarCount += numberOfDollarstoAdd;
    }

    public void print() {
        System.out.println("Denomination      : " + denomination);
        System.out.println("Available Dollars : " + dollarCount);
    }

    public JSONObject toJson() {
        JSONObject obj = new JSONObject();
        obj.put("denomination", denomination);
        obj.put("available_count", dollarCount);
        return obj;
    }

}
