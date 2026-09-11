package dev.joaov.refrigops.controller;

import dev.joaov.refrigops.TestcontainersConfiguration;
import dev.joaov.refrigops.domain.equipment.EquipmentRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.hasItem;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Import(TestcontainersConfiguration.class)
class EquipmentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private EquipmentRepository equipmentRepository;

    @Test
    void shouldReturnBadRequestWhenRequiredTextFieldsAreBlank() throws Exception {
        String requestBody = """
                {
                  "code": "",
                  "name": "",
                  "type": "COMPRESSOR",
                  "location": ""
                }
                """;

        mockMvc.perform(post("/api/equipment")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().isBadRequest());
    }

    @Test
    void shouldReturnBadRequestWhenEquipmentTypeIsNull() throws Exception {
        String requestBody = """
                {
                  "code": "COMP-TEST-01",
                  "name": "Compressor de Teste",
                  "type": null,
                  "location": "Sala 1"
                }
                """;

        mockMvc.perform(post("/api/equipment")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().isBadRequest());
    }

    @Test
    void shouldCreateEquipmentWhenRequestIsValid() throws Exception {
        String requestBody = """
                {
                  "code": "COMP-TEST-02",
                  "name": "Compressor de Teste Válido",
                  "type": "COMPRESSOR",
                  "location": "Sala 2"
                }
                """;

        mockMvc.perform(post("/api/equipment")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").isNumber())
                .andExpect(jsonPath("$.code").value("COMP-TEST-02"))
                .andExpect(jsonPath("$.name").value("Compressor de Teste Válido"))
                .andExpect(jsonPath("$.type").value("COMPRESSOR"))
                .andExpect(jsonPath("$.status").value("STOPPED"))
                .andExpect(jsonPath("$.active").value(true))
                .andExpect(jsonPath("$.location").value("Sala 2"));
    }

    @Test
    void shouldAcceptCodeAtLimitAndRejectCodeAboveLimitWithoutPersistence() throws Exception {
        String codeAtLimit = "A".repeat(50);

        mockMvc.perform(post("/api/equipment")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(equipmentRequest(codeAtLimit, "Equipamento DEMO", "Local DEMO")))
                .andExpect(status().isOk());

        long countAfterValidRequest = equipmentRepository.count();
        String codeAboveLimit = "A".repeat(51);

        mockMvc.perform(post("/api/equipment")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(equipmentRequest(codeAboveLimit, "Equipamento DEMO", "Local DEMO")))
                .andExpect(status().isBadRequest());

        assertEquals(countAfterValidRequest, equipmentRepository.count());
    }

    @Test
    void shouldAcceptNameAtLimitAndRejectNameAboveLimitWithoutPersistence() throws Exception {
        String nameAtLimit = "A".repeat(100);

        mockMvc.perform(post("/api/equipment")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(equipmentRequest("DEMO-LIMIT-NAME-100", nameAtLimit, "Local DEMO")))
                .andExpect(status().isOk());

        long countAfterValidRequest = equipmentRepository.count();
        String nameAboveLimit = "A".repeat(101);

        mockMvc.perform(post("/api/equipment")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(equipmentRequest("DEMO-LIMIT-NAME-101", nameAboveLimit, "Local DEMO")))
                .andExpect(status().isBadRequest());

        assertEquals(countAfterValidRequest, equipmentRepository.count());
    }

    @Test
    void shouldAcceptLocationAtLimitAndRejectLocationAboveLimitWithoutPersistence() throws Exception {
        String locationAtLimit = "A".repeat(100);

        mockMvc.perform(post("/api/equipment")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(equipmentRequest("DEMO-LIMIT-LOCATION-100", "Equipamento DEMO", locationAtLimit)))
                .andExpect(status().isOk());

        long countAfterValidRequest = equipmentRepository.count();
        String locationAboveLimit = "A".repeat(101);

        mockMvc.perform(post("/api/equipment")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(equipmentRequest("DEMO-LIMIT-LOCATION-101", "Equipamento DEMO", locationAboveLimit)))
                .andExpect(status().isBadRequest());

        assertEquals(countAfterValidRequest, equipmentRepository.count());
    }

    @Test
    void shouldListEquipmentUsingPublicResponseContract() throws Exception {
        String requestBody = """
                {
                  "code": "COND-TEST-03",
                  "name": "Condensador de Teste",
                  "type": "CONDENSER",
                  "location": "Área Externa"
                }
                """;

        mockMvc.perform(post("/api/equipment")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().isOk());

        mockMvc.perform(get("/api/equipment"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$[*].id").isNotEmpty())
                .andExpect(jsonPath("$[*].code", hasItem("COND-TEST-03")))
                .andExpect(jsonPath("$[*].name", hasItem("Condensador de Teste")))
                .andExpect(jsonPath("$[*].type", hasItem("CONDENSER")))
                .andExpect(jsonPath("$[*].status", hasItem("STOPPED")))
                .andExpect(jsonPath("$[*].active", hasItem(true)))
                .andExpect(jsonPath("$[*].location", hasItem("Área Externa")));
    }

    private String equipmentRequest(String code, String name, String location) {
        return """
                {
                  "code": "%s",
                  "name": "%s",
                  "type": "COMPRESSOR",
                  "location": "%s"
                }
                """.formatted(code, name, location);
    }
}
