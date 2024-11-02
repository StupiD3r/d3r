package Grecia;

public class SubList {
    private String id,name,code,des;
    private double units, grade;

    public SubList(double units, double grade, String id, String name, String code, String des) {
        this.id = id;
        this.name = name;
        this.code = code;
        this.units = units;
        this.grade = grade;
        this.des = des;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }

    public double getUnits() {
        return units;
    }

    public void setUnits(double units) {
        this.units = units;
    }

    public double getGrade() {
        return grade;
    }

    public void setGrade(double grade) {
        this.grade = grade;
    }
    
    
}
//GRECIA
