import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;
    
public class GuessingGameGui extends JFrame implements ActionListener {

    private int randomNumber;
    private int attemptsLeft;
    private int score;
    private int round;
    private final int maxAttempts = 7;

    private JTextField guessField;
    private JLabel feedbackLabel;
    private JLabel attemptsLabel;
    private JLabel scoreLabel;
    private JButton guessButton;

    public GuessingGameGui() {
        // Set up the frame
        setTitle("Number Guessing Game");
        setSize(400, 250);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Center the window
        setLayout(new GridLayout(6, 1));

        // Initialize components
        add(new JLabel("🎯 Guess a number between 1 and 100:", SwingConstants.CENTER));
        guessField = new JTextField();
        add(guessField);

        guessButton = new JButton("Submit Guess");
        guessButton.addActionListener(this);
        add(guessButton);

        feedbackLabel = new JLabel("", SwingConstants.CENTER);
        add(feedbackLabel);

        attemptsLabel = new JLabel("", SwingConstants.CENTER);
        add(attemptsLabel);

        scoreLabel = new JLabel("Score: 0 | Round: 1", SwingConstants.CENTER);
        add(scoreLabel);

        // Start the game
        startNewRound();
        setVisible(true);
    }

    private void startNewRound() {
        Random rand = new Random();
        randomNumber = rand.nextInt(100) + 1; // 1 to 100
        attemptsLeft = maxAttempts;
        round++;
        guessField.setText("");
        guessField.requestFocus();
        feedbackLabel.setText("");
        attemptsLabel.setText("Attempts left: " + attemptsLeft);
        scoreLabel.setText("Score: " + score + " | Round: " + round);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String input = guessField.getText();
        try {
            int guess = Integer.parseInt(input);

            if (guess < 1 || guess > 100) {
                feedbackLabel.setText("❌ Enter a number between 1 and 100.");
                return;
            }

            attemptsLeft--;

            if (guess == randomNumber) {
                feedbackLabel.setText("✅ Correct! You guessed it!");
                int roundScore = attemptsLeft * 10;
                score += roundScore;
                JOptionPane.showMessageDialog(this,
                        "🎉 You won the round!\nScore this round: " + roundScore +
                        "\nTotal Score: " + score,
                        "Round Won", JOptionPane.INFORMATION_MESSAGE);

                int option = JOptionPane.showConfirmDialog(this, "Do you want to play another round?", "Play Again?",
                        JOptionPane.YES_NO_OPTION);
                if (option == JOptionPane.YES_OPTION) {
                    startNewRound();
                } else {
                    System.exit(0);
                }
            } else if (attemptsLeft > 0) {
                feedbackLabel.setText(guess < randomNumber ? "⬆ Too low!" : "⬇ Too high!");
                attemptsLabel.setText("Attempts left: " + attemptsLeft);
            } else {
                feedbackLabel.setText("❌ Out of attempts!");
                JOptionPane.showMessageDialog(this,
                        "😢 You've lost this round.\nThe correct number was: " + randomNumber,
                        "Game Over", JOptionPane.ERROR_MESSAGE);

                int option = JOptionPane.showConfirmDialog(this, "Do you want to play another round?", "Play Again?",
                        JOptionPane.YES_NO_OPTION);
                if (option == JOptionPane.YES_OPTION) {
                    startNewRound();
                } else {
                    System.exit(0);
                }
            }
        } catch (NumberFormatException ex) {
            feedbackLabel.setText("⚠️ Please enter a valid number.");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(GuessingGameGui::new);
    }
}

