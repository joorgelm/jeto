package br.com.joorgelm.jeto.config;

import net.sourceforge.tess4j.Tesseract;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TesseractConfig {

    @Bean
    public Tesseract buildInstance() {
        var tesseract = new Tesseract();
        tesseract.setLanguage("por");
        tesseract.setVariable("user_defined_dpi", "96");

        return tesseract;
    }
}
