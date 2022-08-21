package com.infomaximum.hackaton.model.calendarevent;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class CalendarEventDto {
    private Long id;
    private String name;
    private String description;
    private LocalDate date;
    private Short minNumber;
}
