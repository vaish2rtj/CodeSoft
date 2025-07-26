# 🧑‍🎓 Student Management System (Java Swing)

A simple desktop-based Student Management System built using **Java Swing**, with support for adding, editing, deleting, searching, and displaying student records. Data is saved persistently using file storage (`students.dat`).

---

## 🚀 Features

- Add a new student with details: Name, Roll Number, Grade, and Email
- Edit existing student records using Roll Number
- Delete a student
- Search for a student by Roll Number
- Display all students in a table (JTable)
- Persistent storage using file serialization
- Input validation for clean data entry

---

## 🛠️ Technologies Used

- Java (JDK 8 or above)
- Java Swing (GUI)
- File I/O (Object Serialization)

---

## 📂 Project Structure

->StudentManagementSystem/ ├── Student.java                  
-> Student data model ├── StudentManagementSystem.java 
->Manages student list and file I/O ├── StudentManagementApp.java    
 ->GUI using Java Swing ├── students.dat                
 -> Data file (auto-generated, ignored by Git) ├── .gitignore └── README.md

---

## 🧪 How to Run

### 🧱 Prerequisites
- Java JDK 8 or higher installed

### ▶️ Compile & Run
```bash
javac *.java
java StudentManagementApp



---

🧼 Input Validation

No fields can be left empty

Email must contain @

Roll number must be unique when adding

🙋‍♀️ Author
Created by Vaishnavi Verma