package service;
import exceptions.InsufficientFundsException;
import exceptions.InsufficientSharesException;
import exceptions.StockNotFoundException;
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
    void getPortfolioValue(){
        double total = 0;

    }
    void getTransactionHistory(){}

}
