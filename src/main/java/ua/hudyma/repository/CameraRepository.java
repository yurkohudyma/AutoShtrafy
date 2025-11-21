package ua.hudyma.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.hudyma.domain.Camera;

public interface CameraRepository extends JpaRepository<Camera, Long> {
}
