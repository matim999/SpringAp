package app.DTO.responseDTO;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Collection;

@Data
public class RoleeDto {
    @EqualsAndHashCode.Exclude
    private int roleeId;
    private String rolee;
    private Collection<StaffDtoNoRole> staff;
}
