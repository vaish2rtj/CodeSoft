import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ATMInterface extends JFrame implements ActionListener {
    private BankAccount account;
    private JTextField amountField;
    private JTextArea messageArea;

    public ATMInterface() {
        account = new BankAccount(1000.0); // Initial balance

        setTitle("ATM Interface");
        setSize(400, 300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(5, 1));

        JLabel label = new JLabel("Enter Amount:", SwingConstants.CENTER);
        add(label);

        amountField = new JTextField();
        add(amountField);

        JPanel buttonPanel = new JPanel();
        JButton withdrawBtn = new JButton("Withdraw");
        JButton depositBtn = new JButton("Deposit");
        JButton checkBtn = new JButton("Check Balance");
        buttonPanel.add(withdrawBtn);
        buttonPanel.add(depositBtn);
        buttonPanel.add(checkBtn);
        add(buttonPanel);

        messageArea = new JTextArea();
        messageArea.setEditable(false);
        add(new JScrollPane(messageArea));

        withdrawBtn.addActionListener(this);
        depositBtn.addActionListener(this);
        checkBtn.addActionListener(this);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = ((JButton) e.getSource()).getText();
        String input = amountField.getText();
        double amount = 0;

        if (!command.equals("Check Balance")) {
            try {
                amount = Double.parseDouble(input);
            } catch (NumberFormatException ex) {
                messageArea.setText("❌ Please enter a valid number.");
                return;
            }
        }

        switch (command) {
            case "Withdraw":
                if (account.withdraw(amount)) {
                    messageArea.setText("✅ Withdrawal successful.\nRemaining balance: ₹" + account.getBalance());
                } else {
                    messageArea.setText("❌ Insufficient balance or invalid amount.");
                }
                break;
            case "Deposit":
                if (account.deposit(amount)) {
                    messageArea.setText("✅ Deposit successful.\nCurrent balance: ₹" + account.getBalance());
                } else {
                    messageArea.setText("❌ Invalid deposit amount.");
                }
                break;
            case "Check Balance":
                messageArea.setText("💰 Current Balance: ₹" + account.getBalance());
                break;
        }
        amountField.setText("");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(ATMInterface::new);
    }
}
