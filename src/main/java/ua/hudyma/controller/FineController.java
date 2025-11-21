package ua.hudyma.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ua.hudyma.service.FineService;

@RestController
@RequestMapping("/fines")
@RequiredArgsConstructor
public class FineController {
    private final FineService fineService;
}
