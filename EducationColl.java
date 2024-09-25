package college;
import java.text.NumberFormat;
public class EducationColl extends College{
    int businessPrograms;
    
    public EducationColl(String collegeName, int totalStudents, int businessPrograms){
        super(collegeName, totalStudents);
        this.businessPrograms = businessPrograms;
    }
    
    @Override
    void displayCollegeDetails(){
        System.out.println( "Education College: " + collegeName + 
                            ", Total Students: " + totalStudents + 
                            ", Business Programs: " + businessPrograms);
    }
    @Override
    double calculateFees(int x) {
        double tuitionFee = 8000.0 * x;
        double libFee = 800.0 * x;
        double tripFee = 300.0 * x;
        double stuassFee = 200.0 * x;
        double examFee = 700.0 * x;
        System.out.println("Tuition Fee:                       " + NumberFormat.getInstance().format(tuitionFee));
        System.out.println("Library and Resource Fee:          " + NumberFormat.getInstance().format(libFee));
        System.out.println("Fiel Trip Fee:                     " + NumberFormat.getInstance().format(tripFee));
        System.out.println("Student Association Fee:           " + NumberFormat.getInstance().format(stuassFee));
        System.out.println("Examination Fee:                   " + NumberFormat.getInstance().format(examFee)); 
        return(tuitionFee+libFee+tripFee+stuassFee+examFee);
    }
}
