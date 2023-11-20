package com.hackerrank.gevents.service;

import com.hackerrank.gevents.model.Repo;
import com.hackerrank.gevents.repository.RepoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RepoService {
    @Autowired
    private RepoRepository repoRepository;


    public Optional<Repo> findByRepoId(Integer repoId) {
        return Optional.of(repoRepository.getReferenceById(Long.valueOf(repoId)));
    }
}
