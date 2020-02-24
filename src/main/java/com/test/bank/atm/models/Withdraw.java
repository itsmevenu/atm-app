package com.test.bank.atm.models;

public class Withdraw extends AbstractAtmModel {

    public Withdraw(DenomiationHolder amountHolder) {
        super(amountHolder);
    }

    public void execute() {
        getExistingDollarSource().decrementDollarCount(amount.getDollarCount());
    }
}
