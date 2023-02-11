package in.upcode.personmanager.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString
public class Car {

    @Id
    @GeneratedValue
    private int id;
    private String model;

    @ManyToOne
    @JoinColumn(name = "owner_id")
    @JsonIgnore
    private Person owner;


}
