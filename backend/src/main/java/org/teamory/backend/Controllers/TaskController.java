package org.teamory.backend.Controllers;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.Data;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.teamory.backend.DTOs.Requests.Create.CreateTaskDTO;
import org.teamory.backend.DTOs.Requests.Update.UpdateTaskDTO;
import org.teamory.backend.DTOs.Responses.TaskResponseDTO;
import org.teamory.backend.Services.Contracts.TaskInterface;

import java.util.UUID;

@RestController
@RequestMapping("/api/tasks")
@Data
@CrossOrigin(origins = "*")
@Tag(name = "Task Management", description = "APIs for managing tasks")
@SecurityRequirement(name = "bearerAuth")
public class TaskController {

    private final TaskInterface taskService;

    @Operation(summary = "Get all tasks", description = "Returns all tasks")
    @GetMapping
    public ResponseEntity<Page<TaskResponseDTO>> all(Pageable pageable){
        Page<TaskResponseDTO> tasks =  taskService.getAllTasks(pageable);
        return ResponseEntity.ok(tasks);
    }

    @Operation(summary = "Get task by id", description = "Returns task by id")
    @GetMapping("/{id}")
    public ResponseEntity<TaskResponseDTO> show(@PathVariable UUID id) {
        TaskResponseDTO taskDto = taskService.getTaskById(id);

        if (taskDto == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(taskDto);
    }

    @Operation(summary = "Create task", description = "Creates a new task")
    @PostMapping
    public ResponseEntity<TaskResponseDTO> create(@RequestBody @Valid CreateTaskDTO taskDTO){
        return ResponseEntity.ok(taskService.createTask(taskDTO));
    }

    @Operation(summary = "Update task by id", description = "Updates task by id")
    @PutMapping("/{id}")
    public ResponseEntity<TaskResponseDTO> update(
            @PathVariable UUID id,
            @RequestBody @Valid UpdateTaskDTO taskDTO
    ){

        TaskResponseDTO task = taskService.updateTask(id, taskDTO);
        return ResponseEntity.ok(task);

    }

    @Operation(summary = "Delete task by id", description = "Deletes task by id")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable UUID id){
        Boolean deleted = taskService.deleteTask(id);
        if (!deleted) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok("Task deleted successfully");
    }



    @Operation(summary = "Assign task", description = "Assign a task to a member")
    @PostMapping("/{taskId}/{memberId}")
    public ResponseEntity<String> assignTaskToMember(@PathVariable UUID taskId, @PathVariable UUID memberId){
        taskService.assignTaskToMember(taskId, memberId);

        return ResponseEntity.ok("Task assigned successfully");
    }

    @Operation(summary = "UnAssign task", description = "UnAssign task to a member")
    @DeleteMapping("/{taskId}/{memberId}")
    public ResponseEntity<String> unAssignTaskToMember(@PathVariable UUID taskId, @PathVariable UUID memberId){
        taskService.unAssignTaskToMember(taskId, memberId);
        return ResponseEntity.ok("Task unassigned successfully");
    }

}