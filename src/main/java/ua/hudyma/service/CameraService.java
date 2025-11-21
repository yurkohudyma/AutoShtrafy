package ua.hudyma.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import ua.hudyma.domain.Camera;
import ua.hudyma.repository.CameraRepository;

@Service
@RequiredArgsConstructor
@Log4j2
public class CameraService {
    private final CameraRepository cameraRepository;

    public Camera getCamera(String cameraCode) {
        return null;
    }
}
