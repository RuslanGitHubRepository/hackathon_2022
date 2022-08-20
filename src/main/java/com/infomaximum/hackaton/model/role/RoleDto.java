package com.infomaximum.hackaton.model.role;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class RoleDto implements Serializable {
    private RoleEnum role;
}
