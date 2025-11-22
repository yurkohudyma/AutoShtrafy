package ua.hudyma.domain;

import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.annotations.NaturalId;
import org.hibernate.type.SqlTypes;
import ua.hudyma.dto.Location;
import ua.hudyma.enums.ChannelType;
import ua.hudyma.util.IdGenerator;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;
import java.util.Set;

@Data
@Entity
@Table(name = "cameras")
public class Camera implements BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NaturalId
    private String cameraCode = IdGenerator.generateId(4, 3);
    @OneToMany(mappedBy = "camera")
    @ToString.Exclude
    private List<Fine> fineList = new ArrayList<>();
    @JdbcTypeCode(SqlTypes.JSON)
    @Column(columnDefinition = "json", name = "location")
    private Location location;
    private String address;
    @ElementCollection(targetClass = ChannelType.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "camera_channels",
            joinColumns = @JoinColumn(name = "camera_id"))
    @Enumerated(EnumType.STRING)
    private Set<ChannelType> channelTypeSet = EnumSet
            .noneOf(ChannelType.class);
}
