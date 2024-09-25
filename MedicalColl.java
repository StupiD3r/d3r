package college;

import java.text.NumberFormat;

public class MedicalColl extends College{
    int businessPrograms;
    
    public MedicalColl(String collegeName, int totalStudents, int businessPrograms){
        super(collegeName, totalStudents);
        this.businessPrograms = businessPrograms;
    }
    
    @Override
    void displayCollegeDetails(){
        System.out.println( "Medical College: " + collegeName + 
                            ", Total Students: " + totalStudents + 
                            ", Business Programs: " + businessPrograms);
    }
    @Override
    double calculateFees(int x) {
        double tuitionFee = 12000.0 * x;
        double labFee = 1000.0 * x;
        double clinicFee = 500.0 * x;
        double medFee = 800.0 * x;
        double resFee = 1500.0 * x;
        System.out.println("Tuition Fee:                       " + NumberFormat.getInstance().format(tuitionFee));
        System.out.println("Laboratory and Equipment Fee:      " + NumberFormat.getInstance().format(labFee));
        System.out.println("Clinical Placement Fee:            " + NumberFormat.getInstance().format(clinicFee));
        System.out.println("Medical Insurance Fee:             " + NumberFormat.getInstance().format(medFee));
        System.out.println("Research and Publication Fee:      " + NumberFormat.getInstance().format(resFee));
        return(tuitionFee+labFee+clinicFee+medFee+resFee);
    }
}
