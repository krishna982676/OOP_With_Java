package com.bank;

import com.bank.accounts.SavingsAccount;
import com.bank.customers.Customer;
import com.bank.loans.Loan;
import com.bank.exceptions.InsufficientBalanceException;

import static com.bank.util.BankUtil.generateAccountNumber;

public class BankApplication {

    public static void main(String[] args) {

        try {

            Customer customer = new Customer("C101", "Krishna");

            String accNo = generateAccountNumber();

            SavingsAccount account =
                    new SavingsAccount(accNo, 5000, 5);

            customer.linkAccount(account);

            account.deposit(2000);

            account.withdraw(1000);

            double interest = account.calculateInterest();

            System.out.println("Interest: " + interest);

            Loan loan = new Loan(50000, 5, 2);

            double emi = loan.calculateEMI();

            System.out.println("Loan EMI: " + emi);

            customer.displayCustomerDetails();

        }

        catch (InsufficientBalanceException e) {
            System.out.println(e.getMessage());
        }

        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}