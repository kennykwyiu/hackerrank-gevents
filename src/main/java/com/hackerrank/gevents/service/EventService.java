package com.hackerrank.gevents.service;

import com.hackerrank.gevents.model.Actor;
import com.hackerrank.gevents.repository.EventRepository;
import com.hackerrank.gevents.repository.RepoRepository;
import com.hackerrank.gevents.repository.ActorRepository;
import com.hackerrank.gevents.model.Event;
import com.hackerrank.gevents.model.Repo;
import org.apache.logging.log4j.util.Strings;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EventService {

    private final ActorRepository actorRepository;
    private final RepoRepository repoRepository;
    private final EventRepository eventRepository;

    public EventService(ActorRepository actorRepository,
                        RepoRepository repoRepository,
                        EventRepository eventRepository) {
        this.actorRepository = actorRepository;
        this.repoRepository = repoRepository;
        this.eventRepository = eventRepository;
    }

    public Event createEvent(Event event) {

        Actor actor = Actor.builder().build();
        actorRepository.save(actor);
        Repo repo = Repo.builder().build();
        repoRepository.save(repo);

        Event createdEvent = Event.builder()
                .repoId(event.getRepoId())
                .actorId(event.getActorId())
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
}
