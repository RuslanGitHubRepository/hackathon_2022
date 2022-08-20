package com.infomaximum.hackaton.mapper;

import com.infomaximum.hackaton.model.event.Event;
import com.infomaximum.hackaton.model.event.EventDto;
import org.mapstruct.Mapper;

import java.util.ArrayList;
import java.util.List;

@Mapper(componentModel = "spring", uses = EventTypeMapper.class)
public interface EventMapper {
    Event eventDtoToEvent(EventDto eventDto);
    EventDto eventToEventDto(Event event);
    ArrayList<EventDto> eventListToEventDtoList(List<Event> event);
}
