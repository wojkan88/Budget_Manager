package budget;

import java.io.Serializable;
import java.util.ArrayList;

public class Budget implements Serializable {
    private double balance;
    private ArrayList<Purchase> listOfPurchases;
    private ArrayList<Purchase> listOfFood;
    private ArrayList<Purchase> listOfClothes;
    private ArrayList<Purchase> listOfEntertainment;
    private ArrayList<Purchase> listOfOther;

    public Budget() {
        this.balance = 0;
        this.listOfPurchases = new ArrayList<>();
        this.listOfFood = new ArrayList<>();
        this.listOfClothes = new ArrayList<>();
        this.listOfEntertainment = new ArrayList<>();
        this. listOfOther = new ArrayList<>();
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public ArrayList<Purchase> getListOfPurchases() {
        return listOfPurchases;
    }

    public ArrayList<Purchase> getListOfFood() {
        return listOfFood;
    }

    public ArrayList<Purchase> getListOfClothes() {
        return listOfClothes;
    }

    public ArrayList<Purchase> getListOfEntertainment() {
        return listOfEntertainment;
    }

    public ArrayList<Purchase> getListOfOther() {
        return listOfOther;
    }
}