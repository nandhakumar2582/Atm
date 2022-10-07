package Assignment_1;

public class MoneyArranging {

    public static void updateDenomination(int amount, int deno, MoneyOrdering moneyArranging){
        if(amount==2000){
        	moneyArranging.setTwoThousand(moneyArranging.getTwoThousand()+deno);
        }
        else if(amount==500){
        	moneyArranging.setFiveHundred(moneyArranging.getFiveHundred()+deno);
        }
        else if(amount==100){
        	moneyArranging.setOneHundred(moneyArranging.getOneHundred()+deno);
        }
    }

    public static int reduceDenomination(int amount, int deno, MoneyOrdering moneyArranging){
        int flag1=0, flag2=0;
        if(amount==2000){
            if(moneyArranging.getTwoThousand()>0){
            	moneyArranging.setTwoThousand(moneyArranging.getTwoThousand()-deno);
                return 1;
            }
            else if(moneyArranging.getFiveHundred()>0){
                flag1=1;
                moneyArranging.setFiveHundred(moneyArranging.getFiveHundred()-deno);
            }
            else if(moneyArranging.getOneHundred()>0){
                flag2=1;
                moneyArranging.setOneHundred(moneyArranging.getOneHundred()-deno);
            }
        }
        else if(amount==500){
            if(moneyArranging.getFiveHundred()>0){
                if(flag1==0){
                	moneyArranging.setFiveHundred(moneyArranging.getFiveHundred()-deno);
                    return 1;
                }
            }
            else if(moneyArranging.getOneHundred()>0)
                if(flag2==0)
                	moneyArranging.setOneHundred(moneyArranging.getOneHundred()-deno);
        }
        else if(amount==100){
            if(moneyArranging.getOneHundred()>0){
                if(flag2==0){
                	moneyArranging.setOneHundred(moneyArranging.getOneHundred()-deno);
                return 1;}
            }
        }
        return -1;
    }

    public static void updateDeposit(Atm atm, MoneyOrdering moneyArranging){
        int deposit=moneyArranging.getTwoThousand()*2000+moneyArranging.getFiveHundred()*500+moneyArranging.getOneHundred()*100;
        atm.setDeposit(deposit);
        atm.setBalanceAmount(atm.getDeposit());
    }

    public static void updateWithdraw(Atm atm, int withdrawAmount){
        atm.setWithdrawAmount(withdrawAmount);
        atm.setBalanceAmount(atm.getBalanceAmount()-withdrawAmount);
    }

    public static int[] dispensingCash(int[] cash, int withdrawAmount){
        int[] counter=new int[cash.length];
        for(int i=0;i<cash.length;i++){
            if(withdrawAmount>=cash[i]){
                counter[i]=withdrawAmount/cash[i];
                withdrawAmount=withdrawAmount-counter[i]*cash[i];
            }
        }
        return counter;
    }

}

