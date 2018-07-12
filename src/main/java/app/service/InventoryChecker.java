package app.service;

import app.entity.Inventory;

import java.util.Optional;

public interface InventoryChecker {
    Optional<Inventory> checkAvaibility(int filmId);
}
