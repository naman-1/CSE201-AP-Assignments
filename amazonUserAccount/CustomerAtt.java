import java.util.ArrayList;

public interface CustomerAtt {
    ArrayList<Integer> getCoupons();
    float generateBill();
    void makePayment(Float amount);
    Customer updateStatus(String customerNewStatus);
}
