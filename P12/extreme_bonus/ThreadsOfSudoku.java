import java.util.HashSet;
import java.util.Scanner;
import java.io.File;

public class ThreadsOfSudoku {
    public static void main(String[] args) {
        
        try {
            if(args.length < 2) {
                System.err.println("usage: java ThreadsOfSudoku threads puzzleFilename [puzzleName]");
                System.exit(-1);
            }
            
            Scanner in = new Scanner(new File(args[1]));
            String name = (args.length > 1) ? args[2] : null;
            Sudoku sud = new Sudoku(in, name);
            System.out.println(sud + "\n\n");
            
            int numThreads = Integer.parseInt(args[0]);
            
            // Split the search field into 81 different sub-puzzles
            int index = 0;
            for(int y=1; y<=9; ++y) {
                for(int x=1; x<=9; ++x) {
                    suds[index++] = new Sudoku(sud, new int[]{x, y});
                }
            }
            
            // if(!sud.solve()) System.out.println("#### Unable to solve!");
            // System.out.println(sud);            
            if(numThreads < 2) {
                solveSuds(0);
            } else {
                Thread[] threads = new Thread[numThreads];
                for(int i=0; i<numThreads; ++i) {
                    final int threadID = i;
                    threads[i] = new Thread(() -> solveSuds(threadID));
                    threads[i].start();
                }
                for(int i=0; i<numThreads; ++i) {
                    threads[i].join();
                }
            }
            
            // Show the solution(s), if any
            System.out.println("Found " + solutions.size() + " solutions");
            for(var s : solutions) System.out.println(s);
        } catch(Exception e) {
            //System.err.println("ABORT: " + e);
            e.printStackTrace(System.err);
            System.exit(-1);
        }
    }
    
    static int nextIndex = 0;
    private static synchronized int getSudIndex() {
        if(nextIndex < suds.length) return nextIndex++;
        else return -1;
    }
    
    private static void solveSuds(int id) {
        int index = 0;
        while((index = getSudIndex()) >= 0) {
            System.out.println("Thread " + id + " solving " + index);// + "\n\n" + suds[i]);
            if(suds[index].solve()) synchronized(lock) {solutions.add(suds[index]);}
        }
    }
    
    private static Object lock = new Object();
    private static Sudoku[] suds = new Sudoku[81];
    private static HashSet<Sudoku> solutions = new HashSet<>();
}
