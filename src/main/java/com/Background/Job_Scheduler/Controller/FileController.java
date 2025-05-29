package com.Background.Job_Scheduler.Controller;

import com.Background.Job_Scheduler.Service.JobService;
import com.Background.Job_Scheduler.Utils.ResultWriter;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

import java.util.UUID;



@RestController

@RequestMapping("/file")

@RequiredArgsConstructor

public class FileController {



    private final JobService jobService;



    @PostMapping("/upload")

    public ResponseEntity<String> upload(@RequestParam("file") MultipartFile file) {

        try {

            UUID jobId = jobService.submitJobWithFile(file);

            return ResponseEntity.ok("File uploaded and job submitted. Job ID: " + jobId);

        } catch (Exception e) {

            return ResponseEntity.status(500).body("Upload failed: " + e.getMessage());

        }

    }



    @GetMapping("/download/{jobId}")

    public ResponseEntity<FileSystemResource> download(@PathVariable UUID jobId,

                                                       @RequestParam(defaultValue = "json") String type) {

        File file = ResultWriter.getResultFile(jobId, type);

        if (!file.exists()) return ResponseEntity.notFound().build();



        HttpHeaders headers = new HttpHeaders();

        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + file.getName());



        return ResponseEntity.ok()

                .headers(headers)

                .contentLength(file.length())

                .contentType(MediaType.APPLICATION_OCTET_STREAM)

                .body(new FileSystemResource(file));

    }

}

