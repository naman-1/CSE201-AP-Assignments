import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

class PlacementCell
{
    LocalDateTime startDateTimeStudents, endDateTimeStudents;
    LocalDateTime startDateTimeCompanies, endDateTimeCompanies;
    private static ArrayList <Student> students = new ArrayList<Student>();
    private static ArrayList <Company> companies = new ArrayList<Company>();
    public PlacementCell()
    {
        this.startDateTimeStudents = null;
        this.endDateTimeStudents = null;
        this.startDateTimeCompanies = null;
        this.endDateTimeCompanies = null;
    }
    void setDateTimeStudents(String startDateTime, String endDateTime) 
    {
        DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        this.startDateTimeStudents = LocalDateTime.parse(startDateTime, myFormatObj);
        this.endDateTimeStudents = LocalDateTime.parse(endDateTime, myFormatObj);
    }
    void setDateTimeCompanies(String startDateTime, String endDateTime)
    {
        DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        this.startDateTimeCompanies = LocalDateTime.parse(startDateTime, myFormatObj);
        this.endDateTimeCompanies = LocalDateTime.parse(endDateTime, myFormatObj);
    }
    LocalDateTime getStartDateTimeStudents()
    {
        return this.startDateTimeStudents;
    }
    LocalDateTime getEndDateTimeStudents()
    {
        return this.endDateTimeStudents;
    }
    LocalDateTime getStartDateTimeCompanies()
    {
        return this.startDateTimeCompanies;
    }
    LocalDateTime getEndDateTimeCompanies()
    {
        return this.endDateTimeCompanies;
    }
    void addStudents(Student student)
    {
        PlacementCell.students.add(student);
    }
    void addCompanies(Company company)
    {
        PlacementCell.companies.add(company);
    }
    int getStudentsRegistered()
    {
        return PlacementCell.students.size();
    }
    int getCompaniesRegistered()
    {
        return PlacementCell.companies.size();
        // return Company.getCompaniesCreated().size();
    }
    ArrayList<Student> getStudentsRegisteredArray()
    {
        return PlacementCell.students;
    }
    ArrayList<Company> getCompaniesRegisteredArray()
    {
        return PlacementCell.companies;
    }
    void getStatusNumbers()
    {
        int offered = 0, unoffered = 0, blocked = 0;
        System.out.println("Number of Offered Students: " );
        for (Student student : PlacementCell.students) 
        {
            if(student.getIsOffered() == true)
            {
                offered++;
            }
            else if (student.getIsBlocked() == true)
            {
                blocked++;
            }
            else
            {
                unoffered++;
            }
        }
        System.out.println(offered);
        System.out.println("Number of Unoffered Students: " + unoffered);
        System.out.println("Number of Blocked Students: " + blocked);
    }
    void studentDetails(String studentName, int studentRollNumber)
    {
        for (Student student : PlacementCell.students) 
        {
            if(student.getName().equals(studentName) && student.getRollNumber() == studentRollNumber)
            {
                System.out.println("Student Name: " + student.getName());
                System.out.println("Student Roll Number: " + student.getRollNumber());
                System.out.println("Student CGPA: " + student.getCGPA());
                System.out.println("Student Branch: " + student.getBranch());
                System.out.println("Student is Offered: " + student.getIsOffered());
                System.out.println("Student is Blocked: " + student.getIsBlocked());
                System.out.println("Student is Placed: " + student.getIsPlaced());
                System.out.println("Student Company Name: " + student.getCompanyName());
                System.out.println("Student Package: " + student.getPackage());
            }
        }
    }
    void companyDetails(String companyName)
    {
        for (Company company : PlacementCell.companies) 
        {
            if(company.getName().equals(companyName))
            {
                System.out.println("Company Name: " + company.getName());
                System.out.println("Company Package: " + company.getPackage());
                System.out.println("Company has offered position to following students: ");
                for (Student stu: company.getOfferedStudents())
                {
                    System.out.println("Student Name: " + stu.getName());
                }
                break;
            }
        }
    }
    void averagePacakge()
    {
        int avgPackage = 0;
                int studentPlaced = 0;
                for (Student student : PlacementCell.students) 
                {
                    if (student.getPackage() != 0)
                    {
                        avgPackage += student.getPackage();
                        studentPlaced++;
                    }
                    avgPackage /= studentPlaced;
                }
                System.out.println(avgPackage);
    }
    void companyResults(String companyName)
    {
        for (Company company : PlacementCell.companies) 
        {
            if(company.getName().equals(companyName))
            {
                System.out.println("Company Name: " + company.getName() + " has recruited following students");
                company.getSelectedStudents();
                company.sendOffers();
            }
        }
    }
    void updateCGPA(int olderCGPA, int newCGPA, String studentName)
    {
        for (Student student : PlacementCell.students) 
        {
            if(student.getName().equals(studentName) && student.getCGPA() == olderCGPA)
            {
                student.setCGPA(newCGPA);
            }
        }
    }
}
