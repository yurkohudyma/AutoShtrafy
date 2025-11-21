package ua.hudyma.mapper;

import ua.hudyma.domain.Camera;
import ua.hudyma.dto.CameraReqDto;
import ua.hudyma.dto.CameraRespDto;

public class CameraMapper extends BaseMapper<CameraRespDto, Camera, CameraReqDto>{
    @Override
    public CameraRespDto toDto(Camera camera) {
        return null;
    }

    @Override
    public Camera toEntity(CameraReqDto cameraReqDto) {
        return null;
    }
}
