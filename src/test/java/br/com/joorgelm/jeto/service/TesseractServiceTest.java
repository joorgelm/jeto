package br.com.joorgelm.jeto.service;

import br.com.joorgelm.jeto.domain.service.IFileSerice;
import br.com.joorgelm.jeto.domain.service.TesseractService;
import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.TesseractException;
import org.instancio.Instancio;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TesseractServiceTest {

    @Mock
    private ITesseract tesseract;
    @Mock
    private IFileSerice fileSerice;

    @Captor
    private ArgumentCaptor<File> fileArgumentCaptor;

    private TesseractService tesseractService;

    @BeforeEach
    void setUp() {
        tesseractService = new TesseractService(tesseract, fileSerice);
    }

    @Test
    void should_DoExtraction() throws TesseractException, IOException {
        File mockFile = Instancio.of(File.class).create();
        when(fileSerice.popFileFromQueue()).thenReturn(mockFile);

        tesseractService.doExtraction();

        verify(tesseract, times(1))
                .doOCR(fileArgumentCaptor.capture());

        assertEquals(mockFile, fileArgumentCaptor.getValue());
    }
}