package model;
import exceptions.StockNotFoundException;
import java.util.Collection;
import java.util.HashMap;
public class StockMarket {
    private HashMap<String, Stock> stocks;
    public StockMarket() {
        stocks = new HashMap<>();
    }
    public void addStock(Stock stock) {
        stocks.put(stock.getSymbol(), stock);
    }
    public Stock getStock(String symbol) throws StockNotFoundException {
        if (!stocks.containsKey(symbol)) {
            throw new StockNotFoundException("Stock not found: " + symbol);
        }
        return stocks.get(symbol);
    }
    public Collection<Stock>getAllStocks() {
        return stocks.values();
    }
    public void updatePrice(String symbol, double newPrice)
            throws StockNotFoundException {
        Stock stock = getStock(symbol);
        stock.setPrice(newPrice);
    }
}
