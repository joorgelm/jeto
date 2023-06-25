package br.com.joorgelm.jeto.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMultipartHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.io.IOException;

@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:application-test.properties")
class JetoRestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void shouldExtractText() throws Exception {

        MockMultipartHttpServletRequestBuilder request = MockMvcRequestBuilders
                .multipart("/v1/jeto/extract")
                .file(getMockMultipartFile());

        mockMvc.perform(request)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("content test\n"));
    }

    private Resource getResource() {
       return new ClassPathResource("./test.pdf");
    }

    private MockMultipartFile getMockMultipartFile() throws IOException {
        return new MockMultipartFile(
                "file",
                "test.pdf",
                MediaType.MULTIPART_FORM_DATA_VALUE,
                getResource().getInputStream().readAllBytes()
        );
    }
}