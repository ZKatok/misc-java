import java.util.*;
public class Runner
{
    public static void main(String[] args)
    {
        int num;
        int steps = 0;
        int[] lstepsi = new int[62]; int[] hstepsi = new int[35]; 
        int lstepsicount = 0; int hstepsicount = 0;
        double nine=0;double thirteen=0;double nineteen=0;double twentythree=0;

        for (int i=1; i<1000; i+=1)
        {
            num = i;
            System.out.print(num+" ");

            // the actual phases of switching between numbers and words
            do {
                System.out.print(romanToWords(decToRoman(num))+",");
                num = romanToWords(decToRoman(num)).length();
                System.out.print(num+",");
                steps += 1;
            } while ((num!=9) && (num!=13) && (num!=19) && (num!=23));

            // count how many loops end in 9, 13, 19, or 23
            if (num==9) {
                nine+=1;
            } else if (num==13) {
                thirteen+=1;
            } else if (num==19) {
                nineteen+=1;
            } else if (num==23) {
                twentythree+=1;
            }
            System.out.print("\b ("+steps+" steps)");
            if (steps==1) {
                lstepsi[lstepsicount] = i;
                lstepsicount+=1;
            }
            if (steps==10) {
                hstepsi[hstepsicount] = i;
                hstepsicount+=1;
            }
            steps = 0;
            System.out.println();
        } 
        System.out.println("   STATS");
        System.out.println("Ended in 9: "+nine+" = "+((nine/999)*100)+"%");
        System.out.println("Ended in 13: "+thirteen+" = "+((thirteen/999)*100)+"%");
        System.out.println("Ended in 19: "+nineteen+" = "+((nineteen/999)*100)+"%");
        System.out.println("Ended in 23: "+twentythree+" = "+((twentythree/999)*100)+"%");
        System.out.println("Lowest number of steps: 1; at these 62 inputs "+Arrays.toString(lstepsi));
        System.out.println("Highest number of steps: 10; at these 35 inputs "+Arrays.toString(hstepsi));
    }

    public static String decToRoman(int x) 
    {
        String[] romanSingles = new String[]{"","I","II","III","IV","V","VI","VII","VIII","IX"};
        String[] romanDoubles = new String[]{"","X","XX","XXX","XL","L","LX","LXX","LXXX","XC"};
        String[] romanTriples = new String[]{"","C","CC","CCC","CD","D","DC","DCC","DCCC","CM"};
        return romanTriples[x/100]+romanDoubles[(x/10)%10]+romanSingles[x%10];
    }

    public static String romanToWords(String x)
    {
        Map<String,String> charToWord = new HashMap<String,String>();
        charToWord.put("I","India");
        charToWord.put("V","Victor");
        charToWord.put("X","Xray");
        charToWord.put("L","Lima");
        charToWord.put("C","Charlie");
        charToWord.put("D","Delta");
        charToWord.put("M","Mike");
        String returner = "";
        for (int i=0; i<x.length(); i+=1)
        {
            returner += charToWord.get(x.substring(i,i+1));
        }
        return returner;
    }
}