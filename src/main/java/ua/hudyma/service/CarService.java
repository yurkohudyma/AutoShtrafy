package ua.hudyma.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import ua.hudyma.dto.CarReqDto;
import ua.hudyma.mapper.CarMapper;
import ua.hudyma.repository.CarRepository;

@Service
@RequiredArgsConstructor
@Log4j2
public class CarService {
    private final CarRepository carRepository;
    private final CarMapper carMapper;

    public String createCar(CarReqDto dto) {
        var car = carMapper.toEntity(dto);
        carRepository.save(car);
        var msg = String.format("Авто [%s] додано до бази",
                car.getLicensePlate());
        log.info(msg);
        return msg;
    }
}
