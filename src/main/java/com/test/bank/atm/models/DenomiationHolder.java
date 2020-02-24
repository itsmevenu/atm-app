package com.test.bank.atm.models;

public class DenomiationHolder {

    private int denom;
    private int availableCount;

    public DenomiationHolder(int denomination, int availableCount) {
        this.denom = denomination;
        this.availableCount = availableCount;
    }

    public int getDenominator() {
        return denom;
    }

    public int getAvailableCount() {
        return availableCount;
    }

    public void incrementDollarCount(int numberOfDollarstoAdd) {
        this.availableCount += numberOfDollarstoAdd;
    }

    public void print() {
        System.out.println("Denomination      : " + denom);
        System.out.println("Available Dollars : " + availableCount);
    }
    
}
