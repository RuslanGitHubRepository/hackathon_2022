package com.infomaximum.hackaton.repository;

import com.infomaximum.hackaton.model.calendarevent.CalendarEvent;
import com.infomaximum.hackaton.model.comment.Comment;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository extends CrudRepository<Comment, Long> {
    Comment findCommentById(Long id);

    @Modifying
    @Query(value = "UPDATE comment SET calendar_event_id = ?1 WHERE id = ?2", nativeQuery = true)
    void updateCommentCalendarEvent(CalendarEvent calendarEvent, Long commentId);
}
