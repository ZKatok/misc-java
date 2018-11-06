
/**
 * 
 */
import java.util.*;
public class Runner
{
    public static void main(String[] args) 
    {
        int steps = 1;
        Scanner scannyboi = new Scanner(System.in);
        System.out.println("Enter any word.");
        String word = scannyboi.nextLine();
        System.out.println(word+" has "+word.length()+" letters.");
        while (word.length()!=4)
        {
            word = numToWord(word.length());
            System.out.println(word+" has "+word.length()+" letters.");
            steps += 1;
        }
          System.out.println("four has 4 letters.");
          System.out.println("This will loop forever.");
          if (steps > 1) 
        {
            System.out.println("("+steps+" steps)");
        } else {
            System.out.println("("+steps+" step)");
        }
    }

    public static String numToWord(int x)
    {
        String[] onesteens = new String[]{"zero","one","two","three","four",
                "five","six","seven","eight","nine","ten","eleven","twelve",
                "thirteen","fourteen","fifteen","sixteen","seventeen",
                "eighteen","nineteen"};
        String[] tens = new String[]{"","","twenty","thirty","forty",
                "fifty","sixty","seventy","eighty","ninety"};
        if (x<20)
        {
            return onesteens[x];
        } else if (x<100) { // standard two-digit
            return tens[x/10]+onesteens[x%10];
        }
        return "";
    }
}