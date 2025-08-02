package org.teamory.backend.Services.Contracts;

import org.teamory.backend.Entities.Task;

import java.util.List;
import java.util.UUID;

public interface TaskInterface {

    public Task getTaskById(UUID taskId);
    public Task updateTask(Task task);
    public List<Task> getAllTasks();
    public void createTask(Task task);
    public void assignTaskToUser(String taskId, String userId);
    public void unassignTaskToUser(String taskId, String userId);
    public void deleteTask(String taskId);
}
