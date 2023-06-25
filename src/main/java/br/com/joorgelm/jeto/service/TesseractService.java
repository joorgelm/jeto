package br.com.joorgelm.jeto.service;

import lombok.AllArgsConstructor;
import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.TesseractException;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
@AllArgsConstructor
public class TesseractService implements ITesseractServce {
    private final ITesseract tesseract;
    private final IFileSerice fileSerice;

    public String doExtraction() throws TesseractException, IOException {
        return tesseract.doOCR(fileSerice.popFileFromQueue());
    }
}
