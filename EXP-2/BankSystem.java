import java.util.*;

class BankException extends Exception {
    BankException(String msg) {
        super(msg);
    }
}

class Account {
    int accNo;
    double balance;

    Account(int accNo, double balance) {
        this.accNo = accNo;
        this.balance = balance;
    }

    void deposit(double amt) throws BankException {
        if (amt <= 0)
            throw new BankException("Invalid deposit amount!");
        balance += amt;
        System.out.println("Deposit Successful!");
    }

    void withdraw(double amt) throws BankException {
        if (amt <= 0)
            throw new BankException("Invalid withdraw amount!");
        if (balance < amt)
            throw new BankException("Insufficient balance!");
        balance -= amt;
        System.out.println("Withdraw Successful!");
    }
}

class SavingsAccount extends Account {
    SavingsAccount(int accNo, double balance) {
        super(accNo, balance);
    }

    void checkBalance() {
        System.out.println("Balance: " + balance);
    }
}

class CurrentAccount extends Account {
    CurrentAccount(int accNo, double balance) {
        super(accNo, balance);
    }

    void transfer(Account to, double amt) throws BankException {
        if (amt <= 0)
            throw new BankException("Invalid transfer amount!");
        if (balance < amt)
            throw new BankException("Insufficient balance!");

        this.balance -= amt;
        to.balance += amt;
        System.out.println("Transfer Successful!");
    }
}

public class BankSystem {

    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

        SavingsAccount acc1 = new SavingsAccount(101, 5000);
        CurrentAccount acc2 = new CurrentAccount(102, 3000);

        while (true) {

            System.out.println("\n--- BANK MENU ---");
            System.out.println("1 Deposit");
            System.out.println("2 Withdraw");
            System.out.println("3 Check Balance");
            System.out.println("4 Transfer");
            System.out.println("5 Exit");
            System.out.print("Choose an option: ");

            try {
                int ch = sc.nextInt();

                switch (ch) {

                    case 1:
                        System.out.print("Enter amount: ");
                        acc1.deposit(sc.nextDouble());
                        break;

                    case 2:
                        System.out.print("Enter amount: ");
                        acc1.withdraw(sc.nextDouble());
                        break;

                    case 3:
                        acc1.checkBalance();
                        break;

                    case 4:
                        System.out.print("Enter amount: ");
                        acc2.transfer(acc1, sc.nextDouble());
                        break;

                    case 5:
                        System.out.println("Thank you!");
                        return;

                    default:
                        throw new BankException("Invalid menu choice!");
                }

            } 
            catch (BankException e) {
                System.out.println("Bank Error: " + e.getMessage());
            } 
            catch (InputMismatchException e) {
                System.out.println("Input must be numeric!");
                sc.nextLine();
            } 
            finally {
                System.out.println("Transaction attempt finished.\n");
            }
        }
    }
}
