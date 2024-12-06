package com.example.collaborativeplatform.service;

import com.example.collaborativeplatform.model.project.File;
import com.example.collaborativeplatform.model.project.Project;
import com.example.collaborativeplatform.model.user.Member;
import com.example.collaborativeplatform.repository.FileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FileService {

    @Autowired
    private FileRepository fileRepository;

    // Upload a file
    public File uploadFile(File file, Project project, Member member) {
        file.setProject(project);
        file.setUploadedBy(member);
        return fileRepository.save(file);
    }

    // Get files by project
    public List<File> getFilesByProject(Project project) {
        return fileRepository.findByProject(project);
    }

    // Get files uploaded by a specific member
    public List<File> getFilesByMember(Member member) {
        return fileRepository.findByUploadedBy(member);
    }
}
