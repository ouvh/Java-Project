package com.example.collaborativeplatform.model.user;

import jakarta.persistence.*;

import com.example.collaborativeplatform.model.invitation.Invitation;
import com.example.collaborativeplatform.model.project.*;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
@Getter
@Setter
@Entity
public class Member extends User {
    @ManyToMany(mappedBy = "members")
    @JsonIgnore
    private List<Project> joinedProjects = new ArrayList<>();

    @OneToMany(mappedBy = "admin", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Project> createdProjects = new ArrayList<>();


   
    // List of invitations received by this member
    @OneToMany(mappedBy = "recipient")
    private List<Invitation> receivedInvitations = new ArrayList<>();



    public List<Invitation> getReceivedInvitations() {
        return receivedInvitations;
    }

    public void setReceivedInvitations(List<Invitation> receivedInvitations) {
        this.receivedInvitations = receivedInvitations;
    }



    public List<Project> getJoinedProjects() {
        return joinedProjects;
    }

    public void setJoinedProjects(List<Project> joinedProjects) {
        this.joinedProjects = joinedProjects;
    }

    public List<Project> getCreatedProjects() {
        return createdProjects;
    }

    public void setCreatedProjects(List<Project> createdProjects) {
        this.createdProjects = createdProjects;
    }


    @JsonGetter("createdProjectsIds")  // Only serialize the list of project IDs
    public List<Long> getCreatedProjectsIds() {
        return createdProjects.stream()
                            .map(Project::getId)
                            .collect(Collectors.toList());
    }


    @JsonGetter("joinedProjectsIds")  // Only serialize the list of project IDs
    public List<Long> getJoinedProjectsIds() {
        return joinedProjects.stream()
                            .map(Project::getId)
                            .collect(Collectors.toList());
    }

  
  
}

















































































