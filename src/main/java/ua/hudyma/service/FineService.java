package ua.hudyma.service;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import ua.hudyma.dto.FineReqDto;
import ua.hudyma.exception.DtoObligatoryFieldsAreMissingException;
import ua.hudyma.mapper.CameraMapper;
import ua.hudyma.mapper.CarMapper;
import ua.hudyma.mapper.FineMapper;
import ua.hudyma.repository.FineRepository;

import static ua.hudyma.util.MessageProcessor.getReturnMessage;

@Service
@RequiredArgsConstructor
@Log4j2
public class FineService {
    private final FineRepository fineRepository;
    private final CarService carService;
    private final DriverService driverService;
    private final FineMapper fineMapper;
    private final CameraService cameraService;

    @SneakyThrows
    public String createFine (@RequestBody FineReqDto dto){
        var licensePlate = dto.licensePlateNumber();
        var driverCode = dto.driverCode();
        var cameraCode = dto.cameraCode();
        if (    licensePlate == null ||
                licensePlate.isEmpty() ||
                driverCode == null ||
                driverCode.isEmpty() ||
                cameraCode == null ||
                cameraCode.isEmpty()){
            throw new DtoObligatoryFieldsAreMissingException("Fine fields ARE VOID");
        }
        var car = carService.getCar(licensePlate);
        var driver = driverService.getDriver(driverCode);
        var fine = fineMapper.toEntity(dto);
        var camera = cameraService.getCamera(cameraCode);
        fine.setCamera(camera);
        fine.setCar(car);
        fine.setDriver(driver);
        fine.setChannelType(dto.channelType());
        fine.setPostanovaNumber(dto.postanovaNumber());
        fineRepository.save(fine);
        return getReturnMessage (fine, "fineCode");
    }
}
