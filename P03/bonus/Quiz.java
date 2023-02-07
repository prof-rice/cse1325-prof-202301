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
                "If no constructor is specified,", // The question
                new String[]{     // The answers
                    "an object cannot be constructed", 
                    "Java will provide a default constructor for free", 
                    "the compiler will generate an error", 
                    "a RuntimeException will be thrown",
                },
                2                 // Correct answer, 1 to answers.size()
            )
        );
        questions.add(
            new Question(
                "Object-oriented programming is as easy as",
                new String[]{
                    "PIE (Polymorphism, Inheritance, Encapsulation)",
                    "C (equally Confusing!)", 
                    "structured programming (maybe easier!)", 
                    "landing a rover on Mars",
                },
                1
            )
        );
        questions.add(
            new Question(
                "For class Foo, field public final static bar",
                new String[]{
                    "is at the exact same memory location for every object",
                    "can be accessed as Foo.bar with no objects at all", 
                    "cannot be changed once initialized", 
                    "all of the above"
                },
                4
            )
        );
        questions.add(
            new Question(
                "A static method",
                new String[]{
                    "may only access static fields",
                    "may be called without a class or object name", 
                    "has no return value", 
                    "doesn't exist - methods cannot be static"
                },
                1
            )
        );
        questions.add(
            new Question(
                "Debuggers are",
                new String[]{
                    "a conspiracy to confuse students",
                    "a poor substitute for a lot of printf statements", 
                    "often the best approach to debugging", 
                    "difficult to set up, use, and terminate"
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
