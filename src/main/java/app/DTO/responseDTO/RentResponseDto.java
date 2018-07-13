package app.DTO.responseDTO;

import app.exceptions.MyNotFoundException;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
public class RentResponseDto {
    private RentalDto rentalDto;
    private PaymentDto paymentDto;
    private String message;

    public RentResponseDto(String message) {
        this.message = message;
    }

    public RentResponseDto(RentalDto rentalDto, PaymentDto paymentDto) {
        this.rentalDto = rentalDto;
        this.paymentDto = paymentDto;
    }
}
