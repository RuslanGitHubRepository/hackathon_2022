package com.infomaximum.hackaton.model.comment;

import com.infomaximum.hackaton.model.calendarevent.CalendarEvent;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class CommentDto {
    private Long id;
    private String userName;
    private LocalDateTime dateTime;
    private String message;
}
