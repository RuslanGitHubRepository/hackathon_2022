package com.infomaximum.hackaton.model.comment;

import com.infomaximum.hackaton.model.calendarevent.CalendarEvent;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(name = "comment")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "user_name", nullable = false)
    private String userName;
    @Column(name = "date_time", nullable = false)
    private LocalDateTime dateTime;
    @Column(name = "message", nullable = false)
    private String message;
    @ManyToOne(cascade = {CascadeType.REFRESH})
    @JoinColumn(name = "calendar_event_id")
    private CalendarEvent calendarEvent;
}
