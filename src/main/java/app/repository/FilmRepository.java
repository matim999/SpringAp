package app.repository;

import app.entity.Film;
import app.entity.Language;
import app.entity.Mpaa_rating;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FilmRepository extends JpaRepository<Film, Integer> {
    Optional<List> findAllByTitle(String title);
    Film findByRating(Mpaa_rating rating);
}
