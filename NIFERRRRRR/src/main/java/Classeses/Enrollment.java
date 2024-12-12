package Classeses;

import java.util.ArrayList;
import java.util.List;
import org.bson.Document;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.FindIterable;
import com.mongodb.client.model.Sorts;

public class Enrollment {
    private String EnrollmentID;
    private String StudentID;
    private double SemesterID;
    private ArrayList<String> SubjectCode;
    private ArrayList<Double> Grade;

    public Enrollment() {}

    public Enrollment(String EnrollmentID, String StudentID, double SemesterID, ArrayList<String> SubjectCode, ArrayList<Double> Grade) {
        this.EnrollmentID = EnrollmentID;
        this.StudentID = StudentID;
        this.SemesterID = SemesterID;
        this.SubjectCode = SubjectCode;
        this.Grade = Grade;
    }

    public String getEnrollmentID() {
        return EnrollmentID;
    }

    public void setEnrollmentID(String EnrollmentID) {
        this.EnrollmentID = EnrollmentID;
    }

    public String getStudentID() {
        return StudentID;
    }

    public void setStudentID(String StudentID) {
        this.StudentID = StudentID;
    }

    public double getSemesterID() {
        return SemesterID;
    }

    public void setSemesterID(double SemesterID) {
        this.SemesterID = SemesterID;
    }

    public ArrayList<String> getSubjectCode() {
        if (SubjectCode == null) {
            return new ArrayList<>();
        }
        // Ensure SubjectCode is always returned as an ArrayList<String>
        return new ArrayList<>(SubjectCode);
    }

    public void setSubjectCode(ArrayList<String> SubjectCode) {
        this.SubjectCode = SubjectCode;
    }

    public ArrayList<Double> getGrade() {
        return Grade;
    }

    public void setGrade(ArrayList<Double> Grade) {
        this.Grade = Grade;
    }

    public static MongoDatabase connectToDatabase() {
        String uri = "mongodb://localhost:27017";
        MongoClient mongoClient = MongoClients.create(uri);
        MongoDatabase database = mongoClient.getDatabase("EnrollmentFinals"); // Change to the correct database name
        System.out.println("Connected to database: " + database.getName());
        return database;
    }

    public static List<Student> getAllStudentsFromDB(MongoDatabase database) {
    MongoCollection<Document> studentCollection = database.getCollection("Student"); // Access the 'Student' collection
    List<Student> students = new ArrayList<>();

    for(Document doc : studentCollection.find().sort(Sorts.ascending("StudentName"))){
        String studentID = doc.getString("StudentID");
        String studentName = doc.getString("StudentName");
        String course = doc.getString("Course");
        String yearlevel = doc.getString("YearLevel");
        String college = doc.getString("CollegeID");

        if(studentID != null && studentName != null){
            students.add(new Student(studentID, studentName, course, yearlevel, college));
        }
    }

    return students;
}

    public static void printEnrollments(List<Enrollment> enrollments) {
        if (enrollments == null || enrollments.isEmpty()) {
            System.out.println("No students found.");
        } else {
            for (Enrollment enrollment : enrollments) {
                System.out.println("ID: " + enrollment.getStudentID());
                System.out.print("Subject Codes: ");
                for (String code : enrollment.getSubjectCode()) {
                    System.out.print(code + " ");
                }
                System.out.println();

                System.out.print("Grades: ");
                for (double grade : enrollment.getGrade()) {
                    System.out.print(grade + " ");
                }
                System.out.println();
                System.out.println("---------------------------");
            }
        }
    }

    public static void updGrade(double x, String studentID, String subjectCode) {
        // Connect to MongoDB and access the Enrollment collection
        MongoDatabase database2 = Enrollment.connectToDatabase();
        MongoCollection<Document> collection = database2.getCollection("Enrollment");

        // Update the grade for the given student and subject
        Document filter = new Document("StudentID", studentID)
                .append("SubjectCode", subjectCode);
        Document updateOperation = new Document("$set", new Document("Grade", x));

        collection.updateOne(filter, updateOperation);
    }

    public static void insertEnrollments(List<Enrollment> enrollments) {
        // Connect to MongoDB and access the Enrollment collection
        MongoDatabase database = Enrollment.connectToDatabase();
        MongoCollection<Document> collection = database.getCollection("Enrollment");

        List<Document> documents = new ArrayList<>();
        for (Enrollment enrollment : enrollments) {
            Document doc = new Document("EnrollmentID", enrollment.getEnrollmentID())
                    .append("StudentID", enrollment.getStudentID())
                    .append("SemesterID", enrollment.getSemesterID())
                    .append("SubjectCode", enrollment.getSubjectCode())
                    .append("Grade", enrollment.getGrade());
            documents.add(doc);
        }

        if (!documents.isEmpty()) {
            collection.insertMany(documents);
            System.out.println("Inserted " + documents.size() + " enrollment records successfully.");
        } else {
            System.out.println("No enrollment records to insert.");
        }
    }
}
