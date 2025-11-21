package ua.hudyma.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ua.hudyma.domain.Car;
import ua.hudyma.domain.Driver;
import ua.hudyma.domain.Fine;
import ua.hudyma.dto.DriverReqDto;
import ua.hudyma.dto.DriverRespDto;
import ua.hudyma.exception.DtoObligatoryFieldsAreMissingException;

import java.util.List;

@Component
@RequiredArgsConstructor
public class DriverMapper extends BaseMapper<DriverRespDto, Driver, DriverReqDto> {
    private final EntityUtilMapper utilMapper;
    @Override
    public DriverRespDto toDto(Driver driver) {
        return new DriverRespDto(
                driver.getFullName(),
                driver.getPhoneNumber(),
                driver.getDriverCode(),
                utilMapper.getEntityFieldList(
                        driver.getCarList(),
                        Car::getLicensePlate),
                utilMapper.getEntityFieldList(
                        driver.getFineList(),
                        Fine::getPostanovaNumber)
        );
    }



    @Override
    public Driver toEntity(DriverReqDto dto) {
        var driverName = dto.driverName();
        if (driverName == null || driverName.isEmpty()){
            throw new DtoObligatoryFieldsAreMissingException
                    ("Driver Name is NULL or missing");
        }
        var driver = new Driver();
        driver.setFullName(dto.driverName());
        return driver;
    }
}
