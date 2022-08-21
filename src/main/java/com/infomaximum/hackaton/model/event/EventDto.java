package com.infomaximum.hackaton.model.event;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@EqualsAndHashCode
public class EventDto implements Serializable {
    private String name;
    private EventType type;
}
