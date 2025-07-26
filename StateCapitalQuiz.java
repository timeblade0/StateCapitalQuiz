import java.awt.GridLayout;
import java.awt.event.*;
import java.util.*;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

public class StateCapitalQuiz {
    private final JFrame frame;
    private final JLabel questionLabel;
    private final JTextField answerField;
    private final JButton submitButton;
    private final JLabel feedbackLabel;

    private final List<String[]> questions;
    private int currentQuestionIndex = 0;
    private int score = 0;

    public StateCapitalQuiz() {
        // Initialize and shuffle questions
        String[][] statesAndCapitals = {
            {"Alabama", "Montgomery"},
            {"Alaska", "Juneau"},
            {"Arizona", "Phoenix"},
            {"Arkansas", "Little Rock"},
            {"California", "Sacramento"},
            {"Colorado", "Denver"},
            {"Connecticut", "Hartford"},
            {"Delaware", "Dover"},
            {"Florida", "Tallahassee"},
            {"Georgia", "Atlanta"},
            {"Hawaii", "Honolulu"},
            {"Idaho", "Boise"},
            {"Illinois", "Springfield"},
            {"Indiana", "Indianapolis"},
            {"Iowa", "Des Moines"},
            {"Kansas", "Topeka"},
            {"Kentucky", "Frankfort"},
            {"Louisiana", "Baton Rouge"},
            {"Maine", "Augusta"},
            {"Maryland", "Annapolis"},
            {"Massachusetts", "Boston"},
            {"Michigan", "Lansing"},
            {"Minnesota", "Saint Paul"},
            {"Mississippi", "Jackson"},
            {"Missouri", "Jefferson City"},
            {"Montana", "Helena"},
            {"Nebraska", "Lincoln"},
            {"Nevada", "Carson City"},
            {"New Hampshire", "Concord"},
            {"New Jersey", "Trenton"},
            {"New Mexico", "Santa Fe"},
            {"New York", "Albany"},
            {"North Carolina", "Raleigh"},
            {"North Dakota", "Bismarck"},
            {"Ohio", "Columbus"},
            {"Oklahoma", "Oklahoma City"},
            {"Oregon", "Salem"},
            {"Pennsylvania", "Harrisburg"},
            {"Rhode Island", "Providence"},
            {"South Carolina", "Columbia"},
            {"South Dakota", "Pierre"},
            {"Tennessee", "Nashville"},
            {"Texas", "Austin"},
            {"Utah", "Salt Lake City"},
            {"Vermont", "Montpelier"},
            {"Virginia", "Richmond"},
            {"Washington", "Olympia"},
            {"West Virginia", "Charleston"},
            {"Wisconsin", "Madison"},
            {"Wyoming", "Cheyenne"}
        };
        questions = new ArrayList<>(Arrays.asList(statesAndCapitals));
        Collections.shuffle(questions);
    
        // Set up GUI
        frame = new JFrame("State Capital Quiz");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600,400); // Window frame size
        frame.setLocationRelativeTo(null); // Center on screen
        frame.setLayout(new GridLayout(4, 1));
        frame.setVisible(true);

        questionLabel = new JLabel();
        answerField = new JTextField();
        answerField.setHorizontalAlignment(JTextField.CENTER);  // Center the text the user types
        submitButton = new JButton("Submit");
        feedbackLabel = new JLabel("", SwingConstants.CENTER);

        frame.add(questionLabel);
        frame.add(answerField);
        frame.add(submitButton);
        frame.add(feedbackLabel);

        askNextQuestion();

        submitButton.addActionListener((ActionEvent e) -> {
            checkAnswer();
        });
    }

    private void askNextQuestion() {
        if (currentQuestionIndex < questions.size()){
            String state = questions.get(currentQuestionIndex)[0];
            questionLabel.setHorizontalAlignment(SwingConstants.CENTER);
            questionLabel.setText("What is the capital of " + state + "?");
            answerField.setText("");
            feedbackLabel.setText("");
        } else {
            JOptionPane.showMessageDialog(frame,
                "Your final score is: " + score + "/" + questions.size(),
                "Quiz Completed",
                JOptionPane.INFORMATION_MESSAGE);
                frame.dispose();
        }
    }

    private void checkAnswer() {
        String userAnswer = answerField.getText().trim();
        String correctAnswer = questions.get(currentQuestionIndex)[1];

        if (userAnswer.equalsIgnoreCase(correctAnswer)) {
            feedbackLabel.setText("Correct!");
            score++;
        } else {
            feedbackLabel.setText("Incorrect. The capital is " + correctAnswer + ".");
        }

        currentQuestionIndex++;

            // Pause before showing the next question
        new javax.swing.Timer(1500, evt -> {
            ((javax.swing.Timer)evt.getSource()).stop();
            askNextQuestion();
        }).start();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new StateCapitalQuiz());
    }
}