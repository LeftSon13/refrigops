package dev.joaov.refrigops.controller.dto;

import dev.joaov.refrigops.domain.equipment.EquipmentType;

public record CreateEquipmentRequest(
        String code,
        String name,
        EquipmentType type,
        String location
) {
}
