package com.Background.Job_Scheduler.Repository;

import com.Background.Job_Scheduler.Model.Job;
import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class JobRepository {

    private final Map<UUID, Job> jobMap = new ConcurrentHashMap<>();



    public void save(Job job) {

        jobMap.put(job.getJobId(), job);

    }



    public Job findById(UUID jobId) {

        return jobMap.get(jobId);

    }

}


