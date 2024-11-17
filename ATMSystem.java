import java.util.ArrayList;
import java.util.Scanner;

class ATM {
    private double balance;
    private ArrayList<String> miniStatement;
    private int pin;

    public ATM(double initialBalance, int pin) {
        this.balance = initialBalance;
        this.miniStatement = new ArrayList<>();
        this.pin = pin;
    }

    public boolean authenticate(int inputPin) {
        return inputPin == pin;
    }

    public void viewBalance() {
        System.out.println("Your Current Balance is : " + balance);
    }

    public boolean withdraw(double amount) {
        if (amount <= balance) {
            balance -= amount;
            System.out.println("Collect the Cash " + amount);
            miniStatement.add("Withdraw: $" + amount);
            return true;
        } else {
            System.out.println("Insufficient Balance.");
            return false;
        }
    }

    public void deposit(double amount) {
        balance += amount;
        System.out.println("Successfully Deposited: $" + amount);
        miniStatement.add("Deposit: $" + amount);
    }

    public void viewMiniStatement() {
        System.out.println("Mini Statement:");
        if (miniStatement.isEmpty()) {
            System.out.println("No transactions yet.");
        } else {
            for (String transaction : miniStatement) {
                System.out.println(transaction);
            }
        }
    }

    public void loadBalance() {
        System.out.println("\nLoading Account Balance.......");
        System.out.println("Your Current Balance is : " + balance);
    }
}

public class ATMSystem {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ATM atmAccount = new ATM(10000.0, 123);  

        boolean exit = false;

        while (!exit) {
            System.out.println("\n1.View Available Balance");
            System.out.println("2.Withdraw Amount");
            System.out.println("3.Deposit Amount");
            System.out.println("4.View Mini Statement");
            System.out.println("5.Exit");

            System.out.print("\nEnter Choice : ");
            int choice = sc.nextInt();

            switch (choice) {
                case 1:
                    atmAccount.viewBalance();
                    break;

                case 2:
                    System.out.print("Enter ATM Pin: ");
                    int inputPin = sc.nextInt();

                    if (atmAccount.authenticate(inputPin)) {
                        System.out.println("Account Authorized!");

                        System.out.print("\nEnter amount to withdraw: ");
                        double withdrawAmount = sc.nextDouble();

                        System.out.print("Confirm? Y/N: ");
                        char confirmWithdraw = sc.next().charAt(0);

                        if (confirmWithdraw == 'Y' || confirmWithdraw == 'y') {
                            boolean success = atmAccount.withdraw(withdrawAmount);
                            if (success) {
                                atmAccount.loadBalance();
                            }
                        } else {
                            System.out.println("Transaction Cancelled.");
                        }
                    } else {
                        System.out.println("Invalid PIN.");
                    }
                    break;

                case 3:
                    System.out.print("Enter amount to deposit: ");
                    double depositAmount = sc.nextDouble();

                    System.out.print("Confirm? Y/N: ");
                    char confirmDeposit = sc.next().charAt(0);

                    if (confirmDeposit == 'Y' || confirmDeposit == 'y') {
                        atmAccount.deposit(depositAmount);
                        atmAccount.loadBalance();
                    } else {
                        System.out.println("Transaction Cancelled.");
                    }
                    break;

                case 4:
                    atmAccount.viewMiniStatement();
                    break;

                case 5:
                    exit = true;
                    System.out.println("Exiting. Thank you for using the ATM.");
                    break;

                default:
                    System.out.println("Invalid Choice. Please try again.");
                    break;
            }
        }
        sc.close();
    }
}