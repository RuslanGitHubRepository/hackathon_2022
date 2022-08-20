package com.infomaximum.hackaton.service;

import com.infomaximum.hackaton.model.employee.Employee;
import com.infomaximum.hackaton.model.event.Event;
import com.infomaximum.hackaton.model.event.EventType;
import com.infomaximum.hackaton.repository.EmployeeRepository;
import com.infomaximum.hackaton.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class EventService {
    private final EventRepository eventRepository;

    @Autowired
    public EventService(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    public Event findEventById(Long id) {
        return eventRepository.findEventById(id);
    }
    public Event findEventByType(EventType type) {
        return eventRepository.findEventByType(type);
    }
    public Event findEventByTypeAndId(EventType type, Long id) {
        return eventRepository.findEventByTypeAndId(type, id);
    }
    public ArrayList<Event> findAllEvent() {
        Iterable<Event> all = eventRepository.findAll();
        return StreamSupport.stream(all.spliterator(), false)
                .collect(Collectors.toCollection(ArrayList::new));
    }
    public boolean createEvent(Event event) {
        Event save = eventRepository.save(event);
        return Objects.equals(save.getId(), event.getId());
    }
    public void deleteEvent(Long eventId){
        eventRepository.deleteById(eventId);
    }
}
