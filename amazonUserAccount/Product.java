import java.util.ArrayList;

public class Product 
{
    private int ID, price, quantity, normalCustomerDiscount, primeCustomerDiscount, eliteCustomerDiscount;
    private String name, abstractData;
    private static ArrayList<Product> allProducts = new ArrayList<Product>();
   
    public Product(int productID, String productName, int quantity, int price, String abstractData) 
    {
        if (checkUniqueID(productID) == 0)
        {
            throw new IllegalArgumentException("Product ID is not unique try again ");
        }
        this.normalCustomerDiscount = 0;
        this.primeCustomerDiscount = 0;
        this.eliteCustomerDiscount = 0;
        this.quantity = quantity;
        this.ID = productID;
        this.name = productName;
        this.price = price;
        this.abstractData = abstractData;
        Product.allProducts.add(this);
    }
    public Product(String name, int dealID, int price){
        this.name = name;
        this.ID = dealID;
        this.quantity = 0;
        this.price = price;
    }; // Default constructor
    
    void printAbstractData() 
    {
        System.out.println(this.abstractData);
    }
    String getName() 
    {
        return this.name;
    }
    int getID()
    {
        return this.ID;
    }
    int getDiscount(Object customer)
    {
        if (customer instanceof Normal)
        {
            return this.normalCustomerDiscount;
        }
        else if (customer instanceof Prime)
        {
            return this.primeCustomerDiscount;
        }
        else if (customer instanceof Elite)
        {
            return this.eliteCustomerDiscount;
        }
        else
        {
            return 0;
        }
    }
    int getPrice()
    {
        return this.price;
    }
    int getQuantity()
    {
        return this.quantity;
    }
    static ArrayList<Product> getAllProducts()
    {
        return allProducts;
    }

    void setCustomerDiscount(int normalCustomerDiscount, int primeCustomerDiscount, int eliteCustomerDiscount) 
    {
        this.normalCustomerDiscount = normalCustomerDiscount;
        this.primeCustomerDiscount = primeCustomerDiscount;
        this.eliteCustomerDiscount = eliteCustomerDiscount;
    }
    void setQuantity(int quantity)
    {
        this.quantity = quantity;
    }   
    
    int checkUniqueID(int productID)
    {
        /*
        * used to check if the product ID is unique
        */
        for (Product i : Product.getAllProducts())
        {
            if (i.getID() == productID)
            {
                return 0;
            }
        }
        return 1;
    }
}