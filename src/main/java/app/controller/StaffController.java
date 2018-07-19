package app.controller;

import app.DTO.converter.BaseConverter;
import app.DTO.requestDTO.StaffDtoRequest;
import app.DTO.responseDTO.StaffDto;
import app.entity.Staff;
import app.finder.StaffFinder;
import app.service.StaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/staff")
public class StaffController {
    private final StaffFinder staffFinder;
    private final StaffService staffService;
    private final BaseConverter<Staff, StaffDto> staffConverter;

    @Autowired
    public StaffController(StaffFinder staffFinder, StaffService staffService, BaseConverter<Staff, StaffDto> staffConverter) {
        this.staffFinder = staffFinder;
        this.staffService = staffService;
        this.staffConverter = staffConverter;
    }

    @GetMapping
    private @ResponseBody
    ResponseEntity<List> findAllStaff() {
        return new ResponseEntity(staffConverter.convertAll(staffFinder.findAllStaff()), HttpStatus.OK);
    }

    @GetMapping(path = "/{id}")
    public @ResponseBody
    ResponseEntity<Staff> findStaffById(@PathVariable int id) {
        return new ResponseEntity(staffConverter.convertAll(staffFinder.findStaffById(id)), HttpStatus.OK);
    }

    @GetMapping(path = "/")
    public @ResponseBody
    ResponseEntity<List> findAllStaffByFirstName(@RequestParam String fname) {
        return new ResponseEntity(staffConverter.convertAll(staffFinder.findAllStaffByFirstName(fname)), HttpStatus.OK);
    }

    @GetMapping(path = "/sign-up")
    public @ResponseBody
    ResponseEntity addNewStaffSignUp(@RequestParam StaffDtoRequest staffDtoRequest) {
        staffService.signUp(staffDtoRequest);
        return new ResponseEntity(HttpStatus.OK);
    }

}
