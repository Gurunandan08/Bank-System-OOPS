import java.util.ArrayList;
import java.util.Scanner;

// Class representing a Bank Customer
class Customer {
    String name;
    String accountNumber;
    double balance;
    String address;
    String phoneNumber;

    public Customer(String name, String accountNumber, String address, String phoneNumber) {
        this.name = name;
        this.accountNumber = accountNumber;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.balance = 0;
    }

    public void deposit(double amount) {
        if (amount > 10000) { // Deposit limit
            System.out.println("Deposit limit exceeded! Maximum deposit allowed is 10000.");
        } else {
            balance += amount;
            System.out.println("Successfully deposited " + amount + ". Current balance: " + balance);
        }
    }

    public void withdraw(double amount) {
        if (amount > balance) {
            System.out.println("Insufficient balance! Your current balance is: " + balance);
        } else {
            balance -= amount;
            System.out.println("Successfully withdrew " + amount + ". Current balance: " + balance);
        }
    }

    public void displayProfile() {
        System.out.println("Customer Name: " + name);
        System.out.println("Account Number: " + accountNumber);
        System.out.println("Address: " + address);
        System.out.println("Phone Number: " + phoneNumber);
        System.out.println("Balance: " + balance);
    }
}

// Class representing a Bank Employee
class Employee {
    String employeeID;
    String name;
    String position;

    public Employee(String employeeID, String name, String position) {
        this.employeeID = employeeID;
        this.name = name;
        this.position = position;
    }

    public void displayProfile() {
        System.out.println("Employee ID: " + employeeID);
        System.out.println("Employee Name: " + name);
        System.out.println("Position: " + position);
    }
}

// Class representing Admin operations
class Admin {
    ArrayList<Customer> customers;
    ArrayList<Employee> employees;

    public Admin(ArrayList<Customer> customers, ArrayList<Employee> employees) {
        this.customers = customers;
        this.employees = employees;
    }

    public void viewCustomerProfiles() {
        for (Customer customer : customers) {
            customer.displayProfile();
            System.out.println("----------------------");
        }
    }

    public void viewEmployeeProfiles() {
        for (Employee employee : employees) {
            employee.displayProfile();
            System.out.println("----------------------");
        }
    }
}

public class BankSystem {
    static ArrayList<Customer> customers = new ArrayList<>();
    static ArrayList<Employee> employees = new ArrayList<>();
    static Scanner sc = new Scanner(System.in);

    // Customer registration
    public static void customerRegistration() {
        System.out.print("Enter Name: ");
        String name = sc.nextLine();
        System.out.print("Enter Account Number: ");
        String accountNumber = sc.nextLine();
        System.out.print("Enter Address: ");
        String address = sc.nextLine();
        System.out.print("Enter Phone Number: ");
        String phoneNumber = sc.nextLine();

        Customer customer = new Customer(name, accountNumber, address, phoneNumber);
        customers.add(customer);
        System.out.println("Registration successful!");
    }

    // Customer login
    public static void customerLogin() {
        System.out.print("Enter Account Number: ");
        String accountNumber = sc.nextLine();
        Customer customer = findCustomer(accountNumber);

        if (customer != null) {
            System.out.println("Login successful!");
            customerPortal(customer);
        } else {
            System.out.println("Customer not found.");
        }
    }

    // Customer portal
    public static void customerPortal(Customer customer) {
        int choice;
        do {
            System.out.println("1. Deposit");
            System.out.println("2. Withdraw");
            System.out.println("3. View Profile");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");
            choice = sc.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Enter amount to deposit: ");
                    double depositAmount = sc.nextDouble();
                    customer.deposit(depositAmount);
                    break;
                case 2:
                    System.out.print("Enter amount to withdraw: ");
                    double withdrawAmount = sc.nextDouble();
                    customer.withdraw(withdrawAmount);
                    break;
                case 3:
                    customer.displayProfile();
                    break;
                case 4:
                    System.out.println("Exiting customer portal...");
                    break;
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        } while (choice != 4);
    }

    // Employee login
    public static void employeeLogin() {
        System.out.print("Enter Employee ID: ");
        String employeeID = sc.nextLine();
        Employee employee = findEmployee(employeeID);

        if (employee != null) {
            System.out.println("Login successful!");
            employee.displayProfile();
        } else {
            System.out.println("Employee not found.");
        }
    }

    // Admin login
    public static void adminLogin() {
        Admin admin = new Admin(customers, employees);
        int choice;
        do {
            System.out.println("1. View Customer Profiles");
            System.out.println("2. View Employee Profiles");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");
            choice = sc.nextInt();

            switch (choice) {
                case 1:
                    admin.viewCustomerProfiles();
                    break;
                case 2:
                    admin.viewEmployeeProfiles();
                    break;
                case 3:
                    System.out.println("Exiting admin portal...");
                    break;
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        } while (choice != 3);
    }

    // Helper function to find a customer by account number
    public static Customer findCustomer(String accountNumber) {
        for (Customer customer : customers) {
            if (customer.accountNumber.equals(accountNumber)) {
                return customer;
            }
        }
        return null;
    }

    // Helper function to find an employee by employee ID
    public static Employee findEmployee(String employeeID) {
        for (Employee employee : employees) {
            if (employee.employeeID.equals(employeeID)) {
                return employee;
            }
        }
        return null;
    }

    public static void main(String[] args) {
        employees.add(new Employee("E001", "John Doe", "Manager"));
        employees.add(new Employee("E002", "Jane Smith", "Teller"));

        int choice;
        do {
            System.out.println("1. Customer Registration");
            System.out.println("2. Customer Login");
            System.out.println("3. Employee Login");
            System.out.println("4. Admin Login");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            choice = sc.nextInt();
            sc.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    customerRegistration();
                    break;
                case 2:
                    customerLogin();
                    break;
                case 3:
                    employeeLogin();
                    break;
                case 4:
                    adminLogin();
                    break;
                case 5:
                    System.out.println("Exiting system...");
                    break;
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        } while (choice != 5);
    }
}
