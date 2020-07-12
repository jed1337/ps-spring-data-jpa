package com.pluralsight.conferencedemo.models;

import com.pluralsight.conferencedemo.repositories.SessionJpaRepository;
import com.pluralsight.conferencedemo.repositories.SessionRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

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
        assertTrue(sessionsNot30MinutesInLength.size() > 0);
    }

    @Test
    public void testJpaNotLike() {
        List<Session> sessionsUnlikeHello = repository.findBySessionNameNotLike("Hello");
        assertTrue(sessionsUnlikeHello.size() > 0);
    }

    @Test
    public void testNativelyFindByName() {
        List<Session> sessions = repository.nativelyFindByName("Keynote - The Golden Age of Software");
        assertTrue(sessions.size() > 0);
    }

    @Test
    @Transactional
    public void testUpdateSessionName() {
        String testName = "Test name";
        String updatedTestName = "Updated test name";

        Session session = new Session();
        session.setSessionName(testName);
        session.setSessionName(testName);
        session.setSessionDescription("");
        session.setSessionLength(123);
        repository.saveAndFlush(session);

        Session sessionWithName = repository.findBySessionNameContaining(testName).get(0);
        assertEquals(session.getSessionName(), testName);
        Long sessionId = sessionWithName.getSessionId();

        int updateCount = repository.updateSessionName(updatedTestName, sessionId);

        assertEquals(updateCount, 1);
        assertEquals(repository.nativelyFindByName(testName).size(),0);
        assertEquals(repository.nativelyFindByName(updatedTestName).size(),1);

        repository.deleteById(sessionId);
    }
}
