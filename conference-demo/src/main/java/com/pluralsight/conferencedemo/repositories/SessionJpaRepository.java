package com.pluralsight.conferencedemo.repositories;

import com.pluralsight.conferencedemo.models.Session;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SessionJpaRepository extends JpaRepository<Session, Long> {

    //DSLs extend the language to work better with a specific domain
    //The query DSL allows Spring to enhance Java to better work with queries
    //Show the generated query by using spring.jpa.show-sql=true in application.properties
    //Hibernate generates this query given the method name findBySessionNameContaining
    //select ...
    //from sessions session0_
    //where session0_.session_name like ? escape ?
    List<Session> findBySessionNameContaining(String name);

    List<Session> findBySessionLengthNot(Integer sessionLength);

    List<Session> findBySessionNameNotLike(String sessionName);

    //You can use native queries to tune your application to your database
    //But it locks you to that database implementation
    @Query(value = "select * " +
            "from sessions " +
            "where session_name = ?1",
            nativeQuery = true
    )
    List<Session> nativelyFindByName(String name);

    @Modifying
    @Query("update Session s set s.sessionName=?1 where s.sessionId=?2")
    int updateSessionName(String name, Long id);
}
