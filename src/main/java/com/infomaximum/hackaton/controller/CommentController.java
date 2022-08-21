package com.infomaximum.hackaton.controller;

import com.infomaximum.hackaton.mapper.CommentMapper;
import com.infomaximum.hackaton.model.calendarevent.CalendarEvent;
import com.infomaximum.hackaton.model.comment.Comment;
import com.infomaximum.hackaton.model.comment.CommentDto;
import com.infomaximum.hackaton.model.employee.Employee;
import com.infomaximum.hackaton.service.CalendarEventService;
import com.infomaximum.hackaton.service.CommentService;
import com.infomaximum.hackaton.service.EmployeeService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Set;

@Controller
@CrossOrigin
public class CommentController {
    private final CommentService commentService;
    private final CommentMapper commentMapper;
    private final CalendarEventService calendarEventService;
    private final EmployeeService employeeService;

    @Autowired
    public CommentController(CommentService commentService, CommentMapper commentMapper, CalendarEventService calendarEventService, EmployeeService employeeService) {
        this.commentService = commentService;
        this.commentMapper = commentMapper;
        this.calendarEventService = calendarEventService;
        this.employeeService = employeeService;
    }

    @PostMapping("/comment/{id}")
    ResponseEntity<Boolean> newComment(@RequestBody CommentDto commentDto, @PathVariable(name = "id") Long employeeId) {
        Employee employee = employeeService.findEmployeeById(employeeId);
        String userName = employee.getUserName() + " " + employee.getSurName();
        commentDto.setUserName(userName);
        boolean status = commentService.createComment(commentMapper.commentDtoToComment(commentDto));
        return new ResponseEntity<>(
                status,
                HttpStatus.CREATED);
    }

    @GetMapping("/comment/{id}")
    ResponseEntity<CommentDto> getCommentById(@PathVariable(name = "id") Long commentId) {
        Comment commentById = commentService.findCommentById(commentId);
        return new ResponseEntity<>(
                commentMapper.commentToCommentDto(commentById),
                HttpStatus.OK
        );
    }

    @GetMapping("/comment/calendar-events/{id}")
    ResponseEntity<Set<CommentDto>> getAllCommentsByCalendarEventsId(@PathVariable(name = "id") Long calendarEventsId) {
        CalendarEvent calendarEventById = calendarEventService.findCalendarEventById(calendarEventsId);
        if (calendarEventById == null) {
            return new ResponseEntity<>(HttpStatus.I_AM_A_TEAPOT);
        }
        return new ResponseEntity<>(
                commentMapper.commentSetToCommentDtoSet(calendarEventById.getComments()),
                HttpStatus.OK
        );
    }

    @GetMapping("/comment/all")
    ResponseEntity<ArrayList<CommentDto>> getAllComment() {
        ArrayList<Comment> allComment = commentService.findAllComment();
        return new ResponseEntity<>(
                commentMapper.commentListToCommentDtoList(allComment),
                HttpStatus.OK
        );
    }

    @PutMapping("/comment/calendar-events/")
    ResponseEntity<Object> setCalendarEventToComment(@RequestParam String calendarEventId, @RequestParam String commentId) {
        long calendarEvent_Id = Long.valueOf(StringUtils.defaultString(calendarEventId, "0"));
        long comment_Id = Long.valueOf(StringUtils.defaultString(commentId, "0"));
        if (calendarEvent_Id != 0 && comment_Id != 0) {
            commentService.updateComment(comment_Id, calendarEvent_Id);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping("/comment/{id}")
    ResponseEntity<Object> deleteComment(@PathVariable(name = "id") Long commentId) {
        commentService.deleteComment(commentId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
