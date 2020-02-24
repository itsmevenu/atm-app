package com.test.bank.atm.service;

import com.test.bank.atm.Main;
import com.test.bank.atm.models.DenomiationHolder;
import com.test.bank.atm.models.Withdraw;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Withdrawer {

    private List<DenomiationHolder> toWithDrawList = new ArrayList<DenomiationHolder>();
    private int amountRemainingToDispense;
    private int amountToWithDraw;

    public Withdrawer(int amountToWithDraw) {

        this.amountToWithDraw = amountToWithDraw;
        this.amountRemainingToDispense = amountToWithDraw;

    }

    // validates the amount.
    public boolean isValid() {
        if (amountToWithDraw <= 0) {
            System.out.println("Incorrect or insufficient funds to dispense.");
            return false;
        }
        return true;
    }

    /*
    Prepares for the dispense.
    Splits the given amount and checks for the all available denominations and adds it to the result list.
     */
    public void prepareDispenser() {

        for (Map.Entry<Integer, DenomiationHolder> entry : Main.atmWallet.entrySet()) {
            DenomiationHolder d = entry.getValue();
            if (getAmountRemainingToDispense() < 0) {
                break;
            }
            int allocatableUnits = getAmountRemainingToDispense() / d.getDenominator();
            allocatableUnits = Math.min(allocatableUnits, d.getDollarCount());

            if (allocatableUnits > 0) {
                DenomiationHolder toWithDraw = new DenomiationHolder(d.getDenominatorUnit(), allocatableUnits);
                toWithDrawList.add(toWithDraw);
                amountRemainingToDispense -= toWithDraw.getAvailableCash();
            }
        }
    }


    /*
    @returns the remaining amount to be dispensed.
    It returns the final remaining or the left out amount from the given amount after checking for the dispension
     */
    public int getAmountRemainingToDispense() {

        return amountRemainingToDispense;

    }

    /*
    Once the validation done,
    it persists the values from the prepared result set.
     */
    public void withDrawAll() {

        if (getAmountRemainingToDispense() > 0) {
            throw new RuntimeException("Unexpected!!");
        }
        System.out.println("Dispensed!!");
        for (DenomiationHolder denomiationHolder : toWithDrawList) {
            new Withdraw(denomiationHolder).execute();
            denomiationHolder.print();
        }

        System.out.println("Withdrawal of $" + amountToWithDraw + " is success!!");

    }

}
