package com.hackerrank.gevents.controller;

import com.hackerrank.gevents.dto.EventResponseDTO;
import com.hackerrank.gevents.exception.InvalidEventTypeException;
import com.hackerrank.gevents.factory.EventDTOFactory;
import com.hackerrank.gevents.model.Event;
import com.hackerrank.gevents.exception.EventNotFindException;
import com.hackerrank.gevents.service.EventService;
import com.hackerrank.gevents.utils.ServiceUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/events")
public class EventController {
    @Autowired
    private EventService eventService;

    @Autowired
    private EventDTOFactory eventDTOFactory;

    @PostMapping
    public ResponseEntity<EventResponseDTO> createEvent(@RequestBody Event event) throws EventNotFindException, InvalidEventTypeException {
        String eventType = event.getType();
        ServiceUtils.validateEventType(eventType);

        Event createdEvent = eventService.createEvent(event);
        EventResponseDTO responseDTO = eventDTOFactory.toDTO(createdEvent);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);

    }

    private boolean isValidaEventType(String eventType) {
        return eventType.equals("PushEvent") || eventType.equals("ReleaseEvent") || eventType.equals("WatchEvent");
    }

    @GetMapping
    public ResponseEntity<List<EventResponseDTO>> getAllEvents() {
        List<EventResponseDTO> responseDTOList = eventService.findAll().stream()
                .map(eventDTOFactory::toDTO)
                .toList();
        return ResponseEntity.status(HttpStatus.OK).body(responseDTOList);
    }

    @GetMapping("/repos/{repoId}/events")
    public ResponseEntity<List<EventResponseDTO>> getRepoEvents(@PathVariable Integer repoId) {
        List<EventResponseDTO> eventResponseDTOList = eventService.getByRepoId(repoId).stream()
                .map(eventDTOFactory::toDTO)
                .toList();
        return ResponseEntity.status(HttpStatus.OK).body(eventResponseDTOList);
    }


    @GetMapping("/repos/{userId}/events")
    public ResponseEntity<EventResponseDTO> getUserEvents(@PathVariable Integer userId) {
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{eventId}")
    public ResponseEntity<EventResponseDTO> getEventById(@PathVariable Integer eventId) throws EventNotFindException {
        Event event = eventService.findByEventId(eventId);
        EventResponseDTO eventResponseDTO = eventDTOFactory.toDTO(event);
        return ResponseEntity.ok(eventResponseDTO);

    }
}
