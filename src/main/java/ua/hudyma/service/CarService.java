package ua.hudyma.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.hudyma.domain.Car;
import ua.hudyma.domain.Driver;
import ua.hudyma.dto.CarReqDto;
import ua.hudyma.dto.CarRespDto;
import ua.hudyma.exception.DtoObligatoryFieldsAreMissingException;
import ua.hudyma.mapper.CarMapper;
import ua.hudyma.repository.CarRepository;
import ua.hudyma.repository.DriverRepository;

import java.util.List;
import java.util.Optional;

import static ua.hudyma.util.MessageProcessor.getReturnMessage;

@Service
@RequiredArgsConstructor
@Log4j2
public class CarService {
    private final CarRepository carRepository;
    private final DriverRepository driverRepository;
    private final CarMapper carMapper;

    @Transactional
    public String bindDriver(String licensePlate, String driverCode) {
        if (licensePlate == null || licensePlate.isEmpty()
                || driverCode == null || driverCode.isEmpty()) {
            throw new DtoObligatoryFieldsAreMissingException("Dto obligatory Fields " +
                    "for binding Driver with Car ARE NULL OR EMPTY");
        }
        var car = getCar(licensePlate);
        var driver = getDriver(driverCode);
        car.getDriverList().add(driver);
        driver.getCarList().add(car);
        var msg = String.format("Driver %s succ BOUND with Car %s",
                driver.getFullName(),
                car.getLicensePlate());
        log.info(msg);
        return msg;
    }

    @Transactional
    public List<CarRespDto> getAll() {
        return carMapper.toDtoList(carRepository.findAll());
    }

    private Driver getDriver(String driverCode) {
        return driverRepository.findByDriverCode(driverCode)
                .orElseThrow(() ->
                        new EntityNotFoundException
                                ("Driver " + driverCode + " NOT FOUND"));
    }

    public String createCar(CarReqDto dto) throws Exception {
        var car = carMapper.toEntity(dto);
        carRepository.save(car);
        return getReturnMessage(car, "licensePlate");
    }

    @Transactional
    public CarRespDto fetchCar(String licensePlate) {
        if (licensePlate == null || licensePlate.isEmpty()) {
            throw new DtoObligatoryFieldsAreMissingException
                    ("License Plate number is NULL or empty");
        }
        var car = getCar(licensePlate);
        return carMapper.toDto(car);
    }

    public Car getCar(String licensePlate) {
        return carRepository
                .findByLicensePlate(licensePlate)
                .orElseThrow(() ->
                        new EntityNotFoundException
                                ("Авто " + licensePlate + " НЕ ЗНАЙДЕНО"));
    }
}
