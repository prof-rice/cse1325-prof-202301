public class Quizzer {
    public static void main(String[] args) {
        // "try" means "watch for an exception - if thrown, skip to the first "catch"
        //   with a matching exception type
        try {
            Quiz quiz = new Quiz(); // Load the quiz with questions
            System.out.println("\nYour grade is " + 100 * quiz.takeQuiz() + "\n");
            
           
        // If an IllegalArgumentException was thrown in the try clause, do this.
        // Any other exception, keep looking for a catch that matches (since this
        //   is main, that means none will be found and the program will abort).
        // If no exception was thrown, skip all catch clauses and continue.
        } catch(IllegalArgumentException e) {
            System.err.println(e.getMessage() + "\nQuiz aborted");
            System.exit(-1);
        }
        
        // This basically demonstrates that the catch is skipped if no exception
        //  was thrown.
        System.out.println("Thank you for using Quizzer!");
    }
}
