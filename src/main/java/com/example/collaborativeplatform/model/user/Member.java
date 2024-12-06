package com.example.collaborativeplatform.model.user;

import jakarta.persistence.*;
import com.example.collaborativeplatform.model.project.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
@Getter
@Setter
@Entity
public class Member extends User {
    @ManyToMany(mappedBy = "members")
    private List<Project> joinedProjects = new ArrayList<>();

    @OneToMany(mappedBy = "assignedTo")
    private List<Task> assignedTasks = new ArrayList<>();

    public List<Project> getJoinedProjects() {
        return joinedProjects;
    }

    public void setJoinedProjects(List<Project> joinedProjects) {
        this.joinedProjects = joinedProjects;
    }

    public List<Task> getAssignedTasks() {
        return assignedTasks;
    }

    public void setAssignedTasks(List<Task> assignedTasks) {
        this.assignedTasks = assignedTasks;
    }
}

















































































