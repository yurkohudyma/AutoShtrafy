package ua.hudyma.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.hudyma.domain.Driver;
import ua.hudyma.dto.DriverReqDto;
import ua.hudyma.dto.DriverRespDto;
import ua.hudyma.exception.DtoObligatoryFieldsAreMissingException;
import ua.hudyma.mapper.DriverMapper;
import ua.hudyma.repository.DriverRepository;

import java.util.ArrayList;
import java.util.List;

import static ua.hudyma.util.MessageProcessor.getReturnMessage;

@Service
@RequiredArgsConstructor
@Log4j2

public class DriverService {
    private final DriverRepository driverRepository;
    private final DriverMapper driverMapper;

    public String createDriver(DriverReqDto[] dto) throws Exception {
        var list = new ArrayList<Driver>();
        for (DriverReqDto req: dto) {
            list.add(driverMapper.toEntity(req));
        }
        driverRepository.saveAll(list);
        return getReturnMessage(list, "fullName");
    }

    @SneakyThrows
    @Transactional
    public DriverRespDto fetchDriver(String driverCode) {
        if (driverCode == null || driverCode.isEmpty()){
            throw new DtoObligatoryFieldsAreMissingException("Driver code is NIHIL");
        }
        var driver = getDriver(driverCode);
        var msg = getReturnMessage(driver, "fullName");
        log.info(msg);
        return driverMapper.toDto(driver);
    }

    @Transactional
    public List<DriverRespDto> getAll() {
        return driverMapper.toDtoList(driverRepository.findAll());
    }

    public Driver getDriver(String driverCode) {
        return driverRepository
                .findByDriverCode(driverCode)
                .orElseThrow( () ->
                        new EntityNotFoundException(" Driver " + driverCode + " NOT FOUND"));
    }
}
