package ua.hudyma.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.hudyma.dto.CarRespDto;
import ua.hudyma.dto.FineReqDto;
import ua.hudyma.dto.FineRespDto;
import ua.hudyma.service.FineService;

import java.util.List;

@RestController
@RequestMapping("/fines")
@RequiredArgsConstructor
public class FineController {
    private final FineService fineService;

    @PostMapping
    public ResponseEntity<String> createFine (@RequestBody FineReqDto dto){
        return ResponseEntity.ok(fineService.createFine(dto));
    }
    @GetMapping
    public ResponseEntity<FineRespDto> fetchFine (@RequestParam String fineCode){
        return ResponseEntity.ok(fineService.fetchFine(fineCode));
    }
    @GetMapping("/all")
    public ResponseEntity<List<FineRespDto>> getAll (){
        return ResponseEntity.ok(fineService.getAll());
    }
}
