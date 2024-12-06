package com.example.collaborativeplatform.repository;

import com.example.collaborativeplatform.model.project.File;
import com.example.collaborativeplatform.model.project.Project;
import com.example.collaborativeplatform.model.user.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FileRepository extends JpaRepository<File, Long> {
    List<File> findByProject(Project project);
    List<File> findByUploadedBy(Member member);
}
