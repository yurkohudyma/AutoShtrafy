package ua.hudyma.mapper;

import org.springframework.stereotype.Component;
import ua.hudyma.domain.Fine;
import ua.hudyma.dto.FineReqDto;
import ua.hudyma.dto.FineRespDto;

import java.util.List;

@Component
public class FineMapper extends BaseMapper<FineRespDto, Fine, FineReqDto> {
    @Override
    public FineRespDto toDto(Fine fine) {
        return null;
    }

    @Override
    public Fine toEntity(FineReqDto fineReqDto) {
        return null;
    }


}
