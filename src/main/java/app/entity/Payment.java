package app.entity;

import app.DTO.requestDTO.PaymentDtoRequest;
import app.DTO.responseDTO.PaymentDto;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
@EqualsAndHashCode
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "payment_payment_id_seq")
    @SequenceGenerator(
            name = "payment_payment_id_seq",
            sequenceName = "payment_payment_id_seq",
            allocationSize = 1
    )
    @Column(name = "payment_id")
    @EqualsAndHashCode.Exclude
    private @Getter
    int paymentId;
    @ManyToOne(cascade = {CascadeType.ALL, CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "customer_id")
    private @Getter
    Customer customer;
    @ManyToOne(cascade = {CascadeType.ALL, CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "staff_id")
    @EqualsAndHashCode.Exclude
    private @Getter
    Staff staff;
    @ManyToOne(cascade = {CascadeType.ALL, CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "rental_id")
    private @Getter
    Rental rental;
    private @Getter
    double amount;
    @JsonFormat(pattern = "yyyy-MM-dd kk:mm:ss")
    @DateTimeFormat(iso = DateTimeFormat.ISO.TIME)
    private @Getter
    LocalDateTime paymentDate;

    public Payment(PaymentDtoRequest paymentDtoRequest, @NotNull Customer customer, @NotNull Staff staff, @NotNull Rental rental) {
        this.customer = customer;
        this.staff = staff;
        this.rental = rental;
        this.amount = paymentDtoRequest.getAmount();
        this.paymentDate = paymentDtoRequest.getPaymentDate();
    }

    private Payment() {
    }

    public void update(PaymentDto paymentDto) {
        this.amount = paymentDto.getAmount();
    }

    ;
}
