package app.DTO;

import app.entity.Address;
import app.entity.Staff;
import app.entity.Store;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class StaffConverter implements BaseConverter<Staff, StaffDto> {
    private final BaseConverter<Address, AddressDto> addressConverter;

    @Autowired
    public StaffConverter(BaseConverter<Address, AddressDto> addressConverter) {
        this.addressConverter = addressConverter;
    }


    @Override
    public StaffDto convert(Staff from) {
        StaffDto staffDto = new StaffDto();
        staffDto.setStaffId(from.getStaffId());
        staffDto.setFirstName(from.getFirstName());
        staffDto.setLastName(from.getLastName());
        staffDto.setAddress(addressConverter.convertAll(from.getAddress()));
        staffDto.setEmail(from.getEmail());
        staffDto.setActive(from.isActive());
        staffDto.setUsername(from.getUsername());
        staffDto.setPassword(from.getPassword());
        staffDto.setStoreId(from.getStore().getStore_id());
        return staffDto;
    }
}
