package service;
import exceptions.InsufficientFundsException;
import exceptions.InsufficientSharesException;
import exceptions.StockNotFoundException;
import model.*;

import java.time.LocalDate;
import java.util.Stack;

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

        public void sellStock(Stock stock,int shares) throws InsufficientSharesException,StockNotFoundException {
            if (!portfolio.getStocks().containsKey(stock.getSymbol())){
                throw new StockNotFoundException("Stock not found");
            }
            int existing = portfolio.getStocks().getOrDefault(stock.getSymbol(), 0);
            if (existing<shares){
                throw new InsufficientSharesException("Insufficient Shares");
            }
            double totalEarned  = shares * stock.getPrice();
            portfolio.setCashBalance(portfolio.getCashBalance() + totalEarned);

            if (existing-shares ==0 ){
                portfolio.getStocks().remove(stock.getSymbol());
            }
            else{
                portfolio.getStocks().put(stock.getSymbol(), existing-shares);
            }
            Transaction t = new Transaction(
                            stock, stock.getPrice(), LocalDate.now(),
                    shares, TransactionType.SELL
                    );
            portfolio.getTransactions().push(t);

        }
    public double getPortfolioValue(StockMarket market){
        double total = 0;
        for(Stock stock: market.getAllStocks()){
            if(portfolio.getStocks().containsKey(stock.getSymbol())){
                int shares = portfolio.getStocks()
                        .getOrDefault(stock.getSymbol(), 0);
                total = total + (shares *  stock.getPrice());

            }
        }
        return total;
    }
    public Stack<Transaction> getTransactionHistory(){
        return portfolio.getTransactions();
    }

}
