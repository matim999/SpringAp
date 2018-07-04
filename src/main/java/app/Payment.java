package app;

import javax.persistence.*;

@Entity
public class Payment {
    @Id
    @Column(name = "payment_id")
    private int payment_id;
    private int customer_id;
    @ManyToOne(cascade = CascadeType.ALL, targetEntity = Staff.class)
    @JoinColumn(name = "staff_id")
    private Staff staff;
    @ManyToOne(cascade = CascadeType.ALL, targetEntity = Rental.class)
    @JoinColumn(name = "rental_id")
    private Rental rental;
    private int amount;
    private String payment_date;

    public int getPayment_id() {
        return payment_id;
    }
    public int getCustomer_id() {
        return customer_id;
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
    public String getPayment_date() {
        return payment_date;
    }
}
