import java.util.*;
public class menu {
    static Admin admin = new Admin("adminOne", "root");
    public static void main(String args[]){
        // admin.addCategory("Home Appliances", 1);
        // admin.addCategory("Electronics and Gadgets", 2);
        // admin.addCategory("Groceries and Vegetables", 3);
        System.out.println("WELCOME TO FLIPZON\n" +
                "1) Enter as Admin\n" +
                "2) Explore the Product Catalog\n" +
                "3) Show Available Deals\n" +
                "4) Enter as Customer\n" +
                "5) Exit the Application");
        Scanner sc = new Scanner(System.in);
        int choice = sc.nextInt();
        sc.nextLine();
        switch(choice){
            case 1:
                // adminMenu();
                System.out.println("Enter username");
                String userName = sc.next();
                System.out.println("Enter password");
                String password = sc.next();
                if (admin.login(userName, password) == 1){
                    adminMenu();
                }
                else{
                    System.out.println("Invalid username or password");
                    main(args);
                }
                break;
            case 2:
                System.out.println("Exploring Catalog");
                System.out.println("Products in catalog are: ");
                for (Product product : Product.getAllProducts()){
                    System.out.println("Product name: " + product.getName());
                    System.out.println("Product price: " + product.getPrice());
                    System.out.println("Product ID: " + product.getID());
                }
                System.out.println("Deals in catalog are: ");
                for (Deals deal : Deals.getAllDeals()){
                    System.out.println("Deal name: " + deal.getName());
                    System.out.println("Deal ID: " + deal.getID());
                    System.out.println("Deal has following products: ");
                    for (Product product : deal.getProducts()){
                        System.out.println("Product name: " + product.getName());
                        System.out.println("Product price: " + product.getPrice());
                        System.out.println("Product ID: " + product.getID());
                    }
                    System.out.println("");
                }
                main(args);
                break;
            case 3:
                if (Deals.getAllDeals().size() == 0){
                    System.out.println("No deals available");
                }
                for (Deals deal : Deals.getAllDeals()){
                    Product productOne = deal.getProducts().get(0);
                    Product productTwo = deal.getProducts().get(1);
                    System.out.println((deal.getName() + "with deal id " + deal.getID() + " is available on " + productOne.getName() + " and " + productTwo.getName()));
                }
                main(args);
                break;
            case 4:
                System.out.println("Enetering in customer mode");
                customerMenu();
                break;
            case 5:
                System.out.println("Thank you for using Flipzon");
                return;
        }
    }

    public static void adminMenu() {
        System.out.println("1) Add category\n" +
                "2) Delete category\n" +
                "3) Add Product\n" +
                "4) Delete Product\n" +
                "5) Set Discount on Product\n" +
                "6) Add giveaway deal\n" +
                "7) Back");
        Scanner sc = new Scanner(System.in);
        int choice = sc.nextInt();
        sc.nextLine();
        switch(choice)
        {
            case 1:
                System.out.println(Category.getAllCategories().size());
                for (Category category : Category.getAllCategories()){
                    System.out.println(category.getCategoryName());
                    System.out.println(category.getCategoryID());

                }
                System.out.println("Enter category name");
                String categoryName = sc.nextLine();
                System.out.println("Enter category ID (in integer) ");
                int categoryID = sc.nextInt();
                sc.nextLine();
                try{
                    admin.addCategory(categoryName, categoryID);
                }
                catch(Exception e){
                    System.out.println(e);
                }
                break;
            case 2:
                System.out.println("Enter category name");
                categoryName = sc.nextLine();
                System.out.println("Enter category ID (in integer) ");
                categoryID = sc.nextInt();
                sc.nextLine();
                admin.removeCategory(categoryName, categoryID);
                break;
            case 3:
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
                System.out.println("Enter category ID (in integer) ");
                int categoryID2 = sc.nextInt();
                sc.nextLine();
                System.out.println("Enter product description");
                String abstractData = sc.nextLine();
                try {
                    admin.addProduct(productName, productQuantity, productID, productPrice, categoryID2,abstractData);
                } catch (Exception e) {
                    System.out.println(e);
                }
                break;
            case 4:
                System.out.println("Enter Category name");
                categoryName = sc.nextLine();
                System.out.println("Enter product ID (in integer) ");
                productID = sc.nextInt();
                sc.nextLine();
                try {
                    admin.removeProduct(productID, categoryName);
                } catch (Exception e) {
                    System.out.println(e);
                }
                break;
            case 5:
                System.out.println("Enter product ID (in integer) ");
                productID = sc.nextInt();
                sc.nextLine();
                System.out.println("Enter discount for normal customer (in integer) ");
                int normalCustomerDiscount = sc.nextInt();
                sc.nextLine();
                System.out.println("Enter discount for prime customer (in integer) ");
                int primeCustomerDiscount = sc.nextInt();
                sc.nextLine();
                System.out.println("Enter discount for elite customer (in integer) ");
                int eliteCustomerDiscount = sc.nextInt();
                sc.nextLine();
                try {
                    admin.setDiscount(productID, normalCustomerDiscount, primeCustomerDiscount, eliteCustomerDiscount);
                } catch (Exception e) {
                    System.out.println(e);
                }
                break;
            case 6:
                System.out.println("Enter deal name ");
                String dealName = sc.nextLine();
                System.out.println("Enter product1 ID (in integer) ");
                int productID1 = sc.nextInt();
                sc.nextLine();
                System.out.println("Enter product2 ID (in integer) ");
                int productID2 = sc.nextInt();
                sc.nextLine();
                System.out.println("Enter deal ID (in integer) ");
                int dealID = sc.nextInt();
                sc.nextLine();
                System.out.println("Enter discount price for normal customer (in integer) ");
                int normalCustomerPrice = sc.nextInt();
                sc.nextLine();
                System.out.println("Enter discount price for prime customer (in integer) ");
                int primeCustomerPrice = sc.nextInt();
                sc.nextLine();
                System.out.println("Enter discount price for elite customer (in integer) ");
                int eliteCustomerPrice = sc.nextInt();
                sc.nextLine();
                try {
                    admin.addDeal(dealName, productID1, productID2, dealID, normalCustomerPrice, primeCustomerPrice, eliteCustomerPrice);
                } catch (Exception e) {
                    System.out.println(e);
                }
                break;
            case 7:
                main(null);
                return;
        }
        adminMenu();
    }

    public static void customerMenu(){
        System.out.println("1) Sign up\n" +
                "2) Log in\n" +
                "3) Back");
        Scanner sc = new Scanner(System.in);
        int choice = sc.nextInt();
        sc.nextLine();
        switch(choice){
            case 1:
                System.out.println("Enter username");
                String userName = sc.nextLine();
                System.out.println("Enter password");
                String password = sc.nextLine();
                System.out.println("Enter email");
                String email = sc.nextLine();
                System.out.println("Enter phone number");
                String phoneNumber = sc.nextLine();
                System.out.println("Enter age");
                int age = sc.nextInt();
                sc.nextLine();
                try {
                    new Normal(userName, age, phoneNumber, email, password);
                } catch (Exception e) {
                    System.out.println(e);
                }
                break;
            case 2:
                System.out.println("Enter username");
                userName = sc.nextLine();
                System.out.println("Enter password");
                password = sc.nextLine();
                for (Customer customer : Customer.getAllCustomers()){
                    if (customer.getName().equals(userName) && customer.getPassword().equals(password)){
                        System.out.println("Logged in successfully as " + customer.getName());
                        customerMenu2(customer);
                    }
                }
                break;
            case 3:
                main(null);
                return;
        }
        customerMenu();
    }

    public static void customerMenu2(Customer customer){
        System.out.println("1) browse products\n" +
                "2) browse deals\n" +
                "3) add a product to cart\n" +
                "4) add products in deal to cart\n" +
                "5) view coupons\n" +
                "6) check account balance\n" +
                "7) view cart\n" +
                "8) empty cart\n" +
                "9) checkout cart\n" +
                "10) upgrade customer status\n" +
                "11) Add amount to wallet\n" +
                "12) back");
        Scanner sc = new Scanner(System.in);
        int choice = sc.nextInt();
        sc.nextLine();
        switch(choice){
            case 1:
                customer.exploreProductCatalogue();
                break;
            case 2:
                customer.exploreDealsCatalogue();
                break;
            case 3:
                System.out.println("Enter product ID (in integer) ");
                int productID = sc.nextInt();
                sc.nextLine();
                System.out.println("Enter quantity (in integer) ");
                int quantity = sc.nextInt();
                sc.nextLine();
                try {
                    customer.addProductToCart(productID, quantity);
                } catch (Exception e) {
                    System.out.println(e);
                }
                break;
            case 4:
                System.out.println("Enter deal ID (in integer) ");
                int dealID = sc.nextInt();
                sc.nextLine();
                try {
                    customer.addDealToCart(dealID);
                } catch (Exception e) {
                    System.out.println(e);
                }
                break;
            case 5:
                if (customer.getCoupons() == null){
                    System.out.println("No coupons");
                }
                else {
                    for (Integer coupon : customer.getCoupons()) {
                        System.out.println("There are " + customer.getCoupons().size() + " coupons, worth");
                        System.out.print(coupon + " %, ");
                    }
                    System.out.println("");
                }
                break;
            case 6:
                System.out.println("Your account balance is " + customer.getWalletBalance());
                break;
            case 7:
                Cart cart = customer.getCart();
                System.out.println("Your cart has the following products: ");
                for (Product product : cart.getProducts()){
                    System.out.println("Product name: " + product.getName() + " Product ID: " + product.getID() + " Product quantity: " + cart.getpQuantity().get(cart.getProducts().indexOf(product)));
                }
                System.out.println("Your cart has the following deals: ");
                for (Deals deal : cart.getDeals()){
                    System.out.println("Deal name: " + deal.getName() + " Deal ID: " + deal.getID());
                }
                break;
            case 8:
                customer.clearCart();
                break;
            case 9:
                try{
                    customer.makePayment(customer.generateBill());
                } catch (Exception e) {
                    System.out.println(e);
                }
                break;
            case 10:
                System.out.println("Enter new customer status");
                String customerStatus = sc.nextLine();
                customer = customer.updateStatus(customerStatus);
                System.out.println("Customer status updated successfully to " + customerStatus);
                break;
            case 11:
                System.out.println("Enter amount to add (in integer) ");
                int amount = sc.nextInt();
                customer.setWalletBalance(amount);
                break;
            case 12:
                customerMenu();
                return;
        }
        customerMenu2(customer);
    }
}

