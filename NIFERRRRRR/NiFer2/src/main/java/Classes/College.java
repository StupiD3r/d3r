package Classes;

import java.util.ArrayList;
import java.util.List;
import org.bson.Document;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Sorts;

public class College {
    private int CollegeID;     // Primary Key
    private String CollegeName;

    // Default constructor
    public College() {}

    // Constructor with parameters
    public College(int CollegeID, String CollegeName) {
        this.CollegeID = CollegeID;
        this.CollegeName = CollegeName;
    }

    // Getters and setters
    public int getCollegeID() {
        return CollegeID;
    }

    public void setCollegeID(int CollegeID) {
        this.CollegeID = CollegeID;
    }

    public String getCollegeName() {
        return CollegeName;
    }

    public void setCollegeName(String CollegeName) {
        this.CollegeName = CollegeName;
    }

    // Connect to MongoDB
    public static MongoDatabase connectToDatabase() {
        String uri = "mongodb://localhost:27017";
        MongoClient mongoClient = MongoClients.create(uri);
        MongoDatabase database = mongoClient.getDatabase("EnrollmentFinals"); // Change to the correct database name
        System.out.println("Connected to database: " + database.getName());
        return database;
    }

    // Add a new college to the MongoDB collection
    public static void addCollege(int id, String name, MongoDatabase database) {
        MongoCollection<Document> collegeCollection = database.getCollection("College");

        Document doc = new Document("CollegeID", id)
                .append("CollegeName", name);

        collegeCollection.insertOne(doc);
        System.out.println("College added: " + doc.toJson());
    }

    // Fetch all colleges from the MongoDB database
    public static List<College> getAllCollegesFromDB(MongoDatabase database) {
        MongoCollection<Document> collegeCollection = database.getCollection("College");
        List<College> colleges = new ArrayList<>();

        for (Document doc : collegeCollection.find().sort(Sorts.ascending("CollegeID"))) {
            int collegeID = doc.getInteger("CollegeID");
            String collegeName = doc.getString("CollegeName");

            colleges.add(new College(collegeID, collegeName));
        }

        return colleges;
    }

    // Print the colleges list to the console
    public static void printColleges(List<College> colleges) {
        if (colleges == null || colleges.isEmpty()) {
            System.out.println("No colleges found.");
        } else {
            for (College college : colleges) {
                System.out.println("ID: " + college.getCollegeID());
                System.out.println("Name: " + college.getCollegeName());
                System.out.println("---------------------------");
            }
        }
    }
}
