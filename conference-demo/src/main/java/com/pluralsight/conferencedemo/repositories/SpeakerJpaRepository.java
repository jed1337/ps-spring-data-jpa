package com.pluralsight.conferencedemo.repositories;

import com.pluralsight.conferencedemo.models.Speaker;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

//This doesn't have the @Repository annotation
//On startup, Spring data is enabled and does a classpath search for interfaces or classes of type JpaRepository
//If they exist, they are treated as repositories and stored in the Spring context that way
public interface SpeakerJpaRepository extends JpaRepository<Speaker, Long> {
    List<Speaker> findByFirstNameAndLastName(String firstName, String lastName);
    List<Speaker> findByFirstNameOrLastName(String firstName, String lastName);
}
