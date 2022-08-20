package com.infomaximum.hackaton.mapper;

import com.infomaximum.hackaton.model.calendarevent.CalendarEvent;
import com.infomaximum.hackaton.model.calendarevent.CalendarEventDto;
import com.infomaximum.hackaton.model.role.Role;
import com.infomaximum.hackaton.model.role.RoleDto;
import org.mapstruct.Mapper;

import java.util.ArrayList;
import java.util.List;

@Mapper(componentModel = "spring")
public interface CalendarEventMapper {
    CalendarEvent calendarEventDtoToCalendarEvent(CalendarEventDto calendarEventDto);
    CalendarEventDto calendarEventToCalendarEventDto(CalendarEvent calendarEvent);
    ArrayList<CalendarEventDto> eventListToEventDtoList(List<CalendarEvent> event);
}
