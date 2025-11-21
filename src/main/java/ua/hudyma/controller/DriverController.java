package ua.hudyma.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.hudyma.dto.DriverReqDto;
import ua.hudyma.dto.DriverRespDto;
import ua.hudyma.service.DriverService;

@RestController
@RequestMapping("/drivers")
@RequiredArgsConstructor
public class DriverController {
    private final DriverService driverService;
    @PostMapping
    public ResponseEntity<String> createDriver(@RequestBody DriverReqDto dto) throws Exception {
        return ResponseEntity.ok(driverService.createDriver(dto));
    }
    @GetMapping
    public ResponseEntity<DriverRespDto> fetchDriver (@RequestParam String driverCode){
        return ResponseEntity.ok(driverService.fetchDriver(driverCode));
    }

}
