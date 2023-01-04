// import java.util.ArrayList;
import java.util.ArrayList;
import java.util.Scanner;

public class Admin {
    private String userName, password;

    public Admin(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    int login(String userName, String password) {
        /*
         * used to authenticate user
         */
        if (userName.equals(this.userName) && password.equals(this.password)) {
            return 1;
        } else {
            return 0;
        }
    }

    void addCategory(String category, int categoryID) {
        Scanner sc = new Scanner(System.in);
        try{
            Category temp = new Category(categoryID, category);
            System.out.println("To create new category a product has to added to it");
            System.out.println("Enter product name");
            String productName = sc.nextLine();
            System.out.println("Enter product ID (in integer) ");
            int productID = sc.nextInt();
            sc.nextLine();
            System.out.println("Enter product price (in integer) ");
            int productPrice = sc.nextInt();
            sc.nextLine();
            System.out.println("Enter product quantity (in integer) ");
            int productQuantity = sc.nextInt();
            sc.nextLine();
            System.out.println("Enter product abstract data (in string) ");
            String abstractData = sc.nextLine();
            Product tempProduct = new Product(productID, productName, productQuantity, productPrice, abstractData);
            temp.addProduct(tempProduct);
        }catch(Exception e){
            System.out.println(e);
            System.out.println("Error occured while adding product to category");
        }
    }

    void removeCategory(String category, int categoryID) {
        int numOfCategories = Category.getAllCategories().size();
        ArrayList<Product> temp;
        ArrayList<Deals> tempDeals;
        for (int i = 0; i < numOfCategories; i++) {
            if (Category.getAllCategories().get(i).getCategoryName().equals(category) && Category.getAllCategories().get(i).getCategoryID() == categoryID) {
                temp = Category.getAllCategories().get(i).getProducts();
                for (Product j : temp) {
                    Product.getAllProducts().remove(j);
                }
                tempDeals = Deals.getAllDeals();
                int numOfDeals = tempDeals.size();
                int j = 0;
                while (j < numOfDeals) {
                    if (temp.contains(tempDeals.get(j).getProducts().get(0)) || temp.contains(tempDeals.get(j).getProducts().get(1))) {
                        numOfDeals--;
                        Deals.getAllDeals().remove(j);
                        continue;
                    }
                    j++;
                }
                Category.getAllCategories().remove(i);
                System.out.println("Category removed successfully");
                return;
            }
        }
    }

    void addProduct(String productName, int quant, int productID, int productPrice, int categoryID, String abstractData) throws Exception {
        /*
         * used to add a product to the system
         */
        int flag = 1;
        for (Category i : Category.getAllCategories()) {
            if (i.getCategoryID() == categoryID) {
                flag = 0;
                i.addProduct(new Product(productID, productName, quant, productPrice,abstractData));
                System.out.println("Product added");
                break;
            } 
        }
        if(flag == 1) {
            throw new Exception("Category ID mentioned is not present in the system, kindly create category first");
        }
    }

    void removeProduct(int productID, String categoryName) {
        /*
         * used to remove a product from the system
         */
        int numOfCategories = Category.getAllCategories().size();
        // ArrayList<Category> temp = Category.getAllCategories();
        for (int i = 0; i < numOfCategories; i++) {
            if (Category.getAllCategories().get(i).getCategoryName().equals(categoryName)) {
                Category.getAllCategories().get(i).removeProduct(productID);
                for (Product j : Product.getAllProducts()) {
                    if (j.getID() == productID) {
                        Product.getAllProducts().remove(j);
                        break;
                    }
                }
                System.out.println("Product removed");

                int numOfDeals = Deals.getAllDeals().size();
                int j = 0;
                while (j < numOfDeals) {
                    if (Deals.getAllDeals().get(j).getProducts().get(0).getID() == productID || Deals.getAllDeals().get(j).getProducts().get(1).getID() == productID) {
                        Deals.getAllDeals().remove(j);
                        numOfDeals--;
                        continue;
                    }
                    j++;
                }
                // return;
            // }
        // }
                if (Category.getAllCategories().get(i).getProducts().size() == 0) {
                    System.out.println(
                            "No products left in the category, category will be removed if no product is added to it");
                    Scanner sc = new Scanner(System.in);
                    System.out.println("Do you want to add a product to the category? (y/n)");
                    String choice = sc.nextLine();
                    if (choice.equals("n")) {
                        this.removeCategory(categoryName, productID);
                    } 
                    else if (choice.equals("y")) {
                        System.out.println("Enter product name");
                        String productName = sc.nextLine();
                        System.out.println("Enter product ID (in integer) ");
                        productID = sc.nextInt();
                        sc.nextLine();
                        System.out.println("Enter product price (in integer) ");
                        int productPrice = sc.nextInt();
                        sc.nextLine();
                        System.out.println("Enter product quantity (in integer) ");
                        int productQuantity = sc.nextInt();
                        sc.nextLine();
                        System.out.println("Enter product abstract data (in string) ");
                        String abstractData = sc.nextLine();
                        try {
                            this.addProduct(productName,productQuantity, productID, productPrice, Category.getAllCategories().get(i).getCategoryID(),abstractData);
                        } catch (Exception e) {
                            System.out.println(e);
                        }
                    }
                }
            }
        }
    }

    void setDiscount(int productID, int normalCustomerDiscount, int primeCustomerDiscount, int eliteCustomerDiscount) {
        /*
         * used to set discount for all the customers
         */
        for (Category i : Category.getAllCategories()) {
            for (Product j : i.getProducts()) {
                if (j.getID() == productID) {
                    j.setCustomerDiscount(normalCustomerDiscount, primeCustomerDiscount, eliteCustomerDiscount);
                }
            }
        }
    }

    void addDeal(String dealName,int productID1, int productID2, int dealID, int normalCustomerPrice, int primeCustomerPrice, int eliteCustomerPrice) {
        /*
         * used to add a deal to the system
         */
        Product first, second;
        first = second = null;
        for (Product i : Product.getAllProducts()){
            if (i.getID() == productID1){
                first = i;
                break;
            }
        }
        for (Product i : Product.getAllProducts()){
            if (i.getID() == productID2){
                second = i;
                break;
            }
        }
        try {
            new Deals(first, second, dealName, dealID, normalCustomerPrice, primeCustomerPrice, eliteCustomerPrice);
        }
        catch (Exception e){
            throw e;
        }
        
    }
}
