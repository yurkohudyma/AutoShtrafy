package ua.hudyma.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.hudyma.dto.CarReqDto;
import ua.hudyma.dto.CarRespDto;
import ua.hudyma.service.CarService;

import java.util.List;

@RestController
@RequestMapping("/cars")
@RequiredArgsConstructor
public class CarController {
    private final CarService carService;
    @PostMapping
    public ResponseEntity<String> createCar (@RequestBody CarReqDto dto)
            throws Exception {
        return ResponseEntity.ok(carService.createCar(dto));
    }
    @GetMapping
    public ResponseEntity<CarRespDto> fetchCar (@RequestParam String licensePlate){
        return ResponseEntity.ok(carService.fetchCar(licensePlate));
    }
    @PatchMapping("/bindDriver")
    public ResponseEntity<String> bindDriver (@RequestParam String licensePlate,
                                              @RequestParam String driverCode){
        return ResponseEntity.ok(carService
                .bindDriver (licensePlate, driverCode));
    }
    @GetMapping("/all")
    public ResponseEntity<List<CarRespDto>> getAll (){
        return ResponseEntity.ok(carService.getAll());
    }
}
