package com.infomaximum.hackaton.controller;

import com.infomaximum.hackaton.mapper.CalendarEventMapper;
import com.infomaximum.hackaton.mapper.EventMapper;
import com.infomaximum.hackaton.model.calendarevent.CalendarEvent;
import com.infomaximum.hackaton.model.calendarevent.CalendarEventDto;
import com.infomaximum.hackaton.model.event.Event;
import com.infomaximum.hackaton.model.event.EventDto;
import com.infomaximum.hackaton.service.CalendarEventService;
import com.infomaximum.hackaton.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@Controller
@CrossOrigin
public class CalendarEventController {
    private final CalendarEventService calendarEventService;
    private final CalendarEventMapper calendarEventMapper;

    @Autowired
    public CalendarEventController(CalendarEventService calendarEventService, CalendarEventMapper calendarEventMapper) {
        this.calendarEventService = calendarEventService;
        this.calendarEventMapper = calendarEventMapper;
    }

    @PostMapping("/calendar-events")
    ResponseEntity<Boolean> newCalendarEvents(@RequestBody CalendarEventDto newCalendarEventDto) {
        boolean status = calendarEventService.createCalendarEvent(calendarEventMapper.calendarEventDtoToCalendarEvent(newCalendarEventDto));
        return new ResponseEntity<>(
                status,
                HttpStatus.CREATED);
    }

    @GetMapping("/calendar-events/{id}")
    ResponseEntity<CalendarEventDto> getCalendarEventsById(@PathVariable(name = "id") Long calendarEventsId) {
        CalendarEvent calendarEvent = calendarEventService.findCalendarEventById(calendarEventsId);
        return new ResponseEntity<>(
                calendarEventMapper.calendarEventToCalendarEventDto(calendarEvent),
                HttpStatus.OK);
    }

    @GetMapping("/calendar-events/all")
    ResponseEntity<ArrayList<CalendarEventDto>> getAllEvents() {
        ArrayList<CalendarEvent> event = calendarEventService.findAllCalendarEvent();
        return new ResponseEntity<>(
                calendarEventMapper.eventListToEventDtoList(event),
                HttpStatus.OK);
    }

    @DeleteMapping("/calendar-events/{id}")
    ResponseEntity<Object> deleteCalendarEvent(@PathVariable(name = "id") Long calendarEventId) {
        calendarEventService.deleteCalendarEvent(calendarEventId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/calendar-events/")
    ResponseEntity<Object> addEmployeeToCalendarEvent(@RequestParam(name = "ce_id") Long calendarEventId, @RequestParam(name = "emp_id") Long employeeId) {
        boolean status = calendarEventService.addEmployeeToCalendarEvent(calendarEventId, employeeId);
        return new ResponseEntity<>(status, HttpStatus.OK);
    }
}
