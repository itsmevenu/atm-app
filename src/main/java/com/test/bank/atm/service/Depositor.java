package com.test.bank.atm.service;

import com.test.bank.atm.models.Deposit;

import java.util.List;

//Should be a DAO layer
// For now added them in the service layer.
// Incase of databases, we should store it and access it from the tables.

public class Depositor {



    public void depositAll(List<Deposit> toDeposit) {

        for (Deposit deposit : toDeposit) {
            deposit.execute();
        }

    }
}
