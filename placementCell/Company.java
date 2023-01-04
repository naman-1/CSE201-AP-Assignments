import java.time.LocalDateTime;
import java.util.*;
class Company
{
    private static ArrayList <Company> companiesCreated = new ArrayList<Company>();
    private ArrayList<Student> studentsApplied = new ArrayList<Student>();
    private ArrayList<Student> offeredStudents = new ArrayList<Student>();
    private String name, roleCompanyOffers;
    private int packageAmount;
    private float requiredCGPA;
    LocalDateTime registrationTime;
    public Company(String name, Float requiredCGPA, int packageAmount, String roleCompanyOffers)
    {
        this.name = name;
        this.requiredCGPA = requiredCGPA;
        this.packageAmount = packageAmount;
        this.roleCompanyOffers = roleCompanyOffers;
        Company.companiesCreated.add(this);
    }
    static ArrayList<Company> getCompaniesCreated()
    {
        return Company.companiesCreated;
    }
    public String getOfferedRole()
    {
        // System.out.println(this.roleCompanyOffers);
        return this.roleCompanyOffers;
    }
    public ArrayList<Student> getOfferedStudents()
    {
        return this.offeredStudents ;
    }
    public String getName() {
        return name;
    }
    public float getCGPA() {
        return requiredCGPA;
    }
    public int getPackage() {
        return packageAmount;
    }
    public void changeRole(String role)
    {
        this.roleCompanyOffers = role;
    }
    public void changePackage(int packageAmount)
    {
        this.packageAmount = packageAmount;
    }
    public void changeRequiredCGPA(float requiredCGPA)
    {
        this.requiredCGPA = requiredCGPA;
    }
    public void addStudent(Student student)
    {
        this.studentsApplied.add(student);
    }
    public void getSelectedStudents()
    {
        for (int i = 0; i < this.studentsApplied.size(); i++) 
        {
            this.offeredStudents.add(this.studentsApplied.get(i));
            // this.studentsApplied.remove(randomIndex);
        }
        for (Student student : this.offeredStudents) 
        {
            // print details of students selected
            System.out.println("selected students: ");
            System.out.println("Student Name: " + student.getName());
            System.out.println("Student CGPA: " + student.getCGPA());
            System.out.println("Student Branch: " + student.getBranch());
            System.out.println("Student Roll Number: " + student.getRollNumber());
            System.out.println("");
        }
        this.studentsApplied.clear();
    }
    public void sendOffers()
    {
        for (Student student : this.offeredStudents) 
        {
            student.addOffer(this);
            student.setIsOffered(true);

        }
    }
}