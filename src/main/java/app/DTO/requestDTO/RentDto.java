package app.DTO.requestDTO;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RentDto {
    private int customerId;
    private int filmID;
}
