package dev.joaov.refrigops.controller.dto;

import dev.joaov.refrigops.domain.equipment.Equipment;
import dev.joaov.refrigops.domain.equipment.EquipmentStatus;
import dev.joaov.refrigops.domain.equipment.EquipmentType;

public record EquipmentResponse(
        Long id,
        String code,
        String name,
        EquipmentType type,
        EquipmentStatus status,
        boolean active,
        String location
) {

    public static EquipmentResponse from(Equipment equipment) {
        return new EquipmentResponse(
                equipment.getId(),
                equipment.getCode(),
                equipment.getName(),
                equipment.getType(),
                equipment.getStatus(),
                equipment.isActive(),
                equipment.getLocation()
        );
    }
}
