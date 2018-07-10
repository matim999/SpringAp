package app.controller;

import app.entity.Staff;
import app.finder.StaffFinder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/staff")
public class StaffController {
    private final StaffFinder staffFinder;

    @Autowired
    public StaffController(StaffFinder staffFinder) {
        this.staffFinder = staffFinder;
    }

    @GetMapping
    private @ResponseBody
    ResponseEntity<List> findAllStaff()
    {
        return new ResponseEntity<>(staffFinder.findAllStaff(), HttpStatus.OK);
    }

    @GetMapping(path = "/{id}")
    public @ResponseBody
    ResponseEntity<Staff> findStaffById(@PathVariable int id) {
        return new ResponseEntity<>(staffFinder.findStaffById(id), HttpStatus.OK);
    }

    @GetMapping(path = "/")
    public @ResponseBody
    ResponseEntity<List> findAllStaffByFirstName(@RequestParam String fname) {
        return new ResponseEntity<>(staffFinder.findAllStaffByFirstName(fname), HttpStatus.OK);
    }
}
