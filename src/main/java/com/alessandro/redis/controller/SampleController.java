package com.alessandro.redis.controller;

import com.alessandro.redis.model.Person;
import com.alessandro.redis.model.repository.PersonRepository;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by alessandro.santos on 1/8/17.
 */

@RestController
public class SampleController {

    @Autowired
    private PersonRepository personRepository;

    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(value = "person", method = RequestMethod.POST)
    public String add(@RequestBody Person person) {

        personRepository.save(person);
        return "OK";
    }


    @RequestMapping(value = "person", method = RequestMethod.GET)
    public ResponseEntity<List<Person>> search() {
        Iterable<Person> iterablePerson = personRepository.findAll();
        ArrayList<Person> persons = Lists.newArrayList(iterablePerson);
        HttpStatus status = HttpStatus.OK;

        if(persons.size() < 1)
            status = HttpStatus.NOT_FOUND;

        return new ResponseEntity<List<Person>>(persons, null, status);
    }

    @RequestMapping(value = "person/name/{name}/lastname/{lastName}", method = RequestMethod.GET)
    public ResponseEntity<List<Person>> search(@PathVariable String name, @PathVariable String lastName) {
        List<Person> persons = personRepository.findByNameAndLastName(name, lastName);
        HttpStatus status = HttpStatus.OK;

        if(persons.size() < 1)
            status = HttpStatus.NOT_FOUND;

        return new ResponseEntity<List<Person>>(persons, null, status);
    }

    @RequestMapping(value = "person/lastname/{lastName}", method = RequestMethod.GET)
    public ResponseEntity<List<Person>> search(@PathVariable String lastName) {
        List<Person> persons = personRepository.findByLastName(lastName);
        HttpStatus status = HttpStatus.OK;

        if(persons.size() < 1)
            status = HttpStatus.NOT_FOUND;

        return new ResponseEntity<List<Person>>(persons, null, status);


    }

    @RequestMapping(value = "person/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<String> deleteById(@PathVariable String id) {

        personRepository.delete(id);
        return new ResponseEntity<String>("OK", null, HttpStatus.OK);
    }
}
