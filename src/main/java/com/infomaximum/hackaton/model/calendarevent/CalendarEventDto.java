package com.infomaximum.hackaton.model.calendarevent;

import javax.persistence.Column;
import java.time.LocalDate;

public class CalendarEventDto {
    private Long id;
    private String name;
    private String description;
    private LocalDate date;
    private Short minNumber;
}
