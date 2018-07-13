package app.repository;

import app.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AddressRepository extends JpaRepository<Address, Integer> {
    List<Address> findAllByCity_Country_Country(String country);

    Optional<List<Address>> findByAddress(String address);
}
