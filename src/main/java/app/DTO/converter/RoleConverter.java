package app.DTO.converter;

import app.DTO.responseDTO.RoleeDto;
import app.DTO.responseDTO.StaffDtoNoRole;
import app.entity.Rolee;
import app.entity.Staff;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RoleConverter implements BaseConverter<Rolee, RoleeDto> {
    private final BaseConverter<Staff, StaffDtoNoRole> staffConverter;

    @Autowired
    public RoleConverter(BaseConverter<Staff, StaffDtoNoRole> staffConverter) {
        this.staffConverter = staffConverter;
    }


    @Override
    public RoleeDto convert(Rolee from) {
        RoleeDto roleeDto = new RoleeDto();
        roleeDto.setRoleeId(from.getRoleeId());
        roleeDto.setRolee(from.getRolee());
        roleeDto.setStaff(staffConverter.convertAll(from.getStaff()));
        return roleeDto;
    }
}
