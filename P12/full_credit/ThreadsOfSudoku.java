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
                solveSuds(0, suds.length-1, 1);
            } else {
                Thread[] threads = new Thread[numThreads];
                int slice = 81/numThreads;
                int nextStart = 0;
                for(int i=0; i<numThreads; ++i) {
                    final int start = Math.min(nextStart, 80-(numThreads-i));
                    final int end   = (i<numThreads-1) ? Math.min(nextStart + slice, 80-(numThreads-i)) : 80;
                    final int threadID = i;
                    threads[i] = new Thread(() -> solveSuds(start, end, threadID));
                    threads[i].start();
                    nextStart = end+1;
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
    
    private static void solveSuds(int first, int last, int id) {
        System.out.println("Thread " + id + " will solve " + first + " to " + last);
        for(int i=first; i<=last; ++i) {
            System.out.println("Thread " + id + " solving " + i);// + "\n\n" + suds[i]);
            if(suds[i].solve()) 
                synchronized(lock) {solutions.add(suds[i]);}
            // could alternately call a synchronized method, for example
            //  addSolution(suds[i]);
        }
    }
    
    private static Object lock = new Object();    
    // could alternately call a synchronized method, for example
    //  private synchronized static void addSolution(Sudoku s) {solutions.add(s);}
    private static Sudoku[] suds = new Sudoku[81];
    private static HashSet<Sudoku> solutions = new HashSet<>();
}
