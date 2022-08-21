package com.infomaximum.hackaton.mapper;

import com.infomaximum.hackaton.model.event.EventType;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface EventTypeMapper {
    EventType stringToEventType(String event);
}
