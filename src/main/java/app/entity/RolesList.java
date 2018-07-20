package app.entity;

import app.DTO.responseDTO.RoleDto;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collection;

@Data
@NoArgsConstructor
public class RolesList {
    private Collection<RoleDto> list;
}
