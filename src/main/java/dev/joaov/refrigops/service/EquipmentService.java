package dev.joaov.refrigops.service;

import dev.joaov.refrigops.domain.equipment.Equipment;
import dev.joaov.refrigops.domain.equipment.EquipmentRepository;
import dev.joaov.refrigops.domain.equipment.EquipmentStatus;
import dev.joaov.refrigops.domain.equipment.EquipmentType;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EquipmentService {

    private final EquipmentRepository equipmentRepository;

    public EquipmentService(EquipmentRepository equipmentRepository) {
        this.equipmentRepository = equipmentRepository;
    }

    public Equipment create(
            String code,
            String name,
            EquipmentType type,
            String location
    ) {
        Equipment equipment = new Equipment();

        equipment.setCode(code);
        equipment.setName(name);
        equipment.setType(type);
        equipment.setLocation(location);

        equipment.setStatus(EquipmentStatus.STOPPED);
        equipment.setActive(true);

        return equipmentRepository.save(equipment);
    }

    public List<Equipment> findAll() {
        return equipmentRepository.findAll();
    }
}
