package dev.joaov.refrigops.controller;

import dev.joaov.refrigops.TestcontainersConfiguration;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Import(TestcontainersConfiguration.class)
class EquipmentControllerTest {

    @Autowired
    private MockMvc mockMvc;

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
                .andExpect(status().isOk());
    }
}
