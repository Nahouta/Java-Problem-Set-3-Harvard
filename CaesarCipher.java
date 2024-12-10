//CaesarCipher.java


/**
 * This class will encipher or decipher data provided over a file and output the results on a specified file
 * 
 * @author Tresor Habib Nahouta
 * @version Last modified March 21, 2024

 */


import java.util.*;
import java.io.*;


public class CaesarCipher {

    /**
     * This method will return the basic unshifted alphabet as an array of char, to be used by other methods in the programs
     * @return the unshifter alphabet as an array of chars
     */
    private static char [] baseAlphabet () {

        char [] alphabet = new char[26];
        alphabet[0] = 'A';
        alphabet[1] = 'B';
        alphabet[2] = 'C';
        alphabet[3] = 'D';
        alphabet[4] = 'E';
        alphabet[5] = 'F';
        alphabet[6] = 'G';
        alphabet[7] = 'H';
        alphabet[8] = 'I';
        alphabet[9] = 'J';
        alphabet[10] = 'K';
        alphabet[11] = 'L';
        alphabet[12] = 'M';
        alphabet[13] = 'N';
        alphabet[14] = 'O';
        alphabet[15] = 'P';
        alphabet[16] = 'Q';
        alphabet[17] = 'R';
        alphabet[18] = 'S';
        alphabet[19] = 'T';
        alphabet[20] = 'U';
        alphabet[21] = 'V';
        alphabet[22] = 'W';
        alphabet[23] = 'X';
        alphabet[24] = 'Y';
        alphabet[25] = 'Z';

        return alphabet;

    }


    /**
     * This method take a character as input and return the original order index that it occupies in the unshifted alphabet array,
     * or returns -1 if it is not an UPPERCASE alphabetical character.
     * 
     * 
     * @param c the character whose index we are looking for
     * @return  the index of that character in the original alphabetical array
     */
    private static int indexInBaseAlphabet (char c) {

        char [] alphabet = baseAlphabet();

        for (int i = 0; i < alphabet.length; i++) {
            if (c == alphabet [i]) {
                return i;
            }
        }
        return -1;
    }



    /**
     * This method will return an alphabet that has been shifted to an integer value shift, 
     * as described in the original problem
     * 
     * @param shift the amount of shift that the alphabet must undergo
     * @return
     */

    private static char [] shiftedAlphabet (int shift) {

        if (shift > 25 || shift < -25) {
            shift = shift % 26;
        }

        if (shift < 0) {
            shift =  shift + 26;
        }

        char [] alphabet = baseAlphabet();
        char [] shifted = new char [26];
        
        for (int i = 0; i < 26; i++) {
            shifted[ i ] = alphabet [ (i + shift) % 26 ];
        }

        return shifted;
    }


    /**
     * This method will take a single string as input, and will return an ciphered string, by a provide shift
     * 
     * @param input The string we want to encode
     * @param shift The shift by which we want to encode the input
     * @return  the encoded string.
     */


    public static String caesarEncipher (String input, int shift) {

        StringBuilder enciphered = new StringBuilder("");
        char [] shiftedAlph = shiftedAlphabet(shift);

        for (int i = 0 ; i < input.length(); i++ ) {

            if ( indexInBaseAlphabet(input.charAt(i)) != -1) {
                enciphered.append( shiftedAlph[ indexInBaseAlphabet(input.charAt(i)) ] );
            }

            else {
                enciphered.append(input.charAt(i));
            }
        }

        return enciphered.toString();
    }


    /**
     * This method will take a single string as input, and will return a deciphered string, by a provided shift.
     * 
     * @param input The string we want to decode        
     * @param shift The shift by which we want to decode the input
     * @return  the decoded shift
     */
    public static String caesarDecipher (String input, int shift) {
        return caesarEncipher (input, -shift);
    }

    /**
     * This method has been used during debugging to display the contents of various ArrayLists
     * @param data
     */
    public static void display (ArrayList<String> data) {
        for (int i = 0; i < data.size(); i++) {
            System.out.println(data.get(i));
        }
    }



    public static void main (String [] args) {

        System.out.println("Welcome to CaesarCipher\n");
        Scanner keyboard = new Scanner (System.in);

        //boolean isValidInput = true;
        boolean exit = false;

            do {
                try {
                    System.out.println("Enter 1 to encipher, or 2 to decipher (-1) to exit");
                    int input = keyboard.nextInt();

                    if (input != 1 && input != 2 && input != -1) {
                        System.out.println("Invalid input. Please Enter 1 to encipher, or 2 to decipher, (-1) to exit");
                        //isValidInput = false;
                    }
                    else if ( input == 1 || input == 2) {
                        System.out.println ("\nWhat shift should I use?");
                        int shift = keyboard.nextInt();

                        System.out.println ("\nWhat is the input filename?");
                        String inPath = keyboard.next();

                        System.out.println ("\nWhat is the Ouput filename?");
                        String outPath = keyboard.next();

                        Scanner inFile = new Scanner (new File (inPath));
                        ArrayList<String> inData = new ArrayList <String> ();

                        if (inFile.hasNextLine()) {
                            while (inFile.hasNextLine()) {
                                inData.add(inFile.nextLine());
                            }

                            inFile.close();

                            ArrayList<String> outData = new ArrayList <String> ();
                            for (int i = 0; i < inData.size(); i++) {
                                if (input == 1) {
                                    outData.add( caesarEncipher ( inData.get(i), shift ) );
                                }
                                else {
                                    outData.add( caesarDecipher ( inData.get(i), shift ) ); 
                                }     
                            }

                            PrintWriter outFile = new PrintWriter(outPath);

                            for (int j = 0; j < outData.size(); j++) {
                                outFile.println( outData.get(j) );
                            }

                            outFile.close();

                            System.out.println("DONE!\n");

                        }
                        else {
                            System.out.println("The provided file seems to be empty, please check the input file");
                        }
                    }
                    else {
                        exit = true;
                    }
                }
                catch (InputMismatchException e) {
                    System.out.println ("Integer values not found. Please try again");
                    keyboard.next();
                }

                catch (FileNotFoundException e){
                    System.out.println("The filepath is not valid. Please enter a valid filepath");
                }
            }
            while (exit == false);

            if (exit == true) {
                keyboard.close();
                System.exit(0);
            }
    }

    
}
