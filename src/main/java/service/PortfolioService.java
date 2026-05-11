package service;
import exceptions.InsufficientFundsException;
import model.Stock;
import model.Portfolio;
import model.Transaction;
import model.TransactionType;

import java.time.LocalDate;

public class PortfolioService {
    private Portfolio portfolio;
    public PortfolioService(Portfolio portfolio) {
        this.portfolio = portfolio;
    }
    public void buyStock(Stock stock,int shares) {
        double totalcost = shares * stock.getPrice();

        if (portfolio.getCashBalance()<totalcost){
            throw new InsufficientFundsException("Insufficient Funds");
        }
        portfolio.setCashBalance(portfolio.getCashBalance()-totalcost);
        int existing = portfolio.getStocks()
                .getOrDefault(stock.getSymbol(), 0);
        portfolio.getStocks().put(stock.getSymbol(), existing + shares);
        Transaction t = new Transaction(
                        stock, stock.getPrice(), LocalDate.now(), shares, TransactionType.BUY
                );
        portfolio.getTransactions().push(t);


    }

    void sellStock(){}
    void getPortfolioValue(){}
    void getTransactionHistory(){}

}
