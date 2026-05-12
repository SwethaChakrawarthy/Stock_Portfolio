package com.portfolio;

import exceptions.InsufficientFundsException;
import exceptions.InsufficientSharesException;
import exceptions.StockNotFoundException;
import model.Portfolio;
import model.Stock;
import model.StockMarket;
import model.Transaction;
import service.PortfolioService;

import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        Stock stock1 = new Stock("Tata Consulting Services", "TCS", 2300, "IT");
        Stock stock2 = new Stock("Fiserv", "Fi", 2550, "IT");
        Stock stock3 = new Stock("IBM", "IBM", 2700, "IT");
        Stock stock4 = new Stock("Paypal", "PPL", 2200, "IT");
        Stock stock5 = new Stock("Meta", "M", 2900, "IT");
        StockMarket stockMarket = new StockMarket();
        stockMarket.addStock(stock1);
        stockMarket.addStock(stock2);
        stockMarket.addStock(stock3);
        stockMarket.addStock(stock4);
        stockMarket.addStock(stock5);
        Portfolio portfolio = new Portfolio(50000, "Joy");
        PortfolioService service = new PortfolioService(portfolio);
        Scanner input = new Scanner(System.in);
        while (true) {
            System.out.println("===== Stock Portfolio Manager =====\n" +
                    "1. View Market Stocks\n" +
                    "2. Buy Stock\n" +
                    "3. Sell Stock\n" +
                    "4. View Portfolio\n" +
                    "5. View Portfolio Value\n" +
                    "6. View Transaction History\n" +
                    "7. Exit");

            int choice = input.nextInt();
            switch (choice) {
                case 1:
                    for (Stock s : stockMarket.getAllStocks()) {
                        System.out.println(s);
                    }
                    break;
                case 2:
                    try {
                        System.out.println("Enter Stock Symbol: ");
                        String symbol = input.next();
                        System.out.println("Enter number of shares: ");
                        int shares = input.nextInt();
                        Stock stock = stockMarket.getStock(symbol);
                        service.buyStock(stock, shares);

                    } catch (InsufficientFundsException e) {
                        System.out.println("Insufficient shares");
                    } catch (StockNotFoundException e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                    break;


                case 3:
                    try {
                        System.out.println("Enter Stock Symbol: ");
                        String stocksymbol = input.next();
                        System.out.println("Enter number of shares: ");
                        int sharestosell = input.nextInt();
                        Stock stocktosell = stockMarket.getStock(stocksymbol);
                        service.sellStock(stocktosell, sharestosell);
                    } catch (InsufficientSharesException e) {
                        System.out.println("Insufficient shares");
                    } catch (StockNotFoundException e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                    break;
                case 4:
                    System.out.println(portfolio.toString());
                    break;
                case 5:

                    System.out.println("Portfolio Value: ₹" +
                            service.getPortfolioValue(stockMarket));
                    break;
                case 6:
                    for (Transaction t : service.getTransactionHistory()) {
                        System.out.println(t);
                    }
                    break;
                case 7:
                    System.out.println("Goodbye!");
                    return;


            }

        }


    }
}
