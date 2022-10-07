package Assignment_1;

public class Atm{
    private int balanceAmount;
    private  int deposit;
    private int withdrawMoney;

 
    Atm(int balanceAmount, int deposit, int withdrawAwithdrawMoneymount) {
        this.balanceAmount = balanceAmount;
        this.deposit = deposit;
        this.withdrawMoney = withdrawMoney;
    }
    Atm(){}

    public int getBalanceAmount() {
        return balanceAmount;
    }

    public void setBalanceAmount(int balanceAmount) {
        this.balanceAmount = balanceAmount;
    }

    public int getDeposit() {
        return deposit;
    }

    public void setDeposit(int deposit) {
        this.deposit = deposit;
    }

    public int getWithdrawAmount() {
        return withdrawMoney;
    }

    public void setWithdrawAmount(int withdrawMoney) {
        this.withdrawMoney = withdrawMoney;
    }
}
