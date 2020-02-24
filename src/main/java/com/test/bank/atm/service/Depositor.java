package com.test.bank.atm.service;

import com.test.bank.atm.models.Deposit;

import java.util.List;

//Should be a DAO layer
public class Depositor {

    public void depositAll(List<Deposit> toDeposit) {

        for (Deposit deposit : toDeposit) {
            deposit.execute();
        }

    }
}
