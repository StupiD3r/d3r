package Classeses;

import java.util.ArrayList;
import java.util.List;
import org.bson.Document;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Sorts;

public class Semester {
    private int SemesterID;  // Primary Key
    private String SemesterName;
    private String SchoolYear;

    // Default constructor
    public Semester() {}

    // Constructor with parameters
    public Semester(int SemesterID, String SemesterName, String SchoolYear) {
        this.SemesterID = SemesterID;
        this.SemesterName = SemesterName;
        this.SchoolYear = SchoolYear;
    }

    // Getters and setters
    public int getSemesterID() {
        return SemesterID;
    }

    public void setSemesterID(int SemesterID) {
        this.SemesterID = SemesterID;
    }

    public String getSemesterName() {
        return SemesterName;
    }

    public void setSemesterName(String SemesterName) {
        this.SemesterName = SemesterName;
    }

    public String getSchoolYear() {
        return SchoolYear;
    }

    public void setSchoolYear(String SchoolYear) {
        this.SchoolYear = SchoolYear;
    }

    // Connect to MongoDB
    public static MongoDatabase connectToDatabase() {
        String uri = "mongodb://localhost:27017";
        MongoClient mongoClient = MongoClients.create(uri);
        MongoDatabase database = mongoClient.getDatabase("EnrollmentFinals"); // Change to the correct database name
        System.out.println("Connected to database: " + database.getName());
        return database;
    }

    // Fetch all semesters from the MongoDB database
    public static List<Semester> getAllSemestersFromDB(MongoDatabase database) {
        MongoCollection<Document> semesterCollection = database.getCollection("Semester"); // Access the 'Semester' collection
        List<Semester> semesters = new ArrayList<>();

        for (Document doc : semesterCollection.find().sort(Sorts.ascending("SemesterID"))) {
            int semesterID = doc.getInteger("SemesterID");
            String semesterName = doc.getString("SemesterName");
            String schoolYear = doc.getString("SchoolYear");

            semesters.add(new Semester(semesterID, semesterName, schoolYear));
        }

        return semesters;
    }

    public void addItem(int semesterID, String semesterName, String schoolYear) {
        MongoDatabase database = connectToDatabase(); // Connect to the MongoDB database
        MongoCollection<Document> semColl = database.getCollection("Semester"); // Access the 'Semester' collection

        // Create a new document to insert
        Document doc = new Document("SemesterID", semesterID)
                .append("SemesterName", semesterName)
                .append("SchoolYear", schoolYear);

        // Insert the document into the collection
        semColl.insertOne(doc);
        System.out.println("Semester added: " + doc.toJson());
    }


}
