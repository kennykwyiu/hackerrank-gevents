package com.hackerrank.gevents.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class EventRequestDTO {
    private String type;
    private Boolean isPublic;
    private Integer repoId;
    private Integer actorId;
}
