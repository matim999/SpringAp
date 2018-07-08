package app.repository;

import app.entity.Address;
import app.entity.City;
import app.entity.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


import javax.persistence.TemporalType;
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


//    @Query(value = "SELECT * from ADDRESS a " +
//            "inner join CITY c on a.city_id = c.city_id " +
//            "inner join COUNTRY co on c.country_id = co.country_id where " +
//            ":aaddress is null or a.address like :aaddress and " +
//            ":ddistrict is null or a.district like :ddistrict and " +
//            //"?3 is null or a.city_id = ?3 and " +
//            ":ccity is null or c.city like :ccity and " +
//            //"?5 is null or c.country_id = ?5 and " +
//            ":ccountry is null or co.country like :ccountry and " +
//            ":ppostalCode is null or a.postal_code like :ppostalCode and " +
//            ":pphone is null or a.phone like :pphone", nativeQuery = true)
@Query(value = "SELECT * from ADDRESS a " +
        "inner join CITY c on a.city_id = c.city_id " +
        "inner join COUNTRY co on c.country_id = co.country_id where " +
        "a.address like :aaddress and " +
        "a.district like :ddistrict and " +
        //"?3 is null or a.city_id = ?3 and " +
        "c.city like :ccity and " +
        //"?5 is null or c.country_id = ?5 and " +
        "co.country like :ccountry and " +
        "a.postal_code like :ppostalCode and " +
        "a.phone like :pphone", nativeQuery = true)
    List findQuery(@Param("aaddress") String address,
                   @Param("ddistrict")String district, /*int cityId,*/
                   @Param("ccity")String city, /*int countryId,*/
                   @Param("ccountry")String country,
                   @Param("ppostalCode")String postalCode,
                   @Param("pphone")String phone);
}
