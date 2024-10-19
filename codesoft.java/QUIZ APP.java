import java.util.*;

class Question {
    String question;
    String[] options;
    int correctAnswerIndex;

    public Question(String question, String[] options, int correctAnswerIndex) {
        this.question = question;
        this.options = options;
        this.correctAnswerIndex = correctAnswerIndex;
    }

    public boolean isCorrect(int userAnswerIndex) {
        return userAnswerIndex == correctAnswerIndex;
    }
}

class Quiz {
    private List<Question> questions;
    private int score;
    private List<String> results;
    private static final int TIME_LIMIT = 10; // Time limit per question in seconds
    private Scanner scanner;

    public Quiz() {
        this.questions = new ArrayList<>();
        this.results = new ArrayList<>();
        this.score = 0;
        this.scanner = new Scanner(System.in);
    }

    public void addQuestion(Question question) {
        questions.add(question);
    }

    public void start() {
        System.out.println("Welcome to the Quiz! You have " + TIME_LIMIT + " seconds for each question.");
        for (int i = 0; i < questions.size(); i++) {
            System.out.println("\nQuestion " + (i + 1) + ":");
            askQuestion(questions.get(i));
        }
        showResults();
    }

    private void askQuestion(Question question) {
        System.out.println(question.question);
        for (int i = 0; i < question.options.length; i++) {
            System.out.println((i + 1) + ". " + question.options[i]);
        }

        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                System.out.println("\nTime's up! Moving to the next question...");
                results.add("Time expired for question: " + question.question);
            }
        };

        Timer timer = new Timer();
        timer.schedule(task, TIME_LIMIT * 1000);

        try {
            System.out.print("Enter your answer (1-" + question.options.length + "): ");
            int userAnswer = scanner.nextInt() - 1;

            if (userAnswer < 0 || userAnswer >= question.options.length) {
                System.out.println("Invalid option selected.");
                results.add("Invalid response for question: " + question.question);
            } else if (question.isCorrect(userAnswer)) {
                System.out.println("Correct!");
                score++;
                results.add("Correct for question: " + question.question);
            } else {
                System.out.println("Wrong! The correct answer was: " + question.options[question.correctAnswerIndex]);
                results.add("Wrong for question: " + question.question);
            }
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter a number.");
            scanner.next(); // Clear the invalid input
            results.add("Invalid input for question: " + question.question);
        } finally {
            timer.cancel(); // Stop the timer regardless of whether the user answered or not
        }
    }

    private void showResults() {
        System.out.println("\n--- Quiz Results ---");
        System.out.println("Your Score: " + score + " out of " + questions.size());
        System.out.println("Summary:");
        for (String result : results) {
            System.out.println(result);
        }
    }
}

public class QuizApp {
    public static void main(String[] args) {
        Quiz quiz = new Quiz();

        // Add questions to the quiz
        quiz.addQuestion(new Question(
                "What is the capital of France?",
                new String[]{"Berlin", "Paris", "Madrid", "Rome"},
                1
        ));
        quiz.addQuestion(new Question(
                "Which planet is known as the Red Planet?",
                new String[]{"Earth", "Mars", "Jupiter", "Venus"},
                1
        ));
        quiz.addQuestion(new Question(
                "Who wrote 'Hamlet'?",
                new String[]{"Charles Dickens", "William Shakespeare", "Jane Austen", "Mark Twain"},
                1
        ));

        // Start the quiz
        quiz.start();
    }
}
