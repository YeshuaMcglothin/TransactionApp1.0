package com.pluralsight;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

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
        Transaction.Home();


    }


    }
