package app.DTO.converter;

import app.DTO.responseDTO.AddressDto;
import app.DTO.responseDTO.StaffDto;
import app.DTO.responseDTO.StoreDto;
import app.entity.Address;
import app.entity.Staff;
import app.entity.Store;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class StoreConverter implements BaseConverter<Store, StoreDto> {
    private final BaseConverter<Staff, StaffDto> staffConverter;
    private final BaseConverter<Address, AddressDto> addressConverter;

    @Autowired
    public StoreConverter(BaseConverter<Staff, StaffDto> staffConverter, BaseConverter<Address, AddressDto> addressConverter) {
        this.staffConverter = staffConverter;
        this.addressConverter = addressConverter;
    }


    @Override
    public StoreDto convert(Store from) {
        StoreDto storeDto = new StoreDto();
        storeDto.setStore_id(from.getStore_id());
        storeDto.setStaff(staffConverter.convertAll(from.getStaff()));
        storeDto.setAddress(addressConverter.convertAll(from.getAddress()));
        return storeDto;
    }
}
