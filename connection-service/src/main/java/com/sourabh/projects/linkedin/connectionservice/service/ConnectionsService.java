package com.sourabh.projects.linkedin.connectionservice.service;

import com.sourabh.projects.linkedin.connectionservice.entity.Person;
import com.sourabh.projects.linkedin.connectionservice.repository.PersonRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
@Slf4j
public class ConnectionsService {

    private final PersonRepository personRepository;

    public List<Person> getFirstDegreeConnections(Long userId) {
        log.info("Getting connections for user {}", userId);

        return personRepository.getFirstDegreeConnections(userId);
    }
}
