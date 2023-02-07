public class Question {
    public Question(String question, String[] answers, int rightAnswer) {
        // Since nextQuestionNumber is static, each Question object gets a unique number
        this.questionNumber = nextQuestionNumber++;
        
        this.question = question;
        this.answers = answers;
        this.rightAnswer = rightAnswer;
        
        // Data validation - ensure the rightAnswer indicates an actual answer!
        // We could also validate that the question and each answer are non-empty,
        //   and that we have at least two answers. What else could we validate?
        if(rightAnswer < 1 || rightAnswer > answers.length)
            throw new IllegalArgumentException(
                "Right answer " + rightAnswer + " doesn't match a question for\n"
              + "  question #" + this.questionNumber + ": "
              + question);
    }
    @Override
    public String toString() {
        // StringBuilder is preferred here over String because we're building up
        //    a String in a loop. Remember, String is immutable - every change
        //    you make creates an entire new object! StringBuider is mutable.
        StringBuilder result = new StringBuilder("" + questionNumber + ". " + question + "\n");
        for(int i=0; i<answers.length; ++i)
            result.append("  " + (i+1) + ") " + answers[i] + "\n");
        return result.toString(); 
    }
    public boolean checkAnswer(int answer) {
        // Remember, == for comparing primitive values, .equals for object values
        return answer == rightAnswer;
    }
    private final int questionNumber; // From 1 to n, set automatically using nextQuestionNumber
    private static int nextQuestionNumber = 1; // static fields usually init inline
    private final String question;  // The question to be asked of the student
    private final String[] answers; // Proposed answers from which the student will choose
    private final int rightAnswer;  // From 1 to number of proposed answers (answers.length)
}
