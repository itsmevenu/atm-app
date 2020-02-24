package com.test.bank.atm.models;

import com.test.bank.atm.Main;

import java.util.Optional;

public abstract  class AbstractAtmModel {

    DenomiationHolder amount;

    public AbstractAtmModel(DenomiationHolder amountHolder) {
        this.amount = amountHolder;
    }

    public DenomiationHolder getExistingDollarSource() {
        DenomiationHolder existingDenominations = Main.atmWallet.get(amount.getDenominator());
        if(existingDenominations == null) {
            System.out.println("Unexpected!! Given Denomination Not found!");
            throw new RuntimeException("Unexpcted");
        }
        return existingDenominations;
    }

    public abstract void execute();
}
