package Classes;

import java.util.ArrayList;
import java.util.List;
import org.bson.Document;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.FindIterable;

public class Enrollment {
    private String StudentID;
    private String SubjectCode;
    private double Grade;

    public Enrollment (){}
    
    public Enrollment(String StudentID, String SubjectCode, double Grade) {
        this.StudentID = StudentID;
        this.SubjectCode = SubjectCode;
        this.Grade = Grade;
    }

    public String getStudentID() {
        return StudentID;
    }

    public void setStudentID(String StudentID) {
        this.StudentID = StudentID;
    }

    public String getSubjectCode() {
        return SubjectCode;
    }

    public void setSubjectCode(String SubjectCode) {
        this.SubjectCode = SubjectCode;
    }

    public double getGrade() {
        return Grade;
    }

    public void setGrade(double Grade) {
        this.Grade = Grade;
    }

    public static MongoDatabase connectToDatabase() {
        String uri = "mongodb://localhost:27017";
        MongoClient mongoClient = MongoClients.create(uri);
        MongoDatabase database = mongoClient.getDatabase("Enrollment"); // Change to the correct database name
        System.out.println("Connected to database: " + database.getName());
        return database;
    }
    
    public static List<Enrollment> getAllEnrollmentFromDB(MongoDatabase database) {
    MongoCollection<Document> enrollmentCollection = database.getCollection("Enrollment");

    List<Enrollment> enrollments = new ArrayList<>();

    FindIterable<Document> enrollmentIterable = enrollmentCollection.find();
    for (Document enrollmentDoc : enrollmentIterable) {
        String StudentID = enrollmentDoc.getString("StudentID");
        String SubjectCode = enrollmentDoc.getString("SubjectCode");

        double Grade;
        // Check and convert the Grade to a double if possible
        Object gradeValue = enrollmentDoc.get("Grade");
        if (gradeValue instanceof Double) {
            Grade = (Double) gradeValue;
        } else if (gradeValue instanceof Integer) {
            Grade = ((Integer) gradeValue).doubleValue(); // Convert Integer to double
        } else if (gradeValue instanceof String) {
            Grade = Double.parseDouble((String) gradeValue); // Convert String to double
        } else {
            Grade = 0.0; // Default or error handling if type is unexpected
            System.err.println("Unexpected type for Grade: " + gradeValue.getClass().getSimpleName());
        }

        Enrollment enrollment = new Enrollment(StudentID, SubjectCode, Grade);
        enrollments.add(enrollment);
    }
    return enrollments;
}

    
    public static void printEnrollments(List<Enrollment> enrollments) {
        if (enrollments == null || enrollments.isEmpty()) {
            System.out.println("No students found.");
        } else {
            for (Enrollment enrollment : enrollments) {
                System.out.println("ID: " + enrollment.getStudentID());
                System.out.println("Name: " + enrollment.getSubjectCode());
                System.out.println("Course: " + enrollment.getGrade());
                System.out.println("---------------------------");
            }
        }
    }
    
    public static void updGrade(double x, String y, String z){
        // Connect to MongoDB and access the Enrollment collection
        MongoDatabase database2 = Enrollment.connectToDatabase();
        MongoCollection<Document> collection = database2.getCollection("Enrollment");

        // Find the matching document and update the grade
        Document filter = new Document("StudentID", y).append("SubjectCode", z);
        Document updateOperation = new Document("$set", new Document("Grade", x));

        // Perform the update
        collection.updateOne(filter, updateOperation);
    }
    
    public static void insertEnrollments(List<Enrollment> enrollments) {
        // Connect to MongoDB and access the Enrollment collection
        MongoDatabase database = Enrollment.connectToDatabase();
        MongoCollection<Document> collection = database.getCollection("Enrollment");

        List<Document> documents = new ArrayList<>();
        for (Enrollment enrollment : enrollments) {
            // Create a Document for each Enrollment object
            Document doc = new Document("StudentID", enrollment.getStudentID())
                    .append("SubjectCode", enrollment.getSubjectCode())
                    .append("Grade", enrollment.getGrade());
            // Add the Document to the list
            documents.add(doc);
        }

        // Insert all documents into the collection
        if (!documents.isEmpty()) {
            collection.insertMany(documents);
            System.out.println("Inserted " + documents.size() + " enrollment records successfully.");
        } else {
            System.out.println("No enrollment records to insert.");
        }
    }
}
