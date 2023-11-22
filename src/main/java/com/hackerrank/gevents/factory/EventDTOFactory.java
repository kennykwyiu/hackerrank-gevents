package com.hackerrank.gevents.factory;

import com.hackerrank.gevents.dto.EventRequestDTO;
import com.hackerrank.gevents.dto.EventResponseDTO;
import com.hackerrank.gevents.model.Event;
import org.springframework.stereotype.Component;

@Component
public class EventDTOFactory {
    public EventResponseDTO toDTO(Event event) {
        return EventResponseDTO.builder()
                .id(event.getId())
                .type(event.getType())
                .isPublic(event.getIsPublic())
                .repoId(event.getRepoId())
                .actorId(event.getActorId())
                .build();
    }

    public Event toEntity(EventRequestDTO requestDTO) {
        return Event.builder()
                .type(requestDTO.getType())
                .isPublic(requestDTO.getIsPublic())
                .repoId(requestDTO.getRepoId())
                .actorId(requestDTO.getActorId())
                .build();
    }

}
