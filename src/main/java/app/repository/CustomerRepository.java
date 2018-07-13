package app.repository;

import app.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {
    Optional<List<Customer>> findAllByFirstName(String fname);

    Optional<List<Customer>> findAllByFirstNameAndLastName(String fname, String lname);
}
