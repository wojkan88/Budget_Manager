package budget;

import java.io.Serializable;

public class Purchase implements Serializable {
    private String name;
    private double price;
    private String type;

    public Purchase(String name, double price, String type) {
        this.name = name;
        this.price = price;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return String.format(name + " $%.2f", price);
    }
}