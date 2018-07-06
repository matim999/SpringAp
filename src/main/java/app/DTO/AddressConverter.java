package app.DTO;

import app.entity.Address;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class AddressConverter implements BaseConverter<Address, AddressDto> {
    private final CityConverter cityConverter;

    @Autowired
    public AddressConverter(CityConverter cityConverter) {
        this.cityConverter = cityConverter;
    }

    @Override
    public AddressDto convert(Address from) {
        AddressDto addressDto = new AddressDto();
        addressDto.setAddressId(from.getAddressId());
        addressDto.setAddress(from.getAddress());
        addressDto.setAddress2(from.getAddress2());
        addressDto.setDistrict(from.getDistrict());
        addressDto.setCity(cityConverter.convert(from.getCity()));
        addressDto.setPostalCode(from.getPostalCode());
        addressDto.setPhone(from.getPhone());
        addressDto.setLastUpdate(from.getLastUpdate());
        return addressDto;
    }

    public List convert(List<Address> addresses) {
        List addressDtos = new ArrayList();
        for (Address address : addresses) {
            addressDtos.add(convert(address));
        }
        return addressDtos;
    }
}