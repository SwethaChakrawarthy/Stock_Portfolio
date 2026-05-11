package model;

import java.util.HashMap;
import java.util.Stack;

public class Portfolio {
    private HashMap<String,Integer> stocks;
    private Stack<Transaction> transactions ;
    private double cashBalance;
    private String name;

    public Portfolio(double cashBalance, String name) {
        this.cashBalance = cashBalance;
        this.name = name;
        stocks = new HashMap<>();
        transactions = new Stack<>();
    }

    public HashMap<String, Integer> getStocks() {
        return stocks;
    }

    public Stack<Transaction> getTransactions() {
        return transactions;
    }

    public double getCashBalance() {
        return cashBalance;
    }

    public String getName() {
        return name;
    }

    public void setCashBalance(double cashBalance) {
        if (cashBalance<0){
            throw new IllegalArgumentException("cashBalance cannot be negative");
        }
        this.cashBalance = cashBalance;
    }

    @Override
    public String toString() {
        return "============\n" +
                "Owner: "+name+ "\n" +
                "Cash Balance: "+ cashBalance+"\n" +
                "Stocks: "+stocks +"\n" +
                "==============";
    }
}
