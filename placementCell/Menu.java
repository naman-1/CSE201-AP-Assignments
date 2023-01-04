import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
public class Menu{
    static PlacementCell pc = new PlacementCell();
    public static void main(String args[])
    {
        System.out.println("Welcome to FutureBuilder: \n" +
                "1) Enter the Application \n" +
                "2) Exit the Application \n");
        Scanner sc = new Scanner(System.in);
        int choice = sc.nextInt();
        switch(choice)
        {
            case 1:
                System.out.println("Entering the Application");
                mainApplication();
                break;
            case 2:
                System.out.println("Exiting the Application");
                return;
            default:
                System.out.println("Invalid Choice");
        }
    }
    public static void mainApplication()
    {
        System.out.println("Choose The mode you want to Enter in:- \n" +
                "1) Enter as Student Mode \n" +
                "2) Enter as Company Mode \n" +
                "3) Enter as Placement Cell Mode \n" +
                "4) Return To Main Application \n");
        Scanner sc = new Scanner(System.in);
        int choice = sc.nextInt();
        switch(choice)
        {
            case 1:
                System.out.println("Entering Student Mode");
                studentMode();
                break;
            case 2:
                System.out.println("Entering Company Mode");
                companyMode();
                break;
            case 3:
                System.out.println("Entering Placement Cell Mode");
                placementCellMode();
                break;
            case 4:
                System.out.println("Returning to Main Application");
                main(null);
                break;
            default:
                System.out.println("Invalid Choice");
        }
        sc.close();
    }
   
    public static void studentMode()
    {
        System.out.println("Welcome to Student Mode: \n" +
                "1) Enter as a Student(Give Student Name, and Roll No.) \n" +
                "2) Add Students \n" +
                "3) back \n");
        Scanner sc = new Scanner(System.in);
        int choice = sc.nextInt();
        switch(choice)
        {
            case 1:
                System.out.println("Entering as a Student");
                System.out.println("Enter Student Name");
                sc.nextLine();
                String name = sc.nextLine();
                System.out.println("Enter Student Roll No.");
                int rollNo = sc.nextInt();
                for (Student student : Student.getAllStudents()) {
                    if(student.getName().equals(name) && student.getRollNumber() == rollNo)
                    {
                        System.out.println(student.getName());
                        System.out.println(student.getRollNumber());
                        System.out.println("Welcome " + name);
                        studentModeChooseStudent(student);
                    }
                }
                break;
            case 2:
                System.out.println("Adding Students");
                System.out.println("Enter number of students to add: ");
                int n = sc.nextInt();
                for(int i = 0; i < n; i++)
                {
                    System.out.println("Enter Student Name");
                    sc.nextLine();
                    name = sc.nextLine();
                    System.out.println("Enter Student Branch");
                    String branch = sc.nextLine();
                    System.out.println("Enter Student Roll No.");
                    rollNo = sc.nextInt();
                    System.out.println("Enter Student CGPA");
                    Float cgpa = sc.nextFloat();
                    new Student(name, cgpa, rollNo, branch);
                }
                studentMode();
                break;
            case 3:
                System.out.println("Returning to Main Application");
                mainApplication();
                break;
            default:
                System.out.println("Invalid Choice");
        }
    }
    public static void studentModeChooseStudent(Student student)
    {
        System.out.println("1) Register For Placement Drive \n" +
                "2) Register For Company \n" +
                "3) Get All available companies \n" +
                "4) Get Current Status \n" +
                "5) Update CGPA \n" +
                "6) Accept offer \n" +
                "7) Reject offer \n" +
                "8) Back \n");
        Scanner sc = new Scanner(System.in);
        int choice = sc.nextInt();
        switch(choice)
        {
            case 1:
                System.out.println("Registering for Placement Drive");
                DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
                System.out.println("Enter the Date and Time of registration in format dd-MM-yyyy HH:mm:ss");
                sc.nextLine();
                String date = sc.nextLine();
                if (pc.getEndDateTimeStudents() == null || pc.getStartDateTimeStudents() == null)
                {
                    System.out.println("deadlines for registration have not been set yet");
                    studentModeChooseStudent(student);
                    break;
                }
                boolean res = LocalDateTime.parse(date, myFormatObj).isBefore(pc.getEndDateTimeStudents());
                boolean res2 = LocalDateTime.parse(date, myFormatObj).isAfter(pc.getStartDateTimeStudents());
                if(res && res2)
                {
                    pc.addStudents(student);
                    System.out.println("Registered for Placement Drive");
                    System.out.println("Your Details are: ");
                    System.out.println("Name: " + student.getName());
                    System.out.println("Roll No: " + student.getRollNumber());
                    System.out.println("Branch: " + student.getBranch());
                    System.out.println("CGPA: " + student.getCGPA());
                }
                else
                {
                    System.out.println("Registration Closed or not started yet");
                } 
                studentModeChooseStudent(student);
                break;
            case 2:
                System.out.println("Registering for Company");
                System.out.println("Enter Company Name");
                String companyName = sc.next();
                for (Company company : pc.getCompaniesRegisteredArray()) {
                    if(company.getName().equals(companyName))
                    {
                        System.out.println("Registering for " + companyName);
                        student.registerForCompany(company);
                        System.out.println("Registered for " + companyName);
                    }
                }
                studentModeChooseStudent(student);
                break;
            case 3:
                System.out.println("Getting all available companies");
                for (Company company : pc.getCompaniesRegisteredArray()) {
                    System.out.println("company name : " + company.getName());
                    System.out.println("company cgpa required : " + company.getCGPA());
                    System.out.println("company package offered : " + company.getPackage());
                    System.out.println("company role offering : " + company.getOfferedRole());
                }
                studentModeChooseStudent(student);
                break;
            case 4:
                System.out.println("Getting Current Status");
                student.currentStatus();
                studentModeChooseStudent(student);
                break;
            case 5:
                System.out.println("Updating CGPA");
                System.out.println("Enter new CGPA");
                float cgpa = sc.nextFloat();
                student.setCGPA(cgpa);
                studentModeChooseStudent(student);
                break;
            case 6:
                System.out.println("Accepting Offer");
                student.accept();
                studentModeChooseStudent(student);
                break;
            case 7:
                System.out.println("Rejecting Offer");
                student.reject();
                studentModeChooseStudent(student);
                break;
            case 8:
                System.out.println("Returning to Student Mode");
                studentMode();
                break;
            default:
                System.out.println("Invalid Choice");
        }
    }
    
    public static void companyMode()
    {
        System.out.println("Choose the Company Query to perform \n" +
                "1) Add Company and Details \n" +
                "2) Choose Company \n" +
                "3) Get Available Companies \n" +
                "4) Back\n");
        Scanner sc = new Scanner(System.in);
        int choice = sc.nextInt();
        switch(choice)
        {
            case 1:
                System.out.println("Adding Company and Details");
                System.out.println("Enter the Company Name");
                String name = sc.next();
                System.out.println("Enter the Required CGPA");
                Float requiredCGPA = sc.nextFloat();
                System.out.println("Enter the Package Amount in LPA in integers");
                int packageAmount = sc.nextInt();
                System.out.println("Enter the Role Company Offers");
                String roleCompanyOffers = sc.next();
                new Company(name, requiredCGPA, packageAmount, roleCompanyOffers);
                companyMode();
                break;
            case 2:
                System.out.println("Choosing Company");
                System.out.println("Companies: ");
                for (Company company : Company.getCompaniesCreated()) {
                    System.out.println(company.getName());
                }
                sc.nextLine();
                String companyName = sc.nextLine();
                for (Company company : Company.getCompaniesCreated()) {
                    if(company.getName().equals(companyName))
                    {
                        System.out.println("Welcome " + companyName);
                        companyModeChooseCompany(company);
                    }
                }
                break;
            case 3:
                System.out.println("Getting Available Companies");
                for (Company company : pc.getCompaniesRegisteredArray()) 
                {
                    System.out.println("Company Name: " + company.getName());
                    System.out.println("");
                }
                companyMode();
                break;
            case 4:
                System.out.println("Returning to Main Application");
                mainApplication();
                break;
            default:
                System.out.println("Invalid Choice");
        }
    }
    public static void companyModeChooseCompany(Company companyName)
    {
        for (Company company : Company.getCompaniesCreated()) 
        {
            if(company.getName().equals(companyName.getName()))
            {
                System.out.println("Welcome to " + company.getName() + " Company");
                System.out.println("1) Update Role \n" +
                        "2) Update Package \n" +
                        "3) Update CGPA criteria \n" +
                        "4) Register To Institute Drive \n" +
                        "5) Back\n");
                Scanner sc = new Scanner(System.in);
                int choice = sc.nextInt();
                switch(choice)
                {
                    case 1:
                        System.out.println("Updating Role");
                        String role = sc.next();
                        company.changeRole(role);
                        break;
                    case 2:
                        System.out.println("Updating Package");
                        int packageAmount = sc.nextInt();
                        company.changePackage(packageAmount);
                        break;
                    case 3:
                        System.out.println("Updating CGPA Criteria");
                        float requiredCGPA = sc.nextFloat();
                        company.changeRequiredCGPA(requiredCGPA);
                        break;
                    case 4:
                        System.out.println("Registering to Institute Drive");
                        if (pc.getEndDateTimeCompanies() == null || pc.getStartDateTimeCompanies() == null)
                        {
                            System.out.println("deadlines for registration have not been set yet");
                        }
                        DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
                        System.out.println("Enter the Date and Time of Drive in format dd-MM-yyyy HH:mm:ss");
                        sc.nextLine();
                        String date = sc.nextLine();
                        boolean res = LocalDateTime.parse(date, myFormatObj).isBefore(pc.getEndDateTimeCompanies());
                        boolean res2 = LocalDateTime.parse(date, myFormatObj).isAfter(pc.getStartDateTimeCompanies());
                        if (res && res2) 
                        {
                            pc.addCompanies(company);
                            System.out.println("Registered for Institute Drive");
                        }
                        else
                        {
                            System.out.println("Registration Closed");
                        }
                        companyModeChooseCompany(companyName);
                        break;
                    case 5:
                        System.out.println("Returning to Main Application");
                        companyMode();
                        break;
                    default:
                        System.out.println("Invalid Choice");
                }
                companyModeChooseCompany(companyName);
            }
        }
    }
    public static void placementCellMode()
    {
        System.out.println("1) Open Student Registrations \n" +
                "2) Open Company Registrations \n" +
                "3) Get Number of Student Registrations \n" +
                "4) Get Number of Company Registrations \n" +
                "5) Get Number of Offered/Unoffered/Blocked Students\n" +
                "6) Get Student Details \n" +
                "7) Get Company Details \n" +
                "8) Get Average Package \n" +
                "9) Get Company Process Results \n" +
                "10) Back \n");
        Scanner sc = new Scanner(System.in);
        int choice = sc.nextInt();
        switch(choice)
        {
            case 1:
                System.out.println("Opening Student Registrations");
                String startDateTimeStudents, endDateTimeStudents;
                System.out.println("Enter the Start Date and Time of Student Registrations in the format dd-MM-yyyy HH:mm:ss");
                System.out.println("Enter the start date and time");
                sc.nextLine();
                startDateTimeStudents = sc.nextLine();
                System.out.println("Enter the end date and time");
                endDateTimeStudents = sc.nextLine();
                pc.setDateTimeStudents(startDateTimeStudents, endDateTimeStudents);
                break;
            case 2:
                System.out.println("Opening Company Registrations");
                String startDateTimeCompanies, endDateTimeCompanies;
                System.out.println("Enter the Start Date and Time of Company Registrations in the format dd-MM-yyyy HH:mm:ss");
                System.out.println("Enter the start date and time");
                sc.nextLine();
                startDateTimeCompanies = sc.nextLine();
                System.out.println("Enter the end date and time");  
                endDateTimeCompanies = sc.nextLine();
                pc.setDateTimeCompanies(startDateTimeCompanies, endDateTimeCompanies);
                break;
            case 3:
                System.out.println("Getting Number of Student Registrations");
                System.out.println(pc.getStudentsRegistered());
                break;
            case 4:
                System.out.println("Getting Number of Company Registrations");
                System.out.println(pc.getCompaniesRegistered());
                break;
            case 5:
                System.out.println("Getting Number of Offered/Unoffered/Blocked Students");
                pc.getStatusNumbers();
                break;
            case 6:
                System.out.println("Getting Student Details");
                System.out.println("Enter the Student Name");
                sc.nextLine();
                String studentName = sc.nextLine();
                System.out.println("Enter the Student roll number");
                int studentRollNumber = sc.nextInt();
                pc.studentDetails(studentName, studentRollNumber);
                break;
            case 7:
                System.out.println("Getting Company Details");
                System.out.println("Enter the Company Name");
                sc.nextLine();
                String companyName = sc.nextLine();
                pc.companyDetails(companyName);
                break;
            case 8:
                System.out.println("Getting Average Package");
                pc.averagePacakge();
                break;
            case 9:
                System.out.println("Getting Company Process Results");
                System.out.println("Enter the Company Name");
                sc.nextLine();
                companyName = sc.nextLine();
                pc.companyResults(companyName);
                break;
            case 10:
                System.out.println("Returning to Main Application");
                mainApplication();
                break;
            default:
                System.out.println("Invalid Choice");
            }
            placementCellMode();
    }
}
