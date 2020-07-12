package com.pluralsight.conferencedemo.models;

import com.pluralsight.conferencedemo.repositories.SessionJpaRepository;
import com.pluralsight.conferencedemo.repositories.SessionRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class SessionTest {
    @Autowired
    private SessionJpaRepository repository;

    @Test
    public void test() throws Exception {
        List<Session> sessions = repository.findBySessionNameContaining("Java");
        assertTrue(sessions.size() > 0);
    }

    @Test
    public void testJpaNot() {
        List<Session> sessionsNot30MinutesInLength = repository.findBySessionLengthNot(30);
        assertTrue(sessionsNot30MinutesInLength.size()>0);
    }

    @Test
    public void testJpaNotLike() {
        List<Session> sessionsUnlikeHello = repository.findBySessionNameNotLike("Hello");
        assertTrue(sessionsUnlikeHello.size()>0);
    }
}
