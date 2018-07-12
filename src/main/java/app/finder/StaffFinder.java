package app.finder;

import app.entity.Staff;
import app.exceptions.MyNotFoundException;
import app.repository.StaffRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

import static app.ErrorCode.DIFFERENT;

@Component
public class StaffFinder {
    private final StaffRepository staffRepository;

    @Autowired
    public StaffFinder(StaffRepository staffRepository) {
        this.staffRepository = staffRepository;
    }

    public List<Staff> findAllStaff()
    {
        return staffRepository.findAll();
    }

    public Staff findStaffById(int id){
        return staffRepository.findById(id).orElseThrow(() -> new MyNotFoundException("Category with given Id not found", DIFFERENT));
    }

    public List<Staff> findAllStaffByFirstName(String fname){
        return staffRepository.findAllByFirstName(fname).orElseThrow(() -> new MyNotFoundException("Category with given name not found", DIFFERENT));
    }
}
