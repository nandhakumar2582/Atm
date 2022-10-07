package Assignment_1;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        try(Scanner scanner=new Scanner(System.in);) {
            Atm atm=new Atm();
            MoneyArranging moneyArrange=new MoneyArranging();
            Verify verify=new Verify();

            TreeMap<String, Customer> tm=new TreeMap<>();
            int[] cash=new int[]{2000, 500, 100};
            MoneyOrdering handleDenomination=new MoneyOrdering();
            while (true){
                int option=0;
                System.out.println("Choose any Option(Before ATM Withdraw...You need to Add Money in ATM)\n1. Add Money\n2. Withdraw From ATM\n3. Check ATM Balance\n4. Create Account\n5. Transfer\n6. Check Account Balance\n7. Display All Customer Details\n8. Deposit\n9. End");
                option=scanner.nextInt();
                System.out.println();
                scanner.nextLine();
                switch (option){
                    case 1:
                    {
                        System.out.println("Add Money To Your Account");
                        System.out.println("Enter the Cash to deposit(2000:10, 500:5) : ");
                        String[] deno=scanner.nextLine().split(",");
                        int flag=1;
                        for(String seperate:deno){
                            String[] values=seperate.split(":");
                            int amount=Integer.valueOf(values[0].trim());
                            int denomination=Integer.valueOf(values[1]);
                            if(amount<0||denomination<0){
                                System.out.println("Incorrect Deposit amount");
                            }
                            else if (amount==0||denomination==0){
                                System.out.println("Deposit amount cannot be Zero");
                            }
                            else{
                            	moneyArrange.updateDenomination(amount, denomination, handleDenomination);
                            }
                        }

                        moneyArrange.updateDeposit(atm, handleDenomination);


                        System.out.println("Cash            Number  Value       ");
                        System.out.println("2000                    "+handleDenomination.getTwoThousand()+"       "+2000*handleDenomination.getTwoThousand());
                        System.out.println("500                     "+handleDenomination.getFiveHundred()+"       "+500*handleDenomination.getFiveHundred());
                        System.out.println("100                     "+handleDenomination.getOneHundred()+"       "+100*handleDenomination.getOneHundred());
                        break;
                    }
                    case 2:
                    {
                        System.out.println("Withdraw");

                        System.out.println("Enter the Account Number : ");
                        String accountNumber=scanner.next();
                        System.out.println("Enter the Pin Number : ");
                        String pinNumber=scanner.next();
                        if(verify.validAccountNo(accountNumber, tm)&&verify.validPinNo(accountNumber, pinNumber, tm)){
                            System.out.println("Enter the amount to Withdraw : ");
                            int withdrawAmount=scanner.nextInt();
                            if(withdrawAmount<=0||withdrawAmount>atm.getBalanceAmount()){
                                System.out.println("Incorrect or Insufficient Funds");break;
                            }
                            else if(tm.get(accountNumber).getBalanceAmount()>10000|tm.get(accountNumber).getBalanceAmount()<100){
                                System.out.println("Withdraw Amount should maximum 10000 and minimum 100");break;
                            }
                            tm.get(accountNumber).withdraw(accountNumber, withdrawAmount, tm);
                            int flag=1;
                            int[] dispensingDenominations=moneyArrange.dispensingCash(cash, withdrawAmount);
                            for(int i=0;i< cash.length;i++){
                                if(dispensingDenominations[i]>0){
                                    flag=moneyArrange.reduceDenomination(cash[i], dispensingDenominations[i], handleDenomination);
                                }
                            }
                            System.out.println();
                            if(flag==1)
                            	moneyArrange.updateWithdraw(atm, withdrawAmount);
                            else {
                                System.out.println("No Available Cash");
                                break;
                            }
                        }
                        else{
                            System.out.println("Invalid Acoount Number or Pin Number");break;
                        }
                        break;
                    }
                    case 3:
                    {
                        int currentAtmBalance=atm.getBalanceAmount();
                        if(currentAtmBalance<=0){
                            System.out.println("No Fund");
                            continue;
                        }
                        else{
                            System.out.println("ATM Balance");
                            System.out.println("Cash            Number  Value       ");
                            System.out.println("2000                    "+handleDenomination.getTwoThousand()+"       "+2000*handleDenomination.getTwoThousand());
                            System.out.println("500                     "+handleDenomination.getFiveHundred()+"       "+500*handleDenomination.getFiveHundred());
                            System.out.println("100                     "+handleDenomination.getOneHundred()+"       "+100*handleDenomination.getOneHundred());
                            System.out.println("Total Amount available in ATM is = Rs. "+atm.getBalanceAmount());
                        }
                        break;
                    }
                    case 4:
                    {
                        System.out.println("Savings Account");
                        System.out.println("Enter the New Account Number : ");
                        String accountNumber=scanner.nextLine();
                        System.out.println("Enter the Customer Name : ");
                        String customerName=scanner.nextLine();
                        System.out.println("Enter the New Pin Number : ");
                        String pinNumber=scanner.nextLine();
                        System.out.println("Enter the Amount to Deposit : ");
                        int acoountBalance=scanner.nextInt();
                        if(acoountBalance>=500){
                            Customer customerDatabase=new Customer(accountNumber,customerName, pinNumber, acoountBalance);
                            tm.put(accountNumber, customerDatabase);
                        }
                        else{
                            System.out.println("You should have Minimum Balance of 500");
                            break;
                        }
                        break;
                    }
                    case 5:
                    {
                        System.out.println("Money Transfer");

                        System.out.println("Enter the Account Number : ");
                        String fromAccountNumber=scanner.next();
                        System.out.println("Enter the Pin Number : ");
                        String fromPinNumber=scanner.next();
                        if(verify.validAccountNo(fromAccountNumber, tm)&&verify.validPinNo(fromAccountNumber, fromPinNumber, tm)){
                            System.out.println("Enter the Account Number to make Transfer : ");
                            String toAccountNumber=scanner.next();
                            System.out.println("Enter the Amount to Transfer : ");
                            int transferAmount=scanner.nextInt();
                            verify.amountTransfer(fromAccountNumber, toAccountNumber, transferAmount, tm);
                        }
                        else{
                            System.out.println("Invalid Account Number or Pin Number");
                            break;
                        }
                        break;
                    }
                    case 6:
                    {
                        System.out.println("Check Account Balance");

                        System.out.println("Enter the Account Number : ");
                        String accountNumber=scanner.next();
                        System.out.println("Enter the Pin Number : ");
                        String pinNumber=scanner.next();
                        if(verify.validAccountNo(accountNumber, tm)&&verify.validPinNo(accountNumber, pinNumber, tm)){
                            System.out.println("Acc No  Account Holder    Pin Number Account Balance");
                            System.out.println(accountNumber+"        "+tm.get(accountNumber).getName()+"        "+pinNumber+"        "+tm.get(accountNumber).getBalanceAmount());
                        }
                        else{
                            System.out.println("Invalid Account Number or Pin Number");
                            break;
                        }
                        break;
                    }
                    case 7:
                    {
                        System.out.println("Customer Details");
                        System.out.println("Acc No    Account Holder    Pin Number    Account Balance");
                        for(Map.Entry<String, Customer> entry: tm.entrySet()){
                            System.out.println(entry.getValue().getAccountNumber()+"       "+entry.getValue().getName()+"        "+entry.getValue().getPin()+"        "+entry.getValue().getBalanceAmount());
                        }
                    }
                    case 8:
                    {
                        System.out.println("Deposit");
                        System.out.println("Enter the Account Number : ");
                        String accountNumber=scanner.next();
                        if(verify.validAccountNo(accountNumber, tm)){
                            System.out.println("Enter the Amount to Deposit : ");
                            int depositAmount=scanner.nextInt();
                            tm.get(accountNumber).deposit(accountNumber, depositAmount, tm);
                            System.out.println("Your Current Balance is Rs. "+tm.get(accountNumber).getBalanceAmount());
                        }
                        else{
                            System.out.println("Please Enter valid Account Number...");break;
                        }break;
                    }
                    case 9:
                    {
                        System.out.println("Thank You Visit Again!!");
                        System.exit(0);
                    }
                    default:
                    {
                        System.out.println("Please Enter valid option...");
                    }
                }
            }
        }
        catch (Exception e){
            System.out.println("Oops Something Wrong ! :(");
        }
    }


}