package ua.hudyma.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.hudyma.domain.Camera;
import ua.hudyma.dto.CameraReqDto;
import ua.hudyma.dto.CameraRespDto;
import ua.hudyma.mapper.CameraMapper;
import ua.hudyma.repository.CameraRepository;

import java.util.List;

import static ua.hudyma.util.MessageProcessor.getReturnMessage;

@Service
@RequiredArgsConstructor
@Log4j2
public class CameraService {
    private final CameraRepository cameraRepository;
    private final CameraMapper cameraMapper;

    @SneakyThrows
    public String createCamera (CameraReqDto dto){
        var camera = cameraMapper.toEntity(dto);
        cameraRepository.save(camera);
        return getReturnMessage(camera, "cameraCode");
    }

    @Transactional
    public CameraRespDto fetchCamera(String cameraCode) {
        return cameraMapper.toDto(getCamera(cameraCode));
    }

    @Transactional
    public List<CameraRespDto> getAll() {
        return cameraMapper.toDtoList(cameraRepository.findAll());
    }

    public Camera getCamera(String cameraCode) {
        return cameraRepository.findByCameraCode(cameraCode)
                .orElseThrow( () -> new EntityNotFoundException(
                        "Camera " + cameraCode + " NOT FOUND"));
    }
}
