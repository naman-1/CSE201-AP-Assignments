import java.util.ArrayList;

public class Deals extends Product {
    private static ArrayList<Deals> allDeals = new ArrayList<Deals>();
    private ArrayList<Product> products;
    public Deals(Product product1, Product product2, String dealName, int dealID, int normalCustomerPrice, int primeCustomerPrice, int eliteCustomerPrice) 
    {
        super(dealName, dealID, normalCustomerPrice);
        if (checkUniqueID(dealID) == 0)
        {
            throw new IllegalArgumentException("Deal ID is not unique try again ");
        }
        int primeCustomerDiscount = ((primeCustomerPrice - normalCustomerPrice)/normalCustomerPrice)*100;
        int eliteCustomerDiscount = ((eliteCustomerPrice - normalCustomerPrice)/normalCustomerPrice)*100;
        this.setCustomerDiscount(0, primeCustomerDiscount, eliteCustomerDiscount);
        int productPrice = product1.getPrice() + product2.getPrice();
        if (productPrice < normalCustomerPrice || productPrice < primeCustomerPrice || productPrice < eliteCustomerPrice)
        {
            throw new IllegalArgumentException("Deal price is less than the sum of the products price");
        }
        this.products = new ArrayList<Product>();
        this.products.add(product1);
        this.products.add(product2);
        this.setQuantity(0);
        Deals.allDeals.add(this);
    }

    public static ArrayList<Deals> getAllDeals() 
    {
        return allDeals;
    }

    public ArrayList<Product> getProducts() 
    {
        return this.products;
    }
    int getPrice(Customer customer) 
    {
        return this.getPrice() * (1 - this.getDiscount(customer)/100);
    }

    @Override
    int checkUniqueID(int ID) 
    {
        for (Deals i : Deals.allDeals) 
        {
            if (i.getID() == ID) 
            {
                return 0;
            }
        }
        return 1;
    }
}