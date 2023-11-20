package com.hackerrank.gevents.repository;

import com.hackerrank.gevents.model.Repo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RepoRepository extends JpaRepository<Repo, Long> {
}