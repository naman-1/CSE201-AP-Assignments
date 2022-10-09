import java.util.ArrayList;

public interface CustomerAtt {
    ArrayList<Integer> getCoupons();
    int generateBill();
    void makePayment(int amount);
    Customer updateStatus(String customerNewStatus);
}
