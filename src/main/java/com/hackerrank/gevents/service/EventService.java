package com.hackerrank.gevents.service;

import com.hackerrank.gevents.exception.EventNotFindException;
import com.hackerrank.gevents.model.Actor;
import com.hackerrank.gevents.repository.EventRepository;
import com.hackerrank.gevents.repository.RepoRepository;
import com.hackerrank.gevents.repository.ActorRepository;
import com.hackerrank.gevents.model.Event;
import com.hackerrank.gevents.model.Repo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventService {
    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private ActorService actorService;
    @Autowired
    private RepoService repoService;


    public Event createEvent(Event event) throws EventNotFindException {

        Integer actorId = event.getActorId();
        Actor actor = actorService.findByActorId(actorId)
                .orElseThrow(() -> new EventNotFindException(
                        String.format("Event not found by s%", actorId)
                ));
        Integer repoId = event.getRepoId();
        Repo repo = repoService.findByRepoId(repoId)
                .orElseThrow(() -> new EventNotFindException(
                        String.format("Event not found by s%", repoId)
                ));

        Event createdEvent = Event.builder()
                .repoId(repoId)
                .actorId(actorId)
                .isPublic(true)
                .type(event.getType())
                .build();
        eventRepository.save(createdEvent);
        return createdEvent;
    }

    public List<Event> findAll() {
        return eventRepository.findAll();
    }

    public List<Event> getByRepoId(Integer repoId) {
        return eventRepository.findAllByRepoId(repoId);
    }

    public Event findByEventId(Integer eventId) throws EventNotFindException {
        Event event = eventRepository.findById(eventId)
                .orElseThrow(() -> new EventNotFindException(
                        String.format("Event not found by: %s", eventId)));
        return event;
    }

    public List<Event> findByUserId(Integer userId) {
        return eventRepository.findAllByActorId(userId);
    }
}
