package com.infomaximum.hackaton.repository;

import com.infomaximum.hackaton.model.calendarevent.CalendarEvent;
import com.infomaximum.hackaton.model.event.Event;
import com.infomaximum.hackaton.model.event.EventType;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Calendar;

@Repository
public interface CalendarEventRepository extends CrudRepository<CalendarEvent, Long> {
    CalendarEvent findCalendarEventById(Long Id);
}
