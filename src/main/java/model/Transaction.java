package model;
import java.time.LocalDate;
import java.util.UUID;

public class Transaction {
    private UUID transactionId;
    private Stock stock;
    private int shares;
    private double price;
    private LocalDate date;
    private TransactionType type;

    public Transaction(Stock stock , double price, LocalDate date,int shares,TransactionType type) {
        this.transactionId = UUID.randomUUID();
        this.stock = stock;
        this.price = price;
        this.date = date;
        this.shares = shares;
        this.type = type;

    }

    public UUID getTransactionId() {
        return transactionId;
    }

    public Stock getStock() {
        return stock;
    }

    public int getShares() {
        return shares;
    }

    public double getPrice() {
        return price;
    }

    public LocalDate getDate() {
        return date;
    }

    public TransactionType getType() {
        return type;
    }

    @Override
    public String toString() {
        return type + " " + shares + " shares of " + stock.getSymbol() + " at ₹" + price + " on " + date + " [ID: " + transactionId + "]";
    }

}
