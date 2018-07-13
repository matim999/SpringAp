package app.repository;

import app.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {
    Optional<List<Customer>> findAllByFirstName(String fname);

    Optional<List<Customer>> findAllByFirstNameAndLastName(String fname, String lname);

//    @Query("select cu.customer_id, f.film_id from customer cu " +
//            "inner join rental r on r.customer_id = cu.customer_id " +
//            "inner join inventory i on i.inventory_id = r.inventory_id " +
//            "inner join film f on i.film_id = f.film_id " +
//            "where r.return_date is null " +
//            "and  date_part('day',age(localtimestamp , r.rental_date)) > f.rental_duration")
//    List<Integer> findCustomersWhoNeedToPayRetentionFee();
}
