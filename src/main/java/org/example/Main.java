package org.example;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    private static final String ERR_MSG = "Invalid ";
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEEE MMMM dd, yyyy HH:mm:ss");
    private static final Scanner scanner = new Scanner(System.in);

    private static final String YEAR_MSG = "year! Please enter YYYY." ;
    private static final String MONTH_MSG = "month! Please enter MM(1-12).";
    private static final String DAY_MSG = "day! Please enter DD(1-31).";

    public static void main(String[] args) {
        LocalDateTime today =getTodayDate();
        System.out.println(formatter.format(today));
        System.out.println(calculatePeriod(today, getBirthDate()));
    }


    private static int getValidDate(String value, int length) {
        int output = isNumeric(value);
        while (output > length) {
            System.out.println(ERR_MSG + value);
            output = isNumeric(value);
        }
        return output;
    }

    public static String calculatePeriod(LocalDateTime from, LocalDateTime to) {
        System.out.println("Enter your name:");
        scanner.nextLine();
        String name = scanner.nextLine().trim();

        Period period = Period.between(to.toLocalDate(), from.toLocalDate());
        System.out.println("BirthDate: "+formatter.format(to));
        System.out.println(period);
        int years = period.getYears();
        int months = period.getMonths();
        int days = period.getDays();

        return String.format("Hi %s!! You have been living on earth for %d years, %d months, and %d days.",
                name, years, months, days);
    }

    public static LocalDateTime getTodayDate() {
        return LocalDateTime.now();
    }

    public static LocalDateTime getBirthDate() {
        System.out.println("Enter your year of birth");
        int year = getValidDate(YEAR_MSG, LocalDateTime.now().getYear());
        System.out.println("Enter your month of birth");
        int month = getValidDate(MONTH_MSG, 12);
        System.out.println("Enter your day of birth");
        int day = getValidDate(DAY_MSG, 31);

        String YY = String.format("%04d-", year);
        String MM = String.format("%02d-", month);
        String DD = String.format("%02d", day);

        return LocalDateTime.parse(YY + MM +   DD + "T07:00:00");

    }

    private static int isNumeric(String value) {
        while (!scanner.hasNextInt()) {
            System.out.println(ERR_MSG + value);
            scanner.next();
        }
        return scanner.nextInt();
    }
}