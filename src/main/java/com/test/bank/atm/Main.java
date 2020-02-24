package com.test.bank.atm;

import com.test.bank.atm.models.DenomiationHolder;
import com.test.bank.atm.models.Deposit;
import com.test.bank.atm.models.ErrorMessages;
import com.test.bank.atm.service.Depositor;
import com.test.bank.atm.service.Withdrawer;
import org.apache.commons.io.FileUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static List<DenomiationHolder> dollarSource = new ArrayList<>();

    public static void main(String[] args) {

        System.out.println("Hello World!");



        String s = "[\n" +
                "   {\n" +
                "      \"denomination\":20,\n" +
                "      \"count\":2\n" +
                "   },\n" +
                "   {\n" +
                "      \"denomination\":10,\n" +
                "      \"count\":3\n" +
                "   }\n" +
                "]";

        File f = new File("denominations_source.json");
        try {
            FileUtils.writeStringToFile(f, s, "UTF-8");
        } catch(Exception e) {
            e.printStackTrace();
        }


        JSONArray array = new JSONArray(s);

        for(int i =0; i< array.length(); i++) {
            JSONObject o = array.getJSONObject(i);
            DenomiationHolder denomiationHolder =
                    new DenomiationHolder(o.getInt("denomination"), o.getInt("count"));
            dollarSource.add(denomiationHolder);

        }

        depositTest();
        depositTest();
        printAll(dollarSource);
        withDrawTest();
        printAll(dollarSource);
    }

    private static void printAll(List<DenomiationHolder> list) {
       int total=0;
        System.out.println("--------------------------------------------------");
        for (DenomiationHolder d : list) {
            d.print();
            total += d.getAvailableCash();
        }
        System.out.println("Total : " + total);
        System.out.println("--------------------------------------------------");
    }



    private static void depositTest() {

        List<Deposit> toDeposit = new ArrayList<Deposit>();
        DenomiationHolder obj = new DenomiationHolder(20, 1);
        DenomiationHolder obj1 = new DenomiationHolder(10, 2);
       // DenomiationHolder obj2 = new DenomiationHolder(5, 2);

        toDeposit.add(new Deposit(obj));
        toDeposit.add(new Deposit(obj1));
      //  toDeposit.add(new Deposit(obj2));

        Depositor depositor = new Depositor();
        depositor.depositAll(toDeposit);

    }

    private static void withDrawTest() {
        Withdrawer withdrawer = new Withdrawer(149);
        withdrawer.prepareDispenser();

        if(withdrawer.getAmountRemainingToDispense() > 0) {
            System.out.println(ErrorMessages.INSUFFICIENT_FUNDS);
            return;
        }

        withdrawer.withDrawAll();
    }

}
