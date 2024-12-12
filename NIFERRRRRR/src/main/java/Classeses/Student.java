package Classeses;

import java.util.ArrayList;
import java.util.List;
import org.bson.Document;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Sorts;

public class Student {
    private String studentID;  // Primary Key
    private String studentName;
    private String course;
    private String yearlevel;
    private String CollegeID;

    // Default constructor
    public Student() {}

    // Parameterized constructor
    public Student(String studentID, String studentName, String course, String yearlevel, String CollegeID) {
        this.studentID = studentID;
        this.studentName = studentName;
        this.course = course;
        this.yearlevel = yearlevel;
        this.CollegeID = CollegeID;
    }

    // Getters and Setters
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

    public String getCollegeID() {
        return CollegeID;
    }

    public void setCollegeID(String CollegeID) {
        this.CollegeID = CollegeID;
    }
    
    // Connect to MongoDB
    public static MongoDatabase connectToDatabase() {
        try {
            String uri = "mongodb://localhost:27017";
            MongoClient mongoClient = MongoClients.create(uri);
            MongoDatabase database = mongoClient.getDatabase("EnrollmentFinals");
            System.out.println("Successfully connected to database: " + database.getName());
            return database;
        } catch (Exception e) {
            System.err.println("Error connecting to database: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

    // Fetch all students from the database
   public static List<Student> getAllStudentsFromDB(MongoDatabase database) {
    List<Student> students = new ArrayList<>();
    
    try {
        MongoCollection<Document> studentCollection = database.getCollection("Student");
        
        for (Document doc : studentCollection.find().sort(Sorts.ascending("StudentName"))) {
            // Safely extract values with type checking and conversion
            Object studentIDObj = doc.get("StudentID");
            Object studentNameObj = doc.get("StudentName");
            Object courseObj = doc.get("Course");
            Object yearlevelObj = doc.get("YearLevel");
            Object collegeIDObj = doc.get("CollegeID");
            
            // Safely get values
            String studentID = studentIDObj != null ? studentIDObj.toString() : null;
            String studentName = studentNameObj != null ? studentNameObj.toString() : null;
            String course = courseObj != null ? courseObj.toString() : null;
            String yearlevel = yearlevelObj != null ? yearlevelObj.toString() : null;
            String collegeID = collegeIDObj != null ? collegeIDObj.toString() : null;
            
            // Only add if essential fields are not null
            if (studentID != null && studentName != null) {
                students.add(new Student(studentID, studentName, course, yearlevel, collegeID));
            }
        }
    } catch (Exception e) {
        System.err.println("Error fetching students: " + e.getMessage());
        e.printStackTrace();
    }
    
    return students;
}

    @Override
    public String toString() {
        return "Student{" +
            "studentID='" + studentID + '\'' +
            ", studentName='" + studentName + '\'' +
            ", course='" + course + '\'' +
            ", yearlevel='" + yearlevel + '\'' +
            ", CollegeID=" + CollegeID +
            '}';
    }
}