package ua.hudyma.mapper;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Component;
import ua.hudyma.domain.Camera;
import ua.hudyma.domain.Fine;
import ua.hudyma.dto.CameraReqDto;
import ua.hudyma.dto.CameraRespDto;
import ua.hudyma.dto.Location;
import ua.hudyma.enums.ChannelType;
import ua.hudyma.exception.DtoObligatoryFieldsAreMissingException;

import java.util.EnumSet;
import java.util.Set;

import static ua.hudyma.util.MessageProcessor.getReturnMessage;

@Component
@RequiredArgsConstructor
public class CameraMapper extends BaseMapper<CameraRespDto, Camera, CameraReqDto>{
    private final EntityUtilMapper utilMapper;
    @Override
    public CameraRespDto toDto(Camera camera) {
        return new CameraRespDto(
                camera.getCameraCode(),
                camera.getLocation(),
                camera.getAddress(),
                camera.getChannelTypeSet(),
                utilMapper.getEntityFieldList(
                        camera.getFineList(),
                        Fine::getPostanovaNumber)
        );
    }

    @Override
    @SneakyThrows
    public Camera toEntity(CameraReqDto dto) {
        var location = dto.location();
        var address = dto.address();
        if (location == null || address == null || address.isEmpty()){
            throw new DtoObligatoryFieldsAreMissingException("Camera DTO fields are VOID");
        }
        var camera = new Camera();
        camera.setAddress(address);
        camera.setLocation(location);
        var channelTypes = dto.channelTypeSet();
        if (channelTypes == null || channelTypes.isEmpty()){
            camera.setChannelTypeSet(EnumSet.of(ChannelType.SPEED_LIMIT));
        }
        else {
            camera.setChannelTypeSet(channelTypes);
        }
        getReturnMessage(camera, "cameraCode");
        return camera;
    }
}
