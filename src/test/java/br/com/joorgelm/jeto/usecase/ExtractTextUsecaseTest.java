package br.com.joorgelm.jeto.usecase;

import br.com.joorgelm.jeto.service.IFileSerice;
import br.com.joorgelm.jeto.service.ITesseractServce;
import net.sourceforge.tess4j.TesseractException;
import org.instancio.Instancio;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class ExtractTextUsecaseTest {

    @Mock
    private ITesseractServce tesseractService;

    @Mock
    private IFileSerice fileService;

    private ExtractTextUsecase extractTextUsecase;

    @BeforeEach
    void setUp() {
        extractTextUsecase = new ExtractTextUsecase(tesseractService, fileService);
    }

    @Test
    void should_ExtractFromFile() throws TesseractException, IOException {
        MultipartFile mockFile = Instancio.of(MultipartFile.class).create();

        extractTextUsecase.extract(mockFile);

        verify(fileService, times(1))
                .addFileToQueue(mockFile);

        verify(tesseractService, times(1))
                .doExtraction();
    }
}