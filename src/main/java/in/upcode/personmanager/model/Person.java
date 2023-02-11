package in.upcode.personmanager.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
public class Person {

    @Id
    @GeneratedValue
    private Integer id;

    private String name;
    private int age;

    @OneToMany(mappedBy = "owner", cascade = CascadeType.ALL)
    List<Car> ownedCars;
}
