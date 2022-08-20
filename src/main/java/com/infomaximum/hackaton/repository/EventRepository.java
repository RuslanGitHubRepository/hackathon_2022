package com.infomaximum.hackaton.repository;

import com.infomaximum.hackaton.model.event.Event;
import com.infomaximum.hackaton.model.event.EventType;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventRepository extends CrudRepository<Event, Long> {
    Event findEventById(Long id);
    Event findEventByTypeAndId(EventType type, Long id);
    Event findEventByType(EventType type);
}
