package com.infomaximum.hackaton.repository;

import com.infomaximum.hackaton.model.calendarevent.CalendarEvent;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CalendarEventRepository extends CrudRepository<CalendarEvent, Long> {
    CalendarEvent findCalendarEventById(Long Id);
}
