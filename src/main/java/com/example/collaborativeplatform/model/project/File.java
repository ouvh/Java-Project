package com.example.collaborativeplatform.model.project;

import jakarta.persistence.*;
import com.example.collaborativeplatform.model.user.Member;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class File {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String fileName;
    private String storageUrl;

    @ManyToOne
    private Project project;

    @ManyToOne
    private Member uploadedBy;

}
