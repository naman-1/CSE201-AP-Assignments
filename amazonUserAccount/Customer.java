import java.util.ArrayList;

abstract class Customer implements CustomerAtt
{
    private int age, categoryDiscount;
    private float walletBalance;
    private String name, emailID, password, phoneNumber;
    private static ArrayList<Customer> allCustomers = new ArrayList<Customer>();
    Cart cart;
    public Customer(String name, int age, String phoneNumber, String emailID, String password)
    {
        this.walletBalance = 1000;
        this.name = name;
        this.age = age;
        this.phoneNumber = phoneNumber;
        this.emailID = emailID;
        this.password = password;
        this.cart = new Cart();
        Customer.allCustomers.add(this);
    }

    float getWalletBalance() 
    {
        return this.walletBalance;
    }
    int getAge() 
    {
        return this.age;
    }
    String getPhoneNumber() 
    {
        return this.phoneNumber;
    }
    int getCategoryDiscount() 
    {
        return this.categoryDiscount;
    }
    String getName() 
    {
        return this.name;
    }
    String getEmailID() 
    {
        return this.emailID;
    }
    String getPassword() 
    {
        return this.password;
    }
    static ArrayList<Customer> getAllCustomers() 
    {
        return Customer.allCustomers;
    }
    Cart getCart() 
    {
        return this.cart;
    }
    
    void setWalletBalance(Float walletBalance) 
    {
        this.walletBalance += walletBalance;
    }
    void setCategotyDiscount(int categoryDiscount) 
    {
        this.categoryDiscount = categoryDiscount;
    }
    
    int login(String name, String emailID, String password) 
    {
        if (this.emailID.equals(emailID) && this.password.equals(password) && this.name.equals(name))
        {
            return 1;
        }
        else
        {
            return 0;
        }
    }

    void exploreProductCatalogue() 
    {
        System.out.println("Exploring Products Catalogue");
        for (Category categories : Category.getAllCategories()) 
        {
            System.out.println("category name: " + categories.getCategoryName() + "\n products: ");
            for (Product products : categories.getProducts()) 
            {
                System.out.println("product name: " + products.getName());
                System.out.println("price: " + products.getPrice());
                System.out.println("discount: " + products.getDiscount(this));
                System.out.println("quantity: " + products.getQuantity());
                System.out.println("product ID: " + products.getID());

            }
        }
    }
   
    void exploreDealsCatalogue()
    {
        System.out.println("Exploring Deals Catalogue");
        for (Deals deals : Deals.getAllDeals()) 
        {
            System.out.println("deal ID: " + deals.getID());
            System.out.println("price: " + deals.getPrice(this));
            System.out.println("products in deal: ");
            System.out.println("product 1: " + deals.getProducts().get(0).getName());
            System.out.println("product 2: " + deals.getProducts().get(1).getName());
        }
    }

    void addProductToCart(int productID, int quantity) 
    {
        for (Product products : Product.getAllProducts()) 
        {
            if (products.getID() == productID) 
            {
                if (products.getQuantity() >= quantity) 
                {
                    this.cart.add(products, quantity);
                    // products.setQuantity(products.getQuantity() - quantity);
                    System.out.println("Product added to cart successfully");
                }
                else 
                {
                    System.out.println("Quantity requested is not available");
                }
            }
        }
    }
    
    void addDealToCart(int dealID)
    {
        for (Deals deals : Deals.getAllDeals())
        {
            if (deals.getID() == dealID)
            {
                this.cart.add(deals);
                // deals.getProducts().get(0).setQuantity(deals.getProducts().get(0).getQuantity() - 1);
                // deals.getProducts().get(1).setQuantity(deals.getProducts().get(1).getQuantity() - 1);
                System.out.println("Deal added to cart successfully");
            }
        }
    }

    void clearCart() 
    {
        this.cart.clear();
    }

    int checkCartAvailability()
    {
        int flag = 0 ;
        ArrayList<Product> products = this.cart.getProducts();
        ArrayList<Integer> quantities = this.cart.getpQuantity();
        ArrayList<Deals> deals = this.cart.getDeals();
        for (int i = 0 ; i < products.size() ; i++)
        {
            int quantDemanded = quantities.get(i);
            for (Deals deal : deals)
            {
                if (deal.getProducts().contains(products.get(i)))
                {
                    quantDemanded += 1;
                }
            }
            if (products.get(i).getQuantity() < quantDemanded)
            {
                flag = 1;
                System.out.println("Product " + products.get(i).getName() + " is not available in the required quantity");
                System.out.println("Quantity available: " + products.get(i).getQuantity());
                System.out.println("Quantity demanded: " + quantDemanded);
            }
        }
        if (flag == 0)
        {
            System.out.println("Cart is ready to be checked out");
            return 1;
        }
        else
        {
            return 0;
        }
    }

    void checkOut()
    {
        ArrayList<Product> products = this.cart.getProducts();
        ArrayList<Integer> quantities = this.cart.getpQuantity();
        ArrayList<Deals> deals = this.cart.getDeals();
        for (int i = 0 ; i < products.size() ; i++)
        {
            int quantDemanded = quantities.get(i);
            Product prod = products.get(i);
            for (Deals deal : deals)
            {
                if (deal.getProducts().contains(prod))
                {
                    quantDemanded += 1;
                }
            }
            prod.setQuantity(prod.getQuantity() - quantDemanded);
        }
    }
}