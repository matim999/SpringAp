package app.repository;

import app.entity.Store;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepositoryStore extends JpaRepository<Store, Integer> {
}
