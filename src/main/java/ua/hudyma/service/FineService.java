package ua.hudyma.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import ua.hudyma.domain.Fine;
import ua.hudyma.dto.FineReqDto;
import ua.hudyma.dto.FineRespDto;
import ua.hudyma.mapper.FineMapper;
import ua.hudyma.repository.FineRepository;

import static ua.hudyma.util.MessageProcessor.getReturnMessage;

@Service
@RequiredArgsConstructor
@Log4j2
public class FineService {
    private final FineRepository fineRepository;
    private final FineMapper fineMapper;


    @SneakyThrows
    public String createFine (@RequestBody FineReqDto dto){
       var fine = fineMapper.toEntity(dto);
        fineRepository.save(fine);
        return getReturnMessage (fine,
                "postanovaNumber");
    }

    @Transactional
    public FineRespDto fetchFine(String fineCode) {
        return fineMapper.toDto(getFine(fineCode));
    }

    public Fine getFine(String fineCode) {
        return fineRepository.findByFineCode(fineCode)
                .orElseThrow(() ->
                new EntityNotFoundException
                        ("Штраф " + fineCode + " НЕ ЗНАЙДЕНО"));
    }
}
