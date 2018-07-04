package controller;

import app.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.security.auth.login.AppConfigurationEntry;
import javax.security.auth.login.Configuration;
import javax.websocket.Session;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/staff")
public class StaffController {

    @Autowired
    private final RepositoryStaff repo;
    private final RepositoryStore repoStore;

//    EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory();
//    EntityManager entityManager = entityManagerFactory.createEntityManager();

    public StaffController(RepositoryStaff repo, RepositoryStore repoStore, RepositoryStore repoStore1) {
        this.repo = repo;
        this.repoStore = repoStore;
    }

    @GetMapping(path="/all")
    public @ResponseBody
    List getAllUsers() {
        return repo.findAll();
    }

    @GetMapping(path = "/specific")
    public @ResponseBody
    Optional getSpecificStaff(){
        int staff_id = 1;
        Optional<Staff> staff;
        staff = repo.findById(staff_id);
        return staff;
    }
//    @PostMapping(path="/add")
//    public @ResponseBody
//    String addStore(@RequestBody Staff staff) {
//        entityManager.getTransaction().begin();
//        entityManager.persist(staff);
//        entityManager.getTransaction().commit();
//        return "done";
//    }
}
