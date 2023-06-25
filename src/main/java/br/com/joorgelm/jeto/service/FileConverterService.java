package br.com.joorgelm.jeto.service;

import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Objects;

@Service
public class FileConverterService implements IFileConverterService {

    private String tempDirPath;

    public File toFile(@NonNull MultipartFile multipartFile) throws IOException {
        File file = new File(getTempDirPath() + File.separator + multipartFile.getOriginalFilename());
        multipartFile.transferTo(file);
        return file;
    }

    private String getTempDirPath() throws IOException {
        if (Objects.isNull(tempDirPath)) {
            tempDirPath = Files.createTempDirectory("temp")
                    .toFile()
                    .getAbsolutePath();
        }

        return tempDirPath;
    }
}
