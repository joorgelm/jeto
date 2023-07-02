package br.com.joorgelm.jeto.adapter.controller;

import br.com.joorgelm.jeto.application.usecase.ExtractTextUsecase;
import lombok.AllArgsConstructor;
import net.sourceforge.tess4j.TesseractException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Controller
@AllArgsConstructor
@RequestMapping(value = "/v1/jeto")
public class JetoRestController {

    private ExtractTextUsecase extractTextUsecase;

    @GetMapping
    public ResponseEntity<String> check() {
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping(value = "/extract")
    public ResponseEntity<String> extract(
            @RequestParam("file") MultipartFile file
    ) throws TesseractException, IOException {
        String extractedContent = extractTextUsecase.extract(file);
        return new ResponseEntity<>(extractedContent, HttpStatus.OK);
    }
}
