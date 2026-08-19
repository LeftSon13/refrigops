package dev.joaov.refrigops.domain.equipment;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class EquipmentRepositoryTest {

    @Autowired
    private EquipmentRepository equipmentRepository;

    @Test
    void shouldSaveAndFindEquipment() {
        Equipment equipment = new Equipment();
        equipment.setCode("COMP-13");
        equipment.setName("Compressor 13");
        equipment.setType(EquipmentType.COMPRESSOR);
        equipment.setStatus(EquipmentStatus.STOPPED);
        equipment.setActive(true);
        equipment.setLocation("Sala 1");

        Equipment saved = equipmentRepository.save(equipment);

        assertNotNull(saved.getId());

        Equipment found = equipmentRepository
                .findById(saved.getId())
                .orElseThrow();

        assertEquals("COMP-13", found.getCode());
        assertEquals("Compressor 13", found.getName());
        assertEquals(EquipmentType.COMPRESSOR, found.getType());
        assertEquals(EquipmentStatus.STOPPED, found.getStatus());
        assertTrue(found.isActive());
        assertEquals("Sala 1", found.getLocation());
    }
}
