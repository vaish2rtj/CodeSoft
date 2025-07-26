import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;

public class StudentManagementApp extends JFrame {
    private StudentManagementSystem sms = new StudentManagementSystem();
    private DefaultTableModel tableModel;

    private JTextField nameField = new JTextField(15);
    private JTextField rollField = new JTextField(15);
    private JTextField gradeField = new JTextField(15);
    private JTextField emailField = new JTextField(15);
    private JTable studentTable;

    public StudentManagementApp() {
        setTitle("Student Management System");
        setSize(750, 450);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        initUI();
        loadTable();
        setVisible(true);
    }

    private void initUI() {
        JPanel form = new JPanel(new GridLayout(5, 2, 5, 5));
        form.add(new JLabel("Name:")); form.add(nameField);
        form.add(new JLabel("Roll Number:")); form.add(rollField);
        form.add(new JLabel("Grade:")); form.add(gradeField);
        form.add(new JLabel("Email:")); form.add(emailField);

        JButton addBtn = new JButton("Add");
        JButton editBtn = new JButton("Edit");
        JButton delBtn = new JButton("Delete");
        JButton searchBtn = new JButton("Search");

        addBtn.addActionListener(e -> addStudent());
        editBtn.addActionListener(e -> editStudent());
        delBtn.addActionListener(e -> deleteStudent());
        searchBtn.addActionListener(e -> searchStudent());

        JPanel buttons = new JPanel();
        buttons.add(addBtn);
        buttons.add(editBtn);
        buttons.add(delBtn);
        buttons.add(searchBtn);

        String[] cols = {"Name", "Roll Number", "Grade", "Email"};
        tableModel = new DefaultTableModel(cols, 0);
        studentTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(studentTable);

        setLayout(new BorderLayout());
        add(form, BorderLayout.NORTH);
        add(buttons, BorderLayout.CENTER);
        add(scrollPane, BorderLayout.SOUTH);
    }

    private void loadTable() {
        tableModel.setRowCount(0);
        for (Student s : sms.getAllStudents()) {
            tableModel.addRow(new Object[]{s.getName(), s.getRollNumber(), s.getGrade(), s.getEmail()});
        }
    }

    private void addStudent() {
        try {
            Student s = new Student(
                nameField.getText().trim(),
                rollField.getText().trim(),
                gradeField.getText().trim(),
                emailField.getText().trim()
            );
            if (sms.searchStudent(s.getRollNumber()) != null) {
                JOptionPane.showMessageDialog(this, "Student already exists.");
                return;
            }
            sms.addStudent(s);
            loadTable();
            JOptionPane.showMessageDialog(this, "Student added.");
        } catch (IllegalArgumentException ex) {
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
        }
    }

    private void editStudent() {
        String roll = rollField.getText().trim();
        if (roll.isBlank()) {
            JOptionPane.showMessageDialog(this, "Enter roll number to edit.");
            return;
        }
        boolean updated = sms.editStudent(roll,
            nameField.getText().trim(),
            gradeField.getText().trim(),
            emailField.getText().trim()
        );
        loadTable();
        JOptionPane.showMessageDialog(this, updated ? "Student updated." : "Student not found.");
    }

    private void deleteStudent() {
        String roll = rollField.getText().trim();
        if (roll.isBlank()) {
            JOptionPane.showMessageDialog(this, "Enter roll number to delete.");
            return;
        }
        boolean removed = sms.removeStudent(roll);
        loadTable();
        JOptionPane.showMessageDialog(this, removed ? "Student removed." : "Student not found.");
    }

    private void searchStudent() {
        String roll = rollField.getText().trim();
        if (roll.isBlank()) {
            JOptionPane.showMessageDialog(this, "Enter roll number to search.");
            return;
        }
        Student s = sms.searchStudent(roll);
        if (s != null) {
            nameField.setText(s.getName());
            gradeField.setText(s.getGrade());
            emailField.setText(s.getEmail());
        } else {
            JOptionPane.showMessageDialog(this, "Student not found.");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(StudentManagementApp::new);
    }
}
