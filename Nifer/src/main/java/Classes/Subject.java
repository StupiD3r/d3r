package Classes;

import java.util.ArrayList;
import java.util.List;
import org.bson.Document;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.FindIterable;
import com.mongodb.client.model.Sorts;

public class Subject {
    private String SubjectCode;
    private String Description;
    private int Units;

    public Subject() {}

    public Subject(String SubjectCode, String Description, int Units) {
        this.SubjectCode = SubjectCode;
        this.Description = Description;
        this.Units = Units;
    }

    public String getSubjectCode() {
        return SubjectCode;
    }

    public void setSubjectCode(String SubjectCode) {
        this.SubjectCode = SubjectCode;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String Description) {
        this.Description = Description;
    }

    public int getUnits() {
        return Units;
    }

    public void setUnits(int Units) {
        this.Units = Units;
    }

    public static MongoDatabase connectToDatabase() {
        String uri = "mongodb://localhost:27017";
        MongoClient mongoClient = MongoClients.create(uri);
        MongoDatabase database = mongoClient.getDatabase("Enrollment"); // Change to the correct database name
        System.out.println("Connected to database: " + database.getName());
        return database;
    }

    public static List<Subject> getAllSubjectsFromDB(MongoDatabase database) {
        MongoCollection<Document> subjectCollection = database.getCollection("Subject"); // Access the 'Subject' collection
    
        List<Subject> subjects = new ArrayList<>();

        // Fetch all subject documents and sort by "SubjectCode"
        FindIterable<Document> subjectIterable = subjectCollection.find().sort(Sorts.ascending("SubjectCode"));
    
        for (Document subjectDoc : subjectIterable) {
            String subjectCode = subjectDoc.getString("SubjectCode");
            String description = subjectDoc.getString("Description");
            Integer units = subjectDoc.getInteger("Units");
            
            // Ensure that the required fields are not null before creating a Subject object
            if (subjectCode != null && description != null && units != null) {
                Subject subject = new Subject(subjectCode, description, units);
                subjects.add(subject);
            }
        }
    
        return subjects;
    }
    
    public static void printSubjects(List<Subject> subjects) {
        if (subjects == null || subjects.isEmpty()) {
            System.out.println("No students found.");
        } else {
            for (Subject subject : subjects) {
                System.out.println("ID: " + subject.getSubjectCode());
                System.out.println("Name: " + subject.getDescription());
                System.out.println("Course: " + subject.getUnits());
                System.out.println("---------------------------");
            }
        }
    }
    //private String SubjectCode;
    //private String Description;
    //private int Units;

    public static void addSubject(String scode, String Des, int units, MongoDatabase database) {
        MongoCollection<Document> subjectCollection = database.getCollection("Subject"); // Access the 'Subje t' collection
        
        Document doc = new Document("SubjectCode", scode)
                .append("Description", Des)
                .append("Units", units);
        
        subjectCollection.insertOne(doc); // Insert the document into the collection
    }
}