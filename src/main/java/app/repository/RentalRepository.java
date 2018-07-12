package app.repository;

import app.entity.Rental;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface RentalRepository extends JpaRepository<Rental, Integer> {
    List<Rental> findAllByCustomerCustomerId(int customer_id);
    Optional<List<Rental>> findAllByRentalDate(LocalDateTime date);
    Optional<List<Rental>> findAllByInventoryFilmFilmIdAndReturnDateIsNullOrderByRentalDateAsc(int filmId);
}
