package app.controller;

import app.DTO.converter.BaseConverter;
import app.DTO.responseDTO.AddressDto;
import app.entity.Address;
import app.finder.AddressFinder;
import app.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/address")
public class AddressController {
    private final AddressFinder addressFinder;
    private final BaseConverter<Address, AddressDto> addressConverter;
    private final AddressService addressService;

    @Autowired
    public AddressController(AddressFinder addressFinder, BaseConverter<Address, AddressDto> addressConverter, AddressService addressService) {
        this.addressFinder = addressFinder;
        this.addressConverter = addressConverter;
        this.addressService = addressService;
    }

    @GetMapping()
    public @ResponseBody
    ResponseEntity<List> getAddressByQuery(@RequestParam(required = false) String address,
                                           @RequestParam(required = false) String district,
                                           @RequestParam(required = false, defaultValue = "0") int cityId,
                                           @RequestParam(required = false) String city,
                                           @RequestParam(required = false, defaultValue = "0") int countryId,
                                           @RequestParam(required = false) String country,
                                           @RequestParam(required = false) String postalCode,
                                           @RequestParam(required = false) String phone) {
        return new ResponseEntity(addressConverter.convertAll(addressFinder.findAddressBy(address, district, cityId, city, countryId, country, postalCode, phone)), HttpStatus.OK);
    }

    @GetMapping(path = "/{id}")
    public @ResponseBody
    ResponseEntity getAddressById(@PathVariable int id) {
        return new ResponseEntity(addressConverter.convertAll(addressFinder.findAddressById(id)), HttpStatus.OK);
    }

    @GetMapping(path = "/search/country/{country}")
    public @ResponseBody
    ResponseEntity getAddressByCountry(@PathVariable String country) {
        return new ResponseEntity(addressConverter.convertAll(addressFinder.findAddressByCountry(country)), HttpStatus.OK);
    }

    @PostMapping
    public @ResponseBody
    ResponseEntity addNewAddress(@RequestBody Address address) {
        addressService.addNewAddress(address);
        return new ResponseEntity(HttpStatus.OK);
    }
}