package model;

public class Stock {
    private String name;
    private String symbol;
    private double price;
    private String sector;

    public Stock(String name, String symbol, double price, String sector) {
        this.name = name;
        this.symbol = symbol;
        if (price<=0){
            throw new IllegalArgumentException("Price must be greater than zero");
        }
        this.price = price;
        this.sector = sector;
    }

    public String getName() {
        return name;
    }

    public String getSymbol() {
        return symbol;
    }

    public double getPrice() {
        return price;
    }

    public String getSector() {
        return sector;
    }

    public void setPrice(double price) {
        if (price<=0){
            throw new IllegalArgumentException("Price must be greater than 0");
        }
        this.price = price;
    }

    @Override
    public String toString() {
        return symbol + " | " + name + " | $" + price + " | " + sector;
    }
}
