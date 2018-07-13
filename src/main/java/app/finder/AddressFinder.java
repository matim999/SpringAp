package app.finder;

import app.entity.Address;
import app.repository.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AddressFinder {
    private final AddressRepository addressRepository;

    @Autowired
    public AddressFinder(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    public Address findAddressById(int id) {
        return addressRepository.findById(id).get();
    }

    public List findAddressByCountry(String country) {
        return addressRepository.findAllByCity_Country_Country(country);
    }

    public List findAddressBy(String address, String district, int cityId, String city, int countryId, String country, String postalCode, String phone) {
        List<Address> addresses = addressRepository.findAll();
        return addresses.stream()
                .filter(a -> address == null || a.getAddress().equals(address))
                .filter(a -> address == null || a.getAddress().equals(address))
                .filter(a -> district == null || a.getDistrict().equals(district))
                .filter(a -> cityId == 0 || a.getCity().getCityId() == cityId)
                .filter(a -> postalCode == null || a.getPostalCode().equals(postalCode))
                .filter(a -> phone == null || a.getPhone().equals(phone))
                .filter(a -> city == null || a.getCity().getCity().equals(city))
                .filter(a -> countryId == 0 || a.getCity().getCountry().getCountryId() == countryId)
                .filter(a -> country == null || a.getCity().getCountry().getCountry().equals(country))
                .collect(Collectors.toList());
        //return addressRepository.findQuery(address, district, /*cityId,*/ city, /*countryId,*/ country, postalCode, phone);
    }
}
