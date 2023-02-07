import java.util.ArrayList;
import java.util.Scanner;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.FileNotFoundException;

public class Quiz {
    // Load the quiz!
    public Quiz(String filename) {
        loadQuiz(filename);
     }
    
    // This method prints each question, accepts an int from the user
    //    as their answer, and checks if it is correct.
    //    The fraction of correct answers is returned, e.g., .92 is 92%
    public double takeQuiz() {
        // Count number of right answers from student below
        double correct = 0;  
        Scanner in = new Scanner(System.in);
        
        // Ask each question in order. The questions are easily reorganized
        //   into a random order with
        // java.util.Collections.shuffle(questions);
        for(Question q : questions) {
            System.out.print("\n" + q + "\n  Your answer? ");
            if(q.checkAnswer(in.nextInt())) ++correct;
        }
        
        // Calculate the fraction correct (0.0 to 1.0)
        return correct / (double) questions.size();
    }
    
    // /////////////////////////////////////
    // ////////// Private Members //////////
    
    // Load questions into the questions ArrayList
    // For an array, use questions[0] = new Question...
    private void loadQuiz(String filename) {
        int line = 1;     // Track filename line numbers for error messages
        String question;  // Hold input data until Question is instanced
        int rightAnswer;
        String answer;
        ArrayList<String> answers = new ArrayList<>();
        
        // Open a file for reading (this is called a "try with resources" block
        //   and guarantees the file will be auto-closed when the block exits)
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
        
            // Read until no more lines of text are available
            while(br.ready()) {
            
                // Read until we find a non-blank line - that's a question!
                question = "";
                while(question.isEmpty()) {
                    question = br.readLine().trim(); line++;
                }
                
                // Read the correct answer number
                rightAnswer = Integer.parseInt(br.readLine()); line++;
                
                // Read all of the answers (any number is fine)
                // Stop reading when a blank line is found
                answers.clear();
                while(br.ready()) {
                    answer = br.readLine(); line++;
                    if(answer.trim().isEmpty()) break;
                    answers.add(answer);
                }
                
                // Read - add the question!
                questions.add(
                    new Question(question, answers.toArray(new String[0]), rightAnswer)
                );
            }
        } catch(FileNotFoundException e) {
            System.err.println("Could not find file " + filename + "\n" + e);
            System.exit(-1);
        } catch(IOException e) {
            System.err.println("Error reading file " + filename 
                             + " at line " + line + "\n");
            e.printStackTrace(System.err);
            System.exit(-line);
        } catch(IllegalArgumentException e) {
            System.err.println("Abort reading " + filename 
                             + " at line " + line + "\n");
            e.printStackTrace(System.err);
            System.exit(-1);
        } catch(Exception e) {
            System.err.println("Panic reading file " + filename 
                             + " at line " + line + "\n");
            e.printStackTrace(System.err);
            System.exit(line);
        }        
    }

    // An ArrayList is the class version of an array
    // Instead of private Question[] questions = new Question[10];
    //   we write this - notice we don't specify size in advance!
    // We add new elements using questions.add(new Question(...));
    // We "subscript" using questions.get(index)
    private ArrayList<Question> questions = new ArrayList<>();
    
    // As an array, we must specify the max number of questions.
    // Question[] questions = new Question[100];

}
