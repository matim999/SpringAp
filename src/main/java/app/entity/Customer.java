package app.entity;

import app.DTO.responseDTO.CustomerDto;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

//gg
@Entity
@EqualsAndHashCode
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "customer_customer_id_seq")
    @SequenceGenerator(
            name = "customer_customer_id_seq",
            sequenceName = "customer_customer_id_seq",
            allocationSize = 1
    )
    @Column(name = "customer_id")
    @EqualsAndHashCode.Exclude
    private @Getter
    int customerId;
    private @Getter
    int storeId;
    private @Getter
    String firstName;
    private @Getter
    String lastName;
    private @Getter
    String email;
    @ManyToOne(cascade = {CascadeType.ALL, CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "address_id")
    private @Getter
    Address address;
    private @Getter
    boolean activebool;
    @JsonFormat(pattern = "yyyy-MM-dd")
    @DateTimeFormat(iso = DateTimeFormat.ISO.TIME)
    private @Getter
    Date createDate;
    private @Getter
    int active;

    public Customer(CustomerDto customerDto, Address address) {
        this.storeId = customerDto.getStoreId();
        this.firstName = customerDto.getFirstName();
        this.lastName = customerDto.getLastName();
        this.email = customerDto.getEmail();
        this.address = address;
        this.activebool = customerDto.isActivebool();
        this.createDate = customerDto.getCreateDate();
        this.active = customerDto.getActive();
    }

    public Customer() {
        super();
    }

    public void update(CustomerDto customerDto) {
        this.firstName = customerDto.getFirstName();
        this.lastName = customerDto.getLastName();
        this.email = customerDto.getEmail();
        this.activebool = customerDto.isActivebool();
        this.createDate = customerDto.getCreateDate();
        this.active = customerDto.getActive();
    }
}
