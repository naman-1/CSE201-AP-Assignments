import java.util.ArrayList;

public class Cart {
    ArrayList<Product> products;
    ArrayList<Integer> pQuantity;
    ArrayList<Deals> deals;
    public Cart() 
    {
        this.products = new ArrayList<Product>();
        this.pQuantity = new ArrayList<Integer>();
        this.deals = new ArrayList<Deals>();
    }
    
    int getProductQuantity(Product product) 
    {
        int index = this.products.indexOf(product);
        return this.pQuantity.get(index);
    }
    ArrayList<Product> getProducts() 
    {
        return this.products;
    }
    ArrayList<Deals> getDeals() 
    {
        return this.deals;
    }
    ArrayList<Integer> getpQuantity() 
    {
        return this.pQuantity;
    }
    public void add(Product product, int quant) 
    {
        if (this.products.contains(product)) 
        {
            int index = this.products.indexOf(product);
            this.pQuantity.set(index, this.pQuantity.get(index) + quant);
        }
        else 
        {
            this.products.add(product);
            this.pQuantity.add(quant);
        }
    }
    public void add(Deals deal) 
    {
        if (this.deals.contains(deal)) 
        {
            System.out.println("Deal already in cart");
        }
        else 
        {
            this.deals.add(deal);
        }
    }
    public void clear() 
    {
        this.products.clear();
        this.pQuantity.clear();
        this.deals.clear();
    }
}