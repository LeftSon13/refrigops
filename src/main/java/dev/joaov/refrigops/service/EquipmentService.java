package dev.joaov.refrigops.service;

import dev.joaov.refrigops.controller.dto.CreateEquipmentRequest;
import dev.joaov.refrigops.domain.equipment.Equipment;
import dev.joaov.refrigops.domain.equipment.EquipmentRepository;
import dev.joaov.refrigops.domain.equipment.EquipmentStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EquipmentService {

    private final EquipmentRepository equipmentRepository;

    public EquipmentService(EquipmentRepository equipmentRepository) {
        this.equipmentRepository = equipmentRepository;
    }

    public Equipment create(CreateEquipmentRequest request) {
        Equipment equipment = new Equipment();

        equipment.setCode(request.code());
        equipment.setName(request.name());
        equipment.setType(request.type());
        equipment.setLocation(request.location());

        equipment.setStatus(EquipmentStatus.STOPPED);
        equipment.setActive(true);

        return equipmentRepository.save(equipment);
    }

    public List<Equipment> findAll() {
        return equipmentRepository.findAll();
    }
}
