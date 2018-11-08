/**
 * 
 */
import java.util.*;
public class Runner
{
    static Scanner scannyboi = new Scanner(System.in);
    static int rows = 12;
    static int columns = 15;
    static String[][] board = new String[rows][columns];
    static String[][] newboard = new String[rows][columns];
    static int x = 0;
    static int y = 0;
    static int step = 0;
    static String r2 = "";

    public static void main(String[] args)
    {
        System.out.println("Welcome to Conway's Game of Life!");
        System.out.println("("+rows+"x"+columns+" board)");
        System.out.println("The rules: \n If a circle is adjacent to 0 or 1 circles, it dies of lonliness."+
            "\n If a circle is adjacent to 4 or more circles, it dies of overpopulation."+
            "\n If a circle is adjacent to 2 or 3 circles, it survives."+
            "\n If a dot is adjacent to 3 circles, the dot becomes a circle.");
        System.out.println("Enter 'a' for a random setup,\n enter 'b' to start plotting circles,"+
            "\n enter 'c' to start with a classic glider formation");
        String r1 = scannyboi.nextLine();
        if (r1.equals("a")) { // random board
            for (int i=0; i<rows; i+=1) {
                for (int j=0; j<columns; j+=1) {
                    int decide = (int)(Math.random()*3);
                    if (decide == 0) {
                        board[i][j] = "o";
                    } else { 
                        board[i][j] = ".";
                    }
                }
                System.out.println(Arrays.toString(board[i]).replace(",",""));
            }
        } else if (r1.equals("b")) { // specific points
            // start with dotted board
            for (int i=0; i<rows; i+=1) {
                for (int j=0; j<columns; j+=1) {
                    board[i][j] = ".";
                }
            }
            do {
                enterPoint();
                r2 = scannyboi.nextLine();
                if (r2.equals("y")) {
                    enterPoint();
                }
                r2 = scannyboi.nextLine();
            } while (r2.equals("y"));
        } else {
            for (int i=0; i<rows; i+=1) {
                for (int j=0; j<columns; j+=1) {
                    board[i][j] = ".";
                }
            }
            board[0][1] = "o";
            board[1][2] = "o";
            board[2][0] = "o";
            board[2][1] = "o";
            board[2][2] = "o";
        }

        System.out.println("\u000C");
        printBoard();
        step+=1;
        System.out.println(step);
        System.out.println("Type in 'a' whenever you want to see the next step.");
        System.out.println("Type in anything else to quit.");
        r2 = scannyboi.nextLine();

        do {
               System.out.println("\u000C");
            // make new board
            for (int i=0; i<rows; i+=1) {
                for (int j=0; j<columns; j+=1) {
                    if (countNeighbors(board,i,j)<2 || countNeighbors(board,i,j)>3) {
                        newboard[i][j] = ".";
                    }
                    if (countNeighbors(board,i,j)==2) {
                        newboard[i][j] = board[i][j];
                    }
                    if (countNeighbors(board,i,j)==3) {
                        newboard[i][j] = "o";
                    }
                    //System.out.print(countNeighbors(board,i,j));
                }
                System.out.println(Arrays.toString(newboard[i]).replace(",",""));
            }
            step+=1;
            System.out.println(step);
            board = copy(newboard);
            r2 = scannyboi.nextLine();
        } while (r2.equals("a"));
    }

    public static void printBoard() {
        for (int i=0; i<rows; i+=1) {
            System.out.println(Arrays.toString(board[i]).replace(",",""));
        }
    }

    public static String[][] copy(String[][] old) {
        String[][] newarray = new String[rows][columns];
        for (int i=0; i<rows; i+=1) {
            for (int j=0; j<columns; j+=1) {
                newarray[i][j] = old[i][j];
            }
        }
        return newarray;
    }

    public static boolean isValid(int i, int j) {
        if (i<0 || i==rows) {
            return false;
        }
        if (j<0 || j==columns) {
            return false;
        }
        return true;
    }

    public static int countNeighbors(String[][] x, int i, int j) {
        int count = 0;
        if (isValid(i-1,j)) {
            count += x[i-1][j].equals("o") ? 1 : 0;
        }
        if (isValid(i+1,j)) {
            count +=  x[i+1][j].equals("o") ? 1: 0;
        }
        if (isValid(i,j-1)) {
            count +=  x[i][j-1].equals("o") ? 1: 0;
        }
        if (isValid(i,j+1)) {
            count +=  x[i][j+1].equals("o") ? 1: 0;
        }
        if (isValid(i-1,j-1)) {
            count +=  x[i-1][j-1].equals("o")? 1 : 0;
        }
        if (isValid(i+1,j+1)) {
            count +=  x[i+1][j+1].equals("o") ? 1: 0;
        }
        if (isValid(i+1,j-1)) {
            count +=  x[i+1][j-1].equals("o")? 1 : 0;
        }
        if (isValid(i-1,j+1)) {
            count +=  x[i-1][j+1].equals("o") ? 1: 0;
        }
        return count;
    }

    public static int countNeighborsold(String[][] x, int i, int j)
    {
        int count = 0;
        if (i!=0) {
            if (x[i-1][j].equals("o")) { // up
                count += 1;
            }
            if (j!=0) {
                if (x[i-1][j-1].equals("o")) { // upleft
                    count += 1;
                }
            }
            if (j!=columns-1) {
                if (x[i-1][j+1].equals("o")) { // upright
                    count += 1;
                }
            }
        }
        if (j!=0) {
            if (x[i][j-1].equals("o")) {  // left
                count += 1;
            }
        }
        if (i!=rows-1) {
            if (x[i+1][j].equals("o")) { // down
                count += 1;
            }
            if (j!=0) {
                if (x[i+1][j-1].equals("o")) { // downleft
                    count += 1;
                }
            }
            if (j!=columns-1) {
                if (x[i+1][j+1].equals("o")) { // downright
                    count += 1;
                }
            }
        }
        if (j!=columns-1) {
            if (x[i][j+1].equals("o")) { // right
                count += 1;
            }
        }
        return count;
    }

    public static void enterPoint() {
        System.out.println("Enter the number of rows down the circle should be.");
        x = scannyboi.nextInt();
        if ((x<0)||(x>=rows)) {
            System.out.println("Invalid response. Please enter a number between 0 and "
                +(rows-1));
            x = scannyboi.nextInt();
        }
        System.out.println("Enter the number of rows across the circle should be.");
        y = scannyboi.nextInt();
        if ((y<0)||(y>=columns)) {
            System.out.println("Invalid response. Please enter a number between 0 and "
                +(columns-1));
            y = scannyboi.nextInt();
        }
        board[x][y] = "o";
        System.out.println("Point ("+x+", "+y+") recorded.");
        System.out.println("Would you like to enter another circle?");
        System.out.println("Enter 'y' for yes, enter 'n' for no.");

    }
}