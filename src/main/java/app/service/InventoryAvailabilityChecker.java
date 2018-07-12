package app.service;

import app.ErrorCode;
import app.entity.Film;
import app.entity.Inventory;
import app.entity.Rental;
import app.exceptions.MyNotFoundException;
import app.repository.FilmRepository;
import app.repository.InventoryRepository;
import app.repository.RentalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;
import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;

import static app.ErrorCode.*;

@Service
public class InventoryAvailabilityChecker implements InventoryChecker {
    private final InventoryRepository inventoryRepository;
    private final RentalRepository rentalRepository;
    private final FilmRepository filmRepository;

    @Autowired
    public InventoryAvailabilityChecker(InventoryRepository inventoryRepository, RentalRepository rentalRepository, FilmRepository filmRepository) {
        this.inventoryRepository = inventoryRepository;
        this.rentalRepository = rentalRepository;
        this.filmRepository = filmRepository;
    }

    @Override
    public Optional<Inventory> checkAvailability(int filmId) {
        Film film = filmRepository.findById(filmId)
                .orElseThrow(() ->
                                new MyNotFoundException(MessageFormat.format("Film with given ID = {0} doesn't exist. Check Film's ID again.", filmId),
                                        FILM_RENT_FILM_WITH_GIVEN_ID_NOT_FOUND));
        Collection<Inventory> inventories = inventoryRepository.findAllByFilmFilmId(filmId)
                .orElseThrow(() ->
                                new MyNotFoundException(MessageFormat.format("Currently we don't have any \"{0}\" DVD's in our offer.", film.getTitle()),
                                        FILM_RENT_INVENTORY_FOR_FILM_NOT_FOUND));
        Collection<Rental> rentals = rentalRepository.findAllByInventoryFilmFilmIdAndReturnDateIsNullOrderByRentalDateAsc(filmId)
                .orElse(null);
        return findAvailableInventory(inventories, rentals, film);
    }

    private Optional<Inventory> findAvailableInventory(Collection<Inventory> inventories, Collection<Rental> rentals, Film film) {
        for (Inventory inventory : inventories){
            if (
            rentals.stream()
                    .noneMatch(rental -> rental.getInventory().getInventoryId() == inventory.getInventoryId())
                    )
                return Optional.of(inventory);
        }
        checkForTheNearestDate(rentals, film);
        return null;
    }

    private void checkForTheNearestDate(Collection<Rental> rentals, Film film) {
        //rentals.stream().limit(3).collect(Collectors.toList());
        System.out.println(rentals.size());
        int rentalDuration = film.getRentalDuration();
        StringBuilder stringBuilder = new StringBuilder("Closest expected return dates for film \"" + film.getTitle() + "\":\n" + System.lineSeparator());
        stringBuilder.append("\n");
        stringBuilder.append(System.lineSeparator());
        rentals.stream()
                .forEach(rental -> stringBuilder.append(rental.getRentalDate().plusDays(rentalDuration).toString() + System.lineSeparator()));
        throw new MyNotFoundException(stringBuilder.toString(), FILM_RENT_NO_AVAIABLE_DVDS);
    }
}
