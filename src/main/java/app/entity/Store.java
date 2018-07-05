package app.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "Store")
public class Store {
    @Id
    @Column(name = "store_id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "store_store_id_seq")
    @SequenceGenerator(
            name="store_store_id_seq",
            sequenceName="store_store_id_seq",
            allocationSize = 1
    )
    private int store_id;
    private int managerStaffId;
    private int addressId;
    @JsonFormat(pattern="yyyy-MM-dd kk:mm:ss.SS")
    @DateTimeFormat(iso = DateTimeFormat.ISO.TIME)
    private LocalDateTime lastUpdate;

    public int getStore_id() {
        return store_id;
    }
    public int getManagerStaffId() {
        return managerStaffId;
    }
    public int getAddressId() {
        return addressId;
    }
    public LocalDateTime getLastUpdate() {
        return lastUpdate;
    }

}