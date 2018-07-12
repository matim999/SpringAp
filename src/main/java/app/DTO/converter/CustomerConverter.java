package app.DTO.converter;

import app.DTO.ErrorCode;
import app.DTO.requestDTO.CustomerDtoRequest;
import app.DTO.responseDTO.AddressDto;
import app.DTO.responseDTO.CustomerDto;
import app.DTO.responseDTO.StoreDto;
import app.entity.Address;
import app.entity.Customer;
import app.entity.Store;
import app.exceptions.MyNotFoundException;
import app.repository.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CustomerConverter implements BaseConverter<Customer, CustomerDto>, ToBaseConverter<CustomerDtoRequest, CustomerDto> {
    private final BaseConverter<Address, AddressDto> addressConverter;
    private final AddressRepository addressRepository;
    @Autowired
    public CustomerConverter(BaseConverter<Address, AddressDto> addressConverter, AddressRepository addressRepository) {
        this.addressConverter = addressConverter;
        this.addressRepository = addressRepository;
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

    @Override
    public CustomerDto convertToBase(CustomerDtoRequest from) {
        CustomerDto customerDto = new CustomerDto();
        customerDto.setStoreId(from.getStoreId());
        customerDto.setFirstName(from.getFirstName());
        customerDto.setLastName(from.getLastName());
        customerDto.setEmail(from.getEmail());
        customerDto.setAddress(addressConverter.convertAll(addressRepository.findById(from.getAddressId())
                .orElseThrow(() -> new MyNotFoundException("Address with given ID not found", ErrorCode.DIFFERENT))));
        customerDto.setActivebool(from.isActivebool());
        customerDto.setCreateDate(from.getCreateDate());
        customerDto.setActive(from.getActive());
        return customerDto;
    }
}
