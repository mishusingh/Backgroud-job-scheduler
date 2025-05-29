package com.Background.Job_Scheduler.Utils;

import com.Background.Job_Scheduler.Model.Job;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;

import java.io.FileWriter;

import java.nio.file.Paths;

import java.util.UUID;



public class ResultWriter {



    private static final String RESULT_DIR = "results";



    static {

        File dir = new File(RESULT_DIR);

        if (!dir.exists()) dir.mkdirs();

    }



    public static void writeResultAsJson(Job job) {

        try {

            ObjectMapper mapper = new ObjectMapper();

            File file = new File(RESULT_DIR + "/" + job.getJobId() + ".json");

            mapper.writerWithDefaultPrettyPrinter().writeValue(file, job);

        } catch (Exception e) {

            e.printStackTrace();

        }

    }



    public static void writeResultAsText(Job job) {

        try (FileWriter writer = new FileWriter(RESULT_DIR + "/" + job.getJobId() + ".txt")) {

            writer.write("Job ID: " + job.getJobId() + "\n");

            writer.write("Input Data: " + job.getInputData() + "\n");

            writer.write("Result: " + job.getResult() + "\n");

            writer.write("Status: " + job.getStatus() + "\n");

            writer.write("Submitted At: " + job.getSubmittedAt() + "\n");

            writer.write("Completed At: " + job.getCompletedAt() + "\n");

        } catch (Exception e) {

            e.printStackTrace();

        }

    }



    public static File getResultFile(UUID jobId, String type) {

        String ext = type.equalsIgnoreCase("json") ? ".json" : ".txt";

        return Paths.get(RESULT_DIR, jobId + ext).toFile();

    }

}
