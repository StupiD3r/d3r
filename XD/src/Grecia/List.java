package Grecia;
import java.util.ArrayList;

public class List {
    private ArrayList<SubList> subjects;

    public List() {
        subjects = new ArrayList<>(); // Initialize the ArrayList
    }

    public void addSubject(SubList subject) {
        subjects.add(subject); // Add subject to the list
    }

    public ArrayList<SubList> getSubjects() {
        return subjects; // Return the list of subjects
    }
    public void clearSubjects() {
    subjects.clear(); // Clear all subjects from the ArrayList
    }
    
}
