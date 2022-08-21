package com.infomaximum.hackaton.service;

import com.infomaximum.hackaton.model.calendarevent.CalendarEvent;
import com.infomaximum.hackaton.model.employee.Employee;
import com.infomaximum.hackaton.repository.CalendarEventRepository;
import com.infomaximum.hackaton.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class CalendarEventService {
    private final CalendarEventRepository calendarEventRepository;
    private final EmployeeRepository employeeRepository;

    @Autowired
    public CalendarEventService(CalendarEventRepository calendarEventRepository, EmployeeRepository employeeRepository) {
        this.calendarEventRepository = calendarEventRepository;
        this.employeeRepository = employeeRepository;
    }

    public CalendarEvent findCalendarEventById(Long id) {
        return calendarEventRepository.findCalendarEventById(id);
    }

    public ArrayList<CalendarEvent> findAllCalendarEvent() {
        Iterable<CalendarEvent> all = calendarEventRepository.findAll();
        return StreamSupport.stream(all.spliterator(), false)
                .collect(Collectors.toCollection(ArrayList::new));
    }

    public boolean createCalendarEvent(CalendarEvent calendarEvent) {
        CalendarEvent save = calendarEventRepository.save(calendarEvent);
        return Objects.equals(save.getId(), calendarEvent.getId());
    }

    @Transactional
    public boolean addEmployeeToCalendarEvent(Long calendarEventId, Long employeeId) {
        Optional<Employee> employeeOpt = employeeRepository.findById(employeeId);
        Optional<CalendarEvent> calendarEventOpt = calendarEventRepository.findById(calendarEventId);
        if(!employeeOpt.isPresent() || !calendarEventOpt.isPresent()) {
            return Boolean.FALSE;
        }
        CalendarEvent calendarEvent = calendarEventOpt.get();
        Employee employee = employeeOpt.get();
        Set<Employee> employees = calendarEvent.getEmployees();
        employees.add(employee);
        calendarEvent.setEmployees(employees);
        calendarEventRepository.save(calendarEvent);
        return Boolean.TRUE;
    }

    @Transactional
    public boolean removeEmployeeToCalendarEvent(Long calendarEventId, Long employeeId) {
        Optional<Employee> employeeOpt = employeeRepository.findById(employeeId);
        Optional<CalendarEvent> calendarEventOpt = calendarEventRepository.findById(calendarEventId);
        if(!employeeOpt.isPresent() || !calendarEventOpt.isPresent()) {
            return Boolean.FALSE;
        }
        CalendarEvent calendarEvent = calendarEventOpt.get();
        Employee employee = employeeOpt.get();
        Set<Employee> employees = calendarEvent.getEmployees();
        employees.remove(employee);
        calendarEvent.setEmployees(employees);
        calendarEventRepository.save(calendarEvent);
        return Boolean.TRUE;
    }

    public void deleteCalendarEvent(Long calendarEventId){
        calendarEventRepository.deleteById(calendarEventId);
    }
}
