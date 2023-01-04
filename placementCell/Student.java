import java.util.ArrayList;

public class Student
{
    private String name, branch, placedIn;
    private int packageAmount, rollNumber, numberOfOffersReceived, numberOfOffersRejected;
    private float CGPA;
    private boolean isPlaced, isOffered, isBlocked, isAccepted, isApplied;
    private ArrayList<Company> offersFromCompanies;
    private static ArrayList <Student> allStudents = new ArrayList<Student>();
    public Student(String name, Float CGPA, int rollNumber, String branch)
    {
        this.name = name;
        this.CGPA = CGPA;
        this.rollNumber = rollNumber;
        this.branch = branch;
        this.placedIn = "";
        this.offersFromCompanies = new ArrayList<Company>();
        this.isPlaced = false; this.isOffered = false; this.isBlocked = false; this.isAccepted = false; this.isApplied = false;
        this.numberOfOffersReceived = 0;
        this.numberOfOffersRejected = 0;
        this.packageAmount = 0;
        Student.allStudents.add(this);
    }
    String getName()
    {
        return this.name;
    }
    String getBranch()
    {
        return this.branch;
    }
    String getCompanyName()
    {
        return this.placedIn;
    }
    static ArrayList <Student> getAllStudents()
    {
        return Student.allStudents;
    }
    boolean getIsOffered()
    {
        if(this.isOffered)
            return true;
        else
            return false;
    }
    void setIsOffered(boolean isOffered)
    {
        this.isOffered = isOffered;
    }
    void setPackageAmount(int packageAmount)
    {
        this.packageAmount = packageAmount;
    }
    boolean getIsBlocked()
    {
        if(this.isBlocked)
            return true;
        else
            return false;
    }
    boolean getIsPlaced()
    {
        if(this.isPlaced)
            return true;
        else
            return false;
    }
    int getPackage()
    {
        return this.packageAmount;
    }
    int getRollNumber()
    {
        return this.rollNumber;
    }
    float getCGPA()
    {
        return this.CGPA;
    }
    void registerForCompany(Company company)
    {
        this.isApplied = true;
        if (this.isPlaced == true && company.getPackage() >= 3*this.packageAmount && this.CGPA >= company.getCGPA() && this.isBlocked == false)
        {
            company.addStudent(this);
        }
        else if (this.CGPA >= company.getCGPA() && this.isPlaced == false && this.isBlocked == false)
        {
            company.addStudent(this);
        }
    }
    private boolean isEligible(Company company)
    {
        if (this.isPlaced == false && this.CGPA >= company.getCGPA())
        {
            return true;
        }
        else if (this.isPlaced == true && 3*this.packageAmount <= company.getPackage() && this.CGPA >= company.getCGPA())
        {
            return true;
        }
        else
        {
            return false;
        }
    }
    void availableCompanies(ArrayList<Company> companies)
    {

        int eligible = 0;
        for(Company company : companies)
        {
            if(this.isEligible(company))
            {
                eligible = 1;
                System.out.println(company.getName());
            }
        }
        if (eligible == 0)
        {
            System.out.println("unavailable");
        }
    }
    void currentStatus()
    {
        if (this.isBlocked == true)
        {
            System.out.println("blocked");
        }
        else if (this.isPlaced == true)
        {
            System.out.println("placed");
            System.out.println("placed in " + this.placedIn );
        }
        else if(this.isOffered == true )
        {
            System.out.println("offered");
            System.out.println("offer received from ") ;
            for (Company company : this.offersFromCompanies)
            {
                System.out.println(company.getName());
            }
        }
        else if(this.isOffered == false)
        {
            System.out.println("not offered");
        }
        else if (this.isApplied == true)
        {
            System.out.println("applied");
        }
        else if (this.isApplied == false)
        {
            System.out.println("not applied");
        }
        else
        {
            System.out.println("not registered");
        }
    }
    void setCGPA(float CGPA)
    {
        this.CGPA = CGPA;
    }
    void addOffer(Company companyName)
    {
        this.offersFromCompanies.add(companyName);
        this.numberOfOffersReceived++;
        this.isOffered = true;
    }
    void accept()
    {
        int highestPackage = Student.highestOffer(this.offersFromCompanies);
        this.isPlaced = true;
        this.isOffered = false;
        this.packageAmount = this.offersFromCompanies.get(highestPackage).getPackage();
        this.placedIn = this.offersFromCompanies.get(highestPackage).getName();
        System.out.println("accepted offer from " + this.placedIn);
        this.offersFromCompanies.clear();
    }
    void reject()
    {
        if (this.offersFromCompanies.size()==0)
        {
            System.out.println("no offers to reject");
            return;
        }
        this.numberOfOffersRejected++;
        if (this.numberOfOffersReceived == this.numberOfOffersRejected)
        {
            this.isBlocked = true;
            this.isOffered = false;
        }
        System.out.println("rejected " + this.offersFromCompanies.get(0).getName());
        this.offersFromCompanies.remove(Student.highestOffer(this.offersFromCompanies));
    }
    static int highestOffer(ArrayList<Company> companies)
    {
        int max = 0;
        for (int i = 1; i < companies.size(); i++)
        {
            if (companies.get(i).getPackage() >= companies.get(max).getPackage())
            {
                max = i;
            }
        }
        return max;
    }
}
