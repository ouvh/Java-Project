package com.example.collaborativeplatform.model.chat;

import jakarta.persistence.*;
import com.example.collaborativeplatform.model.project.Project;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
public class ChatGroup {
    @Getter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JsonIgnore
    private Project project;

    @OneToMany(mappedBy = "chatGroup", cascade = CascadeType.ALL)
    private List<Message> messages = new ArrayList<>();


    
    @JsonGetter("projectId")  // Only serialize the project id
    public Long getProjectId() {
        return project.getId();
    }




}
