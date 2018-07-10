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

    private final StaffRepository repo;

    @Autowired
    public StaffController(StaffRepository repo) {
        this.repo = repo;
    }

    @GetMapping(path="/all")
    public @ResponseBody
    List getAllUsers() {
        return repo.findAll();
    }

    @GetMapping(path = "/specific")
    public @ResponseBody
    Optional getSpecificStaff(){
        int staff_id = 0;
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
