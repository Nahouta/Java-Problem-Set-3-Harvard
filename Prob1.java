//Prob1.java

/**
 * This class is a program that will output all of its command-line arguments, one per line, in reverse order.
 * 
 * @author Tresor Habib Nahouta
 * @version Last modified March 17th, 2024
 */


public class Prob1{


    public static void main (String [] args) {
        /*We start by handling the case where no arguments are provided */
        if (args.length == 0) {
            System.out.println ("No arguments were provided to the program");
        }
        /*If some arguments are provided, we print them in reverse order */
        else {
            System.out.println ("The " + args.length + " command-line args are:");
            for (int i = args.length - 1; i >= 0; i-- ) {
                System.out.println (args[i]);
            }
        }
    }
}