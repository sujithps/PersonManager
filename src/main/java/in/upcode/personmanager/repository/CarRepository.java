package in.upcode.personmanager.repository;

import in.upcode.personmanager.model.Car;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CarRepository extends JpaRepository<Car, Integer> {
    List<Car> findAllByOwnerId(Integer ownerId);
}
