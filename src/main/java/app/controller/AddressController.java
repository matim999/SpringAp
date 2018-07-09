package app.controller;

import app.DTO.AddressDto;
import app.DTO.BaseConverter;
import app.entity.Address;
import app.finder.AddressFinder;
import app.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/address")
public class AddressController {
    private final AddressFinder addressFinder;
    private final AddressService addressService;
    private final BaseConverter<Address, AddressDto> addressConverter;

    @Autowired
    public AddressController(AddressFinder addressFinder, AddressService addressService, BaseConverter<Address, AddressDto> addressConverter) {
        this.addressFinder = addressFinder;
        this.addressService = addressService;
        this.addressConverter = addressConverter;
    }

    @GetMapping()
    public @ResponseBody
    ResponseEntity getAddressByQuery(  @RequestParam(required = false)String address,
                                       @RequestParam(required = false)String district,
                                       @RequestParam(required = false, defaultValue = "0")int cityId,
                                       @RequestParam(required = false)String city,
                                       @RequestParam(required = false, defaultValue = "0")int countryId,
                                       @RequestParam(required = false)String country,
                                       @RequestParam(required = false)String postalCode,
                                       @RequestParam(required = false)String phone)
    {
        return new ResponseEntity(addressConverter.convertAll(addressFinder.findAddresBy(address, district, cityId, city, countryId, country, postalCode, phone)), HttpStatus.OK);
    }

    @GetMapping(path = "/{id}")
    public @ResponseBody
    ResponseEntity getAddressById(@PathVariable int id)
    {
        return new ResponseEntity(addressConverter.convertAll(addressFinder.findAddressById(id)), HttpStatus.OK);
    }

    @GetMapping(path = "/search/country/{country}")
    public @ResponseBody
    ResponseEntity getAddressByCountry(@PathVariable String country)
    {
        return new ResponseEntity(addressConverter.convertAll(addressFinder.findAddressByCountry(country)), HttpStatus.OK);
    }
}