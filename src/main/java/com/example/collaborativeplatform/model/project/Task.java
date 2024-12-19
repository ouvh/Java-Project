package com.example.collaborativeplatform.model.project;

import jakarta.persistence.*;

import java.util.HashMap;
import java.util.Map;

import com.example.collaborativeplatform.model.user.*;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String description;

    @Temporal(TemporalType.DATE)
    private java.util.Date deadline;

    @Enumerated(EnumType.STRING)
    private TaskStatus status;

    @ManyToOne
    @JsonIgnore
    private Project project;

    @ManyToOne
    @JsonIgnore
    private Member assignedTo;

    // need to add the update status in the controller
    // pay attention for the Status Type (Enum)

    @JsonGetter("assignedToInfo")  // Only serialize the admin id
    public Map<String, Object> getAssignedToInfo() {
        Map<String, Object> info = new HashMap<>();
            info.put("id", assignedTo.getId());
            info.put("handle", assignedTo.getHandle());
            info.put("profileImage", assignedTo.getProfileImage());
        return info;
    }

    @JsonGetter("projectId")  // Only serialize the admin id
    public Long getProjectId() {
        return project.getId();
    }




}
