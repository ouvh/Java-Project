package com.example.collaborativeplatform.model.invitation;

import com.example.collaborativeplatform.model.project.Project;
import com.example.collaborativeplatform.model.user.Admin;
import com.example.collaborativeplatform.model.user.User;
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
    @JoinColumn(name = "sender_id", nullable = false) // Maps to sender's primary key
    private Admin sender;

    @ManyToOne
    @JoinColumn(name = "recipient_id", nullable = false) // Maps to recipient's primary key
    private User recipient;

    @ManyToOne
    @JoinColumn(name = "project_id", nullable = false) // Maps to project's primary key
    private Project project;

    private boolean accepted;

    // Constructors
    public Invitation() {
    }

    public Invitation(Admin sender, User recipient, Project project) {
        this.sender = sender;
        this.recipient = recipient;
        this.project = project;
        this.accepted = false; // Default to not accepted
    }

}
