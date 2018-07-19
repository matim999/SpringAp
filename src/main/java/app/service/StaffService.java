package app.service;

import app.DTO.converter.BaseConverter;
import app.DTO.converter.ToBaseConverter;
import app.DTO.requestDTO.StaffDtoRequest;
import app.DTO.responseDTO.StaffDto;
import app.entity.Staff;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StaffService {
    private final BaseConverter<Staff, StaffDto> staffConverter;
    private final ToBaseConverter<StaffDtoRequest, StaffDto> staffRequestConverter;

    @Autowired
    public StaffService(BaseConverter<Staff, StaffDto> staffConverter, ToBaseConverter<StaffDtoRequest, StaffDto> staffRequestConverter) {
        this.staffConverter = staffConverter;
        this.staffRequestConverter = staffRequestConverter;
    }

    public void signUp(StaffDtoRequest staffDtoRequest){

    }
}
