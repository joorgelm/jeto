package br.com.joorgelm.jeto.adapter.config;

import net.sourceforge.tess4j.Tesseract;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TesseractConfig {

    @Bean
    public Tesseract buildInstance() {
        var tesseract = new Tesseract();
        tesseract.setPageSegMode(1);
        tesseract.setLanguage("por");

        return tesseract;
    }
}
