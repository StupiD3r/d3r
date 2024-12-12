package frm;

import Classeses.Student;  // Import the Student class
import Classeses.Subject;
import Classeses.Enrollment;
import com.mongodb.client.MongoDatabase;
import java.util.List;    // Import List class for storing students

public class Main {
    public static void main(String[] args) {
        // Connect to the MongoDB database
        MongoDatabase database = Student.connectToDatabase();

        // Fetch all students from the database
        List<Student> students = Student.getAllStudentsFromDB(database); // Updated method name

        // Print the fetched students
        Student.printStudents(students);
        
        MongoDatabase database1 = Subject.connectToDatabase();

        // Fetch all students from the database
        List<Subject> subjects = Subject.getAllSubjectsFromDB(database1); // Updated method name

        // Print the fetched students
        Subject.printSubjects(subjects);
        
        MongoDatabase database2 = Enrollment.connectToDatabase();

        // Fetch all students from the database
        List<Enrollment> enrollments = Enrollment.getAllEnrollmentFromDB(database2); // Updated method name

        // Print the fetched students
        Enrollment.printEnrollments(enrollments);
    }
}