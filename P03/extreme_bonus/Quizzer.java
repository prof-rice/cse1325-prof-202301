public class Quizzer {
    public static void main(String[] args) {
        String filename = "untitled.qzr";
        if(args.length > 0) filename = args[0];
        
        // "try" means "watch for an exception - if thrown, skip to the first "catch"
        //   with a matching exception type
        try {
            Quiz quiz = new Quiz(filename); // Load the quiz with questions
            System.out.println("\nYour grade is " + 100 * quiz.takeQuiz() + "\n");
            
           
        // If an IllegalArgumentException was thrown in the try clause, do this.
        // Any other exception, panic
        // If no exception was thrown, skip all catch clauses and continue.
        } catch(Exception e) {
            System.err.println("Panic during quiz (and not from the student!)\n");
            e.printStackTrace(System.err);
            System.exit(-255);
        }
        
        // This basically demonstrates that the catch is skipped if no exception
        //  was thrown.
        System.out.println("Thank you for using Quizzer!");
    }
}
