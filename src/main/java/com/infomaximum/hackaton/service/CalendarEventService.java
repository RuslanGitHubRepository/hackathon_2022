package com.infomaximum.hackaton.service;

import com.infomaximum.hackaton.model.calendarevent.CalendarEvent;
import com.infomaximum.hackaton.model.event.Event;
import com.infomaximum.hackaton.model.event.EventType;
import com.infomaximum.hackaton.repository.CalendarEventRepository;
import com.infomaximum.hackaton.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class CalendarEventService {
    private final CalendarEventRepository calendarEventRepository;

    @Autowired
    public CalendarEventService(CalendarEventRepository calendarEventRepository) {
        this.calendarEventRepository = calendarEventRepository;
    }

    public CalendarEvent findCalendarEventById(Long id) {
        return calendarEventRepository.findCalendarEventById(id);
    }

    public ArrayList<CalendarEvent> findAllCalendarEvent() {
        Iterable<CalendarEvent> all = calendarEventRepository.findAll();
        return StreamSupport.stream(all.spliterator(), false)
                .collect(Collectors.toCollection(ArrayList::new));
    }
    public boolean createCalendarEvent(CalendarEvent calendarEvent) {
        CalendarEvent save = calendarEventRepository.save(calendarEvent);
        return Objects.equals(save.getId(), calendarEvent.getId());
    }
    public void deleteCalendarEvent(Long calendarEventId){
        calendarEventRepository.deleteById(calendarEventId);
    }
}
