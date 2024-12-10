//ExamAnalysis.java
/**
 * This class will analyse the set of responses to questions, by taking the correct responses and using them as reference to determine the correct and incorrect answers from submissions
 * 
 * @author Tresor Habib Nahouta
 * @version Last modified March 21, 2024
 */


import java.io.*;
import java.util.*;

public class ExamAnalysis {

    /**
     * This small helper allows to efficiently display the index on screen.
     * The displayed index of a given cell is one more than the index in the array or arraylist.
     * @param i the array index
     * @return the display index
     */
    private static int displayIndex (int i) {
        return i + 1;
    }


    /**
     * This helper will return a string in front of a character if the said character happens to be the correct answer.
     * @param correct the correct answer    
     * @param answer the answer whose correctness we are checking.
     * @return
     */
    private static String isCorrect (char correct, char answer) {
        if (answer == correct) {
            return "*";
        }
        else {
            return "";
        }
    }


    
    public static void main (String [] args) throws FileNotFoundException {
        System.out.println("I hope you are ready to begin ...");

        boolean isValidInput =  true;
        Scanner keyboard = new Scanner (System.in);

        System.out.println("\nPlease type the correct answers to the exam questions, one right after the other: ");
        String correctAnswers = keyboard.nextLine();
        int numQuestions = correctAnswers.length();
 
        do {
            try {
                System.out.println("\nWhat is the name of the file containing each student's responses to the " + numQuestions + " questions?");
                String filepath = keyboard.nextLine();
                Scanner inputAnswers = new Scanner (new File (filepath));
                ArrayList<String> readAnswers = new ArrayList <String> ();
                

                if (inputAnswers.hasNextLine()) {
                    while(inputAnswers.hasNextLine()) {
                        readAnswers.add(inputAnswers.nextLine());
                    }

                    /*Trimming any trailing empty lines */
                    for (int i = 0; i < readAnswers.size(); i++) {
                        if (readAnswers.get(i).equals("")) {
                            readAnswers.remove(i);
                        }
                    }

                    /*The number of students will be the number of non empty lines in the file */
                    int numStudents = readAnswers.size();
    
                    
                    for (int i = 0; i < numStudents; i++) { 
                        System.out.println("Student #" + displayIndex(i) + "'s responses:" + readAnswers.get(i));
                    }

                    System.out.println("We have reached \"end of file\" !");
                    System.out.println("\nThank you for the data on " + readAnswers.size() + " students. Here's the analysis");

                    System.out.println("Student #       Correct     Incorrect       Blank");
                    System.out.println("~~~~~~~~~       ~~~~~~~     ~~~~~~~~~       ~~~~~");

                    for (int i = 0; i < numStudents; i++) {

                        int correctCount = 0;
                        int incorrectCount = 0;
                        int blankCount = 0;

                        for (int j = 0; j < numQuestions; j++) {
                            if (Character.toUpperCase(readAnswers.get(i).charAt(j)) == ' ') {
                                blankCount ++;
                            }
                            else if (Character.toUpperCase(readAnswers.get(i).charAt(j)) == Character.toUpperCase(correctAnswers.charAt(j))) {
                                correctCount ++;
                            }
                            else {
                                incorrectCount ++;
                            }
                        }

                        System.out.println("   " + displayIndex(i) + "               " + correctCount + "            " + incorrectCount + "             " + blankCount + "   ");
                    }

                    System.out.println("QUESTION ANALYSIS       (* marks the correct response)");
                    System.out.println("~~~~~~~~~~~~~~~~~");

                    int [] stats = new int [6];
                    double [] statspercent = new double[6];

                    for (int j = 0; j < numQuestions; j++) {

                        for (int k = 0; k < 6; k++) {
                            stats[k] = 0;
                            statspercent[k] = 0.0;
                        }

                        System.out.println("\nQuestion #" + displayIndex(j) + ":\n");
                        System.out.print( "    "
                                        + "A" + isCorrect (Character.toUpperCase(correctAnswers.charAt(j)), 'A') + "      "
                                        + "     B" + isCorrect (Character.toUpperCase(correctAnswers.charAt(j)), 'B') + "      "
                                        + "     C" +  isCorrect (Character.toUpperCase(correctAnswers.charAt(j)), 'C') + "      "
                                        + "     D" +  isCorrect (Character.toUpperCase(correctAnswers.charAt(j)), 'D') + "      "
                                        + "     E" +  isCorrect (Character.toUpperCase(correctAnswers.charAt(j)), 'E') + "      "
                                        + "     Blank\n"
                        );


                        
                        for (int i = 0; i < numStudents; i++ ) {

                            if ( Character.toUpperCase(readAnswers.get(i).charAt(j)) == 'A') {
                                stats[0] = stats[0] + 1;
                            }
                            else if ( Character.toUpperCase(readAnswers.get(i).charAt(j)) == 'B') {
                                stats[1] = stats[1] + 1;
                            }
                            else if ( Character.toUpperCase(readAnswers.get(i).charAt(j)) == 'C') {
                                stats[2] = stats[2] + 1;
                            }
                            else if ( Character.toUpperCase(readAnswers.get(i).charAt(j)) == 'D') {
                                stats[3] = stats[3] + 1;
                            }
                            else if ( Character.toUpperCase(readAnswers.get(i).charAt(j)) == 'E') {
                                stats[4] = stats[4] + 1;
                            }
                            else if ( Character.toUpperCase(readAnswers.get(i).charAt(j)) == ' ') {
                                stats[5] = stats[5] + 1;
                            }

                        }

                        for (int k = 0; k < 6; k++) {
                            statspercent[k] = 100*(stats[k]/(double)numStudents);
                        }

                        System.out.println("    " + stats[0] + "            " + stats[1] + "            " + stats[2] + "            " + stats[3] + "            " + stats[4] + "            " + stats[5]);
                        System.out.printf("    %.1f%%        %.1f%%        %.1f%%        %.1f%%        %.1f%%        %.1f%%", statspercent[0], statspercent[1], statspercent[2], statspercent[3], statspercent[4], statspercent[5]);
                        System.out.println("\n");             
                    }
                    isValidInput = true;
                    keyboard.close();
                    inputAnswers.close();
                }
                else {
                    System.out.println("The provided file seems to be empty. Please enter a path to a valid student's responses file\n");
                    isValidInput = false;
                }
            }
            catch (FileNotFoundException e){
                System.out.println("The filepath is not valid. Please enter a valid filepath");
                isValidInput = false;
            }
        }
        while (!isValidInput);
    }
}
    
