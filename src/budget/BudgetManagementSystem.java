package budget;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class BudgetManagementSystem {
    Scanner scanner = new Scanner(System.in);
    Budget budget = new Budget();

    public void menu() {
        while (true) {
            System.out.println();
            System.out.println("Choose your action:");
            System.out.println("1) Add income");
            System.out.println("2) Add purchase");
            System.out.println("3) Show list of purchases");
            System.out.println("4) Balance");
            System.out.println("5) Save");
            System.out.println("6) Load");
            System.out.println("7) Analyze (Sort");
            System.out.println("0) Exit");

            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    addIncome();
                    break;
                case 2:
                    boolean back = false;
                    while (!back) {
                        System.out.println();
                        System.out.println("Choose the type of purchase");
                        System.out.println("1) Food");
                        System.out.println("2) Clothes");
                        System.out.println("3) Entertainment");
                        System.out.println("4) Other");
                        System.out.println("5) Back");

                        int nextChoice = scanner.nextInt();
                        switch (nextChoice) {
                            case 1:
                                addPurchase("Food", budget.getListOfFood());
                                break;
                            case 2:
                                addPurchase("Clothes", budget.getListOfClothes());
                                break;
                            case 3:
                                addPurchase("Entertainment", budget.getListOfEntertainment());
                                break;
                            case 4:
                                addPurchase("Other", budget.getListOfOther());
                                break;
                            case 5:
                                back = true;
                                break;
                            default:
                                System.out.println("Invalid choice");
                        }
                    }
                    break;
                case 3:
                    boolean back2 = false;
                    while (!back2) {
                        System.out.println();
                        System.out.println("Choose the type of purchases");
                        System.out.println("1) Food");
                        System.out.println("2) Clothes");
                        System.out.println("3) Entertainment");
                        System.out.println("4) Other");
                        System.out.println("5) All");
                        System.out.println("6) Back");

                        int nextChoice = scanner.nextInt();
                        switch (nextChoice) {
                            case 1:
                                showList("Food", budget.getListOfFood());
                                break;
                            case 2:
                                showList("Clothes", budget.getListOfClothes());
                                break;
                            case 3:
                                showList("Entertainment", budget.getListOfEntertainment());
                                break;
                            case 4:
                                showList("Other", budget.getListOfOther());
                                break;
                            case 5:
                                showList("All", budget.getListOfPurchases());
                                break;
                            case 6:
                                back2 = true;
                                break;
                            default:
                                System.out.println("Invalid choice");
                        }
                    }
                    break;
                case 4:
                    showBalance();
                    break;
                case 5:
                    saveBudget();
                    break;
                case 6:
                    budget = loadBudget();
                    break;
                case 7:
                    boolean back3 = false;
                    while (!back3) {
                        System.out.println();
                        System.out.println("How do you want to sort?");
                        System.out.println("1) Sort all purchases");
                        System.out.println("2) Sort by type");
                        System.out.println("3) Sort certain type");
                        System.out.println("4) Back");

                        int anotherChoice = scanner.nextInt();
                        switch (anotherChoice) {
                            case 1:
                                if (budget.getListOfPurchases().isEmpty()) {
                                    System.out.println();
                                    System.out.println("Purchase list is empty!");
                                } else {
                                    sortAll();
                                }
                                break;
                            case 2:
                                sortByType();
                                break;
                            case 3:
                                System.out.println();
                                System.out.println("Choose the type of purchase");
                                System.out.println("1) Food");
                                System.out.println("2) Clothes");
                                System.out.println("3) Entertainment");
                                System.out.println("4) Other");

                                int lastChoice = scanner.nextInt();
                                switch (lastChoice) {
                                    case 1:
                                        if (budget.getListOfFood().isEmpty()) {
                                            System.out.println();
                                            System.out.println("Purchase list is empty!");
                                        } else {
                                            sortFood();
                                        }
                                        break;
                                    case 2:
                                        if (budget.getListOfClothes().isEmpty()) {
                                            System.out.println();
                                            System.out.println("Purchase list is empty!");
                                        } else {
                                            sortClothes();
                                        }
                                        break;
                                    case 3:
                                        if (budget.getListOfEntertainment().isEmpty()) {
                                            System.out.println();
                                            System.out.println("Purchase list is empty!");
                                        } else {
                                            sortEntertainment();
                                        }
                                        break;
                                    case 4:
                                        if (budget.getListOfOther().isEmpty()) {
                                            System.out.println();
                                            System.out.println("Purchase list is empty!");
                                        } else {
                                            sortOther();
                                        }
                                        break;
                                }
                                break;
                            case 4:
                                back3 = true;
                                break;
                            default:
                                System.out.println("Invalid choice");
                        }
                    }
                    break;
                case 0:
                    System.out.println();
                    System.out.println("Bye!");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice");
            }
        }
    }

    public void addIncome() {
        System.out.println();
        System.out.println("Enter income:");
        double income = scanner.nextDouble();
        budget.setBalance(budget.getBalance() + income);
        System.out.println("Income was added!");
        System.out.println();
    }

    public void addPurchase(String type, ArrayList<Purchase> list) {
        System.out.println();
        System.out.println("Enter purchase name:");
        scanner.nextLine();
        String purchaseName = scanner.nextLine();
        System.out.println("Enter its price:");
        double price = scanner.nextDouble();
        Purchase p = new Purchase(purchaseName, price, type);
        budget.getListOfPurchases().add(p);
        budget.setBalance(budget.getBalance() - price);
        list.add(p);
        System.out.println("Purchase was added!");
        System.out.println();
    }

    public void showList(String type, ArrayList<Purchase> list) {
        System.out.println();
        System.out.println(type + ":");
        if (list.isEmpty()) {
            System.out.println("Purchase list is empty!");
        } else {
            double sum = 0;
            for (Purchase p : list) {
                System.out.println(p.toString());
                sum += p.getPrice();
            }
            System.out.printf("Total sum: $%.2f\n", sum);
        }
        System.out.println();
    }

    public void showBalance() {
        System.out.println();
        System.out.printf("Balance: $%.2f\n", budget.getBalance());
        System.out.println();
    }

    public void saveBudget() {
        try {
            FileOutputStream fos = new FileOutputStream("purchases.txt");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(budget);
            System.out.println();
            System.out.println("Purchases were saved!");
            oos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Budget loadBudget() {
        try {
            FileInputStream fis = new FileInputStream("purchases.txt");
            ObjectInputStream ois = new ObjectInputStream(fis);
            Object obj = ois.readObject();
            ois.close();
            System.out.println();
            System.out.println("Purchases were loaded!");
            return (Budget) obj;
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Failed!");
        }
        return null;
    }

    public void sortAll() {
        System.out.println();
        System.out.println("All:");
        Purchase[] allPurchases = budget.getListOfPurchases().toArray(Purchase[]::new);
        for (int i = 0; i < allPurchases.length - 1; i++) {
            for (int j = 0; j < allPurchases.length - i - 1; j++) {
                if (allPurchases[j].getPrice() < allPurchases[j+1].getPrice()) {
                    Purchase temp = allPurchases[j];
                    allPurchases[j] = allPurchases[j+1];
                    allPurchases[j+1] = temp;
                }
            }
        }
        double sum = 0;
        for (Purchase p : allPurchases) {
            System.out.println(p.toString());
            sum += p.getPrice();
        }
        System.out.printf("Total: $%.2f\n", sum);
    }

    public void sortByType() {
        System.out.println();
        System.out.println("Types:");
        double foodSum = 0;
        for (Purchase p : budget.getListOfFood()) {
            foodSum += p.getPrice();
        }
        System.out.printf("Food - $%.2f\n", foodSum);

        double entertainmentSum = 0;
        for (Purchase p : budget.getListOfEntertainment()) {
            entertainmentSum += p.getPrice();
        }
        System.out.printf("Entertainment - $%.2f\n", entertainmentSum);

        double clothesSum = 0;
        for (Purchase p : budget.getListOfClothes()) {
            clothesSum += p.getPrice();
        }
        System.out.printf("Clothes - $%.2f\n", clothesSum);

        double otherSum = 0;
        for (Purchase p : budget.getListOfOther()) {
            otherSum += p.getPrice();
        }
        System.out.printf("Other - $%.2f\n", otherSum);

        System.out.printf("Total sum: $%.2f\n", foodSum + clothesSum + entertainmentSum + otherSum);
    }

    public void sortFood() {
        System.out.println();
        System.out.println("Food:");
        Purchase[] allFood = budget.getListOfFood().toArray(Purchase[]::new);
        for (int i = 0; i < allFood.length - 1; i++) {
            for (int j = 0; j < allFood.length - i - 1; j++) {
                if (allFood[j].getPrice() < allFood[j+1].getPrice()) {
                    Purchase temp = allFood[j];
                    allFood[j] = allFood[j+1];
                    allFood[j+1] = temp;
                }
            }
        }
        double sum = 0;
        for (Purchase p : allFood) {
            System.out.println(p.toString());
            sum += p.getPrice();
        }
        System.out.printf("Total sum: $%.2f\n", sum);
    }

    public void sortClothes() {
        System.out.println();
        System.out.println("Clothes:");
        Purchase[] allClothes = budget.getListOfClothes().toArray(Purchase[]::new);
        for (int i = 0; i < allClothes.length - 1; i++) {
            for (int j = 0; j < allClothes.length - i - 1; j++) {
                if (allClothes[j].getPrice() < allClothes[j+1].getPrice()) {
                    Purchase temp = allClothes[j];
                    allClothes[j] = allClothes[j+1];
                    allClothes[j+1] = temp;
                }
            }
        }
        double sum = 0;
        for (Purchase p : allClothes) {
            System.out.println(p.toString());
            sum += p.getPrice();
        }
        System.out.printf("Total sum: $%.2f\n", sum);
    }

    public void sortEntertainment() {
        System.out.println();
        System.out.println("Entertainment:");
        Purchase[] allEntertainment = budget.getListOfEntertainment().toArray(Purchase[]::new);
        for (int i = 0; i < allEntertainment.length - 1; i++) {
            for (int j = 0; j < allEntertainment.length - i - 1; j++) {
                if (allEntertainment[j].getPrice() < allEntertainment[j+1].getPrice()) {
                    Purchase temp = allEntertainment[j];
                    allEntertainment[j] = allEntertainment[j+1];
                    allEntertainment[j+1] = temp;
                }
            }
        }
        double sum = 0;
        for (Purchase p : allEntertainment) {
            System.out.println(p.toString());
            sum += p.getPrice();
        }
        System.out.printf("Total sum: $%.2f\n", sum);
    }

    public void  sortOther() {
        System.out.println();
        System.out.println("Other:");
        Purchase[] allOther = budget.getListOfOther().toArray(Purchase[]::new);
        for (int i = 0; i < allOther.length - 1; i++) {
            for (int j = 0; j < allOther.length - i - 1; j++) {
                if (allOther[j].getPrice() < allOther[j+1].getPrice()) {
                    Purchase temp = allOther[j];
                    allOther[j] = allOther[j+1];
                    allOther[j+1] = temp;
                }
            }
        }
        double sum = 0;
        for (Purchase p : allOther) {
            System.out.println(p.toString());
            sum += p.getPrice();
        }
        System.out.printf("Total sum: $%.2f\n", sum);
    }
}