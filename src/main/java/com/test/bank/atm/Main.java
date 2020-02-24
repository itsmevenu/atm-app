package com.test.bank.atm;

import com.test.bank.atm.models.DenomiationHolder;
import com.test.bank.atm.models.enums.DenominationUnit;
import org.apache.commons.io.FileUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.File;
import java.util.*;

public class Main {

    public static TreeMap<Integer, DenomiationHolder> atmWallet = new TreeMap<>(Collections.reverseOrder());

    public static void main(String[] args) {
        loadData();
        getUserInput();
    }

    public static void loadData() {
        String s = null;
        File f = new File("denominations_source.json");
        try {
            s = FileUtils.readFileToString(f, "UTF-8");
        } catch (Exception e) {
            e.printStackTrace();
        }

        JSONArray array = new JSONArray(s);

        for (int i = 0; i < array.length(); i++) {
            JSONObject o = array.getJSONObject(i);
            DenominationUnit denom = DenominationUnit.get(o.getInt("denomination")).get();
            DenomiationHolder denomiationHolder = new DenomiationHolder(denom, o.getInt("count"));
            atmWallet.put(denom.getVal(), denomiationHolder);
        }
    }

    private static void getUserInput() {
        Scanner sc = new Scanner(System.in);    //System.in is a standard input stream
        System.out.println("******** Enter your choice *********");
        int userOption;

        do {
            System.out.println("1. Deposit ");
            System.out.println("2. Withdraw ");
            System.out.println("3. Check Balance ");
            System.out.println("4. Exit");
            userOption = sc.nextInt();
            printConsole(userOption);
        } while(userOption !=4 );

    }

    private static void printConsole(int userOption) {
        UserData userData = new UserData();
        switch (userOption) {
            case 1:
                userData.toDeposit(userData.getDenominations());
                break;
            case 2:
                userData.toWithDraw(userData.getAmountToBeWithDrawn());
                break;
            case 3:
                userData.printAll();
                break;
            default:
                System.out.println("Exiting ...");
                break;
        }
    }

}
