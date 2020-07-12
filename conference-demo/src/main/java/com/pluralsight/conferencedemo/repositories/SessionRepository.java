package com.pluralsight.conferencedemo.repositories;

import com.pluralsight.conferencedemo.models.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

//The annotation tells Spring that this acts as a data repository
//Without the annotation, Spring will treat this as a normal class
@Repository
public class SessionRepository {
    //Proxy pattern so the client code stays the same
    //Change the repository to use Spring data JPA
    @Autowired
    private SessionJpaRepository jpaRepository;

    @PersistenceContext
    private EntityManager entityManager;

    public Session create(Session session) {
        return jpaRepository.saveAndFlush(session);
    }

    public Session update(Session session) {
        return jpaRepository.saveAndFlush(session);
    }

    public void delete(Long id) {
        jpaRepository.deleteById(id);
    }

    public Session find(Long id) {
        return jpaRepository.getOne(id);
    }

    public List<Session> list() {
        return jpaRepository.findAll();
    }

    public List<Session> getSessionsThatHaveName(String name) {
        return jpaRepository.findBySessionNameContaining(name);
    }
}
