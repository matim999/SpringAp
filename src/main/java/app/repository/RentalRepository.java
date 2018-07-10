package app.repository;

import app.entity.Rental;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RentalRepository extends JpaRepository<Rental, Integer> {
    List<Rental> findAllByCustomerCustomerId(int customer_id);
    Optional<List<Rental>> findAllByRentalDate(String date);
}
