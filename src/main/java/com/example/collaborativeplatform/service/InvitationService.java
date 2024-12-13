package com.example.collaborativeplatform.service;

import com.example.collaborativeplatform.model.invitation.Invitation;
import com.example.collaborativeplatform.model.project.Project;
import com.example.collaborativeplatform.model.user.Member;
import com.example.collaborativeplatform.model.user.User;
import com.example.collaborativeplatform.repository.InvitationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InvitationService {

    @Autowired
    private InvitationRepository invitationRepository;

    // Send an invitation
    public Invitation sendInvitation(Member sender, User recipient, Project project) {
        Invitation invitation = new Invitation();
        invitation.setSender(sender);
        invitation.setRecipient(recipient);
        invitation.setProject(project);
        invitation.setAccepted(false); // Default to not accepted
        return invitationRepository.save(invitation);
    }

    // Get invitations sent by an admin
    public List<Invitation> getInvitationsBySender(Member sender) {
        return invitationRepository.findBySender(sender);
    }

    // Get invitations received by a user
    public List<Invitation> getInvitationsByRecipient(User recipient) {
        return invitationRepository.findByRecipient(recipient);
    }
}
