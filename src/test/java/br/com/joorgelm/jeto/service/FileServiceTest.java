package br.com.joorgelm.jeto.service;

import br.com.joorgelm.jeto.domain.service.FileConverterService;
import br.com.joorgelm.jeto.domain.service.FileService;
import org.assertj.core.util.Lists;
import org.instancio.Instancio;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class FileServiceTest {

    @Mock
    private FileConverterService fileConverterService;
    private FileService fileService;

    @BeforeEach
    void setUp() {
        fileService = new FileService(fileConverterService);
    }

    @Test
    void should_addFileToQueue() {
        MultipartFile mockMultipartFile = mock(MultipartFile.class);
        when(mockMultipartFile.getOriginalFilename()).thenReturn("teste.pdf");

        when(mockMultipartFile.getSize()).thenReturn(100L);

        fileService.addFileToQueue(mockMultipartFile);

        assertEquals(mockMultipartFile, fileService.getReadOnlyFilesInQueue().get(0));
    }

    @Test
    void should_popFileFromQueue() throws IOException {
        MultipartFile mockMultipartFile = mock(MultipartFile.class);

        when(mockMultipartFile.getSize()).thenReturn(100L);
        when(mockMultipartFile.getOriginalFilename()).thenReturn("teste.pdf");

        fileService.addFileToQueue(mockMultipartFile);
        File mockFile = Instancio.of(File.class).create();

        when(fileConverterService.toFile(mockMultipartFile))
                .thenReturn(mockFile);

        File file = fileService.popFileFromQueue();
        assertEquals(mockFile, file);
    }

    @Test
    void should_returnListOfQueueFiles() {
        MultipartFile mockMultipartFile = mock(MultipartFile.class);
        when(mockMultipartFile.getOriginalFilename()).thenReturn("teste.pdf");
        when(mockMultipartFile.getSize()).thenReturn(100L);

        fileService.addFileToQueue(mockMultipartFile);

        List<MultipartFile> filesInQueue = fileService.getReadOnlyFilesInQueue();
        assertEquals(Lists.list(mockMultipartFile), filesInQueue);
    }

    @Test
    void should_returnEmptyListOfQueueFiles() {
        List<MultipartFile> filesInQueue = fileService.getReadOnlyFilesInQueue();
        assertTrue(filesInQueue.isEmpty());
    }

    @Test
    void should_fail_whenQueueIsEmpty() {
        Assertions.assertThrows(
                FileNotFoundException.class, () -> fileService.popFileFromQueue()
        );
    }
}
