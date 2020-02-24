package com.test.bank.atm.models;

import com.test.bank.atm.models.enums.DenominationUnit;
import org.json.JSONObject;

public class DenomiationHolder {

    private DenominationUnit denomination;
    private int dollarCount;

    public DenomiationHolder(DenominationUnit denomination, int availableCount) {
        this.denomination = denomination;
        this.dollarCount = availableCount;
    }

    public DenominationUnit getDenominatorUnit() {
        return denomination;
    }

    public int getDenominator() {
        return denomination.getVal();
    }

    public int getAvailableCash() {
        return getDenominator() * getDollarCount();
    }

    public int getDollarCount() {
        return dollarCount;
    }

    public void incrementDollarCount(int numberOfDollarstoAdd) {
        this.dollarCount += numberOfDollarstoAdd;
    }

    public void decrementDollarCount(int numberOfDollarsToDecrement) {
        if (dollarCount < 0 || dollarCount < numberOfDollarsToDecrement) {
            System.out.println("Cannot Dispense Amount!");
            return;
        }
        this.dollarCount -= numberOfDollarsToDecrement;
    }

    public void print() {
        System.out.println("Denomination : $" + denomination.getVal());
        System.out.println("Dollars      : " + dollarCount);
    }

    public JSONObject toJson() {
        JSONObject obj = new JSONObject();
        obj.put("denomination", denomination.getVal());
        obj.put("available_count", dollarCount);
        return obj;
    }

}
