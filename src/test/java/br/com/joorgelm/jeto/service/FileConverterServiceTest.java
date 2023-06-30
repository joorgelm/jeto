package br.com.joorgelm.jeto.service;

import br.com.joorgelm.jeto.domain.service.FileConverterService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class FileConverterServiceTest {

    @Captor
    private ArgumentCaptor<File> fileArgumentCaptor;

    private FileConverterService fileConverterService;

    @BeforeEach
    void setUp() {
        fileConverterService = new FileConverterService();
    }

    @Test
    void should_createFile() throws IOException {

        MultipartFile mockMultipartFile = mock(MultipartFile.class);
        when(mockMultipartFile.getOriginalFilename()).thenReturn("test.pdf");

        File file = fileConverterService.toFile(mockMultipartFile);

        verify(mockMultipartFile, times(1))
                .transferTo(fileArgumentCaptor.capture());

        assertEquals(fileArgumentCaptor.getValue().getName(), file.getName());
    }
}