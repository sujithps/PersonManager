package in.upcode.personmanager.repository;

import in.upcode.personmanager.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PersonRepository extends JpaRepository<Person, Integer> {
    List<Person> findByNameIgnoreCase(String name);
}
