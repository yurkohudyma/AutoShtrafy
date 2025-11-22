package ua.hudyma.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.hudyma.dto.CameraReqDto;
import ua.hudyma.dto.CameraRespDto;
import ua.hudyma.dto.CarRespDto;
import ua.hudyma.dto.FineRespDto;
import ua.hudyma.service.CameraService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/cameras")
public class CameraController {
    private final CameraService cameraService;
    @PostMapping
    public ResponseEntity<String> createCamera (@RequestBody CameraReqDto dto){
        return ResponseEntity.ok(cameraService.createCamera(dto));
    }

    @GetMapping
    public ResponseEntity<CameraRespDto> fetchCamera (@RequestParam String cameraCode){
        return ResponseEntity.ok(cameraService.fetchCamera(cameraCode));
    }
    @GetMapping("/all")
    public ResponseEntity<List<CameraRespDto>> getAll (){
        return ResponseEntity.ok(cameraService.getAll());
    }
}
