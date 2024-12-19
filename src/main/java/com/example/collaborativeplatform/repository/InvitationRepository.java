package com.example.collaborativeplatform.repository;

import com.example.collaborativeplatform.model.invitation.Invitation;
import com.example.collaborativeplatform.model.project.Project;
import com.example.collaborativeplatform.model.user.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface InvitationRepository extends JpaRepository<Invitation, Long> {
    List<Invitation> findBySender(Member sender);
    List<Invitation> findByRecipient(Member recipient);
    List<Invitation> findByProject(Project project);
    Optional<Invitation> findById(Long id);
}
