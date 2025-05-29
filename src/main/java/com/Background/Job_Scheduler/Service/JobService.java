package com.Background.Job_Scheduler.Service;

import com.Background.Job_Scheduler.Model.Job;
import com.Background.Job_Scheduler.Model.JobStatus;
import com.Background.Job_Scheduler.Processor.JobProcessor;
import com.Background.Job_Scheduler.Repository.JobRepository;
import com.Background.Job_Scheduler.Utils.FileStorageUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

import java.time.LocalDateTime;

import java.util.UUID;

import java.util.concurrent.ExecutorService;

import java.util.concurrent.Executors;



@Service
@RequiredArgsConstructor
public class JobService {



    private final JobRepository jobRepository;

    private final ExecutorService executor = Executors.newFixedThreadPool(5);



    public UUID submitJob(String inputData) {

        UUID jobId = UUID.randomUUID();

        Job job = new Job();

        job.setJobId(jobId);

        job.setInputData(inputData);

        job.setStatus(JobStatus.PENDING);

        job.setSubmittedAt(LocalDateTime.now());



        jobRepository.save(job);

        executor.submit(new JobProcessor(job, jobRepository));

        return jobId;

    }



    public UUID submitJobWithFile(MultipartFile file) throws IOException {

        UUID jobId = UUID.randomUUID();

        String content = new String(file.getBytes());

        FileStorageUtil.saveInputFile(jobId, file);



        Job job = new Job();

        job.setJobId(jobId);

        job.setInputData(content);

        job.setStatus(JobStatus.PENDING);

        job.setSubmittedAt(LocalDateTime.now());



        jobRepository.save(job);

        executor.submit(new JobProcessor(job, jobRepository));

        return jobId;

    }



    public Job getJob(UUID jobId) {

        return jobRepository.findById(jobId);

    }

}





