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
@Table(name = "drivers")
public class Driver implements BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NaturalId
    private String driverCode = IdGenerator.generateId(2, 5);
    @ManyToMany(mappedBy = "driverList")
    @ToString.Exclude
    private List<Car> carList = new ArrayList<>();
    private String phoneNumber = IdGenerator.generatePhoneNumber();
    private String fullName;
    @OneToMany(mappedBy = "driver")
    @ToString.Exclude
    private List<Fine> fineList = new ArrayList<>();
    @OneToOne(mappedBy = "owner")
    private Car car;

}
