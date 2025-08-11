import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class BankAccount {
    private String accountHolder;
    private double balance;
    private List<String> transactionHistory;

    public BankAccount(String accountHolder, double initialBalance) {
        this.accountHolder = accountHolder;
        this.balance = initialBalance;
        this.transactionHistory = new ArrayList<>();
        transactionHistory.add("Account opened with balance: ₹" + initialBalance);
    }

    public String getAccountHolder() {
        return accountHolder;
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            transactionHistory.add("Deposited: ₹" + amount + " | New Balance: ₹" + balance);
        } else {
            System.out.println("Deposit amount must be positive!");
        }
    }

    public void withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            transactionHistory.add("Withdrew: ₹" + amount + " | New Balance: ₹" + balance);
        } else {
            System.out.println("Invalid withdrawal amount or insufficient funds!");
        }
    }

    public void transfer(BankAccount recipient, double amount) {
        if (amount > 0 && amount <= balance) {
            this.withdraw(amount);
            recipient.deposit(amount);
            transactionHistory.add("Transferred ₹" + amount + " to " + recipient.getAccountHolder());
        } else {
            System.out.println("Invalid transfer amount or insufficient funds!");
        }
    }

    public void printTransactionHistory() {
        System.out.println("\nTransaction History for " + accountHolder + ":");
        for (String transaction : transactionHistory) {
            System.out.println(transaction);
        }
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Create sample accounts
        BankAccount account1 = new BankAccount("Alice", 5000);
        BankAccount account2 = new BankAccount("Bob", 3000);

        BankAccount currentAccount = account1; 

        while (true) {
            System.out.println("\nWelcome, " + currentAccount.getAccountHolder());
            System.out.println("1. Check Balance");
            System.out.println("2. Deposit");
            System.out.println("3. Withdraw");
            System.out.println("4. Transfer");
            System.out.println("5. Transaction History");
            System.out.println("6. Switch Account");
            System.out.println("7. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.println("Current Balance: ₹" + currentAccount.getBalance());
                    break;

                case 2:
                    System.out.print("Enter deposit amount: ₹");
                    double depositAmount = scanner.nextDouble();
                    currentAccount.deposit(depositAmount);
                    break;

                case 3:
                    System.out.print("Enter withdrawal amount: ₹");
                    double withdrawAmount = scanner.nextDouble();
                    currentAccount.withdraw(withdrawAmount);
                    break;

                case 4:
                    System.out.print("Enter transfer amount: ₹");
                    double transferAmount = scanner.nextDouble();
                    BankAccount recipient = (currentAccount == account1) ? account2 : account1;
                    currentAccount.transfer(recipient, transferAmount);
                    break;

                case 5:
                    currentAccount.printTransactionHistory();
                    break;

                case 6:
                    currentAccount = (currentAccount == account1) ? account2 : account1;
                    System.out.println("Switched to " + currentAccount.getAccountHolder());
                    break;

                case 7:
                    System.out.println("Exiting...");
                    scanner.close();
                    return;

                default:
                    System.out.println("Invalid choice! Please try again.");
            }
        }
    }
}
