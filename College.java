package college;

abstract class College {
    String collegeName;
    int totalStudents;

    public College(String collegeName, int totalStudents) {
        this.collegeName = collegeName;
        this.totalStudents = totalStudents;
    }

    public String getCollegeName() {
        return collegeName;
    }

    public void setCollegeName(String collegeName) {
        this.collegeName = collegeName;
    }

    public int getTotalStudents() {
        return totalStudents;
    }

    public void setTotalStudents(int totalStudents) {
        this.totalStudents = totalStudents;
    }
    
    abstract void displayCollegeDetails();
    abstract double calculateFees(int x);
    
    void welcomeMessage(){
        System.out.println("Welcome to " + collegeName + "!");
    }
}
