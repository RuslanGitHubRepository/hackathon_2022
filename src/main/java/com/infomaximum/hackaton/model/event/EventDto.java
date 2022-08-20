package com.infomaximum.hackaton.model.event;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class EventDto implements Serializable {
    private String name;
    private EventType type;
}
