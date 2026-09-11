package dev.joaov.refrigops.controller.dto;

import dev.joaov.refrigops.domain.equipment.EquipmentType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record CreateEquipmentRequest(

        @NotBlank(message = "O código do equipamento é obrigatório")
        @Size(max = 50, message = "O código do equipamento deve ter no máximo 50 caracteres")
        String code,

        @NotBlank(message = "O nome do equipamento é obrigatório")
        @Size(max = 100, message = "O nome do equipamento deve ter no máximo 100 caracteres")
        String name,

        @NotNull(message = "O tipo do equipamento é obrigatório")
        EquipmentType type,

        @NotBlank(message = "A localização do equipamento é obrigatória")
        @Size(max = 100, message = "A localização do equipamento deve ter no máximo 100 caracteres")
        String location

) {
}
