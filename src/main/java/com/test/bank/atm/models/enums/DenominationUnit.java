package com.test.bank.atm.models.enums;

import java.util.Arrays;
import java.util.Optional;

public enum DenominationUnit {

    TWENTY_DOLLAR(20),
    TEN_DOLLAR(10),
    FIVE_DOLLAR(5),
    ONE_DOLLAR(1);

    // if at all any new denomination is added, we can add one enum here.

    private  int i;

    DenominationUnit(int i) {
        this.i = i;
    }

    public static Optional<DenominationUnit> get(int i) {
        Optional<DenominationUnit> first = Arrays.asList(values()).stream().filter(unit -> unit.i == i).findFirst();
        if(!first.isPresent()) {
            System.out.println("Denomination Unit Not Found!!");
        }
        return first;
    }

    public int getVal() {
        return i;
    }
}
