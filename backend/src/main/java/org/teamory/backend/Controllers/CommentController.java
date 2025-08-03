package org.teamory.backend.Controllers;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.Data;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.teamory.backend.DTOs.Requests.Create.CreateCommentDTO;
import org.teamory.backend.DTOs.Requests.Update.UpdateCommentDTO;
import org.teamory.backend.DTOs.Responses.CommentResponseDTO;
import org.teamory.backend.Services.Contracts.CommentInterface;

import java.util.UUID;

@RestController
@RequestMapping("/api/comments")
@Data
@CrossOrigin(origins = "*")
@Tag(name = "Comment Management", description = "APIs for managing comments")
public class CommentController {

    private final CommentInterface commentService;


    @Operation(summary = "Get all comments", description = "Returns all tasks comments")
    @GetMapping("/{taskId}")
    public ResponseEntity<Page<CommentResponseDTO>> getAllTaskComments(
            @PathVariable UUID taskId,
            Pageable pageable
    ) {
        Page<CommentResponseDTO> comments = commentService.getCommentsByTaskId(taskId, pageable);
        return ResponseEntity.ok(comments);
    }

    @Operation(summary = "Create comment", description = "Creates a new task comment")
    @PostMapping("/{taskId}/{userId}")
    public ResponseEntity<CommentResponseDTO> createTaskComment(
            @PathVariable UUID taskId,
            @PathVariable UUID userId,
            @RequestBody @Valid CreateCommentDTO commentDTO){
        return ResponseEntity.ok(commentService.addCommentToTask(taskId, userId, commentDTO));
    }

    @Operation(summary = "Update comment by id", description = "Updates comment by id")
    @PutMapping("/{id}")
    public ResponseEntity<CommentResponseDTO> update(
            @PathVariable UUID id,
            @RequestBody @Valid UpdateCommentDTO commentDTO
    ){

        CommentResponseDTO comment = commentService.updateComment(id, commentDTO);
        return ResponseEntity.ok(comment);

    }

    @Operation(summary = "Delete comment by id", description = "Delete comment by id")
    @DeleteMapping("/{taskId}/{commentId}")
    public ResponseEntity<String> delete(
            @PathVariable UUID taskId,
            @PathVariable UUID commentId
    ){
        Boolean deleted = commentService.deleteCommentFromTask(taskId, commentId);
        if (!deleted) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok("Comment deleted successfully");
    }



    @Operation(summary = "Get comment", description = "Get comment based on the id")
    @GetMapping("/show/{commentId}")
    public ResponseEntity<CommentResponseDTO> show(@PathVariable UUID commentId){
        CommentResponseDTO comment = commentService.getCommentById(commentId);
        return ResponseEntity.ok(comment);
    }


}