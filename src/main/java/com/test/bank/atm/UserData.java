package com.test.bank.atm;

import com.test.bank.atm.models.DenomiationHolder;
import com.test.bank.atm.models.Deposit;
import com.test.bank.atm.models.ErrorMessages;
import com.test.bank.atm.models.Withdraw;
import com.test.bank.atm.models.enums.DenominationUnit;
import com.test.bank.atm.service.Depositor;
import com.test.bank.atm.service.Withdrawer;

import java.util.*;

import static com.test.bank.atm.Main.atmWallet;

public class UserData {

    public List<DenomiationHolder> getDenominations() {
        char toContinue = 'y';
        List<DenomiationHolder> denomiationHolders = new ArrayList<>();
        do {
            System.out.println("Please enter the denomination");
            Scanner sc = new Scanner(System.in);
            int denomination = sc.nextInt();
            System.out.println("Please enter the count");
            int count = sc.nextInt();
            Optional<DenominationUnit> denominationUnit = DenominationUnit.get(denomination);
            if (!denominationUnit.isPresent()) {
                System.out.println("Invalid Denomination");
                continue;
            }
            if(count < 0) {
                System.out.println("Incorrect deposit amount.");
                continue;
            }
            DenomiationHolder denomiationHolder = new DenomiationHolder(denominationUnit.get(), count);
            denomiationHolders.add(denomiationHolder);
            System.out.println("press y to continue entering or any characters to exit");
            toContinue = sc.next().charAt(0);
        } while (toContinue == 'y');
        return denomiationHolders;
    }

    public int getAmountToBeWithDrawn() {
        System.out.println("Please enter the amount to be withdrawn");
        Scanner sc = new Scanner(System.in);
        int amount = sc.nextInt();
        return amount;
    }

    public void toDeposit(List<DenomiationHolder> denomiationHolders) {
        List<Deposit> toDeposit = new ArrayList<Deposit>();
        denomiationHolders.stream()
                .forEach(denomiationHolder ->
                        toDeposit.add(new Deposit(denomiationHolder))
                );

        if(hasAllZero(denomiationHolders)) {
            System.out.println("Deposit amount cannot be zero");
            return;
        }
        Depositor depositor = new Depositor();
        depositor.depositAll(toDeposit);
        printAll();
    }

    public boolean hasAllZero(List<DenomiationHolder> denomiationHolders){
        int sum = 0;
        for (DenomiationHolder deposit: denomiationHolders) {
            sum += deposit.getDollarCount();
        }
        return sum <= 0;
    }

    public void toWithDraw(int amountToBeWithDrawn) {
        Withdrawer withdrawer = new Withdrawer(amountToBeWithDrawn);
        if(!withdrawer.isValid()) {
            return;
        }
        withdrawer.prepareDispenser();

        if (withdrawer.getAmountRemainingToDispense() > 0) {
            System.out.println(ErrorMessages.INSUFFICIENT_FUNDS);
            return;
        }

        withdrawer.withDrawAll();
        printAll();
    }

    public void printAll() {
        int total = 0;
        System.out.println("--------------------------------------------------");
        for (Map.Entry<Integer, DenomiationHolder> d : atmWallet.entrySet()) {
            d.getValue().print();
            total += d.getValue().getAvailableCash();
        }
        System.out.println("Total : $" + total);
        System.out.println("--------------------------------------------------");
    }
}
