package br.com.joorgelm.jeto.usecase;

import br.com.joorgelm.jeto.service.IFileSerice;
import br.com.joorgelm.jeto.service.ITesseractServce;
import lombok.AllArgsConstructor;
import net.sourceforge.tess4j.TesseractException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
@AllArgsConstructor
public class ExtractTextUsecase {

    private ITesseractServce tesseractService;
    private IFileSerice fileService;

    public String extract(MultipartFile file) throws TesseractException, IOException {
        fileService.addFileToQueue(file);
        return tesseractService.doExtraction();
    }
}
