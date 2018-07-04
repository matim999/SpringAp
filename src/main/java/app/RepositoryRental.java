package app;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RepositoryRental extends JpaRepository<Rental, Integer> {
    List findAllByInventory_id(int customer_id);
}
