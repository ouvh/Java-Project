package com.example.collaborativeplatform.model.project;

import jakarta.persistence.*;

import com.example.collaborativeplatform.model.chat.ChatGroup;
import com.example.collaborativeplatform.model.invitation.Invitation;
import com.example.collaborativeplatform.model.user.*;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
@Getter
@Setter
@Entity
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String type;
    
    @JsonIgnore
    @ManyToOne
    private Member admin;

    @OneToOne
    private ChatGroup chatGroup;

    @ManyToMany
    @JsonIgnore
    private List<Member> members = new ArrayList<>();


    @OneToMany(mappedBy = "project", cascade = CascadeType.ALL)
    private List<Task> tasks = new ArrayList<>();


    @OneToMany(mappedBy = "project", cascade = CascadeType.ALL)
    private List<File> files = new ArrayList<>();

    
    @OneToMany(mappedBy = "project")
    private List<Invitation> invitations = new ArrayList<>();


    @JsonGetter("members")
    public List<Map<String, Object>> getMembersDetails() {
        return members.stream().map(member -> {
            Map<String, Object> memberDetails = new HashMap<>();
            memberDetails.put("id", member.getId());
            memberDetails.put("handle", member.getHandle());
            memberDetails.put("profileImage", member.getProfileImage());
            return memberDetails;
        }).collect(Collectors.toList());
    }



    @JsonGetter("adminInfo")  // Only serialize the admin id
    public Map<String, Object> getAdminInfo() {
        Map<String, Object> info = new HashMap<>();
            info.put("id", admin.getId());
            info.put("handle", admin.getHandle());
            info.put("profileImage", admin.getProfileImage());
        return info;
    }



    @JsonGetter("invitations")
    public List<Map<String, Object>> getInvitationsDetails() {
        return invitations.stream().map(invitation -> {
            Map<String, Object> invitationDetails = new HashMap<>();

            // Recipient details
            if (invitation.getRecipient() != null) {
                Map<String, Object> recipientDetails = new HashMap<>();
                recipientDetails.put("id", invitation.getRecipient().getId());
                recipientDetails.put("handle", invitation.getRecipient().getHandle());
                recipientDetails.put("profileImage", invitation.getRecipient().getProfileImage());
                invitationDetails.put("recipient", recipientDetails);
            }

            // Sender details
            if (invitation.getSender() != null) {
                Map<String, Object> senderDetails = new HashMap<>();
                senderDetails.put("id", invitation.getSender().getId());
                senderDetails.put("handle", invitation.getSender().getHandle());
                senderDetails.put("profileImage", invitation.getSender().getProfileImage());
                invitationDetails.put("sender", senderDetails);
            }

            return invitationDetails;
        }).collect(Collectors.toList());
    }







}
