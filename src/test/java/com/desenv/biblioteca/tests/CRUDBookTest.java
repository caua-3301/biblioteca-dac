package com.desenv.biblioteca.tests;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.springframework.http.MediaType;

@SpringBootTest
@AutoConfigureMockMvc
public class CRUDBookTest {

    @Autowired
    private MockMvc mockMvc;

    private final String BASE_URL = "http://localhost:8080/book";

    @Test
    void mustCreateNew() throws Exception {

        String jsonRequest = """
        {
            "title": "Domain Driven Design",
            "author": "Eric Evans",
            "ISBN": "978-0-321-12521-7",
            "publicationDate": "2004-08-30",
            "inStock": 15
        }
        """;

        mockMvc.perform(post(BASE_URL + "/save")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonRequest))
                .andExpect(status().isCreated());
    }

    @Test
    void mustBeGetById() throws Exception {

        mockMvc.perform(get(BASE_URL + "/find/id/1")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].title").isNotEmpty())
                .andExpect(jsonPath("$[0].author").isNotEmpty());
    }

    @Test
    void mustbeUpdateBook() throws Exception {

        String updateJson = """
        {
            "title": "Clean Code (Revisado)",
            "author": "Robert C. Martin",
            "published": "2008-08-01",
            "inStock": 10
        }
        """;

        mockMvc.perform(put(BASE_URL + "/update/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(updateJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].title").value("Clean Code (Revisado)"));
    }

    @Test
    void mustBeRemoveBook() throws Exception {

        mockMvc.perform(delete(BASE_URL + "/delete/1"))
                .andExpect(status().isOk());
    }

}
