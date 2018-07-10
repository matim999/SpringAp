package app.DTO;

import app.entity.Address;
import app.entity.Customer;
import app.entity.Store;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CustomerConverter implements BaseConverter<Customer, CustomerDto> {
    private final BaseConverter<Address, AddressDto> addressConverter;
    private final BaseConverter<Store, StoreDto> storeConverter;

    @Autowired
    public CustomerConverter(BaseConverter<Address, AddressDto> addressConverter, BaseConverter<Store, StoreDto> storeConverter) {
        this.addressConverter = addressConverter;
        this.storeConverter = storeConverter;
    }

    @Override
    public CustomerDto convert(Customer from) {
        CustomerDto customerDto = new CustomerDto();
        customerDto.setCustomerId(from.getCustomerId());
        customerDto.setStoreId(from.getStoreId());
        customerDto.setFirstName(from.getFirstName());
        customerDto.setLastName(from.getLastName());
        customerDto.setEmail(from.getEmail());
        customerDto.setAddress(addressConverter.convertAll(from.getAddress()));
        customerDto.setActivebool(from.isActivebool());
        customerDto.setCreateDate(from.getCreateDate());
        customerDto.setActive(from.getActive());
        return customerDto;
    }
}
