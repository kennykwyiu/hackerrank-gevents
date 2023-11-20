package com.hackerrank.gevents.service;

import com.hackerrank.gevents.model.Actor;
import com.hackerrank.gevents.repository.ActorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ActorService {
    @Autowired
    private ActorRepository actorRepository;

    public Optional<Actor> findByActorId(Integer actorId) {
        return Optional.of(actorRepository.getReferenceById(Long.valueOf(actorId)));
    }
}
