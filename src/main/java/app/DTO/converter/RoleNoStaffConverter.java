package app.DTO.converter;

import app.DTO.responseDTO.RoleeDtoNoStaff;
import app.DTO.responseDTO.StaffDtoNoRole;
import app.entity.Rolee;
import app.entity.Staff;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RoleNoStaffConverter implements BaseConverter<Rolee, RoleeDtoNoStaff> {
    private final BaseConverter<Staff, StaffDtoNoRole> staffNoRoleConverter;

    @Autowired
    public RoleNoStaffConverter(BaseConverter<Staff, StaffDtoNoRole> staffNoRoleConverter) {
        this.staffNoRoleConverter = staffNoRoleConverter;
    }

    @Override
    public RoleeDtoNoStaff convert(Rolee from) {
        RoleeDtoNoStaff roleeDtoNoStaff = new RoleeDtoNoStaff();
        roleeDtoNoStaff.setRoleeId(from.getRoleeId());
        roleeDtoNoStaff.setRolee(from.getRolee());
        return roleeDtoNoStaff;
    }
}
