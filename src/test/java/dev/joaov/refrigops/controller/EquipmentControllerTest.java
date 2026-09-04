package dev.joaov.refrigops.controller;

import dev.joaov.refrigops.TestcontainersConfiguration;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.hasItem;
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
}
