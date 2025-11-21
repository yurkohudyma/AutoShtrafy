package ua.hudyma.domain;

import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;
import ua.hudyma.enums.ChannelType;

@Entity
@Table(name = "camera_channel")
@Data
public class Channel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Enumerated(EnumType.STRING)
    private ChannelType channelType;
    @ManyToOne
    @ToString.Exclude
    @JoinColumn(name = "fine_id")
    private Fine fine;
}
