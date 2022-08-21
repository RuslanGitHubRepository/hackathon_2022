package com.infomaximum.hackaton.controller;

import com.infomaximum.hackaton.mapper.EventMapper;
import com.infomaximum.hackaton.model.event.Event;
import com.infomaximum.hackaton.model.event.EventDto;
import com.infomaximum.hackaton.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;

@Controller
@CrossOrigin
public class EventController {
    private final EventService eventService;
    private final EventMapper eventMapper;

    @Autowired
    public EventController(EventService employeeService, EventMapper eventMapper) {
        this.eventService = employeeService;
        this.eventMapper = eventMapper;
    }

    @PostMapping("/events")
    ResponseEntity<Boolean> newEvents(@RequestBody EventDto newEventDto) {
        boolean status = eventService.createEvent(eventMapper.eventDtoToEvent(newEventDto));
        return new ResponseEntity<>(
                status,
                HttpStatus.CREATED);
    }

    @GetMapping("/events/{id}")
    ResponseEntity<EventDto> getEvents(@PathVariable(name = "id") Long eventsId) {
        Event event = eventService.findEventById(eventsId);
        return new ResponseEntity<>(
                eventMapper.eventToEventDto(event),
                HttpStatus.OK);
    }

    @GetMapping("/events/all")
    ResponseEntity<ArrayList<EventDto>> getAllEvents() {
        ArrayList<Event> event = eventService.findAllEvent();
        return new ResponseEntity<>(
                eventMapper.eventListToEventDtoList(event),
                HttpStatus.OK);
    }

    @DeleteMapping("/events/{id}")
    ResponseEntity<Object> deleteEvent(@PathVariable(name = "id") Long eventId) {
        eventService.deleteEvent(eventId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
