package com.example.collaborativeplatform.service;

import com.example.collaborativeplatform.model.invitation.Invitation;
import com.example.collaborativeplatform.model.project.Project;
import com.example.collaborativeplatform.model.user.Member;
import com.example.collaborativeplatform.repository.InvitationRepository;
import com.example.collaborativeplatform.repository.ProjectRepository;

import jakarta.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InvitationService {

    @Autowired
    private InvitationRepository invitationRepository;

    @Autowired
    private ProjectRepository projectRepository;



    // Send an invitation
    public Invitation sendInvitation(Member sender, Member recipient, Project project) {
        Invitation invitation = new Invitation();
        invitation.setSender(sender);
        invitation.setRecipient(recipient);
        invitation.setProject(project);
        return invitationRepository.save(invitation);
    }


    // Get invitations sent by an admin
    public List<Invitation> getInvitationsBySender(Member sender) {
        return invitationRepository.findBySender(sender);
    }

    // Get invitations received by a user
    public List<Invitation> getInvitationsByRecipient(Member recipient) {
        return invitationRepository.findByRecipient(recipient);
    }

    public Optional<Invitation> findById(Long id){
        return invitationRepository.findById(id);
    }

    @Transactional
    public void acceptInvitation(Invitation invitation) {
        // Fetch the project and the recipient
        Project project = invitation.getProject();
        Member recipient = invitation.getRecipient();

        // Add recipient to the project
        project.getMembers().add(recipient);
        projectRepository.save(project);  // Save the updated project

        // Delete the invitation
        invitationRepository.delete(invitation);  // Delete the invitation from the database
    }



}
