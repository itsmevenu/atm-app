package com.test.bank.atm.models;

public class Deposit extends AbstractAtmModel {

    public Deposit(DenomiationHolder amountHolder) {

        super(amountHolder);

    }

    public void execute() {
        getExistingDollarSource().incrementDollarCount(amount.getDollarCount());
    }

}
