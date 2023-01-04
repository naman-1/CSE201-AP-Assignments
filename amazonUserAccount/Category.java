import java.util.ArrayList;

public class Category 
{
    private String categoryName;
    private int categoryID;
    private static ArrayList<Category> allCategories = new ArrayList<Category>();
    private ArrayList<Product> products;
    private ArrayList<Deals> deals;

    public Category(int categoryID, String categoryName) 
    {
        if (checkUniqueID(categoryID) == 0)
        {
            throw new IllegalArgumentException("Category ID is not unique try again ");
        }
        this.products = new ArrayList<Product>();
        this.deals = new ArrayList<Deals>();
        this.categoryID = categoryID;
        this.categoryName = categoryName;
        Category.allCategories.add(this);
    }

    public static ArrayList<Category> getAllCategories() 
    {
        return allCategories;
    }
    ArrayList<Product> getProducts() 
    {
        return this.products;
    }
    ArrayList<Deals> getDeals() 
    {
        return this.deals;
    }
    int getCategoryID() 
    {
        return this.categoryID;
    }
    String getCategoryName() 
    {
        return this.categoryName;
    }

    void addProduct(Product product) 
    {
        this.products.add(product);
    }
    void addDeal(Deals deal) 
    {
        this.deals.add(deal);
    }

    int checkUniqueID(int categoryID)
    {
        /*
        * used to check if the product ID is unique
        */
        for (Category i : Category.getAllCategories())
        {
            if (i.getCategoryID() == categoryID)
            {
                return 0;
            }
        }
        return 1;
    }

    public void removeProduct(int productID) 
    {
        int prodSize = this.products.size();
        for (int i = 0 ; i < prodSize ; i++) 
        {
            if (this.products.get(i).getID() == productID) 
            {
                this.products.remove(i);
                break;
            }
        }
    }
}
