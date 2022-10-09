import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class Prime extends Customer implements CustomerAtt {
    private static ArrayList<Prime> primeCustomers = new ArrayList<Prime>();
    private ArrayList<Integer> coupons;
    public Prime(String name, int age, String phoneNumber, String emailID, String password) {
        super(name, age, phoneNumber, emailID, password);
        this.coupons = new ArrayList<Integer>();
        this.setCategotyDiscount(5);
        primeCustomers.add(this);
    }
    
    public Customer updateStatus(String customerNewStatus) {
        switch (customerNewStatus) {
            case "Normal":
                Customer customer = new Normal(this.getName(), this.getAge(), this.getPhoneNumber(), this.getEmailID(), this.getPassword());
                Prime.primeCustomers.remove(this);
                return customer;
            case "Elite":
                customer = new Elite(this.getName(), this.getAge(), this.getPhoneNumber(), this.getEmailID(), this.getPassword());
                customer.setWalletBalance(-100);
                Prime.primeCustomers.remove(this);
                return customer;
            default:
                break;
        }
        return null;
    }

    public ArrayList<Integer> getCoupons() {
        return this.coupons;
    }
   
    void removeCoupon(int coupon) {
        this.coupons.remove(coupon);
    }

    public int generateBill() {
        if (this.checkCartAvailability() == 0){
            throw new RuntimeException("Products in cart are not available");
        }
        int totalBill = 0;
        int flag = 0;
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
            if(Collections.max(this.getCoupons()) > maxDiscountForProd){
                maxDiscountForProd = Collections.max(this.getCoupons());
                flag = 1;
            }
            int prodPrice = product.getPrice() - (product.getPrice() * (maxDiscountForProd / 100));
            int quantity = this.getCart().getProductQuantity(product);
            System.out.println("product name: " + product.getName());
            System.out.println("product details: ");
            product.printAbstractData();
            System.out.println("product price after discount : " + prodPrice*quantity);
            totalBill += (prodPrice*quantity);
        }
        System.out.println("Delivery charges: " + (100 + (0.02 * totalBill)));
        totalBill += (100 + (0.02 * totalBill));
        if (flag == 1){
            this.removeCoupon(Collections.max(this.getCoupons()));
        }
        System.out.println("Total bill: " + totalBill);
        return totalBill;
    }

    public void makePayment(int amount) {
        if (amount>this.getWalletBalance()){
            throw new RuntimeException("Insufficient balance");
        }
        this.setWalletBalance(- amount);
        if (amount > 5000){
            Random rand = new Random();
            for (int i = 0; i < 2; i++) {
                int coupon = rand.nextInt(5, 16);
                this.coupons.add(coupon);
            }
        }
        this.checkOut();
        
        System.out.println("Order will be delivered in 3-6 days");
        this.clearCart();
    }

}