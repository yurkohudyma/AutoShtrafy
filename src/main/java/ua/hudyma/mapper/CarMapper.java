package ua.hudyma.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ua.hudyma.domain.Car;
import ua.hudyma.domain.Driver;
import ua.hudyma.domain.Fine;
import ua.hudyma.dto.CarReqDto;
import ua.hudyma.dto.CarRespDto;
import ua.hudyma.enums.RegionalCodes;
import ua.hudyma.exception.DtoObligatoryFieldsAreMissingException;
import ua.hudyma.service.DriverService;
import ua.hudyma.util.IdGenerator;

@Component
@RequiredArgsConstructor
public class CarMapper extends BaseMapper<CarRespDto, Car, CarReqDto> {
    private final EntityUtilMapper utilMapper;
    private final DriverService driverService;

    @Override
    public CarRespDto toDto(Car car) {
        return new CarRespDto(
                car.getLicensePlate(),
                utilMapper.getEntityFieldList(
                        car.getDriverList(),
                        Driver::getFullName),
                car.getOwner().getFullName(),
                utilMapper.getEntityFieldList(
                        car.getFineList(), 
                        Fine::getPostanovaNumber),
                findRegionByLicensePlate(car.getLicensePlate())
        );
    }

    private String findRegionByLicensePlate(String licensePlate) {
        return RegionalCodes
                .fromCode(licensePlate.substring(0,2))
                .getRegionName();
    }

    @Override
    public Car toEntity(CarReqDto dto) {
        var ownerCode = dto.ownerCode();
        if (ownerCode == null || ownerCode.isEmpty()){
            throw new DtoObligatoryFieldsAreMissingException
                    ("Car owner not PROVIDED in CarReqDto");
        }
        var car = new Car();
        car.setOwner(driverService.getDriver(ownerCode));
        return car;
    }


}
