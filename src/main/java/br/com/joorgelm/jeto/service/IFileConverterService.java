package br.com.joorgelm.jeto.service;

import org.springframework.lang.NonNull;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

public interface IFileConverterService {

    File toFile(@NonNull MultipartFile multipartFile) throws IOException;
}
