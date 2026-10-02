package ca.hccis.util;

import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Random;
import java.util.Scanner;

/**
 * Has some useful methods to be used in our programs.
 *
 * @author bjmaclean
 * @since Oct 19, 2021
 */
public class CisUtility {

    private static Scanner input = new Scanner(System.in);

    /**
     * Return the default currency String value of the double passed in as a
     * parameter.
     *
     * @param inputDouble double to be formatted
     * @return String in default currency format
     *
     * @since 20211020
     * @author BJM
     */
    public static String toCurrency(double inputDouble) {
        NumberFormat formatter = NumberFormat.getCurrencyInstance();
        return formatter.format(inputDouble);
    }

    /**
     * Get input from the user using the console
     *
     * @param prompt Prompt for the user
     * @return String entered by the user
     * @since 20211020
     * @author BJM
     */
    public static String getInputString(String prompt) {

        System.out.println(prompt + " -->");
        String output = input.nextLine().trim();
        return output;
    }

    /**
     * Verifies String from user is not empty
     *
     * @param prompt Prompt for the user
     * @return Non-empty String entered by the user
     * @since 20260924
     * @author Jake Henderson
     */
    public static String getValidatedRequiredString(String prompt) {
        while (true) {
            String input = getInputString(prompt);
            if (!input.isEmpty()) {
                return input;
            }
            System.out.println("Error: This field cannot be left blank");
        }
    }

    /**
     * Verifies that a date is in YYYY-MM-DD format and not in the future
     *
     * @param prompt Prompt for the user
     * @return String entered by the user
     * @since 20260924
     * @author Jake Henderson
     */
    public static String getValidatedDateString(String prompt) {
        while (true) {
            String input = getInputString(prompt + " (YYYY-MM-DD): ");
            try {
                LocalDate parsedDate = LocalDate.parse(input);
                if (parsedDate.isAfter(LocalDate.now())) {
                    System.out.println("Error: The date you entered cannot be in the future");
                } else {
                    return input;
                }
            } catch (DateTimeParseException e) {
                System.out.println("Invalid format. Please enter a real date strictly using  YYYY-MM-DD");
            }
        }
    }

    /**
     * Validates that a double is a positive decimal strictly greater than zero
     *
     * @param prompt Prompt for the user
     * @return The double entered by the user
     * @since 20260924
     * @author Jake Henderson
     */
    public static double getValidatedPositiveDouble(String prompt) {
        while (true) {
            try {
                System.out.println(prompt);
                double amount = Double.parseDouble(input.nextLine().trim());
                if (amount > 0) {
                    return amount;
                } else  {
                    System.out.println("Error: The amount you entered cannot be less than zero");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid format. Please enter a real amount");
            }
        }
    }

    /**
     * Get input from the user using the console
     *
     * @param prompt Prompt for the user
     * @return String entered by the user
     * @since 20211020
     * @author BJM
     */
    public static String getInputString(String prompt, int minLength, int maxLength) {

        System.out.println(prompt+ " ("+minLength+" to "+ maxLength+" characters" + " -->");
        String output = input.nextLine();

        while(output.length() < minLength || output.length() > maxLength) {
            System.out.println(prompt + " -->");
            output = input.nextLine();
        }

        return output;
    }

    /**
     * Get input Boolean from the user using the console and validate it
     *
     * @param prompt Prompt for the user
     * @return Boolean entered by the user
     * @since 20260924
     * @author Jake Henderson
     */
    public static boolean getValidatedInputBoolean(String prompt) {
        while (true) {
                System.out.println(prompt + " (y/n): ");
                String yesOrNo = input.nextLine().trim().toLowerCase();
                if (yesOrNo.equals("y") || yesOrNo.equals("yes") || yesOrNo.equals("true")) {
                    return true;
                } else if (yesOrNo.equals("n") || yesOrNo.equals("no") || yesOrNo.equals("false")) {
                    return false;
                } else {
                    System.out.println("Invalid input. Please enter a 'y' or 'n'.");
                }
        }
    }

    /**
     * Get input int from the user using the console and handles non-numeric text input without crashing
     *
     * @param prompt Prompt for the user
     * @return The int entered by the user
     * @since 20260924
     * @author Jake Henderson
     */
    public static int getValidatedInteger(String prompt) {
        while (true) {
            try {
                System.out.println(prompt);
                return Integer.parseInt(input.nextLine().trim());
            }  catch (NumberFormatException e) {
                System.out.println("Error: Please enter a number.");
            }
        }
    }

    /**
     * Get input from the user using the console
     *
     * @param prompt Prompt for the user
     * @return The double entered by the user
     * @since 20211020
     * @author BJM
     */
    public static double getInputDouble(String prompt) {

        String inputString = getInputString(prompt);
        double output = Double.parseDouble(inputString);
        return output;
    }

    /**
     * Get input from the user using the console
     *
     * @param prompt Prompt for the user
     * @return The double entered by the user
     * @since 20211020
     * @author BJM
     */
    public static int getInputInt(String prompt) {

        String inputString = getInputString(prompt);
        int output = Integer.parseInt(inputString);
        return output;
    }

     /**
     * Get input boolean from the user using the console
     *
     * @param prompt Prompt for the user
     * @return boolean as specified by user input
     * @since 20211108
     * @author BJM
     */
    public static boolean getInputBoolean(String prompt) {

        String inputString = getInputString(prompt+" (y/n)");
        if(inputString.equalsIgnoreCase("y")){
            return true;
        }else{
            return false;
        }
        
    }

     /**
     * Get input boolean from the user using the console
     *
     * @param prompt Prompt for the user
     * @return boolean as specified by user input
     * @since 20211108
     * @author BJM
     */
    public static boolean getInputBoolean(String prompt, String affirmative, String negative) {

        String inputString = getInputString(prompt+" ("+affirmative+"/"+negative+")");
        if(inputString.equalsIgnoreCase(affirmative)){
            return true;
        }else{
            return false;
        }
        
    }


    
    /**
     * Provide today's date in the specified format
     *
     * @param format Date format desired
     * @return Today's date in specified format
     * @since 20211021
     * @author BJM
     */
    public static String getTodayString(String format) {
        //https://www.javatpoint.com/java-get-current-date

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern(format);
        LocalDateTime now = LocalDateTime.now();
        return dtf.format(now);

    }

        /**
     * Get a random number between min and max
     * @since 20211109
     * @author BJM
     */
    public static int getRandom(int min, int max){
        Random rand = new Random();
        int theRandomNumber = rand.nextInt((max - min) + 1) + min;
        return theRandomNumber;
    }
    
    
}
