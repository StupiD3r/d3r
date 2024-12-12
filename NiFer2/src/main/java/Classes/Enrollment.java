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
    private String EnrollmentID;
    private String StudentID;
    private double SemesterID;
    private String[] SubjectCode;
    private double[] Grade;

    public Enrollment (){}

    public Enrollment(String EnrollmentID, String StudentID, double SemesterID, String[] SubjectCode, double[] Grade) {
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

    public String[] getSubjectCode() {
        return SubjectCode;
    }

    public void setSubjectCode(String[] SubjectCode) {
        this.SubjectCode = SubjectCode;
    }

    public double[] getGrade() {
        return Grade;
    }

    public void setGrade(double[] Grade) {
        this.Grade = Grade;
    }

    public static MongoDatabase connectToDatabase() {
        String uri = "mongodb://localhost:27017";
        MongoClient mongoClient = MongoClients.create(uri);
        MongoDatabase database = mongoClient.getDatabase("EnrollmentFinals"); // Change to the correct database name
        System.out.println("Connected to database: " + database.getName());
        return database;
    }
    
    public static List<Enrollment> getAllEnrollmentFromDB(MongoDatabase database) {
        MongoCollection<Document> enrollmentCollection = database.getCollection("Enrollment");
        List<Enrollment> enrollments = new ArrayList<>();
        FindIterable<Document> enrollmentIterable = enrollmentCollection.find();

        for (Document enrollmentDoc : enrollmentIterable) {
            String EnrollmentID = enrollmentDoc.getString("EnrollmentID");
            String StudentID = enrollmentDoc.getString("StudentID");

            // Fetch SubjectCode as an array
            List<String> subjectCodesList = (List<String>) enrollmentDoc.get("SubjectCode");
            String[] subjectCodesArray = subjectCodesList.toArray(new String[0]);

            // Fetch Grade as an array and handle type conversion
            List<?> gradesList = (List<?>) enrollmentDoc.get("Grade");
            double[] gradesArray = new double[gradesList.size()];

            for (int i = 0; i < gradesList.size(); i++) {
                Object gradeObj = gradesList.get(i);
                try {
                    if (gradeObj instanceof Number) {
                        gradesArray[i] = ((Number) gradeObj).doubleValue();
                    } else if (gradeObj instanceof String) {
                        gradesArray[i] = Double.parseDouble((String) gradeObj);
                    } else {
                        System.out.println("Invalid grade format: " + gradeObj);
                        gradesArray[i] = 0.0; // Default value if conversion fails
                    }
                } catch (Exception e) {
                    System.out.println("Error parsing grade: " + e.getMessage());
                    gradesArray[i] = 0.0; // Default value if an exception occurs
                }
            }

            double SemesterID = enrollmentDoc.getDouble("SemesterID");

            // Create and add Enrollment object to the list
            Enrollment enrollment = new Enrollment(EnrollmentID, StudentID, SemesterID, subjectCodesArray, gradesArray);
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
