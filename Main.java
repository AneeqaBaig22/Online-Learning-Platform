package project;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class Main {

    static List<Course> courses = new ArrayList<>();
    static List<Student> students = new ArrayList<>();
    static List<Instructor> instructors = new ArrayList<>();
    static List<Assignment> assignments = new ArrayList<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        // Deadline Checker Thread
        DeadlineChecker checker = new DeadlineChecker(assignments);
        checker.start();

        while (running) {
            System.out.println("\n--- Learning Platform Menu ---");
            System.out.println("1. Add Course");
            System.out.println("2. Add Student");
            System.out.println("3. Add Instructor");
            System.out.println("4. Add Assignment");
            System.out.println("5. Submit Assignment");
            System.out.println("6. Exit and Stop Thread");

            int choice = scanner.nextInt();
            scanner.nextLine();  // Consume newline character after the number input

            switch (choice) {
                case 1:
                    // Add a new course
                    System.out.print("Enter course name: ");
                    String courseName = scanner.nextLine();
                    Course newCourse = new Course();
                    newCourse.courseName = courseName;
                    courses.add(newCourse);
                    System.out.println("Course '" + courseName + "' added.");
                    break;

                case 2:
                    // Add a new student
                    System.out.print("Enter student name: ");
                    String studentName = scanner.nextLine();
                    System.out.print("Enter student email: ");
                    String studentEmail = scanner.nextLine();
                    Student newStudent = new Student();
                    newStudent.name = studentName;
                    newStudent.email = studentEmail;
                    students.add(newStudent);
                    System.out.println("Student '" + studentName + "' added.");
                    break;

                case 3:
                    // Add a new instructor
                    System.out.print("Enter instructor name: ");
                    String instructorName = scanner.nextLine();
                    Instructor newInstructor = new Instructor();
                    newInstructor.name = instructorName;
                    instructors.add(newInstructor);
                    System.out.println("Instructor '" + instructorName + "' added.");
                    break;

                case 4:
                    // Add an assignment
                    System.out.print("Enter assignment title: ");
                    String assignmentTitle = scanner.nextLine();
                    System.out.print("Enter deadline (milliseconds from now): ");
                    long deadlineMillis = scanner.nextLong();
                    scanner.nextLine();  // Consume newline
                    Assignment newAssignment = new Assignment();
                    newAssignment.title = assignmentTitle;
                    newAssignment.deadline = new Date(System.currentTimeMillis() + deadlineMillis); // Convert to date
                    assignments.add(newAssignment);
                    System.out.println("Assignment '" + assignmentTitle + "' added.");
                    break;

                case 5:
                    // Submit an assignment
                    System.out.print("Enter student name: ");
                    String submitStudentName = scanner.nextLine();
                    System.out.print("Enter assignment title: ");
                    String submitAssignmentTitle = scanner.nextLine();

                    Student submitStudent = findStudent(submitStudentName);
                    Assignment submitAssignment = findAssignment(submitAssignmentTitle);

                    if (submitStudent != null && submitAssignment != null) {
                        submitStudent.submitAssignment(submitAssignment);
                        System.out.println(submitStudent.name + " submitted the assignment.");
                    } else {
                        System.out.println("Student or Assignment not found.");
                    }
                    break;

                case 6:
                    // Exit and stop thread
                    System.out.println("Exiting program...");
                    running = false;
                    checker.stopChecker();  // Stop the DeadlineChecker thread
                    break;

                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        }

        scanner.close();
    }

    // Helper method to find a student by name
    private static Student findStudent(String name) {
        for (Student student : students) {
            if (student.name.equals(name)) {
                return student;
            }
        }
        return null;
    }

    // Helper method to find an assignment by title
    private static Assignment findAssignment(String title) {
        for (Assignment assignment : assignments) {
            if (assignment.title.equals(title)) {
                return assignment;
            }
        }
        return null;
    }
}
