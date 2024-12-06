package com.example.collaborativeplatform.repository;

import com.example.collaborativeplatform.model.invitation.Invitation;
import com.example.collaborativeplatform.model.project.Project;
import com.example.collaborativeplatform.model.user.Admin;
import com.example.collaborativeplatform.model.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InvitationRepository extends JpaRepository<Invitation, Long> {
    List<Invitation> findBySender(Admin sender);
    List<Invitation> findByRecipient(User recipient);
    List<Invitation> findByProject(Project project);
}
