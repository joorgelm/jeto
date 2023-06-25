package br.com.joorgelm.jeto.service;

import org.springframework.lang.NonNull;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;

public interface IFileSerice {
    void addFileToQueue (@NonNull MultipartFile file);

    File popFileFromQueue() throws IOException;

    List<MultipartFile> getReadOnlyFilesInQueue();
}
