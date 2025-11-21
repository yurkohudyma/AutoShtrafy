package ua.hudyma.domain;

import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;
import org.hibernate.annotations.NaturalId;
import ua.hudyma.util.IdGenerator;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "cars")
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NaturalId
    private String licensePlate = IdGenerator.generateLicensePlate();

    @ManyToMany
    @ToString.Exclude
    @JoinTable(name = "drivers_using_cars",
            joinColumns = @JoinColumn(name = "driver_id"),
            inverseJoinColumns = @JoinColumn(name = "car_id"))
    private List<Driver> driverList = new ArrayList<>();

    @OneToMany(mappedBy = "car")
    @ToString.Exclude
    private List<Fine> fineList = new ArrayList<>();

}
