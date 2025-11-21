package ua.hudyma.domain;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.NaturalId;
import ua.hudyma.enums.ChannelType;
import ua.hudyma.util.IdGenerator;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "fines")
public class Fine {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NaturalId
    private String fineCode = IdGenerator.generateId(2, 5);
    @ManyToOne
    @JoinColumn(name = "driver_id")
    private Driver driver;
    @ManyToOne
    @JoinColumn(name = "car_id")
    private Car car;
    @ManyToOne
    @JoinColumn(name = "camera_id")
    private Camera camera;
    @Enumerated(EnumType.STRING)
    private ChannelType channelType;
    private String postanovaNumber;

}
