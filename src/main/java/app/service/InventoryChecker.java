package app.service;

import app.entity.Customer;
import app.entity.Inventory;
import app.entity.Rental;

import java.util.Optional;

public interface InventoryChecker {
    Optional<Inventory> checkAvailability(int filmId, int storeId);

    boolean checkIfAlreadyRentedByThisCustomer(Customer customer, int FilmId);

    Rental FindRentalsForCustomerByFilmId(int CustomerId, int filmId);

}
