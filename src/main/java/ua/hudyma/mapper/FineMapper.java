package ua.hudyma.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ua.hudyma.domain.Driver;
import ua.hudyma.domain.Fine;
import ua.hudyma.dto.FineReqDto;
import ua.hudyma.dto.FineRespDto;
import ua.hudyma.dto.Location;
import ua.hudyma.exception.DtoObligatoryFieldsAreMissingException;
import ua.hudyma.service.CameraService;
import ua.hudyma.service.CarService;
import ua.hudyma.service.DriverService;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static java.time.format.DateTimeFormatter.ofPattern;

@Component
@RequiredArgsConstructor
public class FineMapper extends BaseMapper<FineRespDto, Fine, FineReqDto> {
    private final CarService carService;
    private final DriverService driverService;
    private final CameraService cameraService;
    @Override
    public FineRespDto toDto(Fine fine) {
        return new FineRespDto(
                fine.getFineCode(),
                fine.getCar().getLicensePlate(),
                getDriversNamesList(fine.getCar().getDriverList()),
                fine.getCar().getOwner().getFullName(),
                fine.getCamera().getAddress(),
                compileLocation (fine.getCamera().getLocation()),
                compileRegisteredOn(fine.getRegisteredOn()),
                fine.getPostanovaNumber(),
                fine.getChannelType().getValue()
        );
    }

    private List<String> getDriversNamesList(List<Driver> driverList) {
        return driverList.stream().map(Driver::getFullName).toList();
    }

    private String compileRegisteredOn(LocalDateTime registeredOn) {
        return registeredOn.format(ofPattern("dd-MM-yyyy HH:mm"));
    }

    private String compileLocation(Location location) {
        return String.format("%s, %s", location.lat(), location.lon());
    }

    @Override
    public Fine toEntity(FineReqDto dto) {
        var licensePlate = dto.licensePlateNumber();
        var cameraCode = dto.cameraCode();
        if (    licensePlate == null ||
                licensePlate.isEmpty() ||
                cameraCode == null ||
                cameraCode.isEmpty()){
            throw new DtoObligatoryFieldsAreMissingException("Fine fields ARE VOID");
        }
        var car = carService.getCar(licensePlate);
        var camera = cameraService.getCamera(cameraCode);
        var fine = new Fine();
        fine.setCamera(camera);
        fine.setCar(car);
        fine.setChannelType(dto.channelType());
        return fine;
    }


}
