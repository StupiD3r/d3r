package college;

import java.text.NumberFormat;

public class EngineeringColl extends College{
    int businessPrograms;
    
    public EngineeringColl(String collegeName, int totalStudents, int businessPrograms){
        super(collegeName, totalStudents);
        this.businessPrograms = businessPrograms;
    }
    
    @Override
    void displayCollegeDetails(){
        System.out.println( "Engineering College: " + collegeName + 
                            ", Total Students: " + totalStudents + 
                            ", Business Programs: " + businessPrograms);
    }
    @Override
    double calculateFees(int x) {
        double tuitionFee = 10000.0 * x;
        double labFee = 1000.0 * x;
        double techFee = 500.0 * x;
        double projFee = 800.0 * x;
        double industryFee = 1500.0 * x;
        System.out.println("Tuition Fee:                       " + NumberFormat.getInstance().format(tuitionFee));
        System.out.println("Laboratory and Workship Fee:       " + NumberFormat.getInstance().format(labFee));
        System.out.println("Technology and Infrastructure Fee: " + NumberFormat.getInstance().format(techFee));
        System.out.println("Project and Research Fee:          " + NumberFormat.getInstance().format(projFee));
        System.out.println("Industry Collaboration Fee:        " + NumberFormat.getInstance().format(industryFee));
        return(tuitionFee+labFee+techFee+projFee+industryFee);
    }
}
