package com.example.collaborativeplatform.controller;

import com.example.collaborativeplatform.model.invitation.Invitation;
import com.example.collaborativeplatform.model.project.Project;
import com.example.collaborativeplatform.model.user.Member;
import com.example.collaborativeplatform.service.InvitationService;
import com.example.collaborativeplatform.service.ProjectService;
import com.example.collaborativeplatform.service.UserService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/invitations")
public class InvitationController {

    private final InvitationService invitationService;
    private final UserService userService;
    private final ProjectService projectService;

    public InvitationController(InvitationService invitationService,UserService userService,ProjectService projectService) {
        this.invitationService = invitationService;
        this.userService = userService;
        this.projectService = projectService;
    }
    // you stopped here the controllers testing
    // Send an invitation

    // OK
    @PostMapping("/send")
    public ResponseEntity<?> sendInvitation(@RequestParam Long senderId, @RequestParam Long recipientId, @RequestParam Long projectId) {


        // Validate sender existence
        Member sender = userService.getUserById(senderId).orElse(null);
        if (sender == null) {
            return ResponseEntity.badRequest().body("sender with ID " + senderId + " not found");
        }

         // Validate recipient existence
         Member recipient = userService.getUserById(recipientId).orElse(null);
         if (recipient == null) {
             return ResponseEntity.badRequest().body("recipient with ID " + recipientId + " not found");
         }

          // Validate project existence
          Project project = projectService.findById(projectId).orElse(null);
          if (project == null) {
              return ResponseEntity.badRequest().body("project with ID " + projectId + " not found");
          }



        Invitation invitation = invitationService.sendInvitation(sender, recipient, project);
        return ResponseEntity.ok(invitation);
    }

    // accept invitation is missing
    // OK
    @PostMapping("/accept")
    public ResponseEntity<String> acceptInvitation(@RequestParam Long invitationId) {

        // find the invitation 
        Invitation invitation = invitationService.findById(invitationId).orElse(null); // Find the invitation
        if (invitation == null) {
            return ResponseEntity.badRequest().body("Invitation not found");
        }

        // Add member to project and delete the invitation
        invitationService.acceptInvitation(invitation);

        return ResponseEntity.ok("Invitation accepted and member added to the project");
    }

}
