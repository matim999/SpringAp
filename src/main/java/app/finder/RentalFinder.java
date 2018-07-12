package app.finder;

import app.entity.Language;
import app.entity.Rental;
import app.exceptions.MyNotFoundException;
import app.repository.LanguageRepository;
import app.repository.RentalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

import static app.DTO.ErrorCode.DIFFERENT;

@Component
public class RentalFinder {
    private final RentalRepository rentalRepository;

    @Autowired
    public RentalFinder(RentalRepository rentalRepository) {
        this.rentalRepository = rentalRepository;
    }

    public List<Rental> findAllRental()
    {
        return rentalRepository.findAll();
    }

    public Rental findRentalById(int id){
        return rentalRepository.findById(id).orElseThrow(() -> new MyNotFoundException("Category with given Id not found", DIFFERENT));
    }

    public List<Rental> findAllRentalByRentalDate(LocalDateTime date){
        return rentalRepository.findAllByRentalDate(date).orElseThrow(() -> new MyNotFoundException("Category with given name not found", DIFFERENT));
    }
}
