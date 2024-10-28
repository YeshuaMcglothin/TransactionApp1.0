package com.pluralsight;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import static com.pluralsight.Main.*;

public class Transaction {
    public static double totalBalance = 0.0;

    // making Home method
    public static void Home() {
        // declaring variables
        boolean exit = false;

        // making home page to display, read input, and send out puts
        while (!exit) {
            System.out.println("Welcome to the VisionNet! What would you like to do please select an option below:");
            System.out.print("" +
                    " D - Add Deposit\n " +
                    "P - Make a payment\n " +
                    "L - Display ledger screen\n " +
                    "X - Exit");
            scanner.nextLine();

            String optionAnswer = scanner.nextLine();

            if (optionAnswer.equalsIgnoreCase("D")) {
                DepositIfLoop();
            } else if (optionAnswer.equalsIgnoreCase("P")) {
                MakePayment();
            } else if (optionAnswer.equalsIgnoreCase("L")) {
                ledger();
            } else if (optionAnswer.equalsIgnoreCase("X")) {
                System.out.println("You've selected the option \"Exit\"");
                exit = true;
            } else {
                System.out.println("Invalid option");
            }
        }

    }

    // Deposit loop method
    public static void DepositIfLoop() {
        boolean depositIfLoop = true;

        try {
            while (depositIfLoop) {
                // Gathering input
                System.out.println("You've selected the option \"Add Deposit\"");
                System.out.println("Please insert your card then enter your 4-digit pin:");
                int pinCode = scanner.nextInt();
                scanner.nextLine();  // Clear the buffer after reading the integer input

                System.out.println("Thank you! Please enter your deposit amount:");
                double depositAmount = scanner.nextDouble();
                scanner.nextLine();  // Clear the buffer after reading the double input

                System.out.println("Please enter vendor name");
                String vendorName = scanner.nextLine();

                totalBalance += depositAmount;
                System.out.println("You've deposited: $" + depositAmount + " To " + vendorName);
                System.out.println("Your new balance " + vendorName + " is: $" + totalBalance);

                TransactionLog("Deposit", vendorName, depositAmount );

                System.out.println("Your deposit was recorded");

                // Sending out print
                System.out.println("Would you like to return to the \"Home Screen\"?");
                System.out.println("Type 'No' to make another deposit, or 'Yes' to return to the Home Screen:");
                String optionOfReturnMenu = scanner.nextLine();  // Read user's choice

                if (optionOfReturnMenu.equalsIgnoreCase("Yes")) {
                    depositIfLoop = false;  // Exit the loop if the user selects "No"
                } else if (!optionOfReturnMenu.equalsIgnoreCase("No")) {
                    System.out.println("Invalid option. Returning to home screen.");
                    depositIfLoop = true;
                }
            }
        } catch (IOException e) {
            System.out.println("An error occurred while writing to the file.");
            e.printStackTrace();
            // Gathering input
        }
    }

    // payment method
    public static void MakePayment() {

        //variables
        boolean paymentLoop = true;
        double billAmount;
        double paymentAmount;
        String vendorName;

        while (paymentLoop) {
            try {

                System.out.println("You've selected the option \"Make a payment \"");
                System.out.println("Please enter bill amount:");
                billAmount = scanner.nextDouble();
                System.out.println("Your bill amount is : $" + billAmount);
                scanner.nextLine();

                System.out.println("Please enter vendor name");
                vendorName = scanner.nextLine();

                System.out.println("Thank you! Please enter the amount you want to pay" + " The max amount you can pay at once is: $" + billAmount);
                System.out.println("Enter payment amount here:");
                paymentAmount = scanner.nextDouble();
                System.out.println("You've entered $" + paymentAmount);
                scanner.nextLine();

                //sending outputs
                double billAmountStillDue = billAmount - paymentAmount;
                System.out.println("You've payed: $" + paymentAmount);
                System.out.println("Your new balance is: $" + billAmountStillDue);

                TransactionLog("Payment", vendorName, paymentAmount);

                System.out.println("Payment transaction has been recorded");

                System.out.println("Would you like to return to the \"Home Screen\"?");
                System.out.println("Type 'No' to make another payment, or 'Yes' to return to the Home Screen:");
                String optionOfReturnMenu = scanner.nextLine();

                if (optionOfReturnMenu.equalsIgnoreCase("Yes")) {
                    paymentLoop = false;  // Exit the loop if the user selects "No"
                } else if (!optionOfReturnMenu.equalsIgnoreCase("No")) {
                    System.out.println("Invalid option. Returning to home screen.");
                    paymentLoop = true;
                }
                // If "Yes" is selected, the loop continues for another deposit


            } catch (IOException e) {
                System.out.println("An error occurred while writing to the file.");
                e.printStackTrace();
                // Gathering input
            }

        }

    }

    //ledger
    public static void ledger() {

        System.out.println("You've selected the option \"Ledgar Screen\"");
        // declaring variables
        boolean exit = true;

        // making ledger page to display, read input, and send out puts

        while (!exit)
            System.out.println("Welcome to the VisionNet Ledger Screen! What would you like to do please select an option below:");
        System.out.print(" " +
                "A - Display all entries\n " +
                "P - Display payments\n " +
                "R - Takes you to report screen\n " +
                "X - Exit back to home screen");
        scanner.nextLine();
        String optionAnswer = scanner.nextLine();

        if (optionAnswer.equalsIgnoreCase("A")) {
            DisplayAllEntries();
        } else if (optionAnswer.equalsIgnoreCase("P")) {
            DisplayPayments();
        } else if (optionAnswer.equalsIgnoreCase("R")) {
            ReportScreen();
        } else if (optionAnswer.equalsIgnoreCase("X")) {
            exit = false;
        } else {
            System.out.println("Invalid option");
        }
    }

    // reading file then displaying file with all entries
    public static void DisplayAllEntries() {
        try (BufferedReader reader = new BufferedReader(new FileReader("transactions.csv"))) {
            String line;
            System.out.println("\n--- Deposits ---");
            System.out.println("Date|Time|Description|Vendor|Amount");

            while ((line = reader.readLine()) != null) {
                if (line.contains("Deposit")) {
                    System.out.println(line);
                }
            }
        } catch (IOException e) {
            System.out.println("An error occurred while reading the transactions file.");
            e.printStackTrace();
        }
        ledger();
    }

    // reading input gathering file payments then displaying same code as all entries just moved around
    public static void DisplayPayments() {
        try (BufferedReader reader = new BufferedReader(new FileReader("transactions.csv"))) {
            String line;
            System.out.println("\n--- Payments ---");
            System.out.println("Date|Time|Description|Vendor|Amount");

            while ((line = reader.readLine()) != null) {
                if (line.contains("Payment")) {
                    System.out.println(line);
                }
            }
        } catch (IOException e) {
            System.out.println("An error occurred while reading the transactions file.");
            e.printStackTrace();
        }
        ledger();
    }

    public static void ReportScreen() {
        boolean exit = true;

        while (!exit)
            System.out.println("Welcome to the VisionNet Report Screen! What would you like to do please select an option below:");
        System.out.print(" " +
                "1 - Search Month-To-Date\n " +
                "2 - Search previous month\n " +
                "3 - Search Year-To-Date\n " +
                "4 - Search previous year\n " +
                "5 - Search by vendor\n " +
                "6- Return to home menu\n ");
        String reportAnswer = scanner.nextLine();

        if (reportAnswer.equals("1")) {
            MonthToDate();
        } else if (reportAnswer.equals("2")) {
            PreviousMonth();
        } else if (reportAnswer.equals("3")) {
            YearToDate();
        } else if (reportAnswer.equals("4")) {
            PreviousYear();
        } else if (reportAnswer.equals("5")) {
            ByVendor();
        } else if (reportAnswer.equals("6")) {
            System.out.println("You've selected the option \"Exit\"");
            exit = false;
        } else {
            System.out.println("Invalid input");
        }

    }

    public static void MonthToDate() {
        LocalDate now = LocalDate.now();
        int currentMonth = now.getMonthValue();
        int currentYear = now.getYear();

        System.out.println("\n--- Month transactions ---");
        System.out.println("Date|Time|Description|Vendor|Amount");

        try (BufferedReader reader = new BufferedReader(new FileReader("transactions.csv"))) {
            String line;

            while ((line = reader.readLine()) != null) {
                String[] splits = line.split("\\|");
                LocalDate transactionMonth = LocalDate.parse(splits[0]);
                if (transactionMonth.getMonthValue() == currentMonth && transactionMonth.getYear() == currentYear) {
                    System.out.println(line);
                }
            }

        } catch (IOException e) {
            System.out.println("An error while reading transaction");
            e.printStackTrace();
        }
        ReportScreen();
    }

    public static void PreviousMonth() {
        LocalDate now = LocalDate.now();
        LocalDate previousMonth = now.minusMonths(1);
        int previousMonthValue = previousMonth.getMonthValue();
        int previousYear = previousMonth.getYear();

        System.out.println("\n--- Previous Month transactions ---");
        System.out.println("Date|Time|Description|Vendor|Amount");


        try (BufferedReader reader = new BufferedReader(new FileReader("transactions.csv"))) {
            String line;

            while ((line = reader.readLine()) != null) {
                String[] splits = line.split("\\|");
                LocalDate transactionMonth = LocalDate.parse(splits[0]);
                if (transactionMonth.getMonthValue() == previousMonthValue && transactionMonth.getYear() == previousYear) {
                    System.out.println(line);
                }
            }

        } catch (IOException e) {
            System.out.println("An error while reading transaction");
            e.printStackTrace();
        }
        ReportScreen();
    }

    public static void YearToDate() {
        int currentYear = LocalDate.now().getYear();

        System.out.println("\n--- This Year transactions ---");
        System.out.println("Date|Time|Description|Vendor|Amount");


        try (BufferedReader reader = new BufferedReader(new FileReader("transactions.csv"))) {
            String line;

            while ((line = reader.readLine()) != null) {
                String[] splits = line.split("\\|");
                LocalDate transactionYear = LocalDate.parse(splits[0]);
                if (transactionYear.getYear() == currentYear) {
                    System.out.println(line);
                }
            }

        } catch (IOException e) {
            System.out.println("An error while reading transaction");
            e.printStackTrace();
        }
        ReportScreen();
    }

    public static void PreviousYear() {
        int previousYear = LocalDate.now().minusYears(1).getYear();


        System.out.println("\n--- Previous Year transactions ---");
        System.out.println("Date|Time|Description|Vendor|Amount");

        try (BufferedReader reader = new BufferedReader(new FileReader("transactions.csv"))) {
            String line;

            while ((line = reader.readLine()) != null) {
                String[] splits = line.split("\\|");
                LocalDate transactionYear = LocalDate.parse(splits[0]);

                if (transactionYear.getYear() == previousYear) {
                    System.out.println(line);

                }
            }

        } catch (IOException e) {
            System.out.println("An error while reading transaction");
            e.printStackTrace();
        }
        ReportScreen();
    }

    public static void ByVendor() {
        System.out.println("Search by Vendor name" + " Please enter vendor name:");
        String vendorName = scanner.nextLine();

        System.out.println("\n--- transactions ---" );
        System.out.println(vendorName + " Transactions");

        try (BufferedReader reader = new BufferedReader(new FileReader("transactions.csv"))) {
            String line;

            while ((line = reader.readLine()) != null) {
                String[] splits = line.split("\\|");
                if (splits[3].equalsIgnoreCase(vendorName)) {
                    System.out.println(line);
                }
            }

        } catch (IOException e) {
            System.out.println("An error while reading transaction");
            e.printStackTrace();
        }
        ReportScreen();
    }

    public static void TransactionLog(String Type, String vendorName, double amount) throws IOException {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd|HH:mm:ss");

        // Writing to the CSV file (transactions.csv)
        FileWriter writer = new FileWriter("transactions.csv", true);
        writer.write(now.format(formatter) + "|" + Type + "|" + vendorName + "| " + amount + "\n");

    }
}





