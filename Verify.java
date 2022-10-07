package Assignment_1;

import java.util.TreeMap;

public class Verify {

    public boolean validAccountNo(String accountNumber, TreeMap<String, Customer> tm){
        if(accountNumber.equals(tm.get(accountNumber).getAccountNumber())){
            return true;
        }
        return false;
    }
    public boolean validPinNo(String accountNumber, String pinNumber, TreeMap<String, Customer> tm){
        if(pinNumber.equals(tm.get(accountNumber).getPin())){
            return true;
        }
        return false;
    }


    public void amountTransfer(String fromAccountNumber, String toAccountNumber, int amount, TreeMap<String, Customer> tm){
        tm.get(fromAccountNumber).withdraw(fromAccountNumber, amount, tm);
        tm.get(toAccountNumber).deposit(toAccountNumber, amount, tm);
    }
}
