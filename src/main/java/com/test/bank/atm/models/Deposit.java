package com.test.bank.atm.models;

import com.test.bank.atm.Main;

public class Deposit {

    DenomiationHolder toDeposit;

    public Deposit(DenomiationHolder amountToDeposit) {
        this.toDeposit = amountToDeposit;
    }

    public void persist() {
        // get from existing source and Update the
        DenomiationHolder existingDenominations = Main.dollarSource.get(0);

        existingDenominations.incrementDollarCount(toDeposit.getAvailableCount());

    }
}
