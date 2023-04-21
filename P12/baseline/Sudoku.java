import java.util.Arrays;
import java.util.Objects;
import java.util.Scanner;
import java.io.File;

public class Sudoku {
    // Constructs a new Sudoku from a text file
    public Sudoku(Scanner in, String puzzleName) {
        solution = new int[9][9];
        board    = new int[9][9];
        
        // Skip to the requested puzzle
        while(!in.nextLine().trim().equals(puzzleName));

        // Load the puzzle
        for(int y=0; y<9; ++y) {
            for(int x=0; x<9; ++x) {
                board[x][y] = in.nextInt();
            }
        }
    }
    
    // Constructs a new Sudoku from an existing Sudoku,
    //   replacing the first replace.length 0 values
    //   with respective values from replace
    // This is useful for splitting up the search
    //   into 9, 81, etc. different sub-Sudokus
    public Sudoku(Sudoku from, int[] replace) {
        solution = new int[9][9];
        board    = new int[9][9];

        int index = 0;
        for(int x=0; x<9; ++x) {
            for(int y=0; y<9; ++y) {
                if((from.board[x][y] == 0) && (index < replace.length))
                    board[x][y] = replace[index++];
                else
                    board[x][y] = from.board[x][y];
            }
        }
    }
    
    public boolean solve() {return solve(0, "");}
    public boolean solve(int depth, String path) {
        for(int x=0; x<9; ++x) {
            for(int y=0; y<9; ++y) {
                if(board[x][y] == 0) {
                    for(int v=1; v<=9; ++v) {
                        if(isSafe(x, y, v)) {
                            board[x][y] = v;
                            if(isSolved(board)) copy(board, solution);
                            solve(depth+1, path + "(" + x + "," + y + ")");
                            board[x][y] = 0;
                        }
                    }
                }
            }
        }
        if(isSolved(solution)) {
            copy(solution, board);
            return true;
        } else {
            return false;
        }
    }
    
    private boolean isSafe(int x, int y, int v) {
        for(int i=0; i<9; ++i) { 
            if(board[x][i] == v) return false; // check column
            if(board[i][y] == v) return false; // check row
        }
        int minX = (x/3)*3;
        int minY = (y/3)*3;
        for(int i=minX; i<minX+3; ++i) {
            for(int j=minY; j<minY+3; ++j) { 
                if(board[i][j] == v) return false;
            }
        }
        return true;
    }
    
    // True if no zeros remain in puzzle
    private boolean isSolved(int[][] b) {
        for(int x=0; x<9; ++x) {
            for(int y=0; y<9; ++y) {
                if(b[x][y] == 0) return false;
            }
        }
        return true;
    }
    
    private void copy(int[][] from, int[][] to) {
        for(int x=0; x<9; ++x) {
            for(int y=0; y<9; ++y) {
                to[x][y] = from[x][y];
            }
        }
    }
                
    @Override
    public String toString() {
        StringBuilder result = new StringBuilder(name + "\n" 
                          + "-".repeat(name.length()) + "\n");
        for(int y=0; y<9; ++y) {
            String spacer = "";
            for(int x=0; x<9; ++x) {
                result.append(spacer + board[x][y]);
                spacer = " ";
            }
            result.append("\n");
        }
        return result.toString();
    }
    
    @Override
    public boolean equals(Object o) {
        if(o == this) return true;
        if(o == null) return false;
        if(o.getClass() != getClass()) return false;
        Sudoku s = (Sudoku) o;
        return Arrays.deepEquals(board, s.board);
    }
    @Override
    public int hashCode() {
        int result = 17; // Objects.hash doesn't handle 2d arrays of int
        for(int i=0; i<9; ++i) result += Objects.hash(board[i]);
        return result;
    }
    private String name = "";
    private int[][] board;
    private int[][] solution;
    
    public static void main(String[] args) {
        try {
            if(args.length == 0) {
                System.err.println("usage: java Sudoku puzzleFilename [puzzleName]");
                System.exit(-1);
            }
            Scanner in = new Scanner(new File(args[0]));
            String name = (args.length > 1) ? args[1] : null;
            Sudoku s = new Sudoku(in, name);
            System.out.println(s + "\n\n");
            if(!s.solve()) System.out.println("#### Unable to solve!");
            System.out.println(s + "\n\n");            
        } catch(Exception e) {
            System.err.println("ABORT: " + e);
            e.printStackTrace(System.out);
            System.exit(-1);
        }
    }
}
