package app.repository;

import app.entity.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface InventoryRepository extends JpaRepository<Inventory, Integer> {
    Optional<List<Inventory>> findAllByFilmTitle(String filmTitle);

    Optional<List<Inventory>> findAllByFilmFilmIdAndStoreId(int filmId, int storeId);

    Optional<List<Inventory>> findAllByStoreIdAndFilmFilmId(int filmId, int inventoryId);
}
