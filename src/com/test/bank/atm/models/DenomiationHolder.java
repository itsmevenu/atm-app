package com.test.bank.atm.models;

public class DenomiationHolder {

    private int denom;
    private int availableCount;

    public DenomiationHolder(int denomination, int availableCount) {
        this.denom = denomination;
        this.availableCount = availableCount;
    }


    public int getDenom() {
        return denom;
    }

    public int getAvailableCount() {
        return availableCount;
    }

}
