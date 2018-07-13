package app.service;

import app.DTO.converter.BaseConverter;
import app.DTO.responseDTO.AddressDto;
import app.DTO.responseDTO.CityDto;
import app.entity.Address;
import app.entity.City;
import app.repository.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;

@Service
public class AddressService {
    private final AddressRepository addressRepository;
    private final CityService cityService;
    private final BaseConverter<Address, AddressDto> addressConverter;

    @Autowired
    public AddressService(AddressRepository addressRepository, CityService cityService, BaseConverter<Address, AddressDto> addressConverter) {
        this.addressRepository = addressRepository;
        this.cityService = cityService;
        this.addressConverter = addressConverter;
    }

    Address checkForAddress(AddressDto addressDto) {
        CityDto cityDto = addressDto.getCity();
        Collection<AddressDto> existingAddresses = addressConverter.convertAll(addressRepository.findByAddress(addressDto.getAddress()).orElse(new ArrayList<>()));
        if (existingAddresses.isEmpty()) {
            return saveAddress(addressDto, cityDto);
        }
        return existingAddresses
                .stream()
                .filter(a -> a.equals(addressDto))
                .findFirst()
                .map(a -> addressRepository.findById(a.getAddressId()).get())
                .orElseGet(() -> saveAddress(addressDto, cityDto));
    }

    private Address saveAddress(AddressDto addressDto, CityDto cityDto) {
        City city = cityService.checkForCity(cityDto);
        return addressRepository.saveAndFlush(new Address(addressDto, city));
    }
}
