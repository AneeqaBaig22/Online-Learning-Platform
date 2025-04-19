package project;

import java.util.ArrayList;
import java.util.List;

// User class
class User {
    String name;
    String email;

    void login() {
        System.out.println(name + " logged in.");
    }

    void logout() {
        System.out.println(name + " logged out.");
    }
}

// Student class
class Student extends User {
    List<Course> enrolledCourses = new ArrayList<>();

    void submitAssignment(Assignment a) {
        System.out.println(name + " submitted: " + a.title);
    }
}

// Instructor class
class Instructor extends User {
    List<Course> teachingCourses = new ArrayList<>();

    void giveFeedback(Assignment a, String feedback) {
        a.feedback = feedback;
        System.out.println("Feedback given for: " + a.title + " — " + feedback);
    }
}

// Admin class
class Admin extends User {
    void addCourse(Course c) {
        System.out.println("Admin added new course: " + c.courseName);
    }
}
    
