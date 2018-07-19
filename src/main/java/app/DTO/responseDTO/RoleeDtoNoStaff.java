package app.DTO.responseDTO;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
public class RoleeDtoNoStaff {
    @EqualsAndHashCode.Exclude
    private int roleeId;
    private String rolee;
}
