package com.example.upload;

import org.springframework.web.multipart.MultipartFile;


public interface BaseFileService {


    void chunkUpload(String path, MultipartFile file, int chuck, int chuckTotal);
}
