package dev.joaov.refrigops.controller.dto;

import dev.joaov.refrigops.domain.equipment.EquipmentType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CreateEquipmentRequest(

        @NotBlank(message = "O código do equipamento é obrigatório")
        String code,

        @NotBlank(message = "O nome do equipamento é obrigatório")
        String name,

        @NotNull(message = "O tipo do equipamento é obrigatório")
        EquipmentType type,

        @NotBlank(message = "A localização do equipamento é obrigatória")
        String location

) {
}
