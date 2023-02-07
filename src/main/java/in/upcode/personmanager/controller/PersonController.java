package in.upcode.personmanager.controller;

import in.upcode.personmanager.model.Person;
import in.upcode.personmanager.repository.PersonRepository;
import in.upcode.personmanager.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/person")
public class PersonController {

    // GET /api/person  -> List of all persons
    // GET /api/person/{id} -> A Single Person details with the given ID

    // PUT /api/person/{id} body -> Update a single Person details with the given ID, by the given data
    // Delete /api/person/{id} -> Delete a single person with the given ID

    // GET /api/person?name={name} -> List of person with the given name

    //POST /api/person body -> Create a new person in the DB with the given data

    @Autowired
    PersonRepository personRepository;

    @Autowired
    PersonService personService;

    @GetMapping("")
    List<Person> listOfAllPersons(@RequestParam(value = "name", required = false) String name) {
        if (name == null)
            return personRepository.findAll();
        return personRepository.findByNameIgnoreCase(name);
    }

    @GetMapping("/{id}")
    ResponseEntity getAPersonWithID(@PathVariable("id") Integer id) {
        final Optional<Person> optionalPerson = personRepository.findById(id);
        if (optionalPerson.isPresent()) {
            return ResponseEntity.ok(optionalPerson.get());
        }
        return ResponseEntity.badRequest().body("Could not find any person!!");
    }

    @PutMapping(value = "/{id}")
    ResponseEntity updateAPersonWithID(@PathVariable("id") Integer id, @RequestBody Person person) {
        return personService.updateAndSavePerson(id, person);
    }

    @DeleteMapping(value = "/{id}")
    ResponseEntity deleteAPerson(@PathVariable("id") Integer id) {
        personRepository.deleteById(id);

        return ResponseEntity.noContent().build();
    }

    @PostMapping
    ResponseEntity createAPerson(@RequestBody Person person, UriComponentsBuilder uriComponentsBuilder) {
        final Person createdPerson = personRepository.save(person);

        final URI location = uriComponentsBuilder.path("/api/person/{id}").buildAndExpand(createdPerson.getId()).toUri();
        return ResponseEntity.created(location).body(createdPerson);
    }

}
