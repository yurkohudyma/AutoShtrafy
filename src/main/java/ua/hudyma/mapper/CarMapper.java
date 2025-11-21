package ua.hudyma.mapper;

import org.springframework.stereotype.Component;
import ua.hudyma.domain.Car;
import ua.hudyma.dto.CarReqDto;
import ua.hudyma.dto.CarRespDto;

@Component
public class CarMapper extends BaseMapper<CarRespDto, Car, CarReqDto> {
    @Override
    public CarRespDto toDto(Car car) {
        return null;
    }

    @Override
    public Car toEntity(CarReqDto carReqDto) {
        return new Car();
    }
}
