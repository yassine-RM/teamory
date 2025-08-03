package org.teamory.backend.Services.Contracts;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.teamory.backend.DTOs.Requests.Create.CreateCommentDTO;
import org.teamory.backend.DTOs.Requests.Update.UpdateCommentDTO;
import org.teamory.backend.DTOs.Responses.ActivityLogResponseDTO;
import org.teamory.backend.DTOs.Responses.CommentResponseDTO;
import org.teamory.backend.Entities.ActivityLog;

import java.util.List;
import java.util.UUID;

public interface ActivityLogInterface {

    public ActivityLogResponseDTO createActivityLog(ActivityLog activityLog);
    public Boolean clearTaskHistory(UUID taskId);
    public Boolean clearUserHistory(UUID userId);
    public List<ActivityLogResponseDTO> getTaskHistory(UUID taskId);
    public List<ActivityLogResponseDTO> getUserHistory(UUID userId);

}
