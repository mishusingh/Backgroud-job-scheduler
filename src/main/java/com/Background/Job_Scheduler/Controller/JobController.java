package com.Background.Job_Scheduler.Controller;

import com.Background.Job_Scheduler.Model.Job;
import com.Background.Job_Scheduler.Service.JobService;


import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.*;



import java.util.UUID;



@RestController

@RequestMapping("/job")

@RequiredArgsConstructor

public class JobController {



    private final JobService jobService;



    @PostMapping("/submit")

    public UUID submitJob(@RequestParam String input) {

        return jobService.submitJob(input);

    }



    @GetMapping("/{jobId}")

    public Job getJob(@PathVariable UUID jobId) {

        return jobService.getJob(jobId);

    }

}


