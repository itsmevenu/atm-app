package com.test.bank.atm.models;

import com.test.bank.atm.Main;

public class Withdraw extends AbstractAtmModel {

    public Withdraw(DenomiationHolder amountHolder) {
        super(amountHolder);
    }

    public void execute() {
        getExistingDollarSource().decrementDollarCount(amount.getDollarCount());
    }
}
