package com.Background.Job_Scheduler.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class Job {
    private UUID jobId;

    private String inputData;

    private String result;

    private JobStatus status;

    private LocalDateTime submittedAt;

    private LocalDateTime completedAt;
}
