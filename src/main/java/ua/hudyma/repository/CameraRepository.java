package ua.hudyma.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.hudyma.domain.Camera;

import java.util.Optional;

public interface CameraRepository extends JpaRepository<Camera, Long> {
    Optional<Camera> findByCameraCode(String cameraCode);
}
