/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package Student;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
/**
 *
 * @author lduc9
 */

class Student {
    private final String id;
    private String name;
    private double marks;

    public Student(String id, String name, double marks) {
        this.id = id;
        this.name = name;
        this.marks = marks;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getMarks() {
        return marks;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setMarks(double marks) {
        this.marks = marks;
    }

    public String getRank() {
        if (marks < 5.0) return "Fail";
        else if (marks < 6.5) return "Medium";
        else if (marks < 7.5) return "Good";
        else if (marks < 9.0) return "Very Good";
        else return "Excellent";
    }

    @Override
    public String toString() {
        return "ID: " + id + ", Name: " + name + ", Marks: " + marks + ", Rank: " + getRank();
    }
}

public class StudentManagement {
    private static final ArrayList<Student> students = new ArrayList<>();
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            System.out.println("\n--- Student Manager Menu ---");
            System.out.println("1. Add Student");
            System.out.println("2. Edit Student");
            System.out.println("3. Delete Student");
            System.out.println("4. Display Students");
            System.out.println("5. Sort Students (Bubble Sort)");
            System.out.println("6. Sort Students (Selection Sort)");
            System.out.println("7. Search Student by ID");
            System.out.println("8. Exit");
            int choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {
                case 1:
                    addStudent();
                    break;
                case 2:
                    editStudent();
                    break;
                case 3:
                    deleteStudent();
                    break;
                case 4:
                    displayAllStudents();
                    break;
                case 5:
                    bubbleSort ();
                    break;
                case 6:
                    selectionSort ();
                    break;
                case 7:
                    searchStudent ();
                case 8:
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid option! Please try again.");
            }
        }
    }

    // Thêm sinh viên mới
    private static void addStudent() {
        System.out.print("Enter Student ID: ");
        String id = scanner.nextLine();
        System.out.print("Enter Student Name: ");
        String name = scanner.nextLine();
        System.out.print("Enter Student Marks: ");
        double marks = Double.parseDouble(scanner.nextLine());
        students.add(new Student(id, name, marks));
        System.out.println("Student added successfully!");
        displayAllStudents();  // Hiển thị bảng sau khi thêm sinh viên
    }

    // Sửa thông tin sinh viên
    private static void editStudent() {
        System.out.print("Enter Student ID to edit: ");
        String id = scanner.nextLine();
        for (Student student : students) {
            if (student.getId().equals(id)) {
                System.out.print("Enter new name: ");
                student.setName(scanner.nextLine());
                System.out.print("Enter new marks: ");
                student.setMarks(Double.parseDouble(scanner.nextLine()));
                System.out.println("Student updated successfully!");
                displayAllStudents();  // Hiển thị bảng sau khi sửa sinh viên
                return;
            }
        }
        System.out.println("Student not found!");
    }

    // Xóa sinh viên
    private static void deleteStudent() {
        System.out.print("Enter Student ID to delete: ");
        String id = scanner.nextLine();
        students.removeIf(student -> student.getId().equals(id));
        System.out.println("Student deleted successfully!");
        displayAllStudents();  // Hiển thị bảng sau khi xóa sinh viên
    }

    // Tìm kiếm sinh viên theo ID
    // Tìm kiếm sinh viên theo tên
    private static void searchStudent() {
        System.out.print("Enter Student Name to search: ");
        String name = scanner.nextLine();
        boolean found = false;

        for (Student student : students) {
            if (student.getName().equalsIgnoreCase(name)) {  // So sánh không phân biệt hoa thường
                System.out.println(student);
                found = true;
            }
        }

        if (!found) {
            System.out.println("Student not found!");
        }
    }
    public static void bubbleSort() {
        int n = students.size();
        for (int i = 0; i < n - 1; i++) {
    for (int j = 0; j < n - i - 1; j++) {
                if (students.get(j).getMarks() > students.get(j + 1).getMarks()) {
                    Student temp = students.get(j);
                    students.set(j, students.get(j + 1));
                    students.set(j + 1, temp);
                }
            }
        }
        System.out.println("Students sorted by marks using Bubble Sort.");
        displayAllStudents();
    }

    public static void selectionSort() {
        int n = students.size();
        for (int i = 0; i < n - 1; i++) {
            int minIdx = i;
            for (int j = i + 1; j < n; j++) {
                if (students.get(j).getMarks() < students.get(minIdx).getMarks()) {
                    minIdx = j;
                }
            }
            Student temp = students.get(minIdx);
            students.set(minIdx, students.get(i));
            students.set(i, temp);
        }
        System.out.println("Students sorted by marks using Selection Sort.");
        displayAllStudents();
    }
    // Hiển thị danh sách sinh viên dưới dạng bảng
    private static void displayAllStudents() {
        if (students.isEmpty()) {
            System.out.println("No students available.");
        } else {
            System.out.printf("%-10s %-20s %-10s %-10s\n", "ID", "Name", "Marks", "Rank");
            System.out.println("----------------------------------------------------------");
            for (Student student : students) {
                System.out.printf("%-10s %-20s %-10.2f %-10s\n",
                        student.getId(),
                        student.getName(),
                        student.getMarks(),
                        student.getRank());
            }
        }
    }
}

