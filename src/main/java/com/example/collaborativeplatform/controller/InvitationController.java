package com.example.collaborativeplatform.controller;

import com.example.collaborativeplatform.model.invitation.Invitation;
import com.example.collaborativeplatform.model.project.Project;
import com.example.collaborativeplatform.model.user.Member;
import com.example.collaborativeplatform.model.user.User;
import com.example.collaborativeplatform.service.InvitationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/invitations")
public class InvitationController {

    private final InvitationService invitationService;

    public InvitationController(InvitationService invitationService) {
        this.invitationService = invitationService;
    }

    // Send an invitation
    @PostMapping("/send")
    public ResponseEntity<Invitation> sendInvitation(@RequestParam Long adminId, @RequestParam Long recipientId, @RequestParam Long projectId) {
        Member sender = new Member();
        sender.setId(adminId); // Simulate finding the admin
        User recipient = new Member();
        recipient.setId(recipientId); // Simulate finding the recipient
        Project project = new Project();
        project.setId(projectId); // Simulate finding the project
        Invitation invitation = invitationService.sendInvitation(sender, recipient, project);
        return ResponseEntity.ok(invitation);
    }
}
