package com.pluralsight;
import java.util.Scanner;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileReader;

public class Main {
    static Scanner scanner = new Scanner(System.in);

    String dateOfTransaction;
    String timeOfTransaction;
    String infoOfTransaction;
    String nameOfVendor = "VisionNet";
    public static double amountOfTransaction;
    double paymentOfTransaction;
    public static double totalBalance;

    public static void main(String[] args) {

       //Calling to main

        Home();




    }

    // making Home method
    public static void Home() {

        // declaring variables
        boolean exit = true;

        // making home page to display, read input, and send out puts

        while (!exit)
            System.out.println("Welcome to the VisionNet! What would you like to do please select an option below:");
        System.out.print("" +
                " D - Add Deposit\n " +
                "P - Make a payment\n " +
                "L - Display ledger screen\n " +
                "x - Exit");
        scanner.nextLine();

        String optionAnswer = scanner.nextLine();

        if (optionAnswer.equalsIgnoreCase("D")) {
            DepositIfLoop();

        } else if (optionAnswer.equalsIgnoreCase("P")) {


        } else if (optionAnswer.equalsIgnoreCase("L")) {
            System.out.println("You've selected the option \"Ledgar Screen\"");

        } else if (optionAnswer.equalsIgnoreCase("X")) {
            System.out.println("You've selected the option \"Exit\"");
            exit = true;

        } else {
            System.out.println("Invalid option");

        }


    }

    // Deposit loop method
    public static void DepositIfLoop() {
        boolean depositIfLoop = true;

        while (depositIfLoop) {
            // Gathering input
            System.out.println("You've selected the option \"Add Deposit\"");
            System.out.println("Please insert your card then enter your 4-digit pin:");
            int pinCode = scanner.nextInt();
            scanner.nextLine();  // Clear the buffer after reading the integer input

            System.out.println("Thank you! Please enter your deposit amount:");
            double amountOfTransaction = scanner.nextDouble();
            scanner.nextLine();  // Clear the buffer after reading the double input

            totalBalance += amountOfTransaction;
            System.out.println("You've deposited: $" + amountOfTransaction);
            System.out.println("Your new balance is: $" + totalBalance);

            // Sending out print
            System.out.println("You've deposited: $" + amountOfTransaction);
            System.out.println("Would you like to make another deposit or return to the \"Home Screen\"?");
            System.out.println("Type 'Yes' to make another deposit, or 'No' to return to the Home Screen:");
            String optionOfReturnMenu = scanner.nextLine();  // Read user's choice

            if (optionOfReturnMenu.equalsIgnoreCase("Yes")) {
                depositIfLoop = false;  // Exit the loop if the user selects "No"
            } else if (!optionOfReturnMenu.equalsIgnoreCase("No")) {
                System.out.println("Invalid option. Returning to home screen.");
                depositIfLoop = true;  // Exit if the input is invalid
            }
            // If "Yes" is selected, the loop continues for another deposit.
        }
    }

    // payment method
    public static void MakePayment(){

       //variables
        boolean paymentLoop = true;
        double billAmount;


        System.out.println("You've selected the option \"Make a payment\"");
        System.out.println("Please enter bill amount:");
        billAmount = scanner.nextInt();
        scanner.nextLine();




    }
}