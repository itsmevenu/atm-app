package com.test.bank.atm.service;

import com.test.bank.atm.Main;
import com.test.bank.atm.models.DenomiationHolder;
import com.test.bank.atm.models.Withdraw;

import java.util.ArrayList;
import java.util.List;

public class Withdrawer {

    private List<DenomiationHolder> toWithDrawList = new ArrayList<DenomiationHolder>();
    private int amountRemainingToDispense;
    private int amountToWithDraw;

    public Withdrawer(int amountToWithDraw) {
        this.amountToWithDraw = amountToWithDraw;
        this.amountRemainingToDispense = amountToWithDraw;
    }

    private boolean isValid() {
        if(amountToWithDraw <= 0) {
            System.out.println("Incorrect or insufficient funds to dispense.");
            return false;
        }
        return true;
    }

    public void prepareDispenser() {

        for(DenomiationHolder d : Main.dollarSource) {
            if(getAmountRemainingToDispense() < 0) {
                break;
            }
            int allocatableUnits = getAmountRemainingToDispense() / d.getDenominator();
            allocatableUnits = Math.min(allocatableUnits, d.getDollarCount());

            if(allocatableUnits > 0) {
                DenomiationHolder toWithDraw = new DenomiationHolder(d.getDenominator(), allocatableUnits);
                toWithDrawList.add(toWithDraw);
                amountRemainingToDispense -= toWithDraw.getAvailableCash();
            }
        }
    }


    public int getAmountRemainingToDispense() {

        return amountRemainingToDispense;

    }

    public void withDrawAll() {

        if (getAmountRemainingToDispense() > 0) {
            throw new RuntimeException("Unexpected!!");
        }
        System.out.println("Dispensed!!");
        for (DenomiationHolder denomiationHolder : toWithDrawList) {
            new Withdraw(denomiationHolder).execute();
            denomiationHolder.print();
        }

        System.out.println("Withdrawal of " + amountToWithDraw + " is success!!" );

    }

}
