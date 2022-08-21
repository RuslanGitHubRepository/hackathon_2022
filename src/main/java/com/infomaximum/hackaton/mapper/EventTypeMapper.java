package com.infomaximum.hackaton.mapper;

import com.infomaximum.hackaton.model.event.EventType;
import org.mapstruct.EnumMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = "spring")
public interface EventTypeMapper {
    EventType stringToEventType(String event);
}
