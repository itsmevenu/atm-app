package com.test.bank.atm.models;

import com.test.bank.atm.Main;

import java.util.Optional;

public abstract  class AbstractAtmModel {

    DenomiationHolder amount;

    public AbstractAtmModel(DenomiationHolder amountHolder) {
        this.amount = amountHolder;
    }

    public DenomiationHolder getExistingDollarSource() {
        Optional<DenomiationHolder> existingDenominations =
                Main.dollarSource.stream().filter(d -> d.getDenominator() == amount.getDenominator()).findFirst();
        if(!existingDenominations.isPresent()) {
            System.out.println("Unexpected!! Given Denomination Not found!");
            throw new RuntimeException("Unexpcted");
        }
        return existingDenominations.get();
    }

    public abstract void execute();
}
