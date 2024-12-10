//Prob4.java

/**
 * This program class will ask the user to input 2 integers and then divides them.
 * If the user types a non integer value, or if we end up with a division by zero, appropriate exception will
 * be caught, and the user will be prompted to enter new data
 @author Tresor Habib Nahouta
 @version Last Modified March 17th, 2024
 */


import java.util.*;

public class Prob4 {

    public static void main (String [] args) {
        
        Scanner keyboard = new Scanner (System.in);
        int n1, n2;

        /*We create a variable to indicate whether or not the input was valid and no exception was caugth */
        boolean isValidInput = true;

        do {
            try {
                System.out.print("Type an int: ");
                n1 = keyboard.nextInt();
                System.out.print("Now type another int: ");
                n2 = keyboard.nextInt();
                int r = n1 / n2;
    
            }
            catch (InputMismatchException e) {
                /*If the user enters the wrong input, we catch the exception and consume the invalid input */
                System.out.println ("Integer not found. Please, enter only integer values. \nPlease try again");
                keyboard.next();
                isValidInput = false;
            }
            catch (ArithmeticException e) {
                /*If there is a division by zero, we catch the exception */
                System.out.println("Division by zero. Please, enter a non zero int as the second integer. \nPlease try again");
                isValidInput = false;
            }
        }
        
        while (!isValidInput);
    }

}
    