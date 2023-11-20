package com.hackerrank.gevents.repository;

import com.hackerrank.gevents.model.Actor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ActorRepository extends JpaRepository<Actor, Long> {
}