package com.example.collaborativeplatform.model.invitation;

import java.util.HashMap;
import java.util.Map;

import com.example.collaborativeplatform.model.project.Project;
import com.example.collaborativeplatform.model.user.Member;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Invitation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "sender_id", nullable = false) // Maps to sender's primary key
    private Member sender;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "recipient_id", nullable = false) // Maps to recipient's primary key
    private Member recipient;




    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "project_id", nullable = false) // Maps to project's primary key
    private Project project;


    
    @JsonGetter("senderInfo")  // Only serialize the sender id
    public Map<String, Object> getSenderInfo() {
        Map<String, Object> info = new HashMap<>();
            info.put("id", sender.getId());
            info.put("handle", sender.getHandle());
            info.put("profileImage", sender.getProfileImage());
        return info;
    }


    @JsonGetter("recipientInfo")  // Only serialize the sender id
    public Map<String, Object> getRecipientInfo() {
        Map<String, Object> info = new HashMap<>();
            info.put("id", sender.getId());
            info.put("handle", sender.getHandle());
            info.put("profileImage", sender.getProfileImage());
        return info;
    }


    @JsonGetter("projectId")  // Only serialize the sender id
    Long getProjectId() {
        return project.getId();
    }



    // Constructors
    public Invitation() {
    }

    public Invitation(Member sender, Member recipient, Project project) {
        this.sender = sender;
        this.recipient = recipient;
        this.project = project;
    }

}
