/*
* Randomly select a 4-digit positive number excluding
* 1111, 2222, ..., 9999, with leading zeros if necessary
* Thus 158 would be represented by 0158
* And 9 would be represented by 0009
* Let bigger denote the number obtained by taking
* the four digits obtained in step (1) in decreasing order
* Let smaller denote the number obtained by taking
* the four digits obtained in step (1) in increasing order
* Obtain a new 4-digit number by subtracting smaller
* from bigger, inserting leading zeros if necessary
* e.g. 7666 - 6667 = 999, so the 4-digit value is 0999
* Repeat from step (2) for a maximum of 10 iterations
* or until two successive 4-digit numbers are
* identical, whichever occurs first.
* Implement and test this algorithm in Java, using the Random
* class to generate test values.
*/
package randomfourdigitnumber;


import java.util.Scanner;
import java.util.Random;
import java.text.DecimalFormat;

/**
 * 14/11/2015
 * @author Bijan Ghasemi Afshar ( 100125463 )
 */

public class RandomFourDigitNumber {
    
    // A method for placing the digits into a String and converting the
    // String to an integer
    public static int stringToInteger(int [] digits)
    {
        // Converting the number from array into a String
        String strNum = null;
        for(int i = 3; i >= 0; i--)
        {
            if (i == 3)
            {
                strNum = Integer.toString(digits[i]);
            }
            else
            {
                strNum = strNum + Integer.toString(digits[i]);
            }
        }
        
        // Converting the string into an integer
        int number;
        number = Integer.parseInt(strNum);
        return number;
        
    } // END of the METHOD
    
    public static void main(String[] args) {
        
        // Declaring variables and creating a scan and a random and a
        // decimal format object
        int randomNumber = 0;
        int difference = 0;
        int digits[] = new int[4];
        Scanner scan = new Scanner (System.in);
        Random random = new Random();
        DecimalFormat df = new DecimalFormat("0000");
        int counterLoop = 0;
        
        do
        {
            
            counterLoop++;
            
            System.out.println("Iterationn: "+counterLoop);
            
            if(counterLoop == 1)
            {
                // Getting a random 4-digit number and printing it
                randomNumber =random.nextInt(9999) + 1;
                System.out.println("The random number: "+df.format(randomNumber));
            }
            else
            {
                randomNumber = difference;
                System.out.println("The random number: "+df.format(randomNumber));
            }
            
            
            // Placing the digits of the number into an array
            int container = randomNumber;
            for (int i = 0; i < 4; i++)
            {
                digits[i] = container % 10;
                container = container / 10;
            }
            
            // Checking for the same digits
            int counter = 0;
            for(int i = 1; i < 4; i++)
            {
                if(digits[0] == digits[i]){
                    counter++;
                }
            }
            
            // IF the digits are all the same, the program prints an error message
            // and stops
            if(counter == 3)
            {
                System.out.println("The random number is excluded by the "+
                        "question");
                break;
            }
            
            // IF digits are NOT all the same, the program continues
            else
            {
                
                // Ordering the digits in decreasing order(Making it the Biggest)
                int keeper = 0;
                for(int i = 0 ;i < 4; i++)
                {
                    for(int j = 0; j < 4; j++)
                    {
                        if(digits[i] < digits[j])
                        {
                            keeper = digits[i];
                            digits[i] = digits[j];
                            digits[j] = keeper;
                        }
                    }
                }
                
                // Converting the number into a String and then into an integer
                int bigger = stringToInteger(digits);
                System.out.println("The bigger number is: "+df.format(bigger));
                
                // Ordering the digits in increasing order(Making it the Smallest)
                keeper = 0;
                for(int i = 0 ;i < 4; i++)
                {
                    for(int j = 0; j < 4; j++)
                    {
                        if(digits[i] > digits[j])
                        {
                            keeper = digits[i];
                            digits[i] = digits[j];
                            digits[j] = keeper;
                        }
                    }
                }
                
                // Converting the number into a String and then into an integer
                int smaller = stringToInteger(digits);
                System.out.println("The smaller number is: "+df.format(smaller));
                
                // Substracting the smaller number from the bigger number
                difference = bigger - smaller;
                
                // Printing the difference
                System.out.println("The difference is: "+df.format(difference));                                
                
                System.out.println("\n");
                
            } // END of the else CONDITION
            
        }
        while(randomNumber != difference && counterLoop < 10);
        
    } // END of the main METHOD
    
} // END of the CLASS
