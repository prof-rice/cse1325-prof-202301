import java.util.ArrayList;
import java.util.Scanner;

public class Quiz {
    // Load the quiz!
    public Quiz() {
        loadQuiz();
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
    private void loadQuiz() {
        questions.add(
            new Question(
                "What is 1 + 1?", // The question
                new String[]{     // The answers
                    "2", "14", "-42", "0"
                },
                1                 // Correct answer, 1 to answers.size()
            )
        );
        questions.add(
            new Question(
                "The capital of Texas is",
                new String[]{
                    "Dallas", "Houston", "Austin", "Arlington"
                },
                3
            )
        );
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
