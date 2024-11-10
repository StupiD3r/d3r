package Classes;

import java.util.ArrayList;
import java.util.List;
import org.bson.Document;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.FindIterable;

public class Student {
    private String studentID;  // Primary Key
    private String studentName;
    private String course;
    private String yearlevel;

    // Default constructor
    public Student() {}

    // Constructor with parameters
    public Student(String studentID, String studentName, String course, String yearlevel) {
        this.studentID = studentID;
        this.studentName = studentName;
        this.course = course;
        this.yearlevel = yearlevel;
    }

    // Getter and Setter methods for each field
    public String getStudentID() {
        return studentID;
    }

    public void setStudentID(String studentID) {
        this.studentID = studentID;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public String getYearlevel() {
        return yearlevel;
    }

    public void setYearlevel(String yearlevel) {
        this.yearlevel = yearlevel;
    }

    // Connect to MongoDB
    public static MongoDatabase connectToDatabase() {
        String uri = "mongodb://localhost:27017";
        MongoClient mongoClient = MongoClients.create(uri);
        MongoDatabase database = mongoClient.getDatabase("Enrollment"); // Change to the correct database name
        System.out.println("Connected to database: " + database.getName());
        return database;
    }

    // Fetch all students from the MongoDB database
    public static List<Student> getAllStudentsFromDB(MongoDatabase database) {
        MongoCollection<Document> studentCollection = database.getCollection("Student"); // Access the 'Student' collection
        
        List<Student> students = new ArrayList<>();

        // Fetch all student documents
        FindIterable<Document> studentIterable = studentCollection.find();
        for (Document studentDoc : studentIterable) {
            String studentID = studentDoc.getString("StudentID");
            String studentName = studentDoc.getString("StudentName");
            String course = studentDoc.getString("Course");
            String yearLevel = studentDoc.getString("YearLevel");

            
            
            // Create a Student object with the fetched data
            Student student = new Student(studentID, studentName, course, yearLevel);
            students.add(student);
        }
        return students;
    }

    public static void addStudent(String id, String name, String crs, String yl, MongoDatabase database) {
        MongoCollection<Document> studentCollection = database.getCollection("Student"); // Access the 'Student' collection
        
        Document doc = new Document("StudentID", id)
                .append("StudentName", name)
                .append("Course", crs)
                .append("YearLevel", yl);
        
        studentCollection.insertOne(doc); // Insert the document into the collection
        System.out.println("Student added: " + doc.toJson()); // Optional: Print the inserted document
    }
    
    // Print the students list to the console
    public static void printStudents(List<Student> students) {
        if (students == null || students.isEmpty()) {
            System.out.println("No students found.");
        } else {
            for (Student student : students) {
                System.out.println("ID: " + student.getStudentID());
                System.out.println("Name: " + student.getStudentName());
                System.out.println("Course: " + student.getCourse());
                System.out.println("Year Level: " + student.getYearlevel());
                System.out.println("---------------------------");
            }
        }
    }
}