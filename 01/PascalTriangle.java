import java.util.*;

//Drawing the Pascal Triangle with variable row number on the console
public class PascalTriangle {
    public static void main(String[] args) {
        int rows = 0;
        Scanner scanner = new Scanner(System.in);

        // Reading the number of requested rows from stdin and checking the input
        System.out.print("How many rows? ");
        rows = scanner.nextInt();
        if(rows < 1) {
            System.out.println("Error, value has to be at least 1!");
            scanner.close();
            return;
        }

        // Initialising variables and filling the results' matrix with ones where needed
        int sideSpace = 0, nextInt = 1;
        int[][] matrix = new int[rows][rows];
        // SH: If course, this part of the code could have been done in O(rows)
        // time instead of O(rows²) time, since you initially only need the
        // outer one's in your triangle, see blow
        for(int i = 0; i < rows; ++i) {
            // SH: Instead of the inner loop:
            // matrix[i][0] = 1;
            // matrix[i][i] = 1;
            for(int j = 0; j < i+1; ++j) {
                matrix[i][j] = 1;
            }
        }

        // SH: The if is obsolete, because the outer loop is not iterated for
        // row <= 2. Killing the if reduces the nesting depth of your code,
        // which is a good thing.
        // Calculating the "inner parts" of the triangle, where and if needed
        if(rows > 2) {
            for(int i = 2; i < rows; ++i) {
                for(int j = 1; j < i; ++j) {
                    // SH: Add a single space around each operator, like +, to
                    // increase readability.
                    matrix[i][j] = matrix[i-1][j-1]+matrix[i-1][j];
                }
            }
        }

        // Printing the triangle on stdout
        for(int i = 0; i < rows; ++i) {
            sideSpace = (rows-i-1) * 2;
            // SH: I personally to do not place braces for single-line bodies,
            // but others do.
             //SH: This could be one-liner:
            //System.out.print(" ".repeat(sideSpace));
            for(int j = 0; j < sideSpace; ++j) {
                System.out.print(" ");
            }
            for(int j = 0; j < i+1; ++j) {
                nextInt = matrix[i][j];

                // SH: Use printf() or format() of System.out to do it in one line!
                // SH: Do not continue if-body in the same line!
                if(nextInt < 10) System.out.print(" ");
                System.out.print(nextInt);
                if(nextInt < 100) System.out.print(" ");

                System.out.print(" ");
            }
            for(int j = 0; j < sideSpace; ++j) {
                System.out.print(" ");
            }
            // SH: Use println() instead. And %n is the platform-independent
            // \n.
            System.out.print("\n");
        }
        scanner.close();
    }
}
