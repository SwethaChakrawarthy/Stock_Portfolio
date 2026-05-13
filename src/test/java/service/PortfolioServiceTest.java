package service;

import exceptions.InsufficientFundsException;
import exceptions.InsufficientSharesException;
import model.Portfolio;
import model.Stock;
import model.StockMarket;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class PortfolioServiceTest {
    private Portfolio testPortfolio;
    private Stock testStock;
    private StockMarket testStockMarket;
    private PortfolioService testPortfolioService;

    @BeforeEach
    public void setUp() {
        testStock = new Stock("TCS", "TCS", 2400, "IT");
        testPortfolio = new Portfolio(50000, "Swetha");
        testPortfolioService = new PortfolioService(testPortfolio);
        testStockMarket = new StockMarket();
        testStockMarket.addStock(testStock);

    }
    @Test
    public void buyStock_success_balanceAndSharesUpdated() {
        testPortfolioService.buyStock(testStock,2);
        double result = testPortfolio.getCashBalance();
        assertEquals(45200,result);
        int result2  = testPortfolio.getStocks().get("TCS");
        assertEquals(2,result2);

    }
    @Test
    public void  buyStockMorethanCashBalance(){

        assertThrows(InsufficientFundsException.class,()->{
            testPortfolioService.buyStock(testStock,1000);
        });

        }

    @Test
    public void sellStockSuccessfully() throws InsufficientSharesException {
        testPortfolioService.buyStock(testStock,10);
        testPortfolioService.sellStock(testStock,2);
        assertEquals(8,testPortfolio.getStocks().get("TCS"));
    }
    @Test
    public void insufficientShares() throws InsufficientSharesException {

        assertThrows(exceptions.InsufficientSharesException.class,()->{
            testPortfolioService.buyStock(testStock,2);
            testPortfolioService.sellStock(testStock,10);
        });
    }
    @Test
    public void testPortfolioValue(){
        testPortfolioService.buyStock(testStock,2);
        assertEquals(4800, testPortfolioService.getPortfolioValue(testStockMarket));

    }
    @Test
    public void testTransactionHistory(){
        testPortfolioService.buyStock(testStock,2);
        testPortfolioService.buyStock(testStock,2);
        assertEquals(2,testPortfolio.getTransactions().size());
    }
    }

