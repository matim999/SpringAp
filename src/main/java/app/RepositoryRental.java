package app;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RepositoryRental extends JpaRepository<Rental, Integer> {
    List findAllByCustomerId(int customer_id);
}
