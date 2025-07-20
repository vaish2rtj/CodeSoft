import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GradeCalculatorSwing {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Grade Calculator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);
        
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(7, 2, 10, 10));

        JLabel[] subjectLabels = new JLabel[5];
        JTextField[] markFields = new JTextField[5];

        for (int i = 0; i < 5; i++) {
            subjectLabels[i] = new JLabel("Subject " + (i + 1) + " Marks:");
            markFields[i] = new JTextField();
            panel.add(subjectLabels[i]);
            panel.add(markFields[i]);
        }

        JButton calculateButton = new JButton("Calculate Grade");
        JTextField resultField = new JTextField();
        resultField.setEditable(false);

        // Action listener for the button
        calculateButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                double[] marks = new double[5];
                try {
                    for (int i = 0; i < 5; i++) {
                        marks[i] = Double.parseDouble(markFields[i].getText());
                    }
                    // Call logic class method
                    String grade = GradeCalculator.calculateGrade(marks);
                    resultField.setText("Grade: " + grade);
                } catch (NumberFormatException ex) {
                    resultField.setText("Please enter valid numbers!");
                }
            }
        });

        panel.add(calculateButton);
        panel.add(resultField);

        frame.add(panel);
        frame.setVisible(true);
    }
}