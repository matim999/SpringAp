package app;

import javax.persistence.*;

@Entity
public class Payment {
    @Id
    @Column(name = "payment_id")
    private int PaymentId;
    private int customerIdd;
    @ManyToOne(cascade = CascadeType.ALL, targetEntity = Staff.class)
    @JoinColumn(name = "staff_id")
    private Staff staff;
    @ManyToOne(cascade = CascadeType.ALL, targetEntity = Rental.class)
    @JoinColumn(name = "rental_id")
    private Rental rental;
    private int amount;
    private String paymentDate;

    public int getPaymentId() {
        return PaymentId;
    }
    public int getCustomerIdd() {
        return customerIdd;
    }
    public Staff getStaff() {
        return staff;
    }
    public Rental getRental() {
        return rental;
    }
    public int getAmount() {
        return amount;
    }
    public String getPaymentDate() {
        return paymentDate;
    }
}
