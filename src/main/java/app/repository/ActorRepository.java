package app.repository;

import app.entity.Actor;
import app.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ActorRepository extends JpaRepository<Actor, Integer> {
    @Query(value = "select * from Actor where first_name like ?1", nativeQuery = true)
    List<Actor> findByQuery(String fname);
    Optional<List<Actor>> findAllByFirstName(String firstName);
}
