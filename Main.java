package college;
import java.text.NumberFormat;
import java.util.Scanner;

public abstract class Main {
    public static void main(String[] args) {
        Scanner bini = new Scanner(System.in);
        double bloom;
        
        System.out.println("1 - Business Collage");
        System.out.println("2 - Education Collage");
        System.out.println("3 - Medical Collage");
        System.out.println("4 - Engineering Collage");
        System.out.print("Select Program Class: ");
        int maloi = bini.nextInt();
        System.out.print("Enter No. of Students: ");
        int aiah = bini.nextInt();
        
        switch(maloi){
            case 2:
                EducationColl mikha = new EducationColl("Education College", aiah, maloi);
                mikha.displayCollegeDetails();
                System.out.println("");
                bloom = mikha.calculateFees(aiah);
                System.out.println("Total: " + NumberFormat.getInstance().format(bloom));
                break;
            
            case 1:
                BusinessColl staku = new BusinessColl("Business College", aiah, maloi);
                staku.displayCollegeDetails();
                System.out.println("");
                bloom = staku.calculateFees(aiah);
                System.out.println("Total: " + NumberFormat.getInstance().format(bloom));
                break;
                
            case 3:
                MedicalColl jho = new MedicalColl("Medical College", aiah, maloi);
                jho.displayCollegeDetails();
                System.out.println("");
                bloom = jho.calculateFees(aiah);
                System.out.println("Total: " + NumberFormat.getInstance().format(bloom));
                break;
                
            case 4:
                EngineeringColl sheena = new EngineeringColl("Business College", aiah, maloi);
                sheena.displayCollegeDetails();
                System.out.println("");
                bloom = sheena.calculateFees(aiah);
                System.out.println("Total: " + NumberFormat.getInstance().format(bloom));
                break;
        }
    }
}
