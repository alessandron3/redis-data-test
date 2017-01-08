package com.alessandro.redis.model.repository;

import com.alessandro.redis.model.Person;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by alessandro.santos on 1/8/17.
 */
public interface PersonRepository extends CrudRepository<Person, String> {

    List<Person> findByLastName(String lastName);

    List<Person> findByLastName(String lastName, Pageable page);

    List<Person> findByNameAndLastName(String name, String lastName);

    List<Person> findByNameOrLastName(String name, String lastName);




}
