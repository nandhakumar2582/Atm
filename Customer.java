package Assignment_1;

import java.util.*;
public class Customer {
    private  String accountNumber;
    private String name;
    private String pin;
    private int balanceAmount;

    Customer(String accountNumber, String name, String pin, int balanceAmount) {
        this.accountNumber = accountNumber;
        this.name = name;
        this.pin = pin;
        this.balanceAmount = balanceAmount;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public String getName() {
        return name;
    }

    public String getPin() {
        return pin;
    }

    public int getBalanceAmount() {
        return balanceAmount;
    }

    public void setCustomerName(String name) {
        this.name = name;
    }

    public void setPinNumber(String pin) {
        this.pin = pin;
    }

    public void setAccountBalance(int balanceAmount) {
        this.balanceAmount = balanceAmount;
    }

    public void withdraw(String accountNumber, int amount, TreeMap<String, Customer> db){
         db.get(accountNumber).setAccountBalance(getBalanceAmount()-amount);
    }

    public void deposit(String accountNumber, int amount, TreeMap<String, Customer> db){
        db.get(accountNumber).setAccountBalance(getBalanceAmount()+amount);
    }
}
