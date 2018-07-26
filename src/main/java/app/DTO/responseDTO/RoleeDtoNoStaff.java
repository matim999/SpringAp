package app.DTO.responseDTO;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
public class RoleeDtoNoStaff {
    @EqualsAndHashCode.Exclude
    private int roleeId;
    private String rolee;
}
