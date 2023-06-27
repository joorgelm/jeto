package br.com.joorgelm.jeto.service;

import lombok.AllArgsConstructor;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.util.unit.DataSize;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

@Service
@AllArgsConstructor
public class FileService implements IFileSerice {

    private IFileConverterService fileConverterService;

    private final Queue<MultipartFile> fileQueue = new ConcurrentLinkedQueue<>();

    public void addFileToQueue(@NonNull MultipartFile file) {
        validateSize(file);
        fileQueue.add(file);
    }

    public File popFileFromQueue() throws IOException {
        Optional<MultipartFile> optionalMultipartFile = Optional.ofNullable(fileQueue.poll());

        return fileConverterService.toFile(
                optionalMultipartFile.orElseThrow(FileNotFoundException::new)
        );
    }

    public List<MultipartFile> getReadOnlyFilesInQueue() {
        return fileQueue.stream().toList();
    }

    private void validateSize(MultipartFile file) {
        DataSize kilobytes = DataSize.ofKilobytes(100);
        if (DataSize.ofBytes(file.getSize()).compareTo(kilobytes) > 0) {
            throw new MaxUploadSizeExceededException(kilobytes.toBytes());
        }
    }
}
