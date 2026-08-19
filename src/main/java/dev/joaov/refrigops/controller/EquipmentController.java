package dev.joaov.refrigops.controller;

import dev.joaov.refrigops.controller.dto.CreateEquipmentRequest;
import dev.joaov.refrigops.domain.equipment.Equipment;
import dev.joaov.refrigops.service.EquipmentService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/equipment")
public class EquipmentController {

    private final EquipmentService equipmentService;

    public EquipmentController(EquipmentService equipmentService) {
        this.equipmentService = equipmentService;
    }

    @GetMapping
    public List<Equipment> findAll() {
        return equipmentService.findAll();
    }

    @PostMapping
    public Equipment create(@RequestBody CreateEquipmentRequest request) {
        return equipmentService.create(
                request.code(),
                request.name(),
                request.type(),
                request.location()
        );
    }
}
