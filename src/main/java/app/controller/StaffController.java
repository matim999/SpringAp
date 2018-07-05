package app.controller;

import app.entity.Staff;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import app.repository.StaffRepository;
import app.repository.RepositoryStore;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/staff")
public class StaffController {

    @Autowired
    private final StaffRepository repo;
    private final RepositoryStore repoStore;

    public StaffController(StaffRepository repo, RepositoryStore repoStore, RepositoryStore repoStore1) {
        this.repo = repo;
        this.repoStore = repoStore1;
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
    @PostMapping(path="/add")
    public @ResponseBody
    String addStore(@RequestBody Staff staff) {
        repo.save(staff);
        return "Done";
    }
}
