package org.teamory.backend.Repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.teamory.backend.DTOs.Responses.CommentResponseDTO;
import org.teamory.backend.Entities.Comment;
import org.teamory.backend.Entities.User;

import java.util.Optional;
import java.util.UUID;


public interface CommentRepository extends JpaRepository<Comment, UUID> {

    Page<Comment> findByTaskId(UUID taskId, Pageable pageable);
}
