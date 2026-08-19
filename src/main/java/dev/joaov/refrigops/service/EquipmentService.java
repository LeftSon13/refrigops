package dev.joaov.refrigops.service;

import dev.joaov.refrigops.domain.equipment.Equipment;
import dev.joaov.refrigops.domain.equipment.EquipmentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EquipmentService {

    private final EquipmentRepository equipmentRepository;

    public EquipmentService(EquipmentRepository equipmentRepository) {
        this.equipmentRepository = equipmentRepository;
    }

    public Equipment save(Equipment equipment) {
        return equipmentRepository.save(equipment);
    }

    public List<Equipment> findAll() {
        return equipmentRepository.findAll();
    }
}
