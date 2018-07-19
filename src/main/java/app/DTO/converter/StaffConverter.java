package app.DTO.converter;

import app.DTO.requestDTO.StaffDtoRequest;
import app.DTO.responseDTO.AddressDto;
import app.DTO.responseDTO.RoleeDtoNoStaff;
import app.DTO.responseDTO.StaffDto;
import app.ErrorCode;
import app.entity.Address;
import app.entity.Rolee;
import app.entity.Staff;
import app.exceptions.MyNotFoundException;
import app.repository.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class StaffConverter implements BaseConverter<Staff, StaffDto>, ToBaseConverter<StaffDtoRequest, StaffDto> {
    private final BaseConverter<Address, AddressDto> addressConverter;
    private final AddressRepository addressRepository;
    private final BaseConverter<Rolee, RoleeDtoNoStaff> roleNoStaffConverter;

    @Autowired
    public StaffConverter(BaseConverter<Address, AddressDto> addressConverter, AddressRepository addressRepository, BaseConverter<Rolee, RoleeDtoNoStaff> roleNoStaffConverter) {
        this.addressConverter = addressConverter;
        this.addressRepository = addressRepository;
        this.roleNoStaffConverter = roleNoStaffConverter;
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
        staffDto.setRolee(roleNoStaffConverter.convertAll(from.getRolees()));
        return staffDto;
    }

    @Override
    public StaffDto convertToBase(StaffDtoRequest from) {
        StaffDto staffDto = new StaffDto();
        staffDto.setFirstName(from.getFirstName());
        staffDto.setLastName(from.getLastName());
        staffDto.setAddress(addressConverter.convertAll(addressRepository.findById(from.getAddressId())
        .orElseThrow(() -> new MyNotFoundException("Address with given ID does no exist", ErrorCode.DIFFERENT))));
        staffDto.setEmail(from.getEmail());
        staffDto.setActive(from.isActive());
        staffDto.setUsername(from.getUsername());
        staffDto.setPassword(from.getPassword());
        staffDto.setStoreId(from.getStoreId());
        return staffDto;
    }
}
