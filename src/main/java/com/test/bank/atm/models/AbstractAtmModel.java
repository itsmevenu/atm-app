package com.test.bank.atm.models;

public abstract  class AbstractAtmModel {

    DenomiationHolder amount;

    public AbstractAtmModel(DenomiationHolder amountHolder) {
        this.amount = amountHolder;
    }

    public abstract void execute();
}
