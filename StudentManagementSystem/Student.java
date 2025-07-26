import java.io.Serializable;
public class Student implements Serializable {
    private String name, rollNumber, grade, email;

    public Student(String name, String rollNumber, String grade, String email) {
        if (name.isBlank() || rollNumber.isBlank() || grade.isBlank() || !email.matches("^(.+)@(.+)$")) {
            throw new IllegalArgumentException("Invalid input.");
        }
        this.name = name;
        this.rollNumber = rollNumber;
        this.grade = grade;
        this.email = email;
    }

    public String getName() { return name; }
    public String getRollNumber() { return rollNumber; }
    public String getGrade() { return grade; }
    public String getEmail() { return email; }

    public void setName(String name) { if (!name.isBlank()) this.name = name; }
    public void setGrade(String grade) { if (!grade.isBlank()) this.grade = grade; }
    public void setEmail(String email) { if (email.matches("^(.+)@(.+)$")) this.email = email; }
}