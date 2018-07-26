package app.DTO.converter;

import app.DTO.responseDTO.AddressDto;
import app.DTO.responseDTO.StaffDtoNoRole;
import app.entity.Address;
import app.entity.Staff;
import app.repository.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class StaffNoRoleConverter implements BaseConverter<Staff, StaffDtoNoRole> {
    private final BaseConverter<Address, AddressDto> addressConverter;
    private final AddressRepository addressRepository;

    @Autowired
    public StaffNoRoleConverter(BaseConverter<Address, AddressDto> addressConverter, AddressRepository addressRepository) {
        this.addressConverter = addressConverter;
        this.addressRepository = addressRepository;
    }


    @Override
    public StaffDtoNoRole convert(Staff from) {
        StaffDtoNoRole staffDtoNoRole = new StaffDtoNoRole();
        staffDtoNoRole.setStaffId(from.getStaffId());
        staffDtoNoRole.setFirstName(from.getFirstName());
        staffDtoNoRole.setLastName(from.getLastName());
        staffDtoNoRole.setAddress(addressConverter.convertAll(from.getAddress()));
        staffDtoNoRole.setEmail(from.getEmail());
        staffDtoNoRole.setActive(from.isActive());
        staffDtoNoRole.setUsername(from.getUsername());
        staffDtoNoRole.setPassword(from.getPassword());
        staffDtoNoRole.setStoreId(from.getStore().getStore_id());
        return staffDtoNoRole;
    }
}
