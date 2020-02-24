package com.test.bank.atm.models;

import com.test.bank.atm.Main;

public class Deposit extends AbstractAtmModel {

    public Deposit(DenomiationHolder amountHolder) {

        super(amountHolder);

    }

    public void execute() {

        DenomiationHolder existingDenominations = Main.dollarSource.get(0);
        existingDenominations.incrementDollarCount(amount.getDollarCount());

    }

}
