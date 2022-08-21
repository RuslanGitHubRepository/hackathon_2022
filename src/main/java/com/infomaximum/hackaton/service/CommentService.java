package com.infomaximum.hackaton.service;

import com.infomaximum.hackaton.model.calendarevent.CalendarEvent;
import com.infomaximum.hackaton.model.comment.Comment;
import com.infomaximum.hackaton.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class CommentService {

    private final CommentRepository commentRepository;
    private final CalendarEventService calendarEventService;

    @Autowired
    public CommentService(CommentRepository commentRepository, CalendarEventService calendarEventService) {
        this.commentRepository = commentRepository;
        this.calendarEventService = calendarEventService;
    }


    public Comment findCommentById(Long id) {
        return commentRepository.findCommentById(id);
    }

    public ArrayList<Comment> findAllComment() {
        Iterable<Comment> all = commentRepository.findAll();
        return StreamSupport.stream(all.spliterator(), false)
                .collect(Collectors.toCollection(ArrayList::new));
    }

    public Comment createComment(Comment comment) {
        Comment save = commentRepository.save(comment);
        if (Objects.equals(save.getId(), comment.getId())) {
            return save;
        }
        return null;
    }

    @Transactional
    public void updateComment(Long commentId, Long calendarEventId) {
        Comment comment = findCommentById(commentId);
        if (Objects.nonNull(comment)) {
            CalendarEvent calendarEvent = calendarEventService.findCalendarEventById(calendarEventId);
            if (Objects.nonNull(calendarEvent)) {
                commentRepository.updateCommentCalendarEvent(calendarEvent, comment.getId());
            }
        }
    }

    public void deleteComment(Long commentId) {
        commentRepository.deleteById(commentId);
    }
}
