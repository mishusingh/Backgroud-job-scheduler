package com.Background.Job_Scheduler.Processor;

import com.Background.Job_Scheduler.Model.Job;
import com.Background.Job_Scheduler.Model.JobStatus;
import com.Background.Job_Scheduler.Repository.JobRepository;
import com.Background.Job_Scheduler.Utils.ResultWriter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;


@RequiredArgsConstructor
public class JobProcessor implements Runnable {



    private final Job job;

    private final JobRepository jobRepository;






    @Override

    public void run() {

        synchronized (job) {

            job.setStatus(JobStatus.IN_PROGRESS);

            jobRepository.save(job);



            try {

                Thread.sleep(2000); // simulate processing

                String result = job.getInputData().toUpperCase(); // simulate transformation

                job.setResult(result);

                job.setStatus(JobStatus.COMPLETED);

            } catch (Exception e) {

                job.setStatus(JobStatus.FAILED);

                job.setResult("Error: " + e.getMessage());

            }



            job.setCompletedAt(java.time.LocalDateTime.now());

            jobRepository.save(job);

            ResultWriter.writeResultAsJson(job);

            ResultWriter.writeResultAsText(job);

        }

    }

}
