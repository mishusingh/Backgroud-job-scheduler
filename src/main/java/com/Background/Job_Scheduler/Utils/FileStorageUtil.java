package com.Background.Job_Scheduler.Utils;


import org.springframework.web.multipart.MultipartFile;

import java.io.File;

import java.io.FileOutputStream;

import java.io.IOException;

import java.util.UUID;



public class FileStorageUtil {



    private static final String INPUT_DIR = "uploaded-inputs";



    static {

        new File(INPUT_DIR).mkdirs();

    }



    public static void saveInputFile(UUID jobId, MultipartFile file) throws IOException {

        File targetFile = new File(INPUT_DIR + "/" + jobId + "-" + file.getOriginalFilename());

        try (FileOutputStream fos = new FileOutputStream(targetFile)) {

            fos.write(file.getBytes());

        }

    }

}