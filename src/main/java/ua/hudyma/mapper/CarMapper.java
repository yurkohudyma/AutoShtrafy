package ua.hudyma.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ua.hudyma.domain.Car;
import ua.hudyma.domain.Driver;
import ua.hudyma.domain.Fine;
import ua.hudyma.dto.CarReqDto;
import ua.hudyma.dto.CarRespDto;

@Component
@RequiredArgsConstructor
public class CarMapper extends BaseMapper<CarRespDto, Car, CarReqDto> {
    private final EntityUtilMapper utilMapper;

    @Override
    public CarRespDto toDto(Car car) {
        return new CarRespDto(
                car.getLicensePlate(),
                utilMapper.getEntityFieldList(
                        car.getDriverList(),
                        Driver::getFullName),
                utilMapper.getEntityFieldList(
                        car.getFineList(), Fine::getPostanovaNumber)
        );
    }

    @Override
    public Car toEntity(CarReqDto carReqDto) {
        return new Car();
    }


}
