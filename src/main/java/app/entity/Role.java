package app.entity;

import lombok.Data;
import lombok.Getter;

import javax.persistence.*;
import java.util.List;

@Data
public class Role {
    private int roleId;
    private String role;
}
