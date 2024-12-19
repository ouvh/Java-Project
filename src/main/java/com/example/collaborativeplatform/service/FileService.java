package com.example.collaborativeplatform.service;

import com.example.collaborativeplatform.model.project.File;
import com.example.collaborativeplatform.repository.FileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FileService {

    @Autowired
    private FileRepository fileRepository;

    // Save a file
    public File saveFile(File file) {
        return fileRepository.save(file);
    }

    // Get a file by its ID
    public File getFileById(Long fileId) {
        return fileRepository.findById(fileId)
                .orElseThrow(() -> new IllegalArgumentException("File with ID " + fileId + " not found"));
    }
}
