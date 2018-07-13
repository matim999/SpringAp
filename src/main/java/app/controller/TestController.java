package app.controller;

import app.DTO.converter.BaseConverter;
import app.DTO.responseDTO.RentalDto;
import app.entity.Rental;
import app.repository.CityRepository;
import app.repository.RentalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Collection;

@RestController
@RequestMapping(path = "/test")
public class TestController {
    private final CityRepository cityRepository;
    private final RentalRepository rentalRepository;
    private final BaseConverter<Rental, RentalDto> rentalConverter;

    @Autowired
    public TestController(CityRepository cityRepository, RentalRepository rentalRepository, BaseConverter<Rental, RentalDto> rentalConverter) {
        this.cityRepository = cityRepository;
        this.rentalRepository = rentalRepository;
        this.rentalConverter = rentalConverter;
    }

    @GetMapping(path = "/{id}")
    public Collection<RentalDto> test(@PathVariable int id) {
        return rentalConverter.convertAll(rentalRepository.findAllByInventoryFilmFilmIdAndReturnDateIsNullOrderByRentalDateAsc(id).orElse(new ArrayList<>()));
    }
}
