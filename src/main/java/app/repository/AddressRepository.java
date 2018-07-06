package app.repository;

import app.entity.Address;
import app.entity.City;
import app.entity.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


import java.util.List;

@Repository
public interface AddressRepository extends JpaRepository<Address, Integer> {
    List findAllByAddress(String address);
    List findAllByDistrict(String district);
    List findAllByPostalCode(String postalCode);
    List findAllByPhone(String phone);
    List findAllByCity_CityId(int cityId);
    List findAllByCity_City(String city);
    List findAllByCity_Country_CountryId(int countryId);
    List findAllByCity_Country_Country(String country);

    @Query(value = "SELECT * from ADDRESS a, CITY c, Country co where a.city_id = c.city_id and c.country_id = c.country_id and " +
            "?1 is null or a.address = ?1 and " +
            "?2 is null or a.district = ?2 and " +
            //"?3 is null or a.city_id = ?3 and " +
            "?3 is null or c.city = ?3 and " +
            //"?5 is null or c.country_id = ?5 and " +
            "?4 is null or co.country = ?4 and " +
            "?5 is null or a.postal_code = ?5 and " +
            "?6 is null or a.phone = ?6", nativeQuery = true)
    Address findQuery(String address, String district, /*int cityId,*/ String city, /*int countryId,*/ String country, String postalCode, String phone);
    void findBy();
}
