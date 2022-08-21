package com.infomaximum.hackaton.model.role;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@EqualsAndHashCode
public class RoleDto implements Serializable {
    private RoleEnum type;
}
