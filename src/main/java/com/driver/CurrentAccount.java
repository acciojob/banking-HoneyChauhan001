package com.driver;

import java.util.*;

public class CurrentAccount extends BankAccount{
    String tradeLicenseId; //consists of Uppercase English characters only
    public CurrentAccount(String name, double balance, String tradeLicenseId) throws Exception {
        super(name,balance,5000);
        this.tradeLicenseId = tradeLicenseId;
        if(balance<5000)throw new Exception("Insufficient Balance");
        // minimum balance is 5000 by default. If balance is less than 5000, throw "Insufficient Balance" exception

    }

    public String getTradeLicenseId() {
        return tradeLicenseId;
    }

    public void validateLicenseId() throws Exception {
        // A trade license Id is said to be valid if no two consecutive characters are same
        // If the license Id is valid, do nothing
        // If the characters of the license Id can be rearranged to create any valid license Id
        // If it is not possible, throw "Valid License can not be generated" Exception

        int size = tradeLicenseId.length();
        HashMap<Character,Integer> hm = new HashMap<>();
        getFrequencyMap(size, hm);

        if(Collections.max(hm.values()) > (size +1)/2){
            throw new Exception("Valid License can not be generated");
        }

        while(!isValidTradeLicenseId()){
            List list = Arrays.asList(tradeLicenseId.toCharArray());
            Collections.shuffle(list);
            this.tradeLicenseId = list.toString();
        }
        return;
    }

    private void getFrequencyMap(int size, HashMap<Character, Integer> hm) {
        for(int i = 0; i< size; i++){
            char ch = tradeLicenseId.charAt(i);
            hm.put(ch, hm.getOrDefault(ch,0)+1);
        }
    }

    private boolean isValidTradeLicenseId() {
        int size = tradeLicenseId.length();
        for(int i = 0, j = 1; i< size && j< size; i++,j++){
            if(tradeLicenseId.charAt(i)==tradeLicenseId.charAt(j)){
                break;
            }
            if(j== size -1){
                return true;
            }//tradeLicenseID is valid
        }
        return false;
    }

}
