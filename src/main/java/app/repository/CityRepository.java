package app.repository;

import app.entity.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CityRepository extends JpaRepository<City, Integer> {
    City findByCity(String city);
    List findByCountry_Country(String country);
    List findByCityAndCountry_Country(String city, String country);
}
