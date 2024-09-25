package college;
import java.text.NumberFormat;
public class BusinessColl extends College{
    int businessPrograms;
    
    public BusinessColl(String collegeName, int totalStudents, int businessPrograms){
        super(collegeName, totalStudents);
        this.businessPrograms = businessPrograms;
    }
    
    @Override
    void displayCollegeDetails(){
        System.out.println( "Business College: " + collegeName + 
                            ", Total Students: " + totalStudents + 
                            ", Business Programs: " + businessPrograms);
    }
    @Override
    double calculateFees(int x) {
        double tuitionFee = 10000.0 * x;
        double materialFee = 1000.0 * x;
        double technologyFee = 500.0 * x;
        double examinationFee = 800.0 * x;
        double internshipFee = 1500.0 * x;
        String a = NumberFormat.getInstance().format(tuitionFee);
        System.out.println("Tuition Fee:                       " + a);
        System.out.println("Material and Resource Fee:         " + NumberFormat.getInstance().format(materialFee));
        System.out.println("Technology Fee:                    " + NumberFormat.getInstance().format(technologyFee));
        System.out.println("Examination Fee:                   " + NumberFormat.getInstance().format(examinationFee));
        System.out.println("Internship/Placement Fee:          " + NumberFormat.getInstance().format(internshipFee));
        return(tuitionFee+materialFee+technologyFee+examinationFee+internshipFee);
    }
}
