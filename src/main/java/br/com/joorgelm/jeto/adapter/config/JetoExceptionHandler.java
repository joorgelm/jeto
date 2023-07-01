package br.com.joorgelm.jeto.adapter.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jmx.export.metadata.InvalidMetadataException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.multipart.MaxUploadSizeExceededException;

@RestControllerAdvice
public class JetoExceptionHandler {

    Logger logger = LoggerFactory.getLogger(JetoExceptionHandler.class);

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleException(Exception ex) {

        logger.error(ex.getMessage());

        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Ocorreu um erro no servidor");
    }

    @ExceptionHandler(MaxUploadSizeExceededException.class)
    public ResponseEntity<String> handleException(MaxUploadSizeExceededException ex) {

        logger.error(ex.getMessage());

        return ResponseEntity
                .status(HttpStatus.PAYLOAD_TOO_LARGE)
                .body("por enquanto estamos aceitando apenas arquivos de no máximo 2Mb");
    }

    @ExceptionHandler(InvalidMetadataException.class)
    public ResponseEntity<String> handleException(InvalidMetadataException ex) {

        logger.error(ex.getMessage());

        return ResponseEntity
                .status(HttpStatus.PAYLOAD_TOO_LARGE)
                .body("formatos aceitos: tiff, jpeg, jpg, png, bmp, pdf");
    }
}
