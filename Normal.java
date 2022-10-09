import java.util.ArrayList;
public class Normal extends Customer implements CustomerAtt {
    private static ArrayList<Normal> normalCustomers = new ArrayList<Normal>();
    
    public Normal(String name, int age, String phoneNumber, String emailID, String password) {
        super(name, age, phoneNumber, emailID, password);
        normalCustomers.add(this);
        this.setCategotyDiscount(0);
    }
    
    public Customer updateStatus(String customerNewStatus) {
        switch (customerNewStatus) {
            case "Prime":
                Prime prime = new Prime(this.getName(), this.getAge(), this.getPhoneNumber(), this.getEmailID(), this.getPassword());
                prime.setWalletBalance(-200);
                Normal.normalCustomers.remove(this);
                return prime;
            case "Elite":
                Elite elite = new Elite(this.getName(), this.getAge(), this.getPhoneNumber(), this.getEmailID(), this.getPassword());
                elite.setWalletBalance(-300);
                Normal.normalCustomers.remove(this);
                return elite;
            default:
                break;
        }
        return null;
    }
   
    public ArrayList<Integer> getCoupons() {
        return null;
    }

    public int generateBill() {
        if (this.checkCartAvailability() == 0){
            throw new RuntimeException("Products in cart are not available");
        }
        int totalBill = 0;
        System.out.println("Cart has following deals: ");
        for (Deals deal : this.getCart().getDeals()) {
            System.out.println("deal name: " + deal.getName());
            System.out.println("deal price: " + deal.getPrice());
            totalBill += deal.getPrice(this);
        }
        System.out.println("\nCart has following products: ");
        for (Product product : this.getCart().getProducts()) {
            int maxDiscountForProd = 0;
            if (product.getDiscount(this) > maxDiscountForProd){
                maxDiscountForProd = product.getDiscount(this);
            }
            if (this.getCategoryDiscount() > maxDiscountForProd){
                maxDiscountForProd = this.getCategoryDiscount();
            }
            int prodPrice = product.getPrice() - (product.getPrice() * (maxDiscountForProd / 100));
            int quantity = this.getCart().getProductQuantity(product);
            System.out.println("product name: " + product.getName());
            System.out.println("product description: ");
            product.printAbstractData();
            System.out.println("product price after discount : " + prodPrice*quantity);
            totalBill += (prodPrice*quantity);
        }
        System.out.println("Delivery charges: " + ((0.05 * totalBill)+100));
        totalBill += (100 + 0.05*totalBill);
        System.out.println("Total bill: " + totalBill);
        return totalBill;
    }
    
    public void makePayment(int amount) {
        if (this.getWalletBalance() < amount){
            throw new RuntimeException("Insufficient balance");
        }
        this.setWalletBalance(-amount);
        this.checkOut();
        System.out.println("Order will be delivered in 7-10 days");
        this.clearCart();
    }

}

