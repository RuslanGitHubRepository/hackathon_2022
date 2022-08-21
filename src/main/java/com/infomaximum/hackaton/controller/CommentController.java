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
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

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
    ResponseEntity<Long> newComment(@RequestBody CommentDto commentDto, @PathVariable(name = "id") Long employeeId) {
        Employee employee = employeeService.findEmployeeById(employeeId);
        String userName = employee.getUserName() + " " + employee.getSurName();
        commentDto.setUserName(userName);
        Comment comment = commentService.createComment(commentMapper.commentDtoToComment(commentDto));
        return new ResponseEntity<>(
                comment.getId(),
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
    ResponseEntity<List<CommentDto>> getAllCommentsByCalendarEventsId(@PathVariable(name = "id") Long calendarEventsId) {
        CalendarEvent calendarEventById = calendarEventService.findCalendarEventById(calendarEventsId);
        if (calendarEventById == null) {
            return new ResponseEntity<>(HttpStatus.I_AM_A_TEAPOT);
        }
        ArrayList<Comment> commentsList = calendarEventById.getComments().stream()
                .sorted(Comparator.comparing(Comment::getDateTime))
                .collect(Collectors.toCollection(ArrayList::new));
        return new ResponseEntity<>(
                commentMapper.commentListToCommentDtoList(commentsList),
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
