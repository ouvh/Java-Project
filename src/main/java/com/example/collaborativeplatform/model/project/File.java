package com.example.collaborativeplatform.model.project;

import jakarta.persistence.*;

import java.util.HashMap;
import java.util.Map;

import com.example.collaborativeplatform.model.user.Member;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnore;

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

    @Temporal(TemporalType.DATE)
    private java.util.Date createdAt;

    @ManyToOne
    @JsonIgnore
    private Project project;

    @ManyToOne
    @JsonIgnore
    private Member uploadedBy;


    @JsonGetter("uploadedByInfo")  // Only serialize the sender id
    public Map<String, Object> getUploadedByInfo() {
        Map<String, Object> info = new HashMap<>();
            info.put("id", uploadedBy.getId());
            info.put("handle", uploadedBy.getHandle());
            info.put("profileImage", uploadedBy.getProfileImage());
        return info;
    }

    @JsonGetter("projectId")  // Only serialize the sender id
    Long getProjectId() {
        return project.getId();
    }

}
