package hr.tvz.iskra.studapp.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import hr.tvz.iskra.studapp.command.StudentCommand;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;

import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.security.test.web.servlet.request.
        SecurityMockMvcRequestPostProcessors.user;
import static
        org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class StudentRestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void getAllStudents() throws Exception {
        this.mockMvc.perform(
                        get("/students")
                                .with(user("admin")
                                        .password("test")
                                        .roles("ADMIN")
                                )
                                .with(csrf())
                )
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(2)));
    }

    @Test
    void getStudentByJMBAG() throws Exception {
        String TEST_JMBAG = "0123456789";
        String TEST_FIRST_NAME = "Niko";
        String TEST_LAST_NAME = "Iskra";

        this.mockMvc.perform(
                        get("/students/" + TEST_JMBAG)
                                .with(user("admin")
                                        .password("test")
                                        .roles("ADMIN")
                                )
                                .with(csrf())
                )
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.jmbag").value(TEST_JMBAG))
                .andExpect(jsonPath("$.firstName").value(TEST_FIRST_NAME))
                .andExpect(jsonPath("$.lastName").value(TEST_LAST_NAME));
    }

    @DirtiesContext
    @Test
    void save() throws Exception {
        String TEST_JMBAG = "0123456789";
        String TEST_FIRST_NAME = "Niko";
        String TEST_LAST_NAME = "Iskra";
        LocalDate TEST_DATE_OF_BIRTH = LocalDate.now().minusYears(21);
        Integer TEST_NUMBER_OF_ECTS = 120;

        StudentCommand studentCommand = new StudentCommand(
                TEST_JMBAG,
                TEST_FIRST_NAME,
                TEST_LAST_NAME,
                TEST_DATE_OF_BIRTH,
                TEST_NUMBER_OF_ECTS
        );

        this.mockMvc.perform(
                        post("/students")
                                .with(user("admin")
                                        .password("test")
                                        .roles("ADMIN")
                                )
                                .with(csrf())
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(studentCommand))
                                .accept(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.jmbag").value(TEST_JMBAG))
                .andExpect(jsonPath("$.firstName").value(TEST_FIRST_NAME))
                .andExpect(jsonPath("$.lastName").value(TEST_LAST_NAME));
    }
}