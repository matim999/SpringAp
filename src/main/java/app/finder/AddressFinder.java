package app.finder;

import app.entity.Address;
import app.repository.AddressRepository;
import app.repository.CityRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressFinder {
    private final AddressRepository addressRepository;
    private final CityRepository cityRepository;

    public AddressFinder(AddressRepository addressRepository, CityRepository cityRepository) {
        this.addressRepository = addressRepository;
        this.cityRepository = cityRepository;
    }

    public List findAllAddress(){
        return addressRepository.findAll();
    }
    public Address findAddressById(int id){
        return addressRepository.findById(id).get();
    }

    public List findAddressByCountry(String country) {
        return addressRepository.findAllByCity_Country_Country(country);
    }

    public List findAddressBy(String address, String district, int cityId, String city, int countryId, String country, String postalCode, String phone) {
        List result = addressRepository.findAllByAddress(address);
        result.add(district != null ? addressRepository.findAllByDistrict(district) : null);
        result.add(cityId != 0 ? addressRepository.findAllByCity_CityId(cityId) : null);
        result.add(city != null ? addressRepository.findAllByCity_City(city) : null);
        result.add(countryId != 0 ? addressRepository.findAllByCity_Country_CountryId(countryId) : null);
        result.add(country != null ? addressRepository.findAllByCity_Country_Country(country) : null);
        result.add(postalCode != null ? addressRepository.findAllByPostalCode(postalCode) : null);
        result.add(phone == null ? addressRepository.findAllByPhone(phone) : null);
        addressRepository.findBy();
        return result;
    }

    public Address findAddressQuery(String address, String district, /*int cityId,*/ String city, /*int countryId,*/ String country, String postalCode, String phone) {
        return addressRepository.findQuery(address, district, /*cityId,*/ city, /*countryId,*/ country, postalCode, phone);
    }
}
